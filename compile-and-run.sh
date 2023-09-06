#!/bin/bash

javac -cp src -d bin src/main/DebutJeu.java && java -cp bin main.DebutJeu $(stty -g)
