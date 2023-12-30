# Welcome to the course CSYE6200 - Object Oriented Design
> Northeastern University, College of Engineering

## Professor: Daniel Peters

## Course Registration Portal
This is a Spring Boot application that provides a platform for students and professors to manage course registrations. It facilitates user authentication, course selection, and filtering, and allows for various interactions between students and professors. It demonstrates Object-Oriented Design principles while interacting with a MySQL database using Hibernate.

## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [Database Configuration](#database-configuration)
4. [Object-Oriented Design](#object-oriented-design)
5. [Notable Design points](#notable-design-points)
6. [Database Schema](#database-schema)
7. [Authors](#authors)

## Installation

Software Installaion Prerequisites:

1. Java Development Kit (JDK)
2. Spring Tool Suite (STS)
3. Build Tool - Maven

Steps to run the project:

1. Clone the repository:

	```bash
	git clone https://github.com/CSYE6200-Object-Oriented-DesignFall2023/final-project-final-group-10.git
	cd course-registration-portal
	 ```
2. Import the Project:

	1. Open STS.
	2. Go to File > Import....
	3. Choose Existing Maven Projects
	4. Navigate to the location where you saved your Spring Boot project and select the project directory.
	5. Click Finish to import the project.

3. Build the Project:

	Once the project is imported, STS will automatically start building it. You can check the progress in the console at the bottom.

4. Configure the Application Properties:

	Open the application.properties and configure database connection details.

5. Run the Application:

	Right-click on the main class file (CourseRegistrationApplication) in the src/main/java directory.
	Select Run As > Spring Boot App.

Once the application has started, you can access it by opening a web browser and navigating to http://localhost:8080

## Usage

Access the application in your web browser: [http://localhost:8080](http://localhost:8080/courseRegistration/)
Log in as a student or professor.
Explore the available courses, and filter them by ID or name.
Professors can add and edit courses.
Students can register for courses and view the registered courses.
Once the application runs, you can access the API endpoints to interact with the entities.

## Database Configuration

The application is configured to use MySQL as the database.
Update the `application.properties` file with your MySQL database configuration:
`src/main/resources/application.properties`
```
spring.datasource.url=jdbc:mysql://localhost:3306/course_registration_db
spring.datasource.username=[Username]
spring.datasource.password=[Password]
spring.jpa.hibernate.ddl-auto=update
```
## Object-Oriented Design

This project follows **Object-Oriented Design principles and Design Patters.**
The individual contributions are displayed here.

1. Encapsulation - We're creating POJO's [Plain Old Java Objects] -> Nilraj Mayekar
2. Interfaces have been used while creating DAO classes for Hibernate -> Nilraj Mayekar
3. Inheritance - Student and Teacher are inherited from User -> Rajeev Ramesh
4. Polymorphism - we're overriding functions -> Rajeev Ramesh
5. Comparators for sorting course details based on name -> Puja Kalivarapu 
6. Streams to filter and search courses and students -> Puja Kalivarapu 
7. ENUM's to declare user roles -> Manish Chikkavadaragudi Prasanna Kumar
8. Singleton design pattern for creating SessionFactory for Hibernate -> 
9. Factory design pattern to create Student and Person accounts -> Srishti C Rai
10. Read CSV files to get Student and Person details using Generic class -> Srishti C Rai
11. Used various data structures like Arrays, List, Set etc. -> Srishti C Rai
12. Lambda expressions for sorting -> Abdul Azeem Syed
13. Exception handling is done in multiple places -> Abdul Azeem Syed
	
 Entities are designed to represent real-world objects, and relationships are modelled to reflect the business logic.
 
 ## Notable Design points
 
1. GUI - We have used **Thymleaf** templates to create interactive pages.
2. Database - **MySQL** has been used to store the data for the application.
3. ORM - **Hibernate** has been used to maintain data persistence.
4. Framework  - **Spring boot** has been used to create a stand-alone application.
 
 ## Database Schema

Create a new schema named course_registration_db in MySql and the tables are created automatically based on the JPA entity definitions.
![WhatsApp Image 2023-12-11 at 21 00 03_ef5c6cbb](https://github.com/CSYE6200-Object-Oriented-DesignFall2023/final-project-final-group-10/assets/144845169/b386665e-e736-491d-8e11-73066018f043)


## Authors 

Author:
- Srishti C Rai (rai.sr@northeastern.edu)
- Manish Chikkavadaragudi Prasanna Kumar (chikkavadaragudipr.m@northeastern.edu)
- Puja Kalivarapu (Kalivarapu.puja@northeastern.edu)
- Abdul Azeem Syed (syed.abdu@northeastern.edu)
- Rajeev Ramesh (ramesh.raj@northeastern.edu)
- Nilraj Mayekar (mayekar.n@northeastern.edu)
