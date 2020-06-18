# Expense-app

A Java/Springboot-based REST API for managing expenses
  
  ## Requirements
  
  For building and running the application you need:
  
  - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Maven 3](https://maven.apache.org)
  - [IntelliJ IDE](https://www.jetbrains.com/idea/download/#section=windows) or any other Java based IDE
  - Install Lombok in your IDE 
  - MySql


## Getting Started

1. **Clone the application**

	```bash
	git clone https://github.com/victor-onu/expense-app.git
	cd expense-app
	```

2. **Create MySQL database**

	```bash
	create database expenseApp, although it will be creted if it does not exist
	```


3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.yml` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

	There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.inits.expenseapp.expenseAppApplication` class from your IDE.
    
    Alternatively you can run the following command

	```bash
	mvn spring-boot:run
	```

	The server will start on port 8080.


## Testing the endpoints on Postman

# POST
**Saves a new expenses in the database - (Use [requests](#Requests) below)**

	http://localhost:8080/api/expense

# PUT
**Updates an expenses in the database by specifying the Id(Use request below)**

	http://localhost:8080/api/expense/1


# GET
**Retrieves all the expenses from the database**
  
	http://localhost:8080/api/expense/
	
# GET By ID
**Retrieves a particular expenses from the database by specifying the Id**

	http://localhost:8080/api/expense/1

# DELETE
**Deletes an expenses from the database by specifying the Id**

	http://localhost:8080/api/expense/1

# Requests

	{
        "description": "Fueling of the car",
        "location": "London",
        "amount": 56.8
    }
	{
        "description": "Ticket at the movies",
        "location": "Tham movies",
        "amount": 5.8
    }
	{
        "description": "Feeding",
        "location": "Lagos",
        "amount": 6.8
    }



## Built With

  - Java
  - Springboot
  - MySql

