# Patient Portal data layer and REST API server

This repo contains a data layer (shared library) and a REST API server (executable) for the https://github.com/vaadin/patient-portal-demo application.

Branches:
 - `master`: Spring Boot 2.2 (required for Vaadin 14)
 - `spring-boot-1.4`: Spring Boot 1.4 (required for Vaadin 8)

## Requirements
- Java 8+
- Maven

## Building
 - `git checkout master && mvn install` (builds `patient-portal-backend:2.0-SNAPSHOT` for the Vaadin 14 version of Patient Portal)
 - `git checkout spring-boot-1.4 && mvn install` (builds `patient-portal-backend:0.0.1-SNAPSHOT` for the Vaadin 8 version of Patient Portal)

## Running locally
 - `git checkout master && mvn install`
 - `cd rest-and-angular-ui-specific-services`
 - `mvn spring-boot:run`

The API server would be running with its own in-memory database on http://localhost:8080.
To generate random data from randomuser.me, add `db.random.data=true` into the `applicaiton.properties` file, or define the `DB_RANDOM_DATA=true` environment variable. By default it generates dull but reproducible static data.

It's also possible to run the API server with the external database used by other Patient Portal implementations. See the comments in the `applicaiton.properties` file for details.

How to test:
1. Login to get an auth token for the API
    ```
    curl -v http://localhost:8080/login \
        -H "Content-Type: application/json" \
        -d '{"username": "user", "password": "password"}'
    ```
2. Call an API endpoint with the token from the login response, e.g.
    ```
    curl http://localhost:8080/analytics/age -H "Authorization: Bearer [token]"
    ```
