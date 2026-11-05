#!/bin/bash

CONFIG_PATH="src/main/resources/application-dev.yml"
OUTPUT_ZIP="application-dev-backup.zip"
REMOTE_NAME="mycloud" # Name from 'rclone config'
REMOTE_FOLDER="Backups"

if [ ! -f "$CONFIG_PATH" ]; then
    echo "Error: $CONFIG_PATH not found!"
    exit 1
fi

echo "=== Encrypting Config ==="
zip -e "$OUTPUT_ZIP" "$CONFIG_PATH"

if [ $? -eq 0 ]; then
    echo "=== Uploading to Cloud ($REMOTE_NAME:$REMOTE_FOLDER) ==="
    rclone copy "$OUTPUT_ZIP" "$REMOTE_NAME:$REMOTE_FOLDER"
    
    echo "=== Upload Complete ==="
    rm "$OUTPUT_ZIP" # Optional: remove local zip after upload
else
    echo "Encryption failed. Aborting upload."
fi