#!/bin/bash

REMOTE_NAME="mycloud"         # Must match the name you used in 'rclone config'
REMOTE_FOLDER="Backups"       # Folder in your cloud storage
OUTPUT_ZIP="application-dev-backup.zip"
TARGET_DIR="src/main/resources"

echo "=== Downloading backup from cloud ($REMOTE_NAME:$REMOTE_FOLDER) ==="
rclone copy "$REMOTE_NAME:$REMOTE_FOLDER/$OUTPUT_ZIP" .

# Check if download succeeded
if [ ! -f "$OUTPUT_ZIP" ]; then
    echo "Error: Backup zip file could not be found in the cloud or download failed."
    exit 1
fi

echo "=== Extracting configuration file ==="
# unzip will interactively prompt you for your password
unzip -o "$OUTPUT_ZIP" -d .

if [ $? -eq 0 ]; then
    echo "Success! Configuration restored."
    rm "$OUTPUT_ZIP" # Clean up the downloaded zip archive
else
    echo "Extraction failed. Please verify your password."
    exit 1
fi