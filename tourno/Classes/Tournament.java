package Classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Tournament implements java.io.Serializable {
    private String name;
    private Game game;
    private int numOfDays;
    private String startdate;
    private String endDate;
    private Boolean numOfTeamsIsFixed;
    private int numOfTeams;
    private int numOfMembers;
    private Boolean isOpenRegisteration;
    private Boolean isActive;
    private Boolean isCompleted;
    
    private Team winner;

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    protected ArrayList<Team> registeredTeams;
    protected ArrayList<Round> rounds;


    public Boolean playersAreAlreadyRegistered(Team team) {
        for (Team registeredTeam : registeredTeams) {
            for (Paricipant registeredParicipant : registeredTeam.getPlayers()) {
                for (Paricipant paricipant : team.getPlayers()) {
                    if (registeredParicipant.getUserID().equals(paricipant.getUserID())){
                        System.out.println("Player Already Registered FOUND !!");
                        return true; 
                    }
                }
            }
        }
        return false;
    }


    public void setRegisteredTeams(ArrayList<Team> registeredTeams) {
        this.registeredTeams = registeredTeams;
    }

    public int getNumOfTeams() {
        return numOfTeams;
    }

    public int getNumofMembers() {
        return numOfMembers;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game.getName();
    }

    public Game getGameObj(){
        return game;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getNumOfTeamsIsFixed() {
        return numOfTeamsIsFixed;
    }

    public Boolean getIsOpenRegisteration() {
        return isOpenRegisteration;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public ArrayList<Team> getRegisteredTeams() {
        return registeredTeams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startdate) {
        this.startdate = startdate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public void setIsActive(Boolean isActive){
        this.isActive =isActive;
    }

    public void setIsOpenRegisteration(Boolean isOpenRegisteration){
        this.isOpenRegisteration =isOpenRegisteration;
    }


    public int getNumRegisteredTeams() {
        int size = 0;
        if (registeredTeams != null) {
            for (int i = 0; i < registeredTeams.size(); i++) {
                size++;
            }
        }
        return size;
    }

    public Tournament(String name, Game game, int numOfDays, ArrayList<Round> rounds, String startdate, String endDate,
            Boolean numOfTeamsIsFixed, int numOfTeams, int numOfMembers, Boolean isOpenRegisteration, Boolean isActive, Boolean isCompleted,
            ArrayList<Team> registeredTeams) {
        this.name = name;
        this.game = game;
        this.numOfDays = numOfDays;
        this.rounds = rounds;
        this.startdate = startdate;
        this.numOfTeams = numOfTeams;
        this.endDate = endDate;
        this.numOfTeamsIsFixed = numOfTeamsIsFixed;
        this.isOpenRegisteration = isOpenRegisteration;
        this.isActive = isActive;
        this.registeredTeams = registeredTeams;
        this.numOfMembers = numOfMembers;
        this.isCompleted = isCompleted;
    }
    public Tournament(){
        rounds = new ArrayList<>();
        registeredTeams = new ArrayList<>();
    }

    // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
    private String status;

    public String getStatus() {
        if (isOpenRegisteration)
            return "Open for registeration";
        else
            return "Closed";
    }

    // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
    private String registeredToMax;
    public String getRegisteredToMax() {

        if ((getNumOfTeams() == 0) && (getNumOfTeamsIsFixed() == false))
            return getNumRegisteredTeams() + " / Unlimited";
        if (this.registeredTeams == null)
            return "0/" + getNumOfTeams();
        return getNumRegisteredTeams() + " /" + getNumOfTeams();

    }


    public boolean getIsCompleted() {
        return isCompleted;
    }


    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setRoundDates() {
        //YYYY-MM-DD
        LocalDate startDate = LocalDate.of(Integer.valueOf(startdate.substring(0, 4)),
        Integer.valueOf(startdate.substring(5, 7)),
        Integer.valueOf(startdate.substring(8, 10)));
           
            
        LocalDate endDate = LocalDate.of(Integer.valueOf(this.endDate.substring(0, 4)),
        Integer.valueOf(this.endDate.substring(5, 7)),
        Integer.valueOf(this.endDate.substring(8, 10)));
        // Get the number of days between the start and end date
        int days = Period.between(startDate, endDate).getDays()+1;
        // Get the number of rounds in the tournament
        int rounds = this.rounds.size();
                // Calculate the interval between each round date
                int interval = days / rounds;
                // Loop through the rounds and set their dates
                for (int i = 0; i < rounds; i++) {
                // Add the interval times the round index to the start date
                LocalDate roundDate = startDate.plusDays(interval * i);
                // Set the round date
                this.rounds.get(i).setDate(roundDate);
                }    
    }
}