import java.awt.*;

public class Block {

    private int x,y,vel,counter;
    private Rectangle hitbox;
    boolean moving;

    public Block(){
        x=447;
        y=159;
        hitbox=new Rectangle(447,159,130,130);
        vel=-1;
        moving=false;
        counter=0;
    }

    public void move(){
        if (moving){
            y+=vel;
        }
        hitbox=new Rectangle(x,y,130,130);
    }

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
