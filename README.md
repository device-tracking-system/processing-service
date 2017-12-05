# Devices Tracking System - Processing Service
The Processing Service is responsible for persisting GPS positions and aggregating 
batches of data for displaying them on a map.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/processing-service.svg?branch=master)](https://travis-ci.org/device-tracking-system/processing-service)    

## Prerequisites
You need to have the following tools installed and configured:
  - Java SE 1.8+
  - Maven 3.0+

Please note that the Scala dependency is satisfied by the Maven build tool.

## Installation and Commissioning
In order to run the processing service, follow these steps:
  1. Run the [Configuration Server](https://github.com/device-tracking-system/configuration-server).
  2. Run the [Service Discovery](https://github.com/device-tracking-system/service-discovery).
  3. Clone the latest production version of this repository from the `master` branch.
  4. Navigate to the cloned repository and install all dependencies by typing:
```
mvn install
``` 
  5. Run the built `*.jar` file passing the location of configuration files by typing:
```
java -jar target/processing-service-1.0-SNAPSHOT.jar --spring.config.location=classpath:pl/edu/agh/iet/dts/processing/
```
