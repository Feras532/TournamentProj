import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;

import Classes.Elimination;
import Classes.Paricipant;
import Classes.Team;
import Classes.Tournament;

public class testRanks {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        FileInputStream fileInputStream = new FileInputStream("savedTournaments.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        List<Tournament> tournaments = (List<Tournament>) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        System.out.print("Enter the name of the tournament: ");
        String tournamentName = scanner.nextLine();

        Tournament tournament = null;
        for (Tournament t : tournaments) {
            if (t.getName().equals(tournamentName)) {
                tournament = t;
                break;
            }
        }

        if (tournament != null) {
            for (Team team : tournament.getRegisteredTeams()) {
                System.out.println(" team: " + team.getNameString());

                String rank = ((Elimination) tournament).getRank(team);
                System.out.println("The rank of " + team.getNameString() + " in " + tournament.getName() + " is " + rank);

            }
        } else {
            System.out.println("The tournament " + tournamentName + " is not found in the .dat file.");
        }

        scanner.close();

        // FileInputStream fileInputStream = new
        // FileInputStream("savedParticipants.dat");
        // ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // List<Paricipant> players = (List<Paricipant>) objectInputStream.readObject();

        // objectInputStream.close();
        // fileInputStream.close();

        // System.out.print("Enter the name of the ID: ");
        // String player = scanner.nextLine();
        // Paricipant foundPlayer = new Paricipant(player, player, player, player);
        // Paricipant tournament = null;
        // for (Paricipant t : players) {
        // if (t.getUserID().equals(player)) {
        // System.out.println("Player is found");
        // foundPlayer = t;
        // break;
        // }
        // }

        // System.out.println( foundPlayer.getHistoryTournaments());
        // for(Tournament tours : foundPlayer.getHistoryTournaments())
        // System.out.println(tours.getName());

        // scanner.close();
    }
}