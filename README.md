# carapp
Car app is a Car Lease Platform API. It is a Spring Boot backend application made by Peri.

## What does the API do
* Keeps records of both customers and cars.
* Calculates interest rate for cars.
* Loads CSV records to its database.

## Who are the end users for this API
1.	Brokers that calculate the leaserate for a customer and maintain customer data.
2.	Leasing company that maintains data to make an accurate calculation.
The API allows to read, add, modify, and delete records.
The database is saved on a local file.

## Usage
Run application. As a default it runs on localhost and port 8080.

The code below is for client requests as generated by intellij.  
Here are examples for all customer requests:
```json
### Get all customers
GET http://localhost:8080/customers
Accept: application/json

### Get customer by id
GET http://localhost:8080/customers/1
Accept: application/json

### Create a new customer
POST http://localhost:8080/customers
Content-Type: application/json

{
  "name": "abc",
  "street": "streetname",
  "houseNumber": 12,
  "zipcode": "1234AA",
  "place": "amsterdam",
  "emailAddress": "user@email.com",
  "phoneNumber": "0551234567"
}

### Update a customer
PUT http://localhost:8080/customers/1
Content-Type: application/json

{
  "name": "111",
  "emailAddress": "123@hotmail.org"
}

### Delete a customer
DELETE http://localhost:8080/customers/2
Content-Type: application/json

```

Here are examples for all customer requests:
```json
### Get all cars
GET http://localhost:8080/cars
Accept: application/json

### Get car by id
GET http://localhost:8080/cars/1
Accept: application/json

### Create a new car
POST http://localhost:8080/cars
Content-Type: application/json

{
  "make": "maker",
  "model": "model1",
  "version": "version0",
  "numberOfDoors": 5,
  "co2Emission": 4,
  "grossPrice": 3,
  "nettPrice": 2,
  "mileage": 1000,
  "duration": 20,
  "interestRate": 10.5,
  "startDate": "01-01-2001"
}

### Update a car
PUT http://localhost:8080/cars/3
Content-Type: application/json

{
  "model": "111",
  "grossPrice": 1
}

### Delete a car
DELETE http://localhost:8080/cars/1
Content-Type: application/json

### Get lease rate for car by id
GET http://localhost:8080/cars/2/leaserate
Accept: application/json

### Upload from CSV file to database
POST http://localhost:8080/cars/uploadcsv
Content-Type: application/json

{
  "path": "csvfiles\\testdata.csv"
}
```
