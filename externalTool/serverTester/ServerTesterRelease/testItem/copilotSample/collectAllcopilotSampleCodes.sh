#!/bin/bash

start_directory="."
output_file="./copilotSample/AllcopilotSampleCodes.xml"

echo "" > "$output_file"
find "$start_directory" -type f -name "*.xml" -exec cat {} \; >> "$output_file"

echo "All xml setting file content has been written to $output_file."