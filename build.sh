#!/bin/bash
set -e
rm -rf out
mkdir -p out
javac --release 17 -d out $(find src -name "*.java")
jar cfe app.jar pe.bennu.internship.App -C out .
echo "Build OK -> app.jar"