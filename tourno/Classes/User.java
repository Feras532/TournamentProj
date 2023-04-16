package Classes;
public abstract class User {
    int userID;
    String firstName;
    String lastName;
    String emailString;
    String phoneNumber;

public User(int userID,String firstName,String lastName,String emailString,String phoneNumber)
{
    this.userID=userID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.emailString=emailString;
    this.phoneNumber=phoneNumber;
}


//Getters
public int getUserID(){
    return userID;
}
public String getFirstName(){
    return firstName;
}
public String getLastName(){
    return lastName;
}
public String getEmailString(){
    return emailString;
}
public String getPhoneNumber(){
    return phoneNumber;
}

}
