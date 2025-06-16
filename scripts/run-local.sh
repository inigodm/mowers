#!/bin/zsh
cd "$(dirname "$0")/.." || exit 1
./gradlew bootRun --args="$(cat ./scripts/input)" --warning-mode none --quiet
