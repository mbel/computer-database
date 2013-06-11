#!/bin/bash

echo Lancement de l\'application web computer-database :
mvn clean install
cd computer-database-webapp/
mvn jetty:run
