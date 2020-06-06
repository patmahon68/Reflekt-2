//The bullet class holds the stats and functionality of bullets that the player fires
//velocity, position, and other info are stored here
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Bullet {
    private double velX,velY,x,y;
    private Polygon hitbox;//must be a polygon because angled bullets can not be accurately surrounded by a rectangular hitbox
    private Image pic;
    private String dir;
    private int numOfBounces;
    public Bullet(String dir,int px, int py, double speedmod, Image bPic){
        //speedMod can be increased with powerups to make bullets move faster
        this.dir=dir;
        numOfBounces=0;
        //depending on which way the bullet is fired, its starting position, velocity, and shape of its hitbox varies
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
        if (direction=="L/R"){//horizontal movement and vertical movement are done separately
            x+=velX;
        }
        else{
            y+=velY;
        }
        setHitbox();//refresh after moving

    }

    public void bounce(String direction, ArrayList<Image> bPics){
        if (direction=="L/R"){
            x+=-velX;//bounce back
            velX=-velX;//reverse direction
            if (dir=="right"){//change the frame of the bullet so it now points
                //in the new direction it is moving
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
        else{//same as horizontal bounce
            y+=-velY;//bounce opposite way
            velY=-velY;//reverse direction
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
        //each direction has a different shaped hitbox
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
    }//check for collision

    //accessor methods
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
