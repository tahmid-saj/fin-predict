from ts.training_prediction.model.assets import *
from ts.etl.training_prediction import *
from ts.etl.visualization import *

# ML/DS libraries
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import plotly.graph_objs as go
import tensorflow as tf
from tensorflow.keras import layers
import os
from datetime import datetime
from sklearn.preprocessing import minmax_scale
from tensorflow.keras.utils import plot_model

# AWS libraries
import boto3
# Data loading libraries
import yfinance as yf

df_btc_price_closing = pd.read_csv("./data/data_load/train/btc_price_closing.csv")

# Including block reward to the full BTC closing price dataset
df_btc_price_block_reward = include_block_reward(df_btc_price=df_btc_price_closing)

# Plotting the BTC price and block reward over time
plot_block_reward_price_data(df_btc_price_block_reward=df_btc_price_block_reward)

# Obtain the full windows and labels with block reward included
dataset_full_windows_br, dataset_full_labels_br = make_windows_labels_multivariate(df_btc_price_block_reward)

# Obtain the train/test sets of the full windows and labels with block reward included
train_windows, test_windows, train_labels, test_labels = make_train_test_splits(windows=dataset_full_windows_br, labels=dataset_full_labels_br)

# Obtain the train and test datasets
train_dataset, test_dataset = gen_train_test_datasets(X_train=train_windows, y_train=train_labels,
                                                      X_test=test_windows, y_test=test_labels)
print(f"Train dataset: {train_dataset}", f"Test dataset: {test_dataset}")