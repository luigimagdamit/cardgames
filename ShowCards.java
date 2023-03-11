public class ShowCards{

  //method that assigns the proper symbol to send to the print function
  void display(Card card){
    String faceSymbol = "";

    if(card.suit.equals("Sp")){
      faceSymbol = "♠";
    }
    if(card.suit.equals("Cl")){
      faceSymbol = "♣";
    }
    if(card.suit.equals("He")){
      faceSymbol = "♥";
    }
    if(card.suit.equals("Di")){
      faceSymbol = "♦";
    }
    //if statements to assign

    if(card.fValue<11){
      this.show(faceSymbol, card.fValue);
    }
    if(card.fValue>=11){
      if(card.fValue==11){
        this.show(faceSymbol, "J");
      }
      if(card.fValue==12){
        this.show(faceSymbol, "Q");
      }
      if(card.fValue==13){
        this.show(faceSymbol, "K");
      }
    }

  }
  void show(String face, Integer value){
    if(value<10){
      System.out.printf(
      " ___________" +
      "\n| %s.........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|.....%s.....|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|_________%s_| %n", 
      value.toString(), 
      face, 
      value.toString()
      );
    }
    else if(value==10){
      System.out.printf(
      " ___________" +
      "\n| %s........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|.....%s.....|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|________%s_| %n", 
      value.toString(), 
      face, 
      value.toString()
      );
    }
  }
  void show(String face, String value){
    System.out.printf(
      " ___________" +
      "\n| %s.........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|.....%s.....|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|...........|"+
      "\n|_________%s_| %n", 
      value.toString(), 
      face, 
      value.toString()
      );
  }
}
