# Functionality Verification

## 1. Assumptions

The marker has the following installed on their system:
* Oracle MySQL Server
* Oracle MySQL Workshop Community
* JetBrains IntelliJ IDEA 2022 or Newer
* Java Development Kit 17

## 2. Import the data

Before this step ensure that you drop any schemas that are called `library` to ensure that there will no conflicts with any other students' projects.

There is a file called [`library.sql`](data/library.sql) in the data folder in the root of the project directory. 
These are series of sql queries that can be run to create the schema for the database. It is as simple as copying and pasting the contents to the MySQL Workshop and running it.

## 3. Loading the Project

You may get a prompt from IntelliJ in the bottom right hand corner to 

## 4. Selecting the Project JDK

Upon loading the project in IntelliJ IDEA, the program may not find the JDK due to the fact that I was using the OpenJDK 17 bundled by Adoptium.

## 5. Dependency Management

Please ensure that Maven is able to download all the dependencies.

## 6. Running the project

I have found that program sometimes fails to find resources when transferred to different computers or code editors. To solve this you will need to clean using Maven and then run.

