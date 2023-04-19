package Classes;
public class Round {
    Match[] matches;
    Boolean isCompleted;

    public Match[] getMatches() {
        return matches;
    }

    public void setMatches(Match[] matches) {
        this.matches = matches;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Round(Match[] matches, Boolean isCompleted){
        this.matches=matches;
        this.isCompleted=isCompleted;
    }
}
