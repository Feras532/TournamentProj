# TournamentProj

## About

TournamentProj is a dynamic tournament management application developed using JavaFX to provide a robust and user-friendly interface. It is designed to cater to both teams and individual participants, offering a range of functionalities for users and administrators alike.

## Features

### User Accounts
- **Account Creation**: Users can sign up and create their own accounts.
- **Join as Team or Individual**: Flexible registration options allow users to join tournaments as a single player or as part of a team.

### Tournament Management
- **Tournament Setup**: Admins can create and manage tournaments, setting up the structure for Round Robin or Elimination formats.
- **Editing and Updates**: Admins can edit tournament details and record match results in real-time.
- **User Interface**: A JavaFX-based interface ensures all interactions are intuitive and straightforward.

### User Experience
- **Historical Data**: Users have access to their past tournament history, allowing them to track their progress and performance over time.
- **Real-time Access**: All participants can view ongoing tournament details and results as the events unfold.

### Administrative Control
- **Tournament Oversight**: Admins can oversee the entire tournament process, from creation to completion, ensuring a smooth experience for all.
- **Result Recording**: After matches, admins can record the outcomes, which are then reflected across the participant's accounts and tournament brackets.


## Getting Started

### Prerequisites

Before running the application, ensure that you have the JavaFX libraries added to the Referenced Libraries. The necessary libraries can be found in the "Important libs" folder within the project.

### Setting up Email and Activation

To set up the email and activation features, follow these steps:

1. Go to the 'Run and Debug' tab in your IDE.
2. Create a `launch.json` file.
3. Add the following configuration to the file:

```json
{
    "type": "java",
    "name": "MailAPI",
    "request": "launch",
    "mainClass": "MailAPI",
    "projectName": "MailAPI_e765d83c",
    "args": "--add-modules java.activation"
}
```
-------------------------------------------
### Running the Application
To start the application, run `mainApp.java`.

### Login Information
To log in, refer to the CSV files included in the project for usernames and passwords.

### Note
This repository includes sample data for historical tournaments to demonstrate the functionality. However, please note that the history tournaments are not updated.

### Contributors
- Mohammed Alshowaikhat 202178490
- Feras Alsinan 202021580
- Hassan Almaqdoud 202156130
- Hussain Almatrouk 202042760
- Abdullah Bukahri 201951430
