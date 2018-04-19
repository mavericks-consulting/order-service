# Order Service

## Pre-requisites
* Java >=1.8
* PostgreSQL >=10.3

## Build

To build the application, run `./gradlew build`

## Test

To run tests, run `./gradlew test`

## Run

Required evironment variables:

SERVER_PORT - port the appliation will run on

POSTGRES_HOST - host postgreSQL server is running on

POSTGRES_PORT - port postgreSQL server is running on

POSTGRES_USER - user to connect to postgres

POSTGRES_PASSWORD - password for user to connect to postgres

PRODUCT_SERVICE_HOST - host running ProductService

PRODUCT_SERVICE_PORT - port running ProductService


Before booting the app, create database using `createdb order-service`.

Run the app using `./gradlew bootRun`

Verify the app is running using `curl -i localhost:<SERVER_PORT>/health` from a terminal (replace <SERVER_PORT> with the port
the app is running on)
or
navigating to `localhost:<SERVER_PORT>/health` from a web browser.

If the response is "OK", the app is succesfully running.

## Tasks

To see list of available tasks, run `./gradlew tasks`
