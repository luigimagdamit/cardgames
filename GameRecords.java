import java.util.ArrayList;
import java.io.Serializable;

public class GameRecords implements Serializable{
  private static final long serialVersionUID = 1L;
  private ArrayList<GameRecord> records= new ArrayList<GameRecord>();
  
  private class GameRecord implements Serializable{
    // GameRecord subclass to allow easier serialization
    private String[] players = new String[2];
    private String game;
    private String winner;
    public GameRecord(String[] players, String game, String winner){
      this.players = players;
      this.game = game;
      this.winner = winner;
    }
  }
  public void addRecord(GameRecord gr){
    records.add(gr);
  }
  public ArrayList<GameRecord> getRecords(){
    return records;
  }
  public void printRecord(){
    for(int i = 0; i< records.size(); i++){
      System.out.println(
        "\nGame #" + (i+1) + ":\t" + records.get(i).game
      );
      System.out.println(
        "Winner:\t" + records.get(i).winner
      );
    }
  }
  public void createRecord(String[] players, String game, String winner){
    GameRecord record = new GameRecord(players, game, winner);
    addRecord(record);
  }
  public void saveRecords(){
    FileManager fm = new FileManager();
    fm.saveRecords(this, "records.ser");
    // passes itself into a function that saves it as serialized file
  }
  public void loadRecords(){
    FileManager fm = new FileManager();
    GameRecords newCopy = fm.getRecords("records.ser");
    records = newCopy.records;
    // load data from existing file and pass it into the object's local arraylist
  }
}