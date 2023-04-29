package Classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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



        // to be completed: ... .

        // this.allParicipants = allParicipants
        // this.allTournaments = allTournaments

    }
    
    // this method is created for adding new which will just open the .dat file and append an object of type game
    // and then saved it in the file.
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

    public void deleteTournament(Tournament tournament) throws FileNotFoundException, IOException, ClassNotFoundException{
        int j = 0;
        try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
       for(Tournament s: list){
        if(s.getName().equals(tournament.getName()))
        break;
        else
        j++;
       }
     }
       allTournaments.remove(j);
       try {
        FileOutputStream fileOut = new FileOutputStream("savedTournaments.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(allTournaments);
        out.close();
        fileOut.close();
    } catch (IOException i) {
        i.printStackTrace();}
    }



       public void editTournament(Tournament tournament,Tournament tournament2) throws FileNotFoundException, IOException, ClassNotFoundException{
        int j = 0;
        try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
       for(Tournament s: list){
        if(s.getName().equals(tournament.getName())){
        break;
    }
        else
        j++;
       }
    }
       allTournaments.remove(j);
       allTournaments.add(j,tournament2);
       try {
        FileOutputStream fileOut = new FileOutputStream("savedTournaments.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(allTournaments);
        out.close();
        fileOut.close();
    } catch (IOException i) {
        i.printStackTrace();
    }
       }
    }

 
