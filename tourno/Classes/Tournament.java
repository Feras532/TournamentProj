package Classes;
public abstract class Tournament {
    String name;
    Game game;
    int numOfDays;
    Round[] rounds;
    String startdate;
    String endDate;
    Boolean numOfTeamsIsFixed;
    int numOfTeams;
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

    public int getNumRegisteredTeams(){
        int size = 0;
        for(int i  = 0 ; i< registeredTeams.length ; i++){
            size++;
        }
        return size;
    }
    public Tournament(String name,Game game,int numOfDays,Round[] rounds,String startdate,String endDate,Boolean numOfTeamsIsFixed,int numOfTeams,Boolean isOpenRegisteration,Boolean isActive,Team[] registeredTeams){
        this.name=name;
        this.game=game;
        this.numOfDays=numOfDays;
        this.rounds=rounds;
        this.startdate=startdate;
        this.endDate=endDate;
        this.numOfTeamsIsFixed=numOfTeamsIsFixed;
        this.numOfTeams=numOfTeams;
        this.isOpenRegisteration=isOpenRegisteration;
        this.isActive=isActive;
        this.registeredTeams=registeredTeams;

    }

        // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
        private String status;
        public  String getStatus() {
            if(isOpenRegisteration) return "Open for registeration";
            else return "Closed";
        }


}
