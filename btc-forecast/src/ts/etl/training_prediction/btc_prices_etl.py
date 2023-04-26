from ts.training_prediction.model.assets import *

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

import os
from datetime import datetime

import tensorflow as tf
from tensorflow.keras import layers

# Function to include block reward to training and testing dataset before windowing is in effect
def include_block_reward(df_btc_price):
    """
    Includes block reward to an existing dataframe containing BTC prices with
    datetime indexes. Should be used before windowing of dataset is in effect.

    Parameters
    ----------
    df_btc_price: DataFrame containing BTC prices with datetime indexes.
    """
    # TODO: Need to change this function when block reward changes in the future and also need to better manage block reward for the future

    block_reward_1_days = (BLOCK_REWARD_2_DATETIME - df_btc_price.index[0]).days
    block_reward_2_days = (BLOCK_REWARD_3_DATETIME - df_btc_price.index[0]).days
    block_reward_3_days = (BLOCK_REWARD_4_DATETIME - df_btc_price.index[0]).days
    block_reward_4_days = (df_btc_price.index[-1] - BLOCK_REWARD_4_DATETIME).days

    print(f"Days from block reward 1: {block_reward_1_days}\n", 
          f"Days from block reward 2: {block_reward_2_days}\n", 
          f"Days from block reward 3: {block_reward_3_days}\n", 
          f"Days from block reward 4: {block_reward_4_days}\n")

    # Adding the block_reward column
    df_btc_price_block_reward = df_btc_price.copy()
    df_btc_price_block_reward["block_reward"] = None

    # Set values of block_reward column (it's the last column here hence -1 indexing using iloc)
    df_btc_price_block_reward.iloc[:block_reward_2_days, -1] = BLOCK_REWARD_2
    df_btc_price_block_reward.iloc[block_reward_2_days:block_reward_3_days, -1] = BLOCK_REWARD_3
    df_btc_price_block_reward.iloc[block_reward_3_days:, -1] = BLOCK_REWARD_4

    print("Head of dataframe with block reward included: \n", df_btc_price_block_reward.head())
    print("Tail of dataframe with block reward included: \n", df_btc_price_block_reward.tail())

    return df_btc_price_block_reward

def get_labelled_windows(x, horizon=HORIZON_DAY):
    """
    Create labels for windowed dataset.

    E.g. if horizon is 1, then:
    x: [0, 1, 2, 3, 4, 5, 6, 7] -> output: ([0, 1, 2, 3, 4, 5, 6], [7])
    """
    return x[:, :-horizon], x[:, -horizon:]

# View numpy arrays as windows
def make_windows(x, window_size=WINDOW_SIZE_WEEK, horizon=HORIZON_DAY):
    """
    Turns a 1D array into a 2D array of sequential labelled windows of 
    window_size with horizon size labels.

    Returns both a 2D array containing full windowed X values with shape
    (number of samples, window size), and a 2D array containing full
    labelled y values with shape (number of samples, horizon size).
    """
    # TODO: In the future, function could be implemented using https://www.tensorflow.org/api_docs/python/tf/keras/preprocessing/timeseries_dataset_from_array

    # 1. Create a window of specific window_size (add the horizon on the end for labelling later)
    window_step = np.expand_dims(np.arange(window_size + horizon), axis=0)

    # 2. Create a 2D of multiple window steps (minus 1 to account for 0 indexing)
    window_indexes = window_step + np.expand_dims(np.arange(len(x) - (window_size + horizon - 1)), axis=0).T # Create 2D array of windows of window size
    # print(f"Window indexes: \n {window_indexes, window_indexes.shape}")

    windowed_array = x[window_indexes]
    # print(windowed_array)

    # 4. Get the labelled window
    windows, labels = get_labelled_windows(windowed_array, horizon=horizon)

    return windows, labels

