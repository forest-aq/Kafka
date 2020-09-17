#!/bin/bash

case $1 in
"start"){
    for i in hadoop102 hadoop103 hadoop104
    do
        echo "***$i**"
        ssh $i "bin/kafka-server-start.sh -daemon config/server.properties"    # 注意：这里是全路径
    done
};;

"stop"){
    for i in hadoop102 hadoop103 hadoop104
    do
        echo "***$i**"
        ssh $i "bin/kafka-server-stop.sh -daemon config/server.properties"    # 注意：这里是全路径
    done
};;

esac