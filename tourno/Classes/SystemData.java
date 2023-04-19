package Classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SystemData {
    ArrayList<Paricipant> allParicipants;
    ArrayList<Game> allgames = new ArrayList<>();
    ArrayList<Tournament> allTournaments=new ArrayList<>();

    public SystemData() {
        try {
            FileInputStream fileIn = new FileInputStream("savedGames.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Game> games = (ArrayList<Game>) in.readObject();
            in.close();
            fileIn.close();
            this.allgames = games;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Game class not found");
            c.printStackTrace();
        }

        try {
            FileInputStream fileIn = new FileInputStream("savedTournaments.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Tournament> tournaments = (ArrayList<Tournament>) in.readObject();
            in.close();
            fileIn.close();
            this.allTournaments = tournaments;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Tournament class not found");
            c.printStackTrace();
        }



    }
    public void addNewGame(Game game){
        allgames.add(game);
        System.out.println("Game is added to the arraylist.");
        try {
            FileOutputStream fileOut = new FileOutputStream("savedGames.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allgames);
            out.close();
            fileOut.close();
            System.out.println("Data saved in games.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void addNewTournament(Tournament tournament){
        allTournaments.add(tournament);
        System.out.println("a tournament is added to the arraylist.");
        try {
            FileOutputStream fileOut = new FileOutputStream("savedTournaments.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allTournaments);
            out.close();
            fileOut.close();
            System.out.println("Data saved in savedTournaments.dat");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}