//The block class holds the information and functionality of the
//moving block in maps 0 and 3 (hitbox, location, etc)
import java.awt.*;

public class Block {

    private int x,y,vel,counter;//location, speed, counter for when to change direction
    private Rectangle hitbox;
    boolean moving;//in motion

    public Block(){
        x=447;//starting location on the map
        y=159;
        hitbox=new Rectangle(447,159,130,130);
        vel=-1;//start with upward velocity so it switches it and moves down during the
        //first movement iteration
        moving=false;//starts stationary
        counter=0;
    }

    public void move(){
        if (moving){//move gets called regardless of if the block is moving, but its location
            //only changes if it is
            y+=vel;
        }
        //refresh hitbox
        hitbox=new Rectangle(x,y,130,130);
    }
    //accessors

    public Rectangle getHitbox(){return hitbox;}
    public boolean isMoving(){return moving;}
    public void setMoving(boolean b){moving=b;}
    public void setCounter(int n){counter=n;}
    public int getCounter(){return counter;}
    public void setVel(int n){vel=n;}
    public int getVel(){return vel;}
    public int getX(){return x;}
    public int getY(){return y;}

}