# Windowing function for block reward (multivariate dataset)
def make_windows_labels_multivariate(df_btc_price_block_reward, window=WINDOW_SIZE_WEEK):
    """
    Returns windows and labels using a dataframe containing both BTC prices and
    block reward.

    Parameters
    ----------
    df_btc_price_block_reward: DataFrame containing BTC prices and block reward.
    window: Window size.
    """
    # Make a copy of the BTC historical data with block reward feature
    df_btc_price_windowed = df_btc_price_block_reward.copy()

    # Add windowed columns
    for i in range(window):
        df_btc_price_windowed[f"Price{i + 1}"] = df_btc_price_windowed["Price"].shift(periods=i + 1)
    
    print("Head of windowed dataframe: \n", df_btc_price_windowed.head(10))

    # Create X & y, remove the NaNs and convert to float32 to prevent tensorflow errors
    X = df_btc_price_windowed.dropna().drop('Price', axis=1).astype(np.float32)
    y = df_btc_price_windowed.dropna()['Price'].astype(np.float32)

    print("Head of X: \n", X.head())
    print("Head of y: \n", y.head())
    print(f"Shape of X: {X.shape} \n", f"Shape of y: {y.shape}")

    return X, y

# Implementation of train/test split after windowing is in effect
def make_train_test_splits(windows, labels, test_split=0.2):
    """
    Splits matching pairs of windows and labels into train and test splits.
    """
    split_size = int(len(windows) * (1 - test_split)) # This will default to 80% train and 20% test
    train_windows = windows[:split_size]
    train_labels = labels[:split_size]
    test_windows = windows[split_size:]
    test_labels = labels[split_size:]

    print(train_windows.shape, test_windows.shape, train_labels.shape, test_labels.shape)

    return train_windows, test_windows, train_labels, test_labels

# Function to generate train and test datasets using the tf.data API
def gen_train_test_datasets(X_train, y_train, X_test, y_test, batch_size=BATCH_SIZE):
    """
    Returns the train and test datasets using the tf.data API from train and
    test arrays.

    Parameters
    ----------
    X_train: Training windows.
    y_train: Training labels.
    X_test: Testing windows.
    y_test: Testing labels.
    batch_size: Batch size used for training.
    """
    # 1. Turn train and test arrays into tensor Datasets
    train_features_dataset = tf.data.Dataset.from_tensor_slices(X_train)
    train_labels_dataset = tf.data.Dataset.from_tensor_slices(y_train)

    test_features_dataset = tf.data.Dataset.from_tensor_slices(X_test)
    test_labels_dataset = tf.data.Dataset.from_tensor_slices(y_test)

    # 2. Combine features & labels
    train_dataset = tf.data.Dataset.zip((train_features_dataset, train_labels_dataset))
    test_dataset = tf.data.Dataset.zip((test_features_dataset, test_labels_dataset))

    # 3. Batch and prefetch for optimal performance
    train_dataset = train_dataset.batch(batch_size=batch_size).prefetch(tf.data.AUTOTUNE)
    test_dataset = test_dataset.batch(batch_size=batch_size).prefetch(tf.data.AUTOTUNE)

    return train_dataset, test_dataset

def save_train_test_data(train_windowed, train_labels, test_windowed, test_labels, features, save_path=None):
    """
    Saves the train and test datasets to save_path.
    """
    now = datetime.now()
    now_date_time = now.strftime('%d_%m_%Y_%H_%M_%S')

    df_train_windows = pd.DataFrame(train_windowed, columns=features)
    df_train_labels = pd.DataFrame(train_labels)
    df_test_windows = pd.DataFrame(test_windowed, columns=features)
    df_test_labels = pd.DataFrame(test_labels)

    if save_path == None:
        df_train_windows.to_csv(path_or_buf=os.path.join("train_windowed" + "_" + now_date_time + ".csv"))
        df_train_labels.to_csv(path_or_buf=os.path.join("train_labels" + "_" + now_date_time + ".csv"))
        df_test_windows.to_csv(path_or_buf=os.path.join("test_windowed" + "_" + now_date_time + ".csv"))
        df_test_labels.to_csv(path_or_buf=os.path.join("test_labels" + "_" + now_date_time + ".csv"))
    else:
        df_train_windows.to_csv(path_or_buf=os.path.join(save_path, "train_windowed" + "_" + now_date_time + ".csv"))
        df_train_labels.to_csv(path_or_buf=os.path.join(save_path, "train_labels" + "_" + now_date_time + ".csv"))
        df_test_windows.to_csv(path_or_buf=os.path.join(save_path, "test_windowed" + "_" + now_date_time + ".csv"))
        df_test_labels.to_csv(path_or_buf=os.path.join(save_path, "test_labels" + "_" + now_date_time + ".csv"))