package Classes;
public class Game implements java.io.Serializable{
  String name;
  Boolean isTeamGame;
  int minTeamSize;
  int maxTeamSize;  

  public String getName() {
    return name;
  }

  public Boolean getIsTeamGame() {
    return isTeamGame;
  }

  public void setIsTeamGame(Boolean isTeamGame) {
    this.isTeamGame = isTeamGame;
  }

  public int getMinTeamSize() {
    return minTeamSize;
  }

  public void setMinTeamSize(int minTeamSize) {
    this.minTeamSize = minTeamSize;
  }

  public int getMaxTeamSize() {
    return maxTeamSize;
  }

  public void setMaxTeamSize(int maxTeamSize) {
    this.maxTeamSize = maxTeamSize;
  }

  public Game(String name,Boolean isTeamGame,int minTeamSize,int maxTeamSize  ){
    this.name=name;
    this.isTeamGame=isTeamGame;
    this.minTeamSize=minTeamSize;
    this.maxTeamSize=maxTeamSize;
  }

  
}
