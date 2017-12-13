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
  3. Run the RabbitMQ Message Broker by typing:
```
rabbitmq-server
```
  4. Navigate to the cloned repository, then install all dependencies and run the application by typing:
```
sbt run
``` 
