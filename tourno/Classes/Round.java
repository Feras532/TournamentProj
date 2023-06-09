package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Round implements java.io.Serializable{
    private ArrayList<Match> matches;
    private Boolean isCompleted;
    private LocalDate date;

    public Round() {
        this.matches = new ArrayList<>();
    }

    public Round(ArrayList<Match> matches, Boolean isCompleted){
        this.matches=matches;
        this.isCompleted=isCompleted;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }
    
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public void setDate(LocalDate date) {
        for (Match match : matches) {
            match.setDate(date);
        }
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }

}
