This is basic CRUD project devloped in Spring Boot

Spring boot version - 2.7.6

DataBase - Inbuilt H2 database


********************Curl request***************

GET PROPERTIES

curl --location 'http://localhost:8089/api/v1/properties' \
--data ''


ADD PROPRTIES (POST)

curl --location 'http://localhost:8089/api/v1/properties' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"North blue",
    "description":"ok",
    "ownerName":"Teach D",
    "ownerEmail":"Teach.r@gmail.com",
    "price":23888.22,
    "address":"GGG corner pune"

}'


UPDATE PROPERTIES (PUT)

curl --location --request PUT 'http://localhost:8089/api/v1/properties/2' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "title": "classic building",
        "description": null,
        "ownerName": "monkey D. Dragon",
        "ownerEmail": "dragonM@gmail.com",
        "price": 621343.22,
        "address": "pG corner pune"
    }'


PARTIAL UPDATE(PATCH)

curl --location --request PATCH 'http://localhost:8089/api/v1/properties/update-description/2' \
--header 'Content-Type: application/json' \
--data '    {
        "description": "bravo"
    }'
    
    
GET CALCULATION (PRACTICE)


curl --location 'http://localhost:8089/api/v1/calculator/sub/25/12'

