
# waes-assignment

## Overview

This project aims to compare two base64 encoded binary data via http endpoints.

## Getting Started

To run this application, 

**1. Run as Docker container**

you'll need [Docker](https://docs.docker.com/get-docker/) installed in your computer. From your command line in project directory:

```
make docker
```
Via above command, docker runs your application on 8080 host-port by re-packaged code

```
make remove
```

will stop the containers specified in docker-compose and remove image which has "waes" prefix

**2. Run with maven**

you'll need jdk 11 installed in your computer. From your command line in project directory:

```
./mvnw clean spring-boot:run
```

## Usage
Interacting with the application must be with The API which is  RESTFUL and returns results in JSON.

### Resource components and identifiers
Resource components can be used in conjunction with identifiers to do CRUD operations for that identifier on repositories.

| resource           | method        | description  |
| -------------      |:-------------:|:-------------|
| /v1/diff/:id/left  | POST          | create/update the left side of given comparison, by id |
| /v1/diff/:id/right | POST          | create/update the right side of given comparison, by id |
| /v1/diff/:id       | GET           | returns result of comparison |

#### Update left side in comparison sample request
By below http request command, the application will update left side of the given comparison(with 123 id). If comparison not available, it will
be create with given left side.

```
curl -X POST \
  http://host:8080/v1/diff/123/left \
  -H 'content-type: application/json' \
  -d '{
	"data":"YmFzaWM="
}'

```
This request will update the left side of comparison which has *123* as id, with *YmFzaWM=* data. Then return
updated comparison in JSON format.

``` json5
{"id":123,"leftSide":"YmFzaWM=","rightSide":null}

```

**Keys Description**

**host**: Ip or DNS of the server which application runs on.

#### Update right side in comparison sample request
By below http request command, the application will update right side of the given comparison(with 123 id). If comparison not available, it will
be create with given right side.

```
curl -X POST \
  http://host:8080/v1/diff/123/right \
  -H 'content-type: application/json' \
  -d '{
	"data":"YmFzaWM="
}'

```
This request will update the right side of comparison which has *123* as id, with *YmFzaWM=* data. Then return
updated comparison in JSON format.

``` json5
{"id":123,"leftSide":"YmFzaWM=","rightSide":"YmFzaWM="}

```

#### Get diff sample request
By below http request command, the application will return the result of comparison(with 123 id).

```
curl -X GET http://host:8080/v1/diff/123

```
The application will compare the sides in comparison entity. Then return response in JSON format. According comparison 
response can be:

* ```404 status code with error message``` is that, the given comparison not available or a side in comparison is still null(never updated).

* ```200 status code with comparison result in body``` is that, comparison completed successfully, The result model will be as below in json format:

    * ```MISMATCH_SIZES```: when sides's data have different size.
      ``` json5
       {
           "message": "Mismatch sizes",
           "isEqual": false
       }
      ```
    * ```MATCHED```: when sides's data matched/equal.
      ``` json5
       {
           "message": "Matched",
           "isEqual": true
       }
      ``` 
    * ```MISMATCHED```: when sides's data mismatched/not equal.
      ``` json5
       {
           "message": "MisMatched",
           "mismatchOffsets": [1,3],
           "isEqual": false
       }
      ```                 


## Notes
1.  For getting better performance we can handle requests asynchronously in the future, 
   With this improvement, We would get *update* *left* and *right* requests for the same comparison at the same time. In this situation
   they can block each other by trying update the same row in the table. To overcome this problem, we can 
   set up a queue or retry mechanism.

## Suggestions
1. A real DB can be used instead of h2
2. Swagger can be implemented(except in prod profile)
2. CI/CD pipelines
3. A static code analyzer tool can be use