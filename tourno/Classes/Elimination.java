package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elimination extends Tournament {

    public Elimination(String name, Game game, int numOfDays, ArrayList<Round> rounds, String startdate, String endDate,
            Boolean numOfTeamsIsFixed, int numOfTeams, int numOfMembers, Boolean isOpenRegisteration, Boolean isActive,
            ArrayList<Team> registeredTeams) {
        super(name, game, numOfDays, rounds, startdate, endDate, numOfTeamsIsFixed, numOfTeams,numOfMembers, isOpenRegisteration,
                isActive,
                registeredTeams);
        // TODO Auto-generated constructor stub
    }

    public Elimination() {
        super();
    }

    public void generateMatches() {
        int n = registeredTeams.size();
        int r = 0;
        if (n < 2 || registeredTeams == null) {
            System.out.println("Teams number is insuffecient");
        } else {
            /// Generating Rounds
            while (n > Math.pow(2, r)) {
                r = r + 1;
            }

            for (int i = 0; i < r; i++) {
                rounds.add(new Round());
            }
            int matchesInInitialRound = (int) (n - Math.pow(2, r - 1));

            // Filling rounds with matches from last to second
            int z = 0;
            int x = r;
            do {
                ArrayList<Match> roundMatches = rounds.get(x - 1).getMatches();
                for (int i = 0; i < Math.pow(2, z); i++) {
                    roundMatches.add(new Match());
                }
                z++;
                x--;
            } while (x > 1);

            if (r > 1) {
                ArrayList<Match> roundMatches = rounds.get(0).getMatches();
                for (int i = 0; i < matchesInInitialRound; i++) {
                    roundMatches.add(new Match());
                }
            }
            //// Filling Matches with teams
            List<Team> teamsForMatch = new ArrayList<Team>(registeredTeams);
            Collections.shuffle(teamsForMatch);
            /// Filling first round
            ArrayList<Match> firstRoundMatches = rounds.get(0).getMatches();
            for (Match match : firstRoundMatches) {
                match.setTeams(teamsForMatch.get(0), teamsForMatch.get(1));
                teamsForMatch.remove(0);
                teamsForMatch.remove(0);
            }

            /// Filling second round
            if (teamsForMatch.size() > 0) {
                ArrayList<Match> secondRoundMatches = rounds.get(1).getMatches();
                for (Match match : secondRoundMatches) {
                    if (teamsForMatch.size() == 0) {
                        break;
                    }
                    match.setTeam2(teamsForMatch.get(0));
                    teamsForMatch.remove(0);
                    if (teamsForMatch.size() == 0) {
                        break;
                    }
                    match.setTeam1(teamsForMatch.get(0));
                    teamsForMatch.remove(0);
                }
                Collections.reverse(secondRoundMatches);
            }
        }
    }

    // BROTHER DO NOT TOUCH THIS OR I WILL EAT YOU !!
    String type;

    public String getType() {
        return "Elimination";
    }

    // print all matches by rounds
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

    public void promoteWinner(Team winnerTeam) {
        int currentRoundIndex = -1;
        int currentMatchIndex = -1;
        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            ArrayList<Match> matches = round.getMatches();
            for (int j = 0; j < matches.size(); j++) {
                Match match = matches.get(j);
                if (match.getTeam1() == winnerTeam || match.getTeam2() == winnerTeam) {
                    currentRoundIndex = i;
                    currentMatchIndex = j;
                    break;
                }
            }
        }
        if (currentRoundIndex + 1 < rounds.size()) {
            Round currentRound = rounds.get(currentRoundIndex);
            Round nextRound = rounds.get(currentRoundIndex + 1);
            ArrayList<Match> currentRoundMatches = currentRound.getMatches();
            ArrayList<Match> nextRoundMatches = nextRound.getMatches();
            int nextMatchIndex = currentMatchIndex / 2;
            Match nextMatch = nextRoundMatches.get(nextMatchIndex);
            // if (nextMatch.getTeam1().getNameString().equals("TBA")
                    // || nextMatch.getTeam2().getNameString().equals("TBA")) {
                if (currentMatchIndex % 2 == 0)
                    nextMatch.setTeam1(winnerTeam);
                else
                    nextMatch.setTeam2(winnerTeam);
            // }
            nextRoundMatches.set(nextMatchIndex, nextMatch);
            nextRound.setMatches(nextRoundMatches);
            rounds.set(currentRoundIndex + 1, nextRound);
        }
    }

}