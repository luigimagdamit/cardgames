import java.util.Scanner;

class Manager {
  GameRecords gr = new GameRecords();
  String[]players = {"Player 1", "Player 2"};
  // create new game records array for file input/outout 
  public void main() {
    try{
      gr.loadRecords();
    }catch(Exception e){
      System.out.println("Records file does not exist");
    }
    // try-catch to attempt to open 
    boolean continueGames = true;
    String gameChoice = "";
    Scanner sc = new Scanner(System.in);
    
    while(continueGames){
      // application while loop to determine which action to activate
      System.out.println("Blackjack(0) or War(1) or View Records(2)?");
      gameChoice = sc.nextLine();
      if(gameChoice.equals("0")){
        blackjack();

      }
      else if(gameChoice.equals("1")){
        warGame();

      }
      else if(gameChoice.equals("2")){
        try{
          gr.printRecord();
        }catch(Exception e){
          System.out.println("Records file does not exist");
        }
      }
      else{
        System.out.println("Please choose a valid answer");
      }
    }
    blackjack();
  }
  public void warGame(){
    WarManager manager = new WarManager();
    // create a war game instance
    while(!manager.checkTurn()){
      manager.newTurn();
      // check if game is finished
    }
    manager.gameComplete();
    // call function to assign the winner
    gr.createRecord(players, "War", manager.getWinner());
    // add record to local Game Record
    gr.saveRecords();
    // save the local game records object to serialized file
    // serialized file allows objects to be saved
  } 
  public void blackjack(){
    JavaApplication2 ja = new JavaApplication2();
    ja.main();
    gr.createRecord(players, "Blackjack", ja.getWinner());
    gr.saveRecords();
  }
}