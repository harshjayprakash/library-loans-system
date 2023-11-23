# Library Loans System

> A library loaning system that demonstrates the use of object orientated principles as well as the ability of implementing a database.

This is code that was submitted as an assignment.

* Submitted: May 2023
* Module: BS2202 Object-Orientated Software Development
* Course: BSc (Hons) Computer Science

The application was written in Java, using the JavaFX GUI Framework.

## Application Structure

|Package|Description|
|:------|:----------|
|`data.access`| allows access to the database using the MySQL driver. |
|`data.model`| classes that model the data from the database. |
|`meta`| contains the metadata for the application. |
|`service` | contains the logic and translation between the data access and presentation layers. |
|`presentation` | contains the implementation for the graphical user interface. |

## MySQL Database Data

The MySQL default data can be found in a single SQL file found in `./data/library.sql`. This file has been tested multiple times ensure that you can just run this file in the MySQL Workshop.

The credentials for connecting to the database is found in `winchester.library.data.access.DatabaseCredentials`.

## Limitations and Known Issues

* The Individual View Windows can be the same size as the Main Window making it seem that the window's scene has changed.
* The Individual View Windows can be initalised and shown multiple times without a limit.
* The images are requested from the internet directly, which makes the inventory view slower to load and there no garantee on how long the images will be available.
* Refreshing of information in the GUI.
* Changing the database credentials through the GUI is not permanent.
