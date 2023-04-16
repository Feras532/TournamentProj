package Classes;
public class SystemData {
    Paricipant[] allParicipants;
    Game[] allGames;
    Tournament[] alltTournaments;

    public SystemData(Paricipant[] allParicipants,Game[] allGames,Tournament[] alltTournaments){
        this.allParicipants=allParicipants;
        this.allGames=allGames;
        this.alltTournaments=alltTournaments;
    }
}
