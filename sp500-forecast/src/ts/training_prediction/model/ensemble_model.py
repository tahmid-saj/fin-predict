from ts.training_prediction.model.assets import *

# ML/DS libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import plotly.graph_objs as go
import tensorflow as tf
from tensorflow.keras import layers
from tensorflow import keras
import os
from datetime import datetime
from sklearn.preprocessing import minmax_scale
from tensorflow.keras.utils import plot_model

import torch
import torch.nn as nn

## Modelling checkpoint
def create_model_checkpoint(model_name, save_path="stocks_predict_model"):
    """
    Uses the model_name passed and saves model and weights to save_path using 
    datetime.now().
    """
    now = datetime.now()
    now_date_time = now.strftime('%d_%m_%Y_%H_%M_%S')
    return tf.keras.callbacks.ModelCheckpoint(filepath=os.path.join(save_path, model_name + "_" + now_date_time), 
                                              verbose=1, save_best_only=True)

class ARModel(tf.keras.Model):
    def __init__(self, shape, model_name):
        """
        Initializes Autoregressive model using below parameters.
        
        Parameters
        ----------
        shape: Tuple of (N, M) where N is the number of data points
        and M is the window size.
        model_name: Model's name.
        """
        super(ARModel, self).__init__(name=model_name)
        in_shape = shape[0]
        out_shape = shape[1]
        w_init = tf.random.normal(shape, mean=0, stddev=0.01, dtype='float32')
        self.w = tf.Variable(w_init, name='ar_w')
    
    def call(self, input_X, training=False):
        return tf.matmul(input_X, self.w) + tf.reduce_sum(self.w, axis=0)

def get_AR_model(X_shape, y_shape):
    layer_size = 30
    input_0 = tf.keras.Input(shape=X_shape[1])
    output_0 = ARModel((X_shape[1], layer_size))(input_0)
    output_1 = ARModel((layer_size, y_shape[1]))(output_0)
    AR_model = tf.keras.Model(input_0, output_1)
    
    return AR_model

# Create N-BEATS custom layer
class NBEATSBlock(tf.keras.layers.Layer):
    def __init__(self, input_size: int, theta_size: int, horizon: int, n_neurons: int, n_layers: int, **kwargs):
        """
        Initializes NEATSBlock class using below parameters.

        Parameters
        ----------
        input_size: Size of backcast (window size).
        theta_size: Number of neurons in output dense layer, backcast + forecast.
        horizon: Size of horizon, forecast prediction.
        n_neurons: Number of neurons in fully connected dense layer.
        n_layers: Number of fully connected dense layers.
        kwargs: Passed to tf.keras.layers.Layers
        """
        super().__init__(**kwargs)
        self.input_size = input_size
        self.theta_size = theta_size
        self.horizon = horizon
        self.n_neurons = n_neurons
        self.n_layers = n_layers

        # One block contains a stack of 4 fully connected dense layers, each has a ReLU activation function
        self.hidden = [tf.keras.layers.Dense(n_neurons, activation="relu") for layer in range(n_layers)]

        # Output of block is a dense layer of theta_size with linear activation
        self.theta_layer = tf.keras.layers.Dense(theta_size, activation="linear")
    
    def __call__(self, inputs):
        """
        Runs when the layer is called.
        """
        x = inputs
        for layer in self.hidden: # pass inputs through each hidden layer
            x = layer(x)
        theta = self.theta_layer(x)

        # Output the backcast and forecast from theta
        backcast, forecast = theta[:, :self.input_size], theta[:, -self.horizon:]

        return backcast, forecast

