# Employee Management System
## Spring Boot Application

## Table Of Content

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Project Structure](#project-structure)
5. [Configuration](#configuration)
6. [Running the Application](#running-the-application)

## Introduction
The Employee Management System is an application tailored for small to medium-sized
businesses to effectively handle their human resources operations. 
This system features a user-friendly interface designed for administrators to oversee
all employees within the organization. Admin users have the capability to create
and manage departments, positions, and employee accounts, as well as perform
updates and deletions as needed. Additionally, employee users can view their account
details and see colleagues (employees from the same department).

To get started with the application, users need to set up a database with a database
owner user. Afterward, running the application will prompt Spring Boot to automatically 
generate the required database tables. Once this initial setup is complete,
administrators can create an initial admin user directly within MySQL,
\enabling them to access and utilize the application seamlessly.

This system aims to streamline personnel management processes, ultimately 
enhancing organizational efficiency and productivity across businesses of
varying sizes.


## Prerequisites
- Java 17
- Spring Boot
- Maven
- MySQL
- An initial admin user must exist to perform the first operations

## Installation

Before running the application, you need to set up the MySQL database:

1. Clone the repository
   ```sh
   git clone git@github.com:StKrz/employee-management-system.git
   ```
2. Connect to your MySQL server and execute the following script to create the database and user:
    ```sql
    -- Creating Database
    CREATE DATABASE employee_db;

    -- Create User
    CREATE USER your_username WITH PASSWORD 'your_password';

    -- User's Privileges
    GRANT ALL PRIVILEGES ON DATABASE employee_db TO your_username;
    ```
   
3. Configure the database in `src/main/resources/application.properties`.
4. Build the project using Maven:
   ```sh
   mvn clean install
   ```
5. Run the application to let spring boot create the tables-columns:
   ```sh
   mvn spring-boot:run
   ```
6. Create a user-admin in MySQL to be able to sign in the app, and firstly 
   create departments and positions and then users-employees (or users admin)
   ```sql
   INSERT INTO users (username, email, password, role) VALUES ('aduser', 'aduser@example.com' ,'your_password', 'ADMIN');

   ```

## Project Structure
````
src
└── main
   └── java
      └── gr
         └── aueb
            └── cf
               └── employee
                  ├── configuration
                  │   │── JWTAuthenticationFilter.java
                  │   │── SecurityConfiguration.java      
                  │   └── WebConfig.java
                  │
                  ├── controller
                  │   └── admincontroller
                  │       │── AdminContoller.java
                  │       │── DepartmentController.java
                  │       │── EmployeeController.java
                  │       └── PositionController.java
                  │   │── ErrorHandlingController.java
                  │   └── GeneralController.java
                  │
                  ├── dto
                  │   │── BaseDepartmentDTO.java
                  │   │── BaseEmployeeDTO.java
                  │   │── BasePositionPositionDTO.java
                  │   │── BaseUserDTO.java
                  │   │── DepartmentInsertDTO.java
                  │   │── DepartmentReadOnlyDTO.java
                  │   │── DepartmentUpdateDTO.java
                  │   │── EmployeeInsertDTO.java
                  │   │── EmployeeReadOnlyDTO.java
                  │   │── EmployeeUpdateDTO.java
                  │   │── PositionInsertDTO.java
                  │   │── PositionReadOnlyDTO.java
                  │   │── PositionUpdateDTO.java
                  │   │── UserInsertDTO.java
                  │   │── UserReadOnlyDTO.java
                  │   └── UserUpdateDTO.java
                  │
                  ├── mapper
                  │   │── EmployeeMapper
                  │   │── RefisterMapper
                  │   └── UserMapper
                  │
                  ├── model
                  │   │── Department.java
                  │   │── Employee.java
                  │   │── Position.java
                  │   │── Role.java
                  │   └── Movie.java
                  │
                  ├── repository
                  │   │── DepartmentRepository.java
                  │   │── EmployeeRepository.java
                  │   │── PositionRepository.java
                  │   └── UserRepository.java
                  │
                  ├── service
                  │   └── exceptions
                  │       │── DepartmentAlreadyExistException.java
                  │       │── DepartmentNameNotFoundException.java
                  │       │── DepartmentNotFountException.java          
                  │       │── EmployeeAlreadyExistException.java
                  │       │── EmployeeAlreadyExistException.java
                  │       │── EmployeeNotFoundException.java
                  │       │── LastnameNotFoundException.java
                  │       │── PositionAlreadyExistException.java
                  │       │── PositionNameNotFoundException.java
                  │       │── PositionNotFoundException.java
                  │       │── UserAlreadyExistsException.java
                  │       └── UsernameNotFoundException.java
                  │     │── DepartmentServiceImpl.java
                  │     │── EmployeeServiceImpl.java
                  │     │── IDepartmentService.java
                  │     │── IEmployeeService.java
                  │     │── IPositionService.java
                  │     │── IUserService.java
                  │     │── PositionServiceImpl.java
                  │     │── UserDetailServiceImpl.java
                  │     └── UserServiceImpl.java
                  ├── webtoken
                  │   │── JWTService.java
                  │   └── LoginForm.java
                  │
                  └── EmployeeManagementSystemApplication.java
               └── resources
                  │── public
                  │   └── error
                  │       │──403.html
                  │       └── 404.html
                  ├── static
                  │    └── css
                  │        │── admin_employee_coworkers_list.css
                  │        │── admin_home.css
                  │        │── create_department.css
                  │        │── create_employee.css
                  │        │── create_position.css
                  │        │── dashboard.css
                  │        │── employee_coworkers_list.css
                  │        │── employee_home.css
                  │        └── update_employee.css
                  │ 
                  │    └── js
                  │        │── admin_home.js
                  │        │── create_department.js
                  │        │── create_employee.js
                  │        └── create_position.js
                  │
                  └── templates
                      │── admin_employee_coworkers_list.html
                      │── admin_home.html
                      │── create_department.html
                      │── create_employee.html
                      │── create_position.html
                      │── dashboard.html
                      │── employee_coworkers_list.html
                      │── employee_home.html
                      │── login.html
                      └── update_employee.html
            
````
## Configuration
### \`application.properties\`
```
spring.application.name=employee-management-system
```
## MySQL Database Configuration
````
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?serverTimezone=UTC
spring.datasource.username=****
spring.datasource.password=****
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
````

## Running the Application
1. **Build the project**:
   ````sh
   mvn clean install
   ````

2. **Run the application**:
   ````sh
   mvn spring-boot:run
   ````
## Contact
For any question don't hesitate to contact me.
