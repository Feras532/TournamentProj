package Classes;

public abstract class Tournament implements java.io.Serializable {
    String name;
    Game game;
    int numOfDays;
    Round[] rounds;
    String startdate;
    String endDate;
    Boolean numOfTeamsIsFixed;
    int numOfTeams;

    public int getNumOfTeams() {
        return numOfTeams;
    }

    Boolean isOpenRegisteration;
    Boolean isActive;
    Team[] registeredTeams;

    public String getName() {
        return name;
    }

    public String getGame() {
        return game.getName();
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public Round[] getRounds() {
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

    public Team[] getRegisteredTeams() {
        return registeredTeams;
    }

    public int getNumRegisteredTeams() {
        int size = 0;
        if (registeredTeams != null) {
            for (int i = 0; i < registeredTeams.length; i++) {
                size++;
            }
        }
        return size;
    }

    public Tournament(String name, Game game, int numOfDays, Round[] rounds, String startdate, String endDate,
            Boolean numOfTeamsIsFixed, int numOfTeams, Boolean isOpenRegisteration, Boolean isActive,
            Team[] registeredTeams) {
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
