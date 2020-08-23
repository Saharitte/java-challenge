###Overview 

This project aims to manage employees and departments

###Requirements
For building and running the application you need:
•	Java 8
•	Maven latest
•	H2 (With the dev profile you can use H2)
•	Embedded tomcat


###Libraries used

•	Spring Boot 2.1.4.RELEASE
•	Spring Boot Test
•	JSON Library(javax.json)
•	Swagger API
•	JPA 

###Running the application locally
1.	Install packages with 

 mvn package

2.	Run the application

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the jp.co.axa.apidemo.ApiDemoApplication class from your IDE.
Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :
•	Swagger UI : http://localhost:8080/swagger-ui.html
•	H2 UI : http://localhost:8080/h2-console
Don't forget to set the JDBC URL value as jdbc:h2:mem:testdb for H2 UI.

###Features

These services can perform:
1.	Employee CRUD operations:
•	Retrieve all employees 
•	Create a new employee 
•	Retrieve Employee by id 
•	Delete employee
•	Update employee
•	Retrieve employee by email 
•	Retrieve Employee by first name
•	Retrieve Employee by last name
2.	Department CRUD operations

•	Create Department 
•	Retrieve department by id
•	Update department 
•	Delete department 
•	Remove department and all its employees 
•	Retrieve all department 
•	Retrieve department by name
•	Retrieve department by code
•	Retrieve all employee by department name
•	Retrieve all employee by department code 
•	all employee by department id
