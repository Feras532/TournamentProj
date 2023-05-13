package Classes;

import java.util.ArrayList;

public class Team implements java.io.Serializable{
    private Tournament tournament;
    private ArrayList<Paricipant> players ;
    private String nameString;
    private int gamesPlayed;
    private int wins;
    private int losses;
    private int draws;
    private int gamePoints;
    private int goalsScored;
    private int goalsReceived;
    private String rankingString;
    public String getRankString() {
        return rankingString;
    }

    private int highestRoundReached; 

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
        this.highestRoundReached = 0;
    }

    public Team(Tournament tournament,String nameString, Paricipant paricipant){
        this.highestRoundReached = 0;
        this.tournament = tournament;
        this.nameString = nameString;
        this.players = new ArrayList<>();
        players.add(paricipant);
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
    public int getDraws() {
        return draws;
    }
    public int getGamePoints() {
        gamePoints = getWins()*3 + getDraws();
        return gamePoints;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public int getGoalsReceived() {
        return goalsReceived;
    }
    public int getGoalsScored() {
        return goalsScored;
    }
    public int getGoalDifference(){
        return goalsScored - goalsReceived;
    }
    public int getLosses() {
        return losses;
    }
    public ArrayList<Paricipant> getPlayers() {
        return players;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public int getWins() {
        return wins;
    }
    @Override
    public String toString() {
        return getNameString();
    }

    public void setRank(String rankingString) {
        this.rankingString = rankingString;
        System.out.println("Rank is set to: "+ rankingString);
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
    public int getHighestRoundReached() {
        return highestRoundReached;
    }

    public void setHighestRoundReached(int i) {
        this.highestRoundReached = i;
    }
}
