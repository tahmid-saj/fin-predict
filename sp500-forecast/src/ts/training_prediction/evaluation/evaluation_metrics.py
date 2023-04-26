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

def mean_absolute_scaled_error(y_true, y_pred):
    """
    Implement MASE (assuming to seasonality of data). MASE measures the 
    forecast error compared to the error of a naive forecast. When he have a 
    MASE = 1, that means the model is exactly as good as just picking the last 
    observation. An MASE = 0.5, means that our model has doubled the prediction 
    accuracy.
    """
    mae = tf.reduce_mean(tf.abs(y_true - y_pred))

    # Find MAE of naive forecast (no seasonality)
    mae_naive_no_season = tf.reduce_mean(tf.abs(y_true[1:] - y_true[:-1])) # the seasonality is 1 day (hence the shifting of 1 day)

    return mae / mae_naive_no_season

# Updated evaluate preds for large horizons (greater than 1 day)
def evaluate_preds(y_true, y_pred):
    """
    Evaluate mae, mse, rmse, mape, mase metrics for predictions. Updated
    for large horizons as well (greater than 1 day)
    """
    # Make sure float32 (for metric calculations)
    y_true = tf.cast(y_true, dtype=tf.float32)
    y_pred = tf.cast(y_pred, dtype=tf.float32)

    # Calculate various metrics
    mae = tf.keras.metrics.mean_absolute_error(y_true, y_pred)
    mse = tf.keras.metrics.mean_squared_error(y_true, y_pred) # MSE gives emphasis on outliers, as they're squared
    rmse = tf.sqrt(mse)
    mape = tf.keras.metrics.mean_absolute_percentage_error(y_true, y_pred)
    mase = mean_absolute_scaled_error(y_true, y_pred)

    # Account for different sized horizons, if the horizon is greater than 1 day, reduce it to a single number using the mean
    if mae.ndim > 0:
        mae = tf.reduce_mean(mae)
        mse = tf.reduce_mean(mse)
        rmse = tf.reduce_mean(rmse)
        mape = tf.reduce_mean(mape)
        mase = tf.reduce_mean(mase)
    
    return {"mae": mae.numpy(),
            "mse": mse.numpy(),
            "rmse": rmse.numpy(),
            "mape": mape.numpy(),
            "mase": mase.numpy()}

# Create a function to take in model predictions and truth values and return evaluation metrics
def evaluate_preds_1_day_horizon(y_true, y_pred):
    """
    Evaluate mae, mse, rmse, mape, mase metrics for predictions.
    """
    # Make sure float32 (for metric calculations)
    y_true = tf.cast(y_true, dtype=tf.float32)
    y_true = tf.cast(y_pred, dtype=tf.float32)

    # Calculate various metrics
    mae = tf.keras.metrics.mean_absolute_error(y_true, y_pred)
    mse = tf.keras.metrics.mean_squared_error(y_true, y_pred)
    rmse = tf.sqrt(mse)
    mape = tf.keras.metrics.mean_absolute_percentage_error(y_true, y_pred)
    mase = mean_absolute_scaled_error(y_true, y_pred)

    return {"mae": mae.numpy(),
            "mse": mse.numpy(),
            "rmse": rmse.numpy(),
            "mape": mape.numpy(),
            "mase": mase.numpy()}

# Comparing the performance of models using different metrics
def compare_model_metrics(model_results: list, model_names: list, metrics=['mae', 'mse', 'rmse', 'mape', 'mase']):
    """
    Plots comparison of metrics for models.

    Parameters
    ----------
    models : List of dictionaries containing model metrics.
    i.e. model_results=[model_1_results, model_2_results]. Where
    model_1_results and model_2_results are dictionaries of metrics.

    model_names : List of model names.

    metrics : Metrics for comparison. Default is ['mae', 'mse', 'rmse', 'mape', 
    'mase'].
    """
    for metric in metrics:
        pd.DataFrame({model_name: model_result[metric] for (model_name, model_result) in zip(model_names, model_results)},
                     index=[metric]).plot(figsize=(10, 7), kind='bar')