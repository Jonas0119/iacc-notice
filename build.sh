#!/bin/bash
# 便捷构建脚本 - 调用scripts/build.sh
cd "$(dirname "$0")"
./scripts/build.sh "$@"
