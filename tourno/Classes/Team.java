package Classes;
public class Team {
    Tournament tournament;
    Paricipant[] players;
    private String nameString;
    int gamesPlayed;
    int wins;
    int losses;
    int draws;
    int gamePoints;
    int goalsScored;
    int goalsReceived;

    public Team(Tournament tournament,Paricipant[] players,String nameString,int gamesPlayed,int wins,int losses,int draws,int gamePoints,int goalsScored,int goalsReceived){
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

//getters
public String nameString(){
    return nameString;
}
}
