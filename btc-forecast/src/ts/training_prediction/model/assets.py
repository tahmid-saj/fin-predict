import pandas as pd

# Global variables for window and horizon size
WINDOW_SIZE_WEEK = 7
WINDOW_SIZE_MONTH = 30
WINDOW_SIZE_2_MONTH = 60

HORIZON_DAY = 1
HORIZON_WEEK = 7
HORIZON_MONTH = 30

# Timesteps into the future
INTO_FUTURE_1_DAY = 1
INTO_FUTURE_WEEK = 7
INTO_FUTURE_2_WEEK = 14
INTO_FUTURE_MONTH = 30
INTO_FUTURE_2_MONTH = 60

# Block reward values
BLOCK_REWARD_1 = 50 # After 3 January 2009 (2009-01-03) - this block reward isn't accounted for in BTC prices
BLOCK_REWARD_2 = 25 # After 28 November 2012
BLOCK_REWARD_3 = 12.5 # After 9 July 2016
BLOCK_REWARD_4 = 6.25 # After 11 May 2020

# Block reward dates (datetime form of the above date stamps for block rewards)
BLOCK_REWARD_1_DATETIME = pd.Timestamp('2009-01-03')
BLOCK_REWARD_2_DATETIME = pd.Timestamp('2012-11-28')
BLOCK_REWARD_3_DATETIME = pd.Timestamp('2016-07-09')
BLOCK_REWARD_4_DATETIME = pd.Timestamp('2020-05-11')

# Batch sizes
NBEATS_BATCH_SIZE = 1024
BATCH_SIZE = 1024

# N-BEATS hyperparameters
# Values from N-BEATS paper in Figure 1 and Table 18/Appendix D
NBEATS_N_EPOCHS = 100
NBEATS_N_NEURONS = 512
NBEATS_N_LAYERS = 4
NBEATS_N_STACKS = 30

NBEATS_INPUT_SIZE = WINDOW_SIZE_WEEK * HORIZON_DAY # Lookback period in Appendix D
NBEATS_THETA_SIZE = NBEATS_INPUT_SIZE + HORIZON_DAY

# Ensemble models training hyperparameters
ENSEMBLE_NUM_ITER = 10
ENSEMBLE_NUM_EPOCHS = 300