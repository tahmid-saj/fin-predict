# ML/DS libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import plotly.graph_objs as go
import tensorflow as tf
from tensorflow.keras import layers
import os
from datetime import datetime
from sklearn.preprocessing import minmax_scale
from tensorflow.keras.utils import plot_model

# Data loading libraries
import yfinance as yf

# AWS libraries
import boto3
from botocore.client import Config

def plot_time_series(timesteps, values, format='.', start=0, end=None, label=None):
    """
    Plot timesteps (a series of points in time) against values (a series of
    values across timesteps).

    Parameters
    ----------
    timesteps: Array of timestep values.
    values: Array of values across time.
    format: Style of plot, default '.'
    start: Where to start the plot (setting a value will index from start of 
    timesteps and values)
    label: Label to show on plot about values, default is None.
    """
    # Plot the series
    plt.plot(timesteps[start:end], values[start:end], format, label)
    plt.xlabel('Time')
    plt.ylabel('S&P 500 Price')

    if label == True:
        plt.legend(fontsize=14)
    plt.grid(b=True)

def plot_volume_price_data(df_sp_500_price_volume):
    """
    Plots the stock prices and volume in the same plot, before windowing is 
    in effect.

    Parameters
    ----------
    df_sp_500_price_volume: DataFrame containing both prices and volume before
    windowing is in effect.
    """
    # Plot the volume/price over time
    # Note: Because of the different scales of our values we'll scale them to be between 0 and 1.
    df_scaled_price_volume = pd.DataFrame(minmax_scale(df_sp_500_price_volume[['Price', 'Volume']]), # we need to scale the data first
                                          columns=df_sp_500_price_volume.columns,
                                          index=df_sp_500_price_volume.index)
    df_scaled_price_volume.plot(figsize=(20, 14))