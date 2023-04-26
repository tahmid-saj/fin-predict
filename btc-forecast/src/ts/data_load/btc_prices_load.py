from ts.training_prediction.model.assets import *

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import plotly.graph_objs as go
import yfinance as yf
import boto3

import os
from datetime import datetime

DATA_LOAD_FOLDER_SAVE_PATH = "./data/data_load/train/btc_price_closing.csv"

# Live training dataset loading

# TODO: Experiment how finalized model performs with different intervals (keep in mind curse of dimensionality)
df_btc_price = yf.download(tickers='BTC-USD', period='7y', interval='1d')
print(f"Head of BTC prices: \n{df_btc_price.head()}")
print(f"BTC prices info: \n{df_btc_price.info()}")

fig = go.Figure()

fig.add_trace(go.Candlestick(x=df_btc_price.index, open=df_btc_price['Open'], high=df_btc_price['High'], low=df_btc_price['Low'], close=df_btc_price['Close'], name='market_data'))

fig.update_layout(title='Bitcoin live share price evolution', yaxis_title='Bitcoin Price (USD per Shares)', autosize=False, width=2000, height=1500)

fig.update_xaxes(rangeslider_visible=True, rangeselector=dict(
    buttons=list([
        #dict(count=15, label='15m', step='minute', stepmode='backward'),
        #dict(count=45, label='45m', step='minute', stepmode='backward'),
        #dict(count=1, label='HTD', step='hour', stepmode='todate'),
        #dict(count=1, label='1h', step='hour', stepmode='backward'),
        #dict(count=3, label='3h', step='hour', stepmode='backward'),
        #dict(count=6, label='6h', step='hour', stepmode='backward'),
        #dict(count=12, label='12h', step='hour', stepmode='backward'),
        #dict(count=24, label='24h', step='hour', stepmode='backward'),
        dict(count=7, label='7d', step='day', stepmode='backward'),
        dict(count=14, label='14d', step='day', stepmode='backward'),
        dict(count=30, label='30d', step='day', stepmode='backward'),
        dict(count=60, label='60d', step='day', stepmode='backward'),
        dict(count=90, label='90d', step='day', stepmode='backward'),
        dict(count=120, label='120d', step='day', stepmode='backward'),
        dict(count=365, label='1y', step='day', stepmode='backward'),
        dict(count=730, label='2y', step='day', stepmode='backward'),
        dict(count=1095, label='3y', step='day', stepmode='backward'),
        dict(count=1460, label='4y', step='day', stepmode='backward'),
        dict(count=1825, label='5y', step='day', stepmode='backward'),
        dict(count=2190, label='6y', step='day', stepmode='backward'),
        dict(count=2555, label='7y', step='day', stepmode='backward'),
        dict(step='all')
    ])
))

fig.show()

# Only looking for the closing price for each record
df_btc_price_closing = pd.DataFrame(df_btc_price['Close']).rename(columns={'Close': 'Price'})
print(df_btc_price_closing.head())

print(f"Saving df_btc_price_closing to {DATA_LOAD_FOLDER_SAVE_PATH}")
df_btc_price_closing.to_csv(path_or_buf=DATA_LOAD_FOLDER_SAVE_PATH)

df_btc_price_closing.plot(figsize=(20, 14))
plt.ylabel('BTC Price')
plt.title('Price of Bitcoin in the last 7 years with 1 day intervals', fontsize=16)
plt.legend(fontsize=14)

# Function to generate future dates for prediction data
def get_future_dates(start_date, into_future, offset=1):
    """
    Returns array of datetime values ranging from start_date to start_date +
    into_future (horizon).

    Parameters
    ----------
    start_date: date to start range (np.datetime64).
    into_future: number of days to add onto start date for range (int).
    offset: number of days to offset start_date by (default = 1).
    """
    start_date = start_date + np.timedelta64(offset, "D")
    end_date = start_date + np.timedelta64(into_future, "D")

    return np.arange(start_date, end_date, dtype="datetime64[D]")

