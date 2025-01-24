# Apples2Apples

This is an event-driven textbased implimentation of the board game **Apples to Apples.** 

## Overview
This version of **Apples to Apples** is completly textbased and is played using a terminal.
It can be played as single player versus bots and/or versus other people on the local network.

## How to play
The game works exactly like the real board game **Apples to Apples**. More information can be found in the docs folder.

## How to run with Maven
The game has been created using Maven. It can be run from an IDE by typing "mvn compile exec:java@run-apples2apples" in a terminal. This will start the gameserver. To connect with a client first open a new terminal and then type "mvn exec:java@run-client".

## How to run without Maven
To start the gameserver, type in a terminal "java -jar target/server-app.jar". To start the client, first open a new terminal window and type "java -jar target/client-app.jar".