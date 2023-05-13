package Classes;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void generateMatches() {
        int n = registeredTeams.size();
        boolean hasDummyTeam = false;
        ArrayList<Team> teams = new ArrayList<Team>(registeredTeams);

        if (n % 2 != 0) {
            teams.add(new Team(null, null, "Dummy Team", n, n, n, n, n, n, n)); // Add a dummy team if n is odd
            hasDummyTeam = true;
        }
        int numOfTeams = teams.size();

        for (int currentRound = 0; currentRound < numOfTeams - 1; currentRound++) {
            rounds.add(new Round());
            ArrayList<Match> roundMatches = rounds.get(currentRound).getMatches();

            for (int currentMatch = 0; currentMatch < numOfTeams / 2; currentMatch++) {
                int team1 = (currentRound + currentMatch) % (numOfTeams - 1);
                int team2 = (numOfTeams - 1 - currentMatch + currentRound) % (numOfTeams - 1);

                if (currentMatch == 0) {
                    // Fix the first team in the first match of each round
                    team2 = numOfTeams - 1;
                }
                if (hasDummyTeam && (teams.get(team1).getNameString().equals("Dummy Team")) || 
                teams.get(team2).getNameString().equals("Dummy Team")) {
                    // Skip the match that has the dummy team
                    continue;
                }
                roundMatches.add(new Match(teams.get(team1), teams.get(team2)));
            }

        }
        System.out.println(teams);
        if(hasDummyTeam)
            teams.remove(teams.size()-1);
        System.out.println(teams);

    }
    public void printMe() {
        int i = 1;
        for (Round round : rounds) {
            System.out.print("Round " + i);
            i++;
            ArrayList<Match> matches = round.getMatches();
            System.out.println(" --> " + matches.size() + " matches");
            for (Match match : matches) {
                System.out.println(match);
            }

        }
    }
    public void updateTeamsRank() {
        sortTeamsByPointsAndGoalDifference(registeredTeams);
        int number;
        int lastDigit;
        String suffix;

        for (int i = 0; i < registeredTeams.size(); i++) {
            number = i+1;
            lastDigit = number%10;
            if(lastDigit == 1){
                suffix = "st";
            }
            else if (lastDigit == 2){
                suffix = "nd";
            }
            else if (lastDigit == 3){
                suffix = "rd";
            }
            else{
                suffix = "th";
            }
            registeredTeams.get(i).setRank(lastDigit+suffix);
            System.out.println(registeredTeams.get(i).getNameString()+" "+registeredTeams.get(i).getRankString());
        }
    }
    public static ArrayList<Team> sortTeamsByPointsAndGoalDifference(ArrayList<Team> teams) {
        teams.sort((t1, t2) -> {
            int pointsDiff = t2.getGamePoints() - t1.getGamePoints();
            if (pointsDiff != 0) {
                return pointsDiff;
            } else {
                return t2.getGoalDifference() - t1.getGoalDifference();
            }
        });
        return teams;
    }
}