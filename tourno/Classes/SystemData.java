package Classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SystemData {
    ArrayList<Paricipant> allParicipants = new ArrayList<>();
    ArrayList<Game> allgames = new ArrayList<>();
    ArrayList<Tournament> allTournaments = new ArrayList<>();

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
        try {
            FileInputStream fileIn = new FileInputStream("savedParticipants.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Paricipant> paricipants = (ArrayList<Paricipant>) in.readObject();
            in.close();
            fileIn.close();
            this.allParicipants = paricipants;
            System.out.println("Data loaded from savedParticipants.dat");
        } catch (IOException i) {
            System.out.println("saved participants not found...");
            this.allParicipants = loadParicipantsCSV("StudentDetails.csv");
        } catch (ClassNotFoundException c) {
            System.out.println("Participant class not found");
            c.printStackTrace();
        }
        // this.allTournaments = allTournaments

    }

    // this method is created for adding new which will just open the .dat file and
    // append an object of type game
    // and then saved it in the file.
    public void addNewGame(Game game) {
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

    public void addNewTournament(Tournament tournament) {
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

    public void deleteTournament(Tournament tournament)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        int j = 0;
        try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
            for (Tournament s : list) {
                if (s.getName().equals(tournament.getName()))
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
            i.printStackTrace();
        }
    }

    public void editTournament(Tournament tournament, Tournament tournament2)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        int j = 0;
        try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
            for (Tournament s : list) {
                if (s.getName().equals(tournament.getName())) {
                    break;
                } else
                    j++;
            }
        }
        allTournaments.remove(j);
        allTournaments.add(j, tournament2);
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

    

    // check them
    public static Tournament getTournament(String fileName, Tournament searchTournament) {
        ArrayList<Tournament> tournaments = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tournaments = (ArrayList<Tournament>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Tournament tournament : tournaments) {
            if (tournament.equals(searchTournament)) {
                System.out.println("found tournament");

                return tournament;
            }
        }
        return null;
    }


    // used in elimination you can check its functionality.
    public void updateTournament(String fileName, String name, Tournament newTournament) {
        ArrayList<Tournament> tournaments = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tournaments = (ArrayList<Tournament>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        int index = -1;
        for (int i = 0; i < tournaments.size(); i++) {
            if (tournaments.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            tournaments.set(index, newTournament);
        } else {
            System.out.println();
        }
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tournaments);
            oos.close();
            fos.close();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method is created for getting participant from csv file 
    // will just create the .dat file and
    // append an all object of type participant
    public static ArrayList<Paricipant> loadParicipantsCSV(String fileName) {
        ArrayList<Paricipant> allParicipants = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            ///// Skipping first line 
            String [] line = bufferedReader.readLine().split(",");
            ///// Reading Students data and adding them to allParticipants
            for (int i = 0; i < numberOfLines(fileName)-1; i++) { 
                line = bufferedReader.readLine().split(",");
                String userID = line[0];
                String emailString = line[3];
                String lastName = line[4].substring(1).strip();
                String firstName = line[5].substring(0, line[5].length()-1).strip();
                Paricipant paricipant = new Paricipant(userID, firstName, lastName, emailString);
                allParicipants.add(paricipant);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        //// Saving allParicipants to .dat file
        try {
            FileOutputStream fos = new FileOutputStream("savedParticipants.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allParicipants);
            oos.close();
            fos.close();
            System.out.println("Student Details has been loaded from CSV to savedParticipant.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allParicipants;
    }

    public static Paricipant getParticipant(String id) {
        ArrayList<Paricipant> paricipants = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("savedParticipants.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            paricipants = (ArrayList<Paricipant>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Paricipant paricipant : paricipants) {
            if (paricipant.getUserID().equals(id)) {
                System.out.println("found participant");

                return paricipant;
            }
        }
        return null;
    }

    // used in elimination you can check its functionality.
    public static void updateParticipant(Paricipant participant) {
        ArrayList<Paricipant> paricipants = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("savedParticipants.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            paricipants = (ArrayList<Paricipant>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        int index = -1;
        for (int i = 0; i < paricipants.size(); i++) {
            if (paricipants.get(i).getUserID().equals(participant.getUserID())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            paricipants.set(index, participant);
        } else {
            System.out.println();
        }
        try {
            FileOutputStream fos = new FileOutputStream("savedParticipants.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(paricipants);
            oos.close();
            fos.close();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public Boolean idIsVaild(int id) {
    //     ArrayList<Paricipant> paricipants = new ArrayList<>();
    //     try {
    //         FileInputStream fis = new FileInputStream("savedParticipants.dat");
    //         ObjectInputStream ois = new ObjectInputStream(fis);
    //         paricipants = (ArrayList<Paricipant>) ois.readObject();
    //         ois.close();
    //         fis.close();
    //     } catch (IOException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //     for (Paricipant paricipant : paricipants) {
    //         if (paricipant.getUserID().equals(id)) {
    //             System.out.println("found participant");

    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public Boolean participantIsAlreadyRegistered(Paricipant paricipant){
        
    // }

    public ArrayList<Paricipant> getAllParicipants() {
        return allParicipants;
    }

    /// Method that return number of lines in file
    public static int numberOfLines(String fileName) {
        try {
            Path file = Paths.get(fileName);
            long count = Files.lines(file).count();
            return (int) count;
          } catch (Exception e) {
            e.getStackTrace();
            return 0;
          } 
    }
}
