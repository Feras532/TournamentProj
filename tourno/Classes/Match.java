package Classes;
public class Match {
    Team team1;
    Team team2;
    int[] score;
    int status; //number of teams

    public Match(Team team1,Team team2,int[] score, int status){
        this.team1=team1;
        this.team2=team2;
        this.score=score;
        this.status=status;
    }
}
