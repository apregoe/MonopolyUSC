package MonopolyGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SinglePlayer implements Serializable{

    private GamePawn gamePawn;
    private final static long SerialVersionUID = -1290902921;
    private int money;
    private int x;
    private int y;
    private List<MonopolyObject> purchases;
    String username;
    private int turnsLeftInJail;
    private boolean inJail;
    public SinglePlayer(GamePawn gamePawn, String username, int money){
        this.username = username;
        this.gamePawn = gamePawn;
        this.money = money;
        purchases = new ArrayList<MonopolyObject>();
        this.x = 10;
        this.y = 10;
    }

    public void setCoords( int x, int y ) {
        this.x = x;
        this.y = y;
        gamePawn.setCoords( x, y );
    }

    public GamePawn getPawn(){
        return gamePawn;
    }

    public void releaseProperties(){ //to be called when player loses
        for(int i = 0; i < purchases.size(); i++){
            if(purchases.get(i) instanceof Property) {
                Property p = (Property) purchases.get(i);
                p.setOwnerStatus(false);
                p.setOwner(null);
            }else{
                Road r = (Road) purchases.get(i);
                r.setOwnerStatus(false);
                r.setOwner(null);
            }
        }
    }

    public int getMoney(){
        return money;
    }

    public void setInJail(){ inJail = true;
        turnsLeftInJail = 2;
    }

    public boolean takeTurnFromJail(){
        if(inJail) {
            if (turnsLeftInJail > 0) {
                turnsLeftInJail--;
                return true;
            }
        }

        return false;//if not in jail, return false
    }

    public int getX(){
        return x;
    }
    public int getY(){return y;}
    public int getAccountAmount() {
        return money;
    }

    public void purchase( MonopolyObject mo ) {
        if ( mo instanceof Property || mo instanceof Road ) {
            if (!purchases.contains(mo)) {
                purchases.add(mo);
                deductFromAccount(mo.getValue());
            }
        }
    }

    public void addToAccount( int amount ) {
        money += amount;
    }

    public void deductFromAccount( int amount ) {
        money -= amount;
    }

}
