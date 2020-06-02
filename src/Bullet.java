import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Bullet {
    private double velX,velY,x,y;
    private Polygon hitbox;
    private Image pic;
    private String dir;
    private int numOfBounces;
    public Bullet(String dir,int px, int py, double speedmod, Image bPic){
        this.dir=dir;
        numOfBounces=0;
        if (dir=="right"){
            x=px+15;
            y=py+7;
            velX=speedmod;
            velY=0;
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+40,(int)x+40},new int[]{(int)y,(int)y+15,(int)y+15,(int)y},4);
        }
        else if (dir=="left"){
            x=px;
            y=py+7;
            velX=-speedmod;
            velY=0;
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+40,(int)x+40},new int[]{(int)y,(int)y+15,(int)y+15,(int)y},4);
        }
        else if (dir=="up"){
            x=px;
            y=py-10;
            velX=0;
            velY=-speedmod;
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+15,(int)x+15},new int[]{(int)y,(int)y+40,(int)y+40,(int)y},4);
        }
        else if (dir=="down"){
            x=px;
            y=py+35;
            velX=0;
            velY=speedmod;
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+15,(int)x+15},new int[]{(int)y,(int)y+40,(int)y+40,(int)y},4);
        }
        else if (dir=="up/right"){
            x=px+15;
            y=py;
            velX=speedmod*Math.cos(45);
            velY=-speedmod*Math.cos(45);
            hitbox=new Polygon(new int[]{(int)x,(int)x+10,(int)x+40,(int)x+30},new int[]{(int)y-10,(int)y,(int)y-30,(int)y-40},4);
        }
        else if (dir=="down/right"){
            x=px+15;
            y=py+35;
            velX=speedmod*Math.cos(45);
            velY=speedmod*Math.cos(45);
            hitbox=new Polygon(new int[]{(int)x,(int)x+10,(int)x+40,(int)x+30},new int[]{(int)y+10,(int)y,(int)y+30,(int)y+40},4);
        }
        else if (dir=="up/left"){
            x=px;
            y=py;
            velX=-speedmod*Math.cos(45);
            velY=-speedmod*Math.cos(45);
            hitbox=new Polygon(new int[]{(int)x,(int)x-10,(int)x-40,(int)x-30},new int[]{(int)y-10,(int)y,(int)y-30,(int)y-40},4);
        }
        else if (dir=="down/left"){
            x=px;
            y=py+35;
            velX=-speedmod*Math.cos(45);
            velY=speedmod*Math.cos(45);
            hitbox=new Polygon(new int[]{(int)x,(int)x-10,(int)x-40,(int)x-30},new int[]{(int)y+10,(int)y+30,(int)y+40,(int)y},4);
        }

        pic=bPic;
    }

    public void move(String direction){
        if (direction=="L/R"){
            x+=velX;
        }
        else{
            y+=velY;
        }
        setHitbox();

    }

    public void bounce(String direction, ArrayList<Image> bPics){
        if (direction=="L/R"){
            x+=-velX;
            velX=-velX;
            if (dir=="right"){
                dir="left";
                pic=bPics.get(3);
            }
            else if (dir=="left"){
                dir="right";
                pic=bPics.get(4);
            }
            else if (dir=="up/right"){
                dir="up/left";
                pic=bPics.get(6);
            }
            else if (dir=="down/right"){
                dir="down/left";
                pic=bPics.get(1);
            }
            else if (dir=="up/left"){
                dir="up/right";
                pic=bPics.get(7);
            }
            else if (dir=="down/left"){
                dir="down/right";
                pic=bPics.get(2);
            }
        }
        else{
            y+=-velY;
            velY=-velY;
            if (dir=="up"){
                dir="down";
                pic=bPics.get(0);
            }
            else if (dir=="down"){
                dir="up";
                pic=bPics.get(5);
            }
            else if (dir=="up/right"){
                dir="down/right";
                pic=bPics.get(2);
            }
            else if (dir=="down/right"){
                dir="up/right";
                pic=bPics.get(7);
            }
            else if (dir=="up/left"){
                dir="down/left";
                pic=bPics.get(1);
            }
            else if (dir=="down/left"){
                dir="up/left";
                pic=bPics.get(6);
            }
        }
    }

    public void setHitbox(){
        if (dir=="right"){
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+40,(int)x+40},new int[]{(int)y,(int)y+15,(int)y+15,(int)y},4);
        }
        if (dir=="left"){
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+40,(int)x+40},new int[]{(int)y,(int)y+15,(int)y+15,(int)y},4);
        }
        if (dir=="up"){
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+15,(int)x+15},new int[]{(int)y,(int)y+40,(int)y+40,(int)y},4);
        }
        if (dir=="down"){
            hitbox=new Polygon(new int[]{(int)x,(int)x,(int)x+15,(int)x+15},new int[]{(int)y,(int)y+40,(int)y+40,(int)y},4);
        }
        if (dir=="up/right"){
            hitbox=new Polygon(new int[]{(int)x,(int)x+10,(int)x+40,(int)x+30},new int[]{(int)y-10,(int)y,(int)y-30,(int)y-40},4);
        }
        if (dir=="down/right"){
            hitbox=new Polygon(new int[]{(int)x,(int)x+10,(int)x+40,(int)x+30},new int[]{(int)y+10,(int)y,(int)y+30,(int)y+40},4);
        }
        if (dir=="up/left"){
            hitbox=new Polygon(new int[]{(int)x,(int)x-10,(int)x-40,(int)x-30},new int[]{(int)y-10,(int)y,(int)y-30,(int)y-40},4);
        }
        if (dir=="down/left"){
            hitbox=new Polygon(new int[]{(int)x,(int)x-10,(int)x-40,(int)x-30},new int[]{(int)y+10,(int)y,(int)y+30,(int)y+40},4);
        }
    }

    public Boolean isColliding(Rectangle r){
        return hitbox.intersects(r);
    }
    public Boolean isColliding(Area a){
        a.intersect(new Area(hitbox));
        return a!=null;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setY(double n){y=n;}
    public void setX(double n){x=n;}
    public Image getPic(){return pic;}
    public void setNumOfBounces(int n){numOfBounces=n;}
    public int getNumOfBounces(){return numOfBounces;}
    public Polygon getHitbox(){return hitbox;}
    public void setPic(Image bPic){pic=bPic;}
    public String getDir(){return dir;}
}