def get_NBEATS_model(model_name="NBEATS_model"):
        """
        Returns a NBEATS model using the Functional API.

        Parameters
        ----------
        model_name: Model's name.        
        """
        # TODO: Adjust this function to take into consideration if more features are used for the model, if more features are
        # used, then the NBEATS_INPUT_SIZE + 1 will need to change.
        # 1. Setup NBEATSBlock layer
        nbeats_block_layer = NBEATSBlock(input_size=NBEATS_INPUT_SIZE + 1, theta_size=NBEATS_THETA_SIZE, horizon=HORIZON_DAY,
                                         n_neurons=NBEATS_N_NEURONS, n_layers=NBEATS_N_LAYERS, name="initial_block")
        
        # 2. Create input to stacks
        stack_input = layers.Input(shape=(NBEATS_INPUT_SIZE + 1), name="stack_input")

        # 3. Create initial backcast and forecast input (backwards predictions are referred to as residuals in the paper)
        backcast, forecast = nbeats_block_layer(stack_input)
        # Add in subtraction residual connections
        residuals = layers.subtract([stack_input, backcast], name=f"subtract_00")

        # 4. Create stacks of blocks
        for i, _ in enumerate(range(NBEATS_N_STACKS - 1)):
            # First stack is already created in # 3
            # 5. Use NBEATSBlock to calculate the backcast as well as block's forecast
            backcast, block_forecast = NBEATSBlock(
                input_size=NBEATS_INPUT_SIZE + 1,
                theta_size=NBEATS_THETA_SIZE,
                horizon=HORIZON_DAY,
                n_neurons=NBEATS_N_NEURONS,
                n_layers=NBEATS_N_LAYERS,
                name=f"NBEATSBlock_{i}"
            )(residuals) # Pass in the residuals (the backcast)

            # 6. Create the double residual stacking
            residuals = layers.subtract([residuals, backcast], name=f"subtract_{i}")
            forecast = layers.add([forecast, block_forecast], name=f"add_{i}")
        
        # 7. Put the stack model together
        NBEATS_model = tf.keras.Model(inputs=stack_input, outputs=forecast, name=model_name)

        return NBEATS_model

# Implement a layer normalization to normalize across the features dimension
# Also helps with unstable gradients
class LNRNNCell(tf.keras.layers.Layer):
    def __init__(self, units, activation="tanh", **kwargs):
        """
        Initializes a Layer Normalization RNN cell using below parameters.

        Parameters
        ----------
        units: Dimensionality of the output space of the SimpleRNNCell.
        activation: Activation function to use.
        """
        super().__init__(**kwargs)
        self.state_size = units
        self.output_size = units
        self.rnn_cell = layers.SimpleRNNCell(units, activation=None)
        self.layer_norm = tf.keras.layers.LayerNormalization()
        self.activation = tf.keras.activations.get(activation)
    
    def __call__(self, inputs, states, training=True):
        """
        Runs when layer is called.
        """
        outputs, new_states = self.rnn_cell(inputs, states)
        norm_outputs = self.activation(self.layer_norm(outputs))
        
        return norm_outputs, [norm_outputs]

def get_LNRNN_model(window=WINDOW_SIZE_WEEK, horizon=HORIZON_DAY, model_name="LNRNN_model"):
    """
    Returns a LNRNN model using the LNRNNCell implementation, with the
    Sequential API.

    Parameters
    ----------
    window: Window size.
    horizon: Horizon size.
    model_name: Model's name.        
    """
    # TODO: Adjust this function to take into consideration if more features are used for the model, if more features are
    # used, then the window + 1 will need to change.
    LNRNN_model = tf.keras.Sequential([
        layers.Lambda(lambda x: tf.expand_dims(x, axis=1)),
        layers.RNN(LNRNNCell(units=horizon), return_sequences=False, input_shape=[None, window + 1]),
        layers.Dense(horizon)
    ], name=model_name)

    return LNRNN_model

class LSTMModel():
    def __init__(self, window=WINDOW_SIZE_WEEK, vol=True, horizon=HORIZON_DAY, n_lstm_neurons=128):
        """
        Initializes a LSTMModel class using below parameters.

        Parameters
        ----------
        window: Window size.
        horizon: Horizon size.
        n_lstm_neurons: Number of neurons to be used in LSTM layer.
        """
        # TODO: Adjust this function to take into consideration if more features are used for the model, if more features are
        # used, then the window + 1 will need to change.
        if vol == True:
            self.inputs_layer = layers.Input(shape=(window + 1))
        else:
            self.inputs_layer = layers.Input(shape=(window))
        self.lambda_layer = layers.Lambda(lambda x: tf.expand_dims(x, axis=1))
        self.lstm_layer = layers.LSTM(n_lstm_neurons, activation="relu")
        self.outputs_layer = layers.Dense(horizon)
    
    def get_model(self, model_name="LSTM_model"):
        """
        Returns a LSTM model using the Functional API, utilizing the 
        inputs_layer and outputs_layer.

        Parameters
        ----------
        model_name: Model's name.
        """
        inputs = self.inputs_layer
        x = self.lambda_layer(inputs)
        x = self.lstm_layer(x)
        outputs = self.outputs_layer(x)

        lstm_model = tf.keras.Model(inputs=inputs, outputs=outputs, name=model_name)

        return lstm_model

class DenseModel():
    def __init__(self, n_neurons=128, horizon=HORIZON_DAY):
        """
        Initializes a DenseModel class using below parameters.

        Parameters
        ----------
        n_neurons: Number of neurons in dense layers.
        horizon: Horizon size.
        """
        self.n_neurons = n_neurons
        self.horizon = horizon
    
    def get_model(self, model_name="dense_model"):
        """
        Returns the dense model, using the Sequential API.

        Parameters
        ----------
        model_name: Model's name
        """
        dense_model = tf.keras.Sequential([
            layers.Dense(self.n_neurons, activation="relu"),
            layers.Dense(self.horizon, activation="relu")
        ], name=model_name)

        return dense_model
    
class WaveNet():
    def __init__(self):
        self.model = keras.models.Sequential()
        self.model.add(keras.layers.InputLayer(input_shape=[None, 1]))
        
        for rate in (1, 2, 4, 8) * 2:
            # Define a spacing between the values in a kernel
            # 3x3 will be similar to 5x5 with a spacing of 2
            self.model.add(keras.layers.Conv1D(filters=20, kernel_size=2, padding="causal", activation="relu", dilation_rate=rate))
        
        self.model.add(keras.layers.Conv1D(filters=10, kernel_size=1))
    
    def get_model(self):
        return self.model

# Generate an ensemble of models using various loss functions
def get_ensemble_models(models, train_data, test_data, horizon=HORIZON_DAY, num_iter=10, num_epochs=100,
                        loss_funcs=["mae", "mse", "mape"]):
    """
    Returns a list of num_iter models each trained on MAE, MSE and MAPE loss 
    functions by default.

    For instance, if num_iter = 10, a list of 30 trained models will be returned.
    10 * len(loss_funcs) * 3 = 90
    """
    # Make empty list for trained ensemble models
    ensemble_models = []

    # Create num_iter number of models per loss function for NBeats, LSTM and Dense models
    for i in range(num_iter):
        # Build and fit ensemble of models
        for model in models:
            for loss_func in loss_funcs:
                print(f"Optimizing model by reducing: {loss_func} for epochs: {num_epochs}, num_iter: {i}, model: {model.name}")

                # Compile model with current loss function
                model.compile(loss=loss_func, optimizer=tf.keras.optimizers.Adam(learning_rate=0.01), metrics=["mae", "mse"])

                # Fit the model
                model.fit(train_data, epochs=num_epochs, verbose=2, validation_data=test_data,
                          callbacks=[#create_model_checkpoint(model_name=f"{model.name}_{i}_{loss_func}"),
                                     tf.keras.callbacks.EarlyStopping(monitor="val_loss", patience=200, restore_best_weights=True),
                                     tf.keras.callbacks.ReduceLROnPlateau(monitor="val_loss", patience=100, verbose=1)])
                
                # Append fitted model to list of ensemble models
                ensemble_models.append(model)
    
    return ensemble_models

def make_preds(model, input_data):
    """
    Uses model to make predictions on input_data.

    Parameters
    ----------
    model : trained model
    input_data : windowed input data (same kind of data model was trained on)

    Returns model predictions on input_data.
    """
    forecast = model.predict(input_data)
    return tf.squeeze(forecast) # return 1D array of predictions

def make_ensemble_preds(ensemble_models, input_data):
    """
    Returns predictions of ensemble models.

    Parameters
    ----------
    ensemble_models: Trained ensemble of models.
    input_data: Data to be predicted with.
    """
    ensemble_preds = []

    for model in ensemble_models:
        preds = model.predict(input_data) # Make predictions with current ensemble model
        ensemble_preds.append(preds)
    
    return tf.constant(tf.squeeze(ensemble_preds))

def get_ensemble_models_summary(models):
    """
    Generates model summaries of ensemble models.

    Parameters
    ----------
    models: Ensemble of models.
    """
    for model in models:
        print(model.summary())

def auto_regressive_model(window_size=WINDOW_SIZE_WEEK, lr=0.1):
    model = nn.Linear(window_size, 1)
    criterion = nn.MSELoss()
    optimizer = torch.optim.Adam(model.parameters(), lr=lr)
                                      
    return model, criterion, optimizer

class RNNModel_torch(nn.Module):
    def __init__(self, n_inputs, n_hidden, n_rnnlayers, n_outputs):
        super(RNNModel_torch, self).__init__()
        self.n_inputs = n_inputs
        self.n_hidden = n_hidden
        self.n_outputs = n_outputs
        self.n_rnnlayers = n_rnnlayers
        
        self.rnn = nn.RNN(
                input_size=self.n_inputs,
                hidden_size=self.n_hidden,
                num_layers=self.n_rnnlayers,
                nonlinearity="relu",
                batch_first=True)
        
        self.fc = nn.Linear(self.n_hidden, self.n_outputs)
        
    def forward(self, inputs):
        hidden_state = torch.zeros(self.n_rnnlayers, inputs.size(0), self.n_hidden).to(device)
        
        out, time_step_val = self.rnn(inputs, hidden_state)
        out = self.fc(out[:, -1, :])
        
        return out
    
class LSTMModel_torch(nn.Module):
    def __init__(self, n_inputs, n_hidden, n_rnnlayers, n_outputs):
        super(LSTMModel_torch, self).__init__()
        self.n_inputs = n_inputs
        self.n_hidden = n_hidden
        self.n_outputs = n_outputs
        self.n_rnnlayers = n_rnnlayers
        
        self.lstm = nn.LSTM(input_size=self.n_inputs,
                          hidden_size=self.n_hidden,
                          num_layers=self.n_rnnlayers,
                          batch_first=True)
        
        self.fc = nn.Linear(self.n_hidden, self.n_outputs)
        
    def forward(self, inputs):
        hidden_state = torch.zeros(self.n_rnnlayers, inputs.size(0), self.n_hidden).to(device)
        cell_state = torch.zeros(self.n_rnnlayers, inputs.size(0), self.n_hidden).to(device)
        
        out, time_step_val = self.lstm(inputs, (hidden_state, cell_state))
        out = self.fc(out[:, -1, :])
        
        return out

def BGD_training(model, criterion, optimizer, train_windows, train_labels, test_windows, test_labels, epochs=200):
    train_losses = np.zeros(epochs)
    test_losses = np.zeros(epochs)
    
    for epoch in range(epochs):
        optimizer.zero_grad()
        
        outputs = model(train_windows)
        loss = criterion(outputs, train_labels)
        
        loss.backward()
        optimizer.step()
        
        train_losses[epoch] = loss.item()
        
        test_outputs = model(test_windows)
        test_loss = criterion(test_outputs, test_labels)
        
        test_losses[epoch] = test_loss.item()
        
        if (epoch + 1) % 5 == 0:
            print(f"Epoch {epoch + 1} / {epochs}, test loss: {test_loss.item()}")
            
    return train_losses, test_losses

def plot_forecast_torch(model, test_windows, full_windows, full_labels, n_samples):
    validation_target = full_labels[-n_samples // 20:]
    validation_predictions = []

    day = 0

    while len(validation_predictions) < len(validation_target):
        inputs = test_windows[day].view(-1, 1)
        print(inputs)
        pred = model(inputs)[0, 0].item()
        day += 1

        validation_predictions.append(pred)
        
    return validation_target, validation_predictions