public class BjHand extends Hand{
    
    private int sum;
    private boolean acesUsed;
    private int numAces;
    
    public BjHand(){
        super(2);
        this.sum = 0;
        this.acesUsed = false;
        this.numAces = 0;
    }
    
    public void setCards(int a, int b){
        this.hand[a] = b;
    }
    
    public void setSum(int s){
        this.sum = s;
    }
    public void setAU(boolean a){
        this.acesUsed = a;
    }
    public void setNAces(int n){
        this.numAces = n;
    }
    
    public int getSum(){
        return this.sum;
    }
    public boolean getAU(){
        return this.acesUsed;
    }
    public int getNAces(){
        return this.numAces;
    }
}