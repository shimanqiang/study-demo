#!/usr/bin/env bash
mkdir target
cd study-demo
mvn clean -U package -P $PLUS_TEMPLATE_ENV  -DskipTests=true
cp ${MODULE}/target/*.jar ../target/${MODULE}.jar