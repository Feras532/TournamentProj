package Classes;
public abstract class Tournament {
    private String name;
    Game game;
    int numOfDays;
    Round[] rounds;
    String startdate;
    String endDate;
    Boolean numOfTeamsIsFixed;
    Boolean isOpenRegisteration;
    Boolean isActive;
    Team[] registeredTeams;

    public Tournament(String name,Game game,int numOfDays,Round[] rounds,String startdate,String endDate,Boolean numOfTeamsIsFixed,Boolean isOpenRegisteration,Boolean isActive,Team[] registeredTeams){
        this.name=name;
        this.game=game;
        this.numOfDays=numOfDays;
        this.rounds=rounds;
        this.startdate=startdate;
        this.endDate=endDate;
        this.numOfTeamsIsFixed=numOfTeamsIsFixed;
        this.isOpenRegisteration=isOpenRegisteration;
        this.isActive=isActive;
        this.registeredTeams=registeredTeams;

    }

    //getters

    public String getName(){
        return name;
    }

    public int numOfDays(){
        return numOfDays;
    }
}
