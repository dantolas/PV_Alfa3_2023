
# Project alfa 3 -- Database system
## Kuta Samuel C4b 

## Table of contents (TOC)
==========================
1. [Introduction](##introduction)
2. [Requirements](##requirements)
3. [Installation](##installation)
4. [Usage](##usage)
5. [Configurations](##configurations)
6. [Docs](##docs)
7. [Testing](##testing-and-test-scenarios)
8. [Dependencies](##dependencies)
9. [Shortcomings](##things-to-work-on-and-shortcomings)

## Introduction
This application provides a database design for managing medical ePrescriptions
for medication.

It also provides and API to work with mentioned database, and a CLI that can
operate on the API.

The application provides basic CRUD functionality, along with generating data
reports and a degree of configuration.

## Requirements
`Java` - version *20.0.1*+
`Git` - *OPTIONAL*
`Gradle` - version *9.4*+ *OPTIONAL*

## Installation
For the full experience clone this repository from the command line
`git clone address <directory>`

Or just download the **jar** file and run it, however make sure your database
is setup correctly according to the next step
- see [Usage](##Usage)

### DB Setup
Navigate to *db/exports* and locate the **schema.sql** and **data.sql** files.
Import the schema and the data to your MySQL database using your favorite approach.
If everything imported correctly, that database can now be used with this application.

## Usage
Double click the alfa3-all.jar file and the program should start.

Alternatively execute this command from the command line
`java -jar alfa3-all.jar`

## Configurations
U can configure the database access point, but make sure u have the schema
set up correctly as mentioned in [Installation](##instalation)

Configurations are read from the *conf/* **config.json** file. 
This file can be modified to for example change the database connection or
rename report files generated.

**config** file example:


    {
        "db":{
            "database_host":"jdbc:mysql://localhost:3306",
            "database_name":"alfa3",
            "username":"username",
            "password":"password"
        }

    }

**Default configuration**
- For the default configuration ensure all the values are equal to:`default`
- **Default configuration**:

        {
            "db":{
                "database_host":"default",
                "database_name":"default",
                "username":"default",
                "password":"default"
            }

        }
    


## Docs
- **Developer documentation** 
    - If Gradle is installed on the system (check with `gradle -v`)
    the following command can be executed from the command line: 
        - **Windows**: `gradlew build`
        - **Unix**: `./gradlew build`
    After that u can find generated Javadoc in *build/docs/javadoc/index.html*
- **User documentation**
    - This can be considered user documentation

## Testing and Test scenarios
- U can find all test scenarios in *test/* as .pdf files 
- The program does not contain any unit tests



## Dependencies
- Google.com GSON Json parsing and serialization tool. See [GSON](https://github.com/google/gson) 
- MySql JDBC Driver. See [MySQL Connector/J](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)

## Things to work on and shortcomings
- The application does not provide a great way to delete data from db if the 
rows depend on each other. This couldn't be implemented due to lack of time.
- The User Interface is quite bland and boring. Colors couldn't be implemented
due to lack of time.
- The application doesn't provide perfect information to solve every error 
encountered in the user interface. This couldn't be implemented due to lack of time.
- Application uses a three tier architecture, however I'm inexperienced with
building three tier applications so the implementation propably isn't very good.

