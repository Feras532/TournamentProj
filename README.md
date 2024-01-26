# TournamentProj

This repository contains an application designed to manage Round Robin and Elimination tournaments.

## Getting Started

### Prerequisites

Before running the application, ensure that you have the JavaFX libraries added to the Referenced Libraries. The necessary libraries can be found in the "Important libs" folder within the project.

### Setting up Email and Activation

To set up the email and activation features, follow these steps:

1. Go to the 'Run and Debug' tab in your IDE.
2. Create a `launch.json` file.
3. Add the following configuration to the file:

json
{
    "type": "java",
    "name": "MailAPI",
    "request": "launch",
    "mainClass": "MailAPI",
    "projectName": "MailAPI_e765d83c",
    "args": "--add-modules java.activation"
}
-------------------------------------------
Running the Application
To start the application, run mainApp.java.

Login Information
To log in, refer to the CSV files included in the project for usernames and passwords.

Note
This repository includes sample data for historical tournaments to demonstrate the functionality. However, please note that the history tournaments are not updated.

Contributors
Mohammed Alshowaikhat 202178490
Feras Alsinan 202021580
Hassan Almaqdoud 202156130
Hussain Almatrouk 202042760
Abdullah Bukahri 201951430
