import org.w3c.dom.css.Rect;

import java.awt.*;

public class Powerup {
    private Rectangle hitbox,bottomBox;
    private String name;
    private double vely;
    private int x,y;
    private boolean onGround;
    private Image picture;
    public Powerup(String name, int x, int y, Image img){
        this.x=x;
        this.y=y;
        this.name=name;
        vely=0;
        hitbox=new Rectangle(x,y,25,35);
        bottomBox=new Rectangle(x,y+25,25,10);
        onGround=false;
        picture=img;
        picture = picture.getScaledInstance(25, 35, Image.SCALE_SMOOTH);
    }

    public void move(){
        vely=Math.min(vely+0.2,7);
        y+=vely;
    }

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
