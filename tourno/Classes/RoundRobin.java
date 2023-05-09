package Classes;

import java.util.ArrayList;

public class RoundRobin extends Tournament {

    public RoundRobin(String name, Game game, int numOfDays, ArrayList<Round> rounds, String startdate, String endDate,
            Boolean numOfTeamsIsFixed, int numOfTeams,int numOfMembers, Boolean isOpenRegisteration, Boolean isActive, Boolean isCompleted, ArrayList<Team> registeredTeams) {
        super(name, game, numOfDays, rounds, startdate, endDate, numOfTeamsIsFixed, numOfTeams,numOfMembers, isOpenRegisteration, isActive,  isCompleted,
                registeredTeams);
        // TODO Auto-generated constructor stub
    }

    // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
    String type;
    public String getType() {
        return "Round Robin";
    }

}