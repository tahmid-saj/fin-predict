from ts.training_prediction.model.assets import *
from ts.etl.training_prediction import *
from ts.etl.visualization import *
from ts.training_prediction.model.ensemble_model import *
from ts.training_prediction.evaluation import *
from ts.training_prediction.visualization import *
from ts.training_prediction.visualization.forecast_visualization import *
from ts.training_prediction.evaluation.evaluation_metrics import *
from ts.etl.training_prediction.sp_prices_etl import *
from ts.etl.visualization.time_series_visualization import *

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

# Data loading libraries
import yfinance as yf

df_sp_500_price = pd.read_csv("./data/data_load/train/sp_500_price_closing.csv")

# Including volume to the full stock closing price dataset
df_sp_500_price_volume = include_price_volume(df_sp_500_price=df_sp_500_price)

print(f"Head of S&P 500 price and volume dataset: \n {df_sp_500_price_volume.head()}")
print(f"Tail of S&P 500 price and volume dataset: \n {df_sp_500_price_volume.tail()}")
# Plotting the S&P 500 price and volume over time
plot_volume_price_data(df_sp_500_price_volume=df_sp_500_price_volume)

# Obtain the full windows and labels with volume included
dataset_full_windows_vol, dataset_full_labels_vol = make_windows_labels_multivariate(df_sp_500_price_volume)

# Obtain the train/test sets of the full windows and labels with block reward included
train_windows, test_windows, train_labels, test_labels = make_train_test_splits(windows=dataset_full_windows_vol, labels=dataset_full_labels_vol)

# Obtain the train and test datasets
train_dataset, test_dataset = gen_train_test_datasets(X_train=train_windows, y_train=train_labels,
                                                      X_test=test_windows, y_test=test_labels)
print(f"Train dataset: {train_dataset}", f"Test dataset: {test_dataset}")

# Create AR model
AR_model = get_AR_model(len(train_windows), WINDOW_SIZE_WEEK + 1)

# Create NBEATS model
NBEATS_model = get_NBEATS_model()

# Create LNRNN model
LNRNN_model = get_LNRNN_model()

# Create LSTM model
LSTM_model_obj = LSTMModel()
LSTM_model = LSTM_model_obj.get_model()

# Create Dense model
dense_model_obj = DenseModel()
dense_model = dense_model_obj.get_model()

#train_models = [LSTM_model]
train_models = [NBEATS_model, LNRNN_model, LSTM_model, dense_model]
# train_models = [LNRNN_model]

# Obtain list of trained ensemble models
ensemble_models = get_ensemble_models(models=train_models, num_iter=ENSEMBLE_NUM_ITER, num_epochs=ENSEMBLE_NUM_EPOCHS)

# Generate model summaries
get_ensemble_models_summary(models=train_models)

# Plot the ensemble models
plot_model(AR_model)

plot_model(NBEATS_model)

plot_model(LNRNN_model)

plot_model(LSTM_model)

plot_model(dense_model)

# Generate ensemble predictions
ensemble_preds = make_ensemble_preds(ensemble_models=ensemble_models, input_data=test_dataset)

# Evaluate ensemble model predictions
ensemble_results = evaluate_preds(y_true=test_labels, y_pred=np.median(ensemble_preds, axis=0))
print(ensemble_results)

# Obtain the upper and lower bounds of the 95% confidence levels
lower, upper = get_upper_lower_confidence(preds=ensemble_preds)

# Get the median values of the ensemble preds
ensemble_median = np.median(ensemble_preds, axis=0)

# Plot the confidence interval
plot_confidence_interval(test_windows, test_labels, ensemble_median, lower=lower, upper=upper, offset=0)

# Make forecasts into future of the price of S&P 500 stocks
future_forecast = make_future_forecast(models=ensemble_models, values=dataset_full_labels_vol, 
                                       into_future=INTO_FUTURE_2_WEEK, window_size=WINDOW_SIZE_WEEK + 1)

plot_future_forecast(df_stock_price=df_sp_500_price, future_forecast=future_forecast, start=0)