To run Transaction-Management

````
gradle bootRun
````

API's

````
GET

http://localhost:8080/statistics

POST

http://localhost:8080/transactions
{
    "amount": 12.3,
    "timestamp": 1478192204000
}