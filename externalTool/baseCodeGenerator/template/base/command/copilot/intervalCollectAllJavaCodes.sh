#!/bin/bash

while :
do
    start_directory="src/main/java/[[SERVICE_INFO.PACKAGE_NAME]]"
    output_file="./copilotSample/AllJavaCodes.java"

    echo "" > "$output_file"
    find "$start_directory" -type f -name "*.java" -exec cat {} \; >> "$output_file"

    echo "$(date): All java file content has been written to $output_file."

	sleep 60
done