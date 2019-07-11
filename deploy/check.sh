#!/bin/sh
echo "health check start......"
if [ -z $CHECK_PORT ]; then
    CHECK_PORT=8080
fi

echo "lsof -i :$CHECK_PORT | awk 'NR > 1 {print $2}' | wc -l"

pro_num=`lsof -i :$CHECK_PORT | awk 'NR > 1 {print $2}' | wc -l`
echo "pro_num: $pro_num"

if [ $pro_num == 0  ]
then
    echo "$CHECK_PORT is not available..."
    exit 1
else
    echo "$CHECK_PORT is available!"
    exit 0
fi