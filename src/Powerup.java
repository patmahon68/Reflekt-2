//The powerup class holds the information for items that spawn around the map
//items can move and be picked up, and this class holds the methods to do so
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Powerup {
    private Rectangle hitbox,bottomBox;//hitbox for being picked up, bottom box for determining when on ground
    private String name;
    private double vely;//can only move vertically
    private int x,y;
    private boolean onGround;
    private Image picture;
    public Powerup(String name, int x, int y, Image img){//need a name and picture for an item to be created
        this.x=x;
        this.y=y;
        this.name=name;
        vely=0;
        hitbox=new Rectangle(x,y,25,35);//create the hitboxes
        bottomBox=new Rectangle(x,y+25,25,10);
        onGround=false;
        picture=img;
        picture = picture.getScaledInstance(25, 35, Image.SCALE_SMOOTH);
    }

    public void move(){
        vely=Math.min(vely+0.2,7);//apply gravity when falling
        y+=vely;
    }

    //accessors

    public Rectangle getHitbox(){
        return hitbox;
    }
    public void setHitbox(Rectangle r){hitbox=r;}
    public Rectangle getBottomBox(){return bottomBox;}
    public void setBottomBox(Rectangle r){bottomBox=r;}
    public String getName(){return name;}
    public Image getPicture(){return picture;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void setY(int n){y=n;}
    public void setVely(double d){vely=d;}
    public boolean isOnGround(){return onGround;}
    public void setOnGround(boolean b){onGround=b;}
}
