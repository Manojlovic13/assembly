#!/bin/bash

# Set the classpath
CLASSPATH="../lib/*:../lib/assembly-1.0-SNAPSHOT.jar"

# Run the JAR file
java -cp "$CLASSPATH" com.assembly.Main

# Pause to keep the terminal open after execution
# read -p "Press any key to continue..."