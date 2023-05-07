package Classes;

import java.util.ArrayList;

public class Paricipant extends User implements java.io.Serializable{
    ArrayList<Team> teams ;
    
    public Paricipant(String userID,String firstName,String lastName,String emailString){
        super(userID,firstName,lastName,emailString);
        teams = new ArrayList<>();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }


    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
