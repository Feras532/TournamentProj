package Classes;

public class Elimination extends Tournament {
    

    public Elimination(String name, Game game, int numOfDays, Round[] rounds, String startdate, String endDate,
            Boolean numOfTeamsIsFixed, int numOfTeams, Boolean isOpenRegisteration, Boolean isActive, Team[] registeredTeams) {
        super(name, game, numOfDays, rounds, startdate, endDate, numOfTeamsIsFixed,numOfTeams, isOpenRegisteration, isActive,
                registeredTeams);
        // TODO Auto-generated constructor stub
    }


    // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
    String type;
    public String getType() {
        return "Elimination";
    }
    

}