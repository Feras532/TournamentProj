package Classes;
public abstract class User implements java.io.Serializable {
    String userID;
    String firstName;
    String lastName;
    String emailString;
    

public User(String userID,String firstName,String lastName,String emailString){
    this.userID=userID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.emailString=emailString;    
}


public User(String userID) {
    this.userID = userID;
}


//Getters
public String getUserID(){
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

}
