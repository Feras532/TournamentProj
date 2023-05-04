package Classes;

import java.util.ArrayList;

public class Team implements java.io.Serializable{
    private Tournament tournament;
    private ArrayList<Paricipant> players;
    private String nameString;
    private int gamesPlayed;
    private int wins;
    private int losses;
    private int draws;
    private int gamePoints;
    private int goalsScored;
    private int goalsReceived;

    public Team(Tournament tournament,ArrayList<Paricipant> players,String nameString,int gamesPlayed,int wins,int losses,int draws,int gamePoints,int goalsScored,int goalsReceived){
        this.tournament=tournament;
        this.players=players;
        this.nameString=nameString;
        this.gamesPlayed=gamesPlayed;
        this.wins=wins;
        this.losses=losses;
        this.draws=draws;
        this.gamePoints=gamePoints;
        this.goalsScored=goalsScored;
        this.goalsReceived=goalsReceived;
    }

    public Team(int n){
        this.nameString = "Team" + n;
    }
    public Team(){
        this.nameString = "TBA";
    }
    //getters
    public String getNameString(){
        return nameString;
    }

    @Override
    public String toString() {
        return getNameString();
    }
}
