#!/bin/bash

while :
do
    start_directory="src"
    output_file="./copilotSample/AllJsCodes.js"

    echo "" > "$output_file"
    find "$start_directory" -type f -name "*.js" -exec cat {} \; >> "$output_file"

    echo "$(date): All js file content has been written to $output_file."

	sleep 60
done