

/**
 *
 * @author ncc
 */
public class Card {
    public Integer fValue;
    public String face;
    public String suit;
    
    public Card(){
        this.face = "0";
        this.suit = "0";
        this.fValue = 0;
    }
    
    public Card(String s, int f){
        this.suit = s;
        this.fValue = f;
        if(this.fValue == 1){
            this.face = "Ace";
        }
        else if(this.fValue <= 10){
                this.face = String.format("%d", this.fValue);
        }
        else if(this.fValue > 10){
            switch(this.fValue){
                case 11:
                    this.face = "Jack";
                    break;
                case 12:
                    this.face = "Queen";
                    break;
                case 13:
                    this.face = "King";
                    break;
                default:
                    this.face = "ASSINGMENT ERROR";
            }
        }
    }
    
    public void setFValue(int f){
        this.fValue = f;
    }
    
    public void setSuit(String s){
        this.suit = s;

    }
    
    public void setFace(String f){
        this.face = f;
    }
    
    public Integer getFValue(){
        return this.fValue;
    }
    
    public String getSuit(){
        return this.suit;
    }
    public String getFace(){
        return this.face;
    }
    
    public void showCard(){
        System.out.print("[" + this.face + "->" + this.suit + "]");
    }
    
}