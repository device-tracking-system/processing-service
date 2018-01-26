# Devices Tracking System - Processing Service
The Processing Service is responsible for persisting GPS positions and aggregating 
batches of data for displaying them on a map.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/processing-service.svg?branch=master)](https://travis-ci.org/device-tracking-system/processing-service)    

## Prerequisites
You need to have the following tools installed and configured:
  - Scala 2.12.4+
  - SBT 0.13.16+

## Installation and Commissioning
This service is not created as a Spring Boot one, because it is a standalone application, not even exposed to the Web. 
In order to run the processing service, follow these steps:
  1. Clone the latest production version of this repository from the `master` branch.
  2. Run the MongoDB Server by typing:
```
mongod --dbpath [path to the directory containing database files]
```
or using the official Docker image:
```
docker run -p 27017:27017 mongo
```
  3. Run the RabbitMQ Message Broker by typing:
```
rabbitmq-server
```
or using the official Docker image:
```
docker run -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
  4. Navigate to the cloned repository, then install all dependencies and run the application by typing:
```
sbt run
``` 

## Building the Docker image
In order to build a Docker image (dedicated to the production environment) from scratch:
  1. Enter the root directory of this repository.
  2. Build the Docker image by typing:
```
sbt docker:publishLocal
```
  3. In order to run the image, type:
```
docker run -t processing-service
```
Please note that this service does not expose anything to the network, therefore it is not necessary to map any ports.
If you want to override some configuration options (e.g. MongoDB or RabbitMQ host/port or logging level), pass 
appropriate environment variables (given in the configuration files placed under the `conf/include` directory) by `-e` 
flag when running the Docker image. 
