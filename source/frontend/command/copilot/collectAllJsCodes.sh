#!/bin/bash

start_directory="src"
output_file="./copilotSample/AllJsCodes.js"

echo "" > "$output_file"
find "$start_directory" -type f -name "*.js" -exec cat {} \; >> "$output_file"

echo "All js file content has been written to $output_file."