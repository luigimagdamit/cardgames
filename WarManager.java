import java.util.ArrayList;

class WarManager{
  private ArrayList<Card> player1   = new ArrayList<Card>();
  private ArrayList<Card> player2   = new ArrayList<Card>();
  // MAIN PLAYER DECKS
  private ArrayList<Card> p1WarDeck = new ArrayList<Card>();
  private ArrayList<Card> p2WarDeck = new ArrayList<Card>();

  private ShowCards sc = new ShowCards();
  private Deck deck = new Deck();

  private int currentTurn = 0;
  private int warCount = 0;
  private boolean finished = false;
  private String winner;
  // general game management variables

  // =======================CLASS VARIABLES END

  public WarManager(){
    deck.shuffle();
    for(int i = 0; i < 26; i++){
      player1.add(deck.getCards()[i]);
    }
    for(int i = 26; i < 52; i++){
      player2.add(deck.getCards()[i]);
    }// distriibute cards evenly to players
  }
  //=======================CONSTRUCTOR END

  public boolean checkTurn(){
    boolean finished = false;
    if(player1.size() == 0){
      winner = "PLAYER 2";
      finished = true;
    }
    if(player2.size() == 0){
      winner = "PLAYER 1";
      finished = true;
    }
    return finished;
    // check for win-lose condition
  }
  //-----------------------CHECK TURN, CHECK FOR winner

  public void newTurn(){
    currentTurn += 1;
    Card p1Card = player1.get(0);
    Card p2Card = player2.get(0);
    // draw cards from top of the deck
    sc.display(p1Card);
    sc.display(p2Card);
    // call display function to show ASCII
    System.out.println("\n" + p1Card.fValue + " - " + p2Card.fValue);

    if(p1Card.fValue > p2Card.fValue){
      System.out.println("PLAYER ONE wins this turn.");
      moveCards(player1, player2, p1Card, p2Card);
    }
    else if(p1Card.fValue < p2Card.fValue){
      System.out.println("PLAYER TWO wins this turn.");
      moveCards(player2, player1, p2Card, p1Card);
    }
    // compare the card values, check who wins the turn based on face value
    else if(p1Card.fValue == p2Card.fValue){
      p1WarDeck.add(p1Card);
      p2WarDeck.add(p2Card);

      player1.remove(0);
      player2.remove(0);
      war(0 , 0);

      // add cards to war deck, remove them from the top to avoid duplication. call war function.
    }
    announce();

  }
  public void war(int p1, int p2){
    int p1Score = p1;
    int p2Score = p2;
    warCount++;
    System.out.println("War #" + warCount);

    p1WarDeck.add(player1.get(0));
    p2WarDeck.add(player2.get(0));

    for(int i = 0; i < p1WarDeck.size(); i++){
      //add the face values to the total
      p1Score+=p1WarDeck.get(i).fValue;
      p2Score+=p2WarDeck.get(i).fValue;
    }
    sc.display(player1.get(0));
    sc.display(player2.get(0));

      // draw from the players decks and move them to the war decks
    player1.remove(0);
    player2.remove(0);

    if(p1Score > p2Score){
      System.out.println(
        "\nPlayer ONE has won the war!\nP1 WARSCORE\t" +
        p1Score +
        "\nP2 WARSCPRE\t" +
        p2Score
      );
      p1WarDeck.addAll(p2WarDeck);
      player1.addAll(p1WarDeck);

      p1WarDeck.clear();
      p2WarDeck.clear();
    }
    // checks win condition in war, based on which total war value is higher
    else if(p1Score < p2Score){
      System.out.println(
        "\nPlayer TWO has won the war!\nP1 WARSCORE\t" +
        p1Score +
        "\nP2 WARSCPRE\t" +
        p2Score
      );
      p2WarDeck.addAll(p1WarDeck);
      player2.addAll(p2WarDeck);

      p1WarDeck.clear();
      p2WarDeck.clear();
    }
    else if(p1Score==p2Score){
      System.out.println(
        "\nANOTHER WAR OCCURS!\nP1 WARSCORE\t" +
        p1Score +
        "\nP2 WARSCPRE\t" +
        p2Score
      );
      war(p1Score, p2Score);
      // if cards are tied again, call war function recursively
    }
    warCount = 0;
  }
  public void moveCards(ArrayList<Card> deck1, ArrayList<Card> deck2, Card winCard, Card loseCard){
    deck1.add(loseCard);
    deck1.remove(0);
    deck1.add(winCard);
    deck2.remove(0);

    // move the cards from one deck to another; mostly used in war
  }
  public Card drawTopCard(ArrayList<Card> deck){
    return deck.get(0);
  }
  public void announce(){
    System.out.println(
      "\nPLAYER 1 DECK SIZE: " + player1.size() +
      "\nPLAYER 2 DECK SIZE: " + player2.size() + 
      "\nTURN: " + currentTurn
    );
    System.out.println("Press Enter key to continue...");
    try
      {
        System.in.read();
      }  
    catch(Exception e){}  
    // try catch loop continue turns when enter is pressed
  }
  public void gameComplete(){
    System.out.println(winner + " has won the game!");
  }
  public String getWinner(){
    return winner;

    // function to return the winner of the game to the game records class
  }
}