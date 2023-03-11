
import java.util.Random;

/**
 *
 * @author ncc
 */
public class Deck {
    
    // Static variables that will be used in deck creation
    public static final int NCARDS = 52; // number of cards in a deck
    public static final int NSUITS = 4; // number of suits
    
    
    // The suits abreviations that will be added to the cards
    public static String suits[] = {"Sp", "Cl", "He", "Di"}; 
    
    // The array of card objects that will create the deck
    public Card cards[];
    
    // Deck Constructor
    public Deck(){
        this.cards = new Card[NCARDS];
        for (int i = 0; i < NCARDS; i++) {
            this.cards[i] = new Card();
        }
        for (int i = 0; i < NSUITS; i++) {
            for (int j = 0; j < NCARDS/NSUITS; j++) {
                this.cards[j+(i*(NCARDS/NSUITS))] = new Card(suits[i], (j+1));
            }
        }
    }
    
    public Card[] getCards(){
        return this.cards;
    }
    
    public void showDeck(){
        for (int i = 0; i < NCARDS; i++) {
            System.out.println(this.cards[i].face + "->" + this.cards[i].suit);
        }
    }
    public void shuffle(){
        Random rand = new Random();
        Card str;
        int j;
        for(int i = 0; i<NCARDS; i++){
            j = rand.nextInt(NCARDS);
            str = this.cards[j];
            this.cards[j] = this.cards[i];
            this.cards[i] = str;
        }
    }
}