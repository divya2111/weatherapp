# Weather application

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Configure MySQL Database](#configure-mysql-database)
- [Build Instructions](#build-instructions)
- [Design Choices](#design-choices)
- [Dependencies](#dependencies)
- [Usage](#usage)

## Description
This application simulates a sequence of weather updates for major metro cities in India over several days, providing users with a graphical representation of weather conditions, including temperature, humidity, wind speed, and more.


## Features
- Displays weather information for multiple cities.
- Provides real-time updates and weather summaries.
- User-friendly interface built with SPRINGBOOT, JSP, HTML, CSS, and JavaScript.


## Technologies Used
- **Backend**: Spring Boot
- **Frontend**: JSP, HTML, CSS, JavaScript
- **Database**: MySQL
- **Build Tool**: Maven


## Installation
# Prerequisites
- **Java JDK**: Version 11 or higher
  - [Download Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**: Version 3.6.0 or higher
  - [Download Maven](https://maven.apache.org/download.cgi)
- **MySQL**: Version 5.7 or higher
  - [Download MySQL](https://dev.mysql.com/downloads/mysql/)
  
### Steps to Install
**Clone the repository:**
  
   git clone https://github.com/divya2111/weatherapp.git
   cd weatherapp
   
   Install Required Software: Ensure Java, Maven, and MySQL are installed as mentioned in the prerequisites.
   
## Configure MySQL Database
   
   USE weather_db
   
   CREATE TABLE daily_weather_summary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    avg_temp DECIMAL(5, 2) NOT NULL,
    min_temp DECIMAL(5, 2) NOT NULL,
    max_temp DECIMAL(5, 2) NOT NULL,
    dominant_condition VARCHAR(100) NOT NULL,
    temp DECIMAL(5, 2) NOT NULL
);

## Build Instructions
 **Run the Application**:
   You can run the application using one of the following methods:

   **Option A: Using Eclipse**
   - Open Eclipse and import the project:
     - Go to **File** > **Import** > **Existing Maven Projects** and select the cloned repository.
   - After the project is imported, right-click on the project in the **Project Explorer**.
   - Select **Run As** > **Spring Boot App** (or **Java Application** if it doesn't appear).

   **Option B: Using Command Line**
   If you prefer to run the application from the command line, use:
   
   ./mvnw spring-boot:run
   
  Access the Application: http://localhost:8080/
   
## Design Choices
Architecture: The application follows a layered architecture with separate layers for controllers, services, and repositories to maintain separation of concerns.
Database Design: The weather table is designed to store relevant weather information, including temperature, humidity, and conditions, facilitating efficient querying and updates.

## Dependencies
To set up and run the application, you will need the following dependencies:

Required Software
Java JDK: Version 11 or higher
Maven: Version 3.6.0 or higher
MySQL: Version 5.7 or higher
Optional (if using Docker)
Docker: Required if you want to run services in containers.
Download Docker
Spring Boot Dependencies
   The following dependencies are automatically managed by the Spring Boot application:

- **Spring Web**: For building web applications and REST APIs.
- **Spring Data JPA**: For database interaction using Java Persistence API.
- **MySQL Connector/J**: JDBC driver for MySQL, included in the `pom.xml` fil
   
## Usage
Upon visiting the application, users will be presented with weather Forecast of metro cities . Users can view present weather update and also weather forecast of next 30 days .  

