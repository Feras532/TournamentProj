package Classes;

import java.util.ArrayList;

public abstract class Tournament implements java.io.Serializable {
    private String name;
    private Game game;
    private int numOfDays;
    private String startdate;
    private String endDate;
    private Boolean numOfTeamsIsFixed;
    private int numOfTeams;
    private Boolean isOpenRegisteration;
    private Boolean isActive;
    protected ArrayList<Team> registeredTeams;
    public void setRegisteredTeams(ArrayList<Team> registeredTeams) {
        this.registeredTeams = registeredTeams;
    }

    protected ArrayList<Round> rounds;

    public int getNumOfTeams() {
        return numOfTeams;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game.getName();
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
            Boolean numOfTeamsIsFixed, int numOfTeams, Boolean isOpenRegisteration, Boolean isActive,
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

}