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

# Take the full dataset's timesteps and closing prices
dataset_timesteps = df_btc_price_closing.index.to_numpy()
dataset_prices = df_btc_price_closing['Price'].to_numpy()

# Testing out the windowing function for multiple records
dataset_full_windows, dataset_full_labels = make_windows(dataset_prices, window_size=WINDOW_SIZE_WEEK, horizon=HORIZON_DAY)
print(dataset_full_windows.shape, dataset_full_labels.shape)

print(pd.DataFrame(dataset_prices))
print(pd.DataFrame(dataset_full_windows))
print(pd.DataFrame(dataset_full_labels))

# Testing out the train/test split function
train_windows, test_windows, train_labels, test_labels = make_train_test_splits(dataset_full_windows, dataset_full_labels)
print(train_windows.shape, test_windows.shape, train_labels.shape, test_labels.shape)