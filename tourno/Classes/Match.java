package Classes;

import java.time.LocalDate;

public class Match implements java.io.Serializable{
    Team team1;
    Team team2;
    int[] score;
    int status; //number of teams
    String matchString;
    private LocalDate date;

    public Match(Team team1,Team team2,int[] score, int status){
        this.team1=team1;
        this.team2=team2;
        this.score=score;
        this.status=status;
        this.matchString = team1 + " vs " + team2;
    }

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.matchString = team1 + " vs " + team2;
    }

    public Match(){
        this.team1 = new Team();
        this.team2 = new Team();
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public void setTeams(Team team1,Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }
    public String getMatchString() {
        return matchString;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
        public String toString() {
            return team1 + " vs " + team2;
        }

    public void remove(int index) {
    }

    public Team getWinner() {
        Team winner;
        if(getScore()[0] > getScore()[1])
            winner = team1;
        else
            winner= team2;
        return winner;
    }
    public boolean hasBeenRecorded = false;
    
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
