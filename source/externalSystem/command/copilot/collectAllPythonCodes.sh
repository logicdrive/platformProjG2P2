#!/bin/bash

start_directory="app"
output_file="./copilotSample/AllPythonCodes.py"

echo "" > "$output_file"
find "$start_directory" -type f -name "*.py" -exec cat {} \; >> "$output_file"

echo "All python file content has been written to $output_file."