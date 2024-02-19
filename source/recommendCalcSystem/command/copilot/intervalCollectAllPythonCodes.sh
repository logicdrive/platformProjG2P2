#!/bin/bash

while :
do

    start_directory="app"
    output_file="./copilotSample/AllPythonCodes.py"

    echo "" > "$output_file"
    find "$start_directory" -type f -name "*.py" -exec cat {} \; >> "$output_file"

    echo "$(date): All python file content has been written to $output_file."

	sleep 60

done