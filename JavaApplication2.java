import java.util.Scanner;

public class JavaApplication2 {
    private static ShowCards sc = new ShowCards();
    private static String winner = "";
    public void main() {
        Scanner scan = new Scanner(System.in);
        
        int choice;
        
        
        
        System.out.println("WELCOME TO BLACKJACK");
        System.out.println("WOULD YOU LIKE TO PLAY A GAME?");
        System.out.println("(1)Yes or (0)No");
        choice = Integer.parseInt(scan.nextLine());
        

        if(choice == 1){
            blackJack();
        }
            
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void blackJack(){
        Scanner scan = new Scanner(System.in);
        
        final int WIN = 21; // condition for winning total
        final int HSIZE = 2; // constant for defining hand array size
        int c = 0; // counter for the deck
        Deck deck = new Deck(); // the deck (Duh)
        BjHand player = new BjHand();  // the player's hand object
        BjHand dealer = new BjHand();  // the dealer's hand object
        int choice = 1; // defining the choice variable with a value
        
        // shuffleing the deck seven times
        for (int i = 0; i < 7; i++) {
            deck.shuffle();
        }
        
        // dealing out every other integer to the player hands
        for (int i = 0; i < (HSIZE * 2); i++) {
            if(i%2 == 0){
                player.setCards(i/2, c);
                c++;
                
            }
            else if(i%2 == 1){
                dealer.setCards(i/2, c);
                c++;
                
            }
            
        }
        
        
        // calling the functions to out put the intitial dealings
        showTable(player, dealer, deck);
        
        // adding the cards to get the sum of the player and dealer cards
        addCards(player, deck);
        addCards(dealer, deck);
        
        // this is the codition to test if the player recieved 21
        if(player.getSum() == WIN){
            System.out.println("YOU HAVE 21");
            System.out.println("YOU WIN");
        }
        // this is what executes otherwise
        else{

            while(choice == 1){
                // first we output the sums of the cards
                // then we ask for the user to tell wether they want to
                // hit or stay
                System.out.println("Your Sum : " + player.getSum());
                System.out.println("WOULD YOU LIKE TO HIT");
                System.out.println("(1)Yes or (0)No");
                choice = Integer.parseInt(scan.nextLine());

                // this calls the hit function
                if(choice == 1){
                    hit(player, deck, c);
                    c++;
                }
                // tests for 21
                if(player.getSum() == 21){
                    System.out.println("YOU HAVE 21");
                    System.out.println("YOU WIN");
                    choice = 0;
                    winner = "Player";
                }
                // if the total is greater than 21 then the ace manage function
                // which will make the aces 1 if needed
                else if(player.getSum() > WIN){
                    aceManage(player, deck);
                    if(player.getSum() > WIN){
                        System.out.println("BUST");
                        choice = 0;
                        winner = "Dealer";
                    }
                }
            }

            // these blocks handle the dealer funtionality
            // first it tested to to see if the total is less than 17
            // if it is then it calls hit recursively to increment the dealer's total 
            // once the total is greater than 17 or busted then this bloc stops

            if(dealer.getSum() < 17){
                while(dealer.getSum() < 17){
                    hit(dealer, deck, c);
                    c++;
                    System.out.println(dealer.getSum());
                    if(dealer.getSum() > WIN){
                        System.out.println("BUST");
                    }
                    
                }
            }
            else{
                System.out.println(dealer.getSum());
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void showTable(BjHand a, BjHand b, Deck d){
        System.out.println("DEALER CARDS"); // title
        d.cards[b.hand[0]].showCard(); // displays the cards
        System.out.println("\n"); 
        sc.display(d.cards[b.hand[0]]); // displays ascii art
//        System.out.println("[FACE-DOWN]\n");
        d.cards[b.hand[1]].showCard(); // displays the card
        System.out.println("\n");
        sc.display(d.cards[b.hand[1]]); // displays ascii art
        
        System.out.println(""); // endl
        
        System.out.println("YOUR CARDS"); // now the player
        for (int i = 0; i < 2; i++) {
            d.cards[a.hand[i]].showCard(); // print card
            System.out.println("\n");
            sc.display(d.cards[a.hand[i]]); // print ascii art
            
            
        }
        System.out.println("");
    }
    
    public static void addCards(BjHand a, Deck d){
        int sum = 0; // sum of card values
        int aces = 0; // number of aces
        for (int i = 0; i < 2; i++) {
            if(d.cards[a.hand[i]].fValue > 10){ // for face cards
                sum += 10; // every face card is ten

            }
            else if(d.cards[a.hand[i]].fValue == 1){ // aces
                sum += 11; // aces are 11
                aces++; // increments the count of aces
            }
            else { // if normal card, adds face value
                sum += d.cards[a.hand[i]].fValue; 
            }
        }
        
        if(aces == 2){ // if someone has two aces sum would be 22, must make 1 = 1
            sum-=10;
        }
        
        a.setNAces(aces); // set values to the BjHand fields
        a.setSum(sum);
    }
    
    public static void hit(BjHand a, Deck d, int c){
        
        int sum = a.getSum(); // creates a sum variable
        int aces = 0; // number of aces
        boolean bust = false; // bust boolean
        boolean done = false; // sentinel
        
        
        d.cards[c].showCard();// card name
        System.out.println("\n");
        sc.display(d.cards[c]);// ascii art
        System.out.println("");
        
        if(d.cards[c].fValue > 10){
            sum += 10; // every face card is a ten
        }
        else{
            if(d.cards[c].face.equals("Ace")){
                sum += 11; // aces are eleven
                aces ++; // number of aces go up
            }
            else{
                sum += d.cards[c].fValue; // normal face value
            }
        }
            
        a.setNAces(a.getNAces() + aces); // increments the number of aces in the hand
        a.setSum(sum); // sets the sum in the hand
    }
    
    public static void aceManage(BjHand a, Deck d){
        
        // this method handles the player sum if the total is
        // greater than 21
        // it will set aces to 1 in coordination with the number
        int aces = a.getNAces();
        int sum = a.getSum();
        boolean safe = false;
        
        while(!safe){
            if(aces > 0){
                sum -= 10;
                if(sum < 21){
                    safe = true;
                }
                aces--;
            }
            else{
                safe = true;
            }
        }
        
        a.setSum(sum);
        a.setNAces(aces);
    }
    public String getWinner(){
      return winner;
    }
}