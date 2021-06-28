#!/bin/bash

## copy env specific properties file mounted by kubernetes
cp /appdir/application*.properties /app/BOOT-INF/classes/

echo $JAVA_OPTIONS

## start application server
java -cp /app:/app/BOOT-INF/classes:/app/lib/* $JAVA_OPTIONS com/odc/gid/FbAdIdApplication

