from ts.training_prediction.model.assets import *
from ts.training_prediction.evaluation import *
from ts.training_prediction.model.ensemble_model import *
from ts.data_load.btc_prices_load import *
from ts.etl.visualization import *

# ML/DS libraries
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import tensorflow as tf
import os
from datetime import datetime
from tensorflow.keras.utils import plot_model

def get_upper_lower_confidence(preds):
    """
    Returns the upper and lower bounds of the 95% confidence level using the
    following logic:

    95% confidence upper, lower bounds = mean of preds +/- (standard deviation
    of preds * 1.96)

    Parameters
    ----------
    preds: Predictions of ensemble of models.
    """
    # 1. Take the predictions of multiple randomly initialized deep learning neural networks using the preds parameter
    # 2. Measure the standard deviation of the predictions
    std = tf.math.reduce_std(preds, axis=0)

    # 3. Multiply the standard deviation by 1.96
    interval = 1.96 * std

    # 4. Get the prediction interval's upper and lower bounds
    preds_mean = tf.reduce_mean(preds, axis=0)
    lower, upper = preds_mean - interval, preds_mean + interval

    return lower, upper

# Plot the median of ensemble preds with the prediction's confidence intervals
def plot_confidence_interval(X_test, y_test, y_pred, lower, upper, test_label="Test data", pred_label="Pred", pred_interval_label="Pred intervals", offset=500):
    """
    Plots the 95% confidence intervals along the predictions.

    Parameters
    ----------
    X_test: Test dataframe containing datetime indexes and BTC prices.
    y_test: Test dataframe containing actual BTC prices.
    y_pred: Generated ensemble model preds.
    """
    plt.figure(figsize=(20, 14))
    plt.plot(X_test.index[offset:], y_test[offset:], "g", label=test_label)
    plt.plot(X_test.index[offset:], y_pred[offset:], "k--", label=pred_label)
    plt.xlabel("Date")
    plt.ylabel("BTC Price")
    plt.fill_between(X_test.index[offset:], (lower)[offset:], (upper)[offset:], label=pred_interval_label)
    plt.legend(loc="upper left", fontsize=14)

# Function to make predictions into future (can include retraining the model with the predicted data appended, everytime the model makes a prediction)
# 1. Create function to make predictions into the future
def make_future_forecast(models, values, into_future, window_size=WINDOW_SIZE_WEEK) -> list:
    """
    Makes future forecasts into_future steps after values ends.

    Returns future forecasts as list of floats.

    Parameters
    ----------
    values: BTC price labels after windowing is in effect.
    models: List of ensemble models to predict forecast using.
    into_future: How many days into future to make forecast for.
    window_size: Window size.
    """
    # 2. Make an empty list for future forecasts/prepare data to forecast on
    future_forecast = []
    last_window = values[-window_size:] # Only want preds from the last window (this will get updated after every prediction)

    # 3. Make into_future number of predictions, altering the data which gets predicted on each time
    for day in range(into_future):
        # Predict on last window then append it again and again (model will start to make forecasts on its own forecasts)
        ensemble_preds = make_ensemble_preds(ensemble_models=models, input_data=tf.expand_dims(last_window, axis=0))
        future_pred = np.median(ensemble_preds, axis=0)
        print(f"Predicting on: \n {last_window} -> Prediction: {tf.squeeze(future_pred).numpy()}\n")

        # Append predictions on future_forecast
        future_forecast.append(tf.squeeze(future_pred).numpy())

        # Update last window with the new pred and get window_size of most recent preds (model was trained on window_size windows)
        last_window = np.append(last_window, future_pred)[-window_size:]
    
    return future_forecast

def plot_future_forecast(df_btc_price, future_forecast, into_future=INTO_FUTURE_2_WEEK, start=2300):
    """
    Plots future forecast using passed dataframe containing prices and  future
    forecast.

    Parameters
    ----------
    df_btc_price: DataFrame containing datetimes and prices.
    future_forecast: List containing future forecast for a into_future horizon.
    into_future: How many days to make/plot future forecasts on.
    """
    # Last timestep of timesteps (np.datetime64 format)
    last_timestep = df_btc_price.index[-1]

    # Get next two weeks of timesteps
    next_time_steps = get_future_dates(start_date=last_timestep, into_future=into_future)

    # Insert last timestep/finel price so the graph doesn't look messed
    next_time_steps = np.insert(next_time_steps, 0, last_timestep)
    future_forecast = np.insert(future_forecast, 0, df_btc_price.iloc[-1])

    # Plot future price predictions of bitcoin
    plt.figure(figsize=(20, 14))
    plot_time_series(df_btc_price.index, df_btc_price, start=start, format='-', label='Actual BTC Price')
    plot_time_series(next_time_steps, future_forecast, format='-', label='Predicted BTC Price')