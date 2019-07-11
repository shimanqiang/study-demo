#!/usr/bin/env bash

#generate memory options for JVM according to the memory size of current system
#-------------Using G1 GC
#1 G Xms=512m MaxMetaspaceSize=64m ReservedCodeCacheSize=32m InitialCodeCacheSize=32m
#2 G Xms=1g MaxMetaspaceSize=128m ReservedCodeCacheSize=64m InitialCodeCacheSize=64m
#4 G Xms=2g MaxMetaspaceSize=256m ReservedCodeCacheSize=64m InitialCodeCacheSize=64m
#8 G Xms=4g MaxMetaspaceSize=256m ReservedCodeCacheSize=128m InitialCodeCacheSize=128m
#16G Xms=12g MaxMetaspaceSize=512m ReservedCodeCacheSize=256m InitialCodeCacheSize=256m
#22-28G Xms=18g MaxMetaspaceSize=512m ReservedCodeCacheSize=256m InitialCodeCacheSize=256m
#32-44G Xms=24g MaxMetaspaceSize=1g ReservedCodeCacheSize=512m InitialCodeCacheSize=512m
#64-88G Xms=32g MaxMetaspaceSize=2g ReservedCodeCacheSize=1g InitialCodeCacheSize=1g


function init() {
    if [ -z "$LOG_PATH" ]; then
        LOG_PATH="/opt/logs/$APP_KEY"
    fi
    mkdir -p $LOG_PATH

    if [ -z "$WORK_PATH" ]; then
        WORK_PATH="/opt/xxx/apps/work"
    fi
    mkdir -p $WORK_PATH

    if [ -z "$DEBUG_PORT" ]; then
        DEBUG_PORT=44399
    fi

    JAVA_CMD="java8"
    if ! command -v $JAVA_CMD >/dev/null 2>&1; then
        JAVA_CMD="/usr/local/$JAVA_CMD/bin/java"
    fi

    JVM_ARGS="-server -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Djava.io.tmpdir=/tmp -Djava.net.preferIPv6Addresses=false"

    if [ -z "$JVM_SIZE" ]; then
        JVM_SIZE="-Xms4g -Xmx4g"
    fi
    JVM_HEAP="-XX:+AlwaysPreTouch -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/logs/"$APP_KEY"/jvm.heap.log"
    JVM_JIT="-XX:-UseBiasedLocking"
    JVM_GC="-XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime"

    #远程debug开启
    DEBUG_ARGS=" -Xdebug -Xrunjdwp:transport=dt_socket,address=8012,server=y,suspend=n "
    #远程监控
    JMXREMOTE_ARGS=" -Dcom.sun.management.jmxremote
        -Dcom.sun.management.jmxremote.port=9099
        -Dcom.sun.management.jmxremote.authenticate=false
        -Dcom.sun.management.jmxremote.ssl=false"

    if [ -z "$REMOTE_DEBUG" ]; then
        REMOTE_DEBUG=$DEBUG_ARGS $JMXREMOTE_ARGS
    fi
}

function run() {
    EXEC="exec"
    CONTEXT=/
    EXEC_JAVA="$EXEC $JAVA_CMD $JVM_ARGS $JVM_SIZE $JVM_HEAP $JVM_GC $REMOTE_DEBUG"
	EXEC_JAVA=$EXEC_JAVA" -Xloggc:$LOG_PATH/gc.log -XX:ErrorFile=$LOG_PATH/vmerr.log -XX:HeapDumpPath=$LOG_PATH"

    if [ "$UID" = "0" ]; then
        ulimit -n 1024000
        umask 000
    else
        echo $EXEC_JAVA
    fi
    cd $WORK_PATH
    pwd

    $EXEC_JAVA -jar ${MODULE}.jar 2>&1
}

# ------------------------------------
# actually work
# ------------------------------------
init
run
