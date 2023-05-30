# Intelligent Tic Tac Toe in Java

This project is an intelligent implementation of the classic game Tic Tac Toe using the principles of Reinforcement Learning.

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [Classes](#classes)
- [License](#license)

## Introduction
The main objective of this project is to train an AI model that can play the Tic Tac Toe game optimally. We use a model-free reinforcement learning approach where the AI learns the game by playing it multiple times and learning from the rewards it receives for its actions.

## Installation
The project requires the latest version of Java Development Kit (JDK). You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html).

To install the project:

1. Clone the repository to your local machine.
    ```
    git clone https://github.com/maligir/tic-tac-toe_java.git
    ```
2. Navigate to the project directory.
    ```
    cd repo-name
    ```

## Usage
1. To train the model, use the `train()` method in the `ISM2` class. This should be run before trying to play the game.
2. To play the game, use the `play()` method in the `ISM2` class.

## Classes
The project is composed of several classes:

- `Player.java`: This class represents the AI player. It contains methods to choose an action based on the current state, update its Q-table, and add a replay set.

- `TTT.java`: This class sets up the GUI for the Tic Tac Toe game and handles the user's interaction with the game.

- `DataObject.java`: This class encapsulates the information needed for a replay set in reinforcement learning. This includes the current state, the action taken, the resulting reward, the next state, and a boolean indicating whether the game has ended.

- `ISM2.java`: This class is the main driver of the project. It contains methods for training the AI player, playing the game, and checking for a win. It also includes the `main` method, which is the entry point of the application.

- `GameState.java`: This class tracks the current state of the game and checks for winning conditions.

## License
This project is licensed under the MIT License.
