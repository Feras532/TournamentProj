package Classes;
public class Match {
    Team team1;
    Team team2;
    int[] score;
    int status; //number of teams

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
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

    public void setStatus(int status) {
        this.status = status;
    }

    public Match(Team team1,Team team2,int[] score, int status){
        this.team1=team1;
        this.team2=team2;
        this.score=score;
        this.status=status;
    }
}
