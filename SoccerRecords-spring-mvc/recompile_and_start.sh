#!/bin/bash

cd ..
mvn clean install
cd SoccerRecords-spring-mvc
mvn tomcat7:run
