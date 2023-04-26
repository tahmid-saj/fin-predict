from ts.training_prediction.model.assets import *

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

from sklearn.preprocessing import minmax_scale

import os
from datetime import datetime

def plot_time_series(timesteps, values, format='.', start=0, end=None, label=None):
    """
    Plot timesteps (a series of points in time) against values (a series of
    values across timesteps).

    Parameters
    ----------
    timesteps: Array of timestep values
    values: Array of values across time
    format: Style of plot, default '.'
    start: Where to start the plot (setting a value will index from start of
    timesteps and values)
    end: Where to end the plot (similar to start but for the end)
    label: Label to show on plot about values, default is None
    """
    # Plot the series
    plt.plot(timesteps[start:end], values[start:end], format, label=label)
    plt.xlabel('Time')
    plt.ylabel('BTC Price')
    
    if label == True:
        plt.legend(fontsize=14)
    plt.grid(b=True)

def plot_block_reward_price_data(df_btc_price_block_reward):
    """
    Plots the BTC prices and block reward in the same plot, before windowing is
    in effect.

    Parameters
    ----------
    df_btc_price_block_reward: DataFrame containing both prices and block reward
    before windowing is in effect.
    """
    # Plot the block reward/price over time
    # Note: Because of the different scales of our values we'll scale them to be between 0 and 1.
    df_scaled_price_block = pd.DataFrame(minmax_scale(df_btc_price_block_reward[["Price", "block_reward"]]), # we need to scale the data first
                                        columns=df_btc_price_block_reward.columns,
                                        index=df_btc_price_block_reward.index)
    df_scaled_price_block.plot(figsize=(20, 14))