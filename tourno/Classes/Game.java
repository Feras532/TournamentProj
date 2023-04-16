package Classes;
public class Game {
  String gameName;
  Boolean isTeamGame;
  int minTeamSize;
  int maxTeamSize;  

  public Game(String gameName,Boolean isTeamGame,int minTeamSize,int maxTeamSize  ){
    this.gameName=gameName;
    this.isTeamGame=isTeamGame;
    this.minTeamSize=minTeamSize;
    this.maxTeamSize=maxTeamSize;
  }

  //Getters
  public String getGameName(){
    return gameName;
  }
}
