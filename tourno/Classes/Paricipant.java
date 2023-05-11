package Classes;

import java.util.ArrayList;
import java.util.TreeSet;

public class Paricipant extends User implements java.io.Serializable{
    ArrayList<Team> teams ;
    ArrayList<Tournament> historyTournaments;

    public ArrayList<Tournament> getHistoryTournaments() {
        return historyTournaments;
    }

    public void addToHistoryTournaments(Tournament tournament) {
        historyTournaments.add(tournament);
    }


    public Paricipant(String userID,String firstName,String lastName,String emailString){
        super(userID,firstName,lastName,emailString);
        teams = new ArrayList<>();
        historyTournaments = new ArrayList<>();  // initialize the historyTournaments list
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }


    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
