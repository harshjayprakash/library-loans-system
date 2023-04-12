# Library Loans System

> A project that demonstrates the use of the Object-Orientated Paradigm as well as the ability of implementing a database.

## Table of Contents

1. [Application Information](#1-application-information)  
1.1. [Structure](#11-structure)  
1.2. [Required Libraries](#12-required-libraries)  
1.3. [Compilation and Execution](#13-compilation-and-execution)
1.4. [Additional Information](#14-additional-information)


## 1. Application Information

### 1.1 Structure
Below shows the application's package structure
```
winchester/library
├── data
│   ├── access
│   └── model
├── meta
├── presentation
│   ├── component
│   ├── style
│   ├── view
│   └── window
└── service
```

| package        | name                                                                       |
|----------------|----------------------------------------------------------------------------|
| `data.access`  | This contains the interaction with the JDBC Driver to access the database. |
| `data.model`   | These are all the classes used to represent objects.                       |
| `meta`         | This contains metadata for program (e.g. version number and name).         |
| `presentation` | This contains the user interface that the end user will interact with.     |
| `service`      | This is a layer that helps with the interaction between data and the ui.   |

### 1.2 Required Libraries
* JetBrains Annotations
* JUnit 
* JavaFX
* MySQL JDBC Connector

### 1.3 Compilation and Execution

This application uses the Maven build tool, which both IntelliJ IDEA and Microsoft Visual Studio Code have good support for.
Please make sure that Maven is able to download sources and documentation required before proceeding to run the application.

Please ensure that you have the MySQL Server (MariaDB if you are on linux) so that the data provided, in the data folder, can be imported. This folder includes an export from the MySQL Workshop. If this fails the sql queries to create the database have also been provided in `data/library.sql`.


### 1.4 Additional Information

For more technical information, please visit the `project.toml` file.