//The energyball class is a projectile class that holds info about the projectiles
//fired by the turret in maps 2 and 5
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EnergyBall {
    private int x,y,frameCounter;//location and animation counter
    private double velx,vely;
    private Rectangle hitbox;
    private ArrayList<Image> frames;
    private int currentFrame;
    public EnergyBall(Player p){
        loadPics();//get frames
        currentFrame=0;
        x=490;//starting location (where the turret is)
        y=500;
        frameCounter=0;
        hitbox=new Rectangle(x,y,45,45);
        //find the angle the nearest player is to the turret
        double ang=Math.atan2(p.getY()-y,p.getX()-x);
        //and get the corresponding velocities to head in that direction
        velx=Math.cos(ang)*3;
        vely=Math.sin(ang)*3;
    }
    public void loadPics(){
        frames=new ArrayList<>();
        for (int i=1;i<9;i++){
            Image pic=new ImageIcon("energy/energy00"+i+".png").getImage();
            pic=pic.getScaledInstance(45,45,Image.SCALE_SMOOTH);
            frames.add(pic);
        }
    }

    public void move(){
        x+=velx;//move forward
        y+=vely;
        frameCounter+=1;
        if (frameCounter>=3){//increase frame counter to move through the animation
            frameCounter=0;
            currentFrame+=1;
            if (currentFrame==8){
                currentFrame=0;
            }
        }
        hitbox=new Rectangle(x,y,45,45);
    }
    //accessors

    public Rectangle getHitbox(){return hitbox;}
    public int getCurrentFrame(){return currentFrame;}
    public ArrayList<Image> getFrames(){return frames;}
    public int getX(){return x;}
    public int getY(){return y;}
}
