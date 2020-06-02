import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private double speedX,speedY,defaultSpeed;
    private double jumpMod;
    private int numOfBullets;
    private int lives;
    private int jumpCounter,invincibleCounter;
    private String dir;
    private int x,y;
    private boolean onGround,onWall,wallJumping,invincible;
    private Rectangle hitBox,feetBox,headBox,leftBox,rightBox,shieldHitBox;
    private LinkedList<Bullet> bullets;
    private double bulletSpeedMod;
    private double shieldEnergy;
    private double maxShieldEnergy;
    private double shieldRechargeMod;
    private double shieldRadius;
    private Boolean activeShield;
    private ArrayList<Image> frames;
    private int currentFrame;
    private ArrayList<Image> bulletPics;

    private ArrayList<String> currentPowers;
    private int rightBtn,leftBtn,upBtn,downBtn,shootBtn,blockBtn,jumpBtn;

    public Player(int playerNum){
        loadPics();
        lives=2;
        jumpCounter=0;
        invincibleCounter=0;
        speedX=4;
        speedY=0;
        defaultSpeed=4;
        onGround=false;
        onWall=false;
        activeShield=false;
        shieldRadius=25;
        jumpMod=1;
        numOfBullets=3;
        shieldEnergy=30;
        invincible=false;
        maxShieldEnergy=30;
        shieldRechargeMod=1;
        bulletSpeedMod=1;
        if (playerNum==1){
            x=200;
            y=125;
            rightBtn=KeyEvent.VK_D;
            leftBtn=KeyEvent.VK_A;
            upBtn=KeyEvent.VK_W;
            downBtn=KeyEvent.VK_S;
            shootBtn= KeyEvent.VK_Q;
            blockBtn=KeyEvent.VK_E;
            jumpBtn=KeyEvent.VK_R;
            dir="right";

        }
        else if (playerNum==2){
            x=800;
            y=125;
            rightBtn=KeyEvent.VK_H;
            leftBtn=KeyEvent.VK_F;
            upBtn=KeyEvent.VK_T;
            downBtn=KeyEvent.VK_G;
            shootBtn= KeyEvent.VK_Y;
            blockBtn=KeyEvent.VK_U;
            jumpBtn=KeyEvent.VK_J;
            dir="left";
        }
        hitBox =new Rectangle(x,y,15,40);
        feetBox=new Rectangle(x,y+35,15,5);
        headBox=new Rectangle(x,y-2,15,8);
        leftBox=new Rectangle(x-5,y,5,35);
        rightBox=new Rectangle(x+15,y,5,35);
        shieldHitBox=new Rectangle(x+7-(int)shieldRadius,y+20-(int)shieldRadius,2*(int)shieldRadius,2*(int)shieldRadius);
        bullets=new LinkedList<>();

        shieldRechargeMod=1;
        currentFrame=1;
        currentPowers=new ArrayList<>();
        //addPower("Longer Shield");


    }

    public void loadPics(){
        bulletPics=new ArrayList<>();
        for (int i=1;i<9;i++) {
            Image bulletPic;
            bulletPic = new ImageIcon("Bullet/bullet"+i+".png").getImage();
            if (i==1 || i==6) {
                bulletPic = bulletPic.getScaledInstance(20, 40, Image.SCALE_SMOOTH);
            }
            else if (i==4 || i==5){
                bulletPic = bulletPic.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
            }
            else{
                bulletPic = bulletPic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            }
            bulletPics.add(bulletPic);

        }
    }

    public void move(){
        x+=speedX;
        y+=speedY;
        if (invincible){
            invincibleCounter+=1;
        }
        if (invincibleCounter>=280){
            invincibleCounter=0;
            invincible=false;
        }
    }
    public void jump(){
        speedY=-7;
        onGround=false;
    }

    public void shoot(){
        Image bulletPic =null;
        if (dir=="right"){
            bulletPic=bulletPics.get(4);
        }
        if (dir=="left"){
            bulletPic=bulletPics.get(3);
        }
        if (dir=="up"){
            bulletPic=bulletPics.get(5);
        }
        if (dir=="down"){
            bulletPic=bulletPics.get(0);
        }
        if (dir=="up/right"){
            bulletPic=bulletPics.get(7);
        }
        if (dir=="down/right"){
            bulletPic=bulletPics.get(2);
        }
        if (dir=="up/left"){
            bulletPic=bulletPics.get(6);
        }
        if (dir=="down/left"){
            bulletPic=bulletPics.get(1);
        }
        bullets.add(new Bullet(dir,x,y,7*bulletSpeedMod,bulletPic));
        numOfBullets-=1;
    }

    public void setHitboxes(){
        hitBox=new Rectangle(x,y,15,40);
        feetBox=new Rectangle(x,y+35,15,5);
        leftBox=new Rectangle(x-5,y,5,35);
        headBox=new Rectangle(x,y-2,15,8);
        rightBox=new Rectangle(x+15,y,5,35);
        shieldHitBox=new Rectangle(x+7-(int)shieldRadius,y+20-(int)shieldRadius,2*(int)shieldRadius,2*(int)shieldRadius);


    }

    public void addPower(String name){
        if (name=="Jump Boost"){
            jumpMod=0.5;
        }
        else if (name=="Bigger Shield"){
            shieldRadius*=1.5;
        }
        else if (name=="Longer Shield"){
            System.out.println("ooga");
            shieldRechargeMod=0.4;
        }
        else if (name=="Bullet Pack"){
            numOfBullets=Math.min(numOfBullets+3,6);
        }
        else if (name=="Extra Life"){
            lives+=1;
        }
        else if (name=="Fast Bullets"){
            bulletSpeedMod=1.5;
        }
        else if (name=="Single Bullet"){
            numOfBullets=Math.min(numOfBullets+1,6);
        }
        else if (name=="Invincibility"){
            invincible=true;
        }
        if (name!="Extra Life" && name!="Bullet Pack" && name!="Single Bullet" && name!="Invincibility") {
            currentPowers.add(name);
        }
    }

    public int getX(){
        return x;
    }
    public void setX(int n){
        x=n;
    }
    public int getY(){
        return y;
    }
    public void setY(int n){
        y=n;
    }
    public int getJumpBtn(){
        return  jumpBtn;
    }
    public int getRightBtn(){
        return rightBtn;
    }
    public int getLeftBtn(){
        return leftBtn;
    }
    public int getUpBtn(){
        return upBtn;
    }
    public int getDownBtn(){
        return downBtn;
    }
    public int getShootBtn(){
        return shootBtn;
    }
    public int getBlockBtn(){
        return blockBtn;
    }
    public boolean isOnGround(){
        return onGround;
    }
    public void setOnGround(Boolean b){
        onGround=b;
    }
    public double getSpeedY(){
        return speedY;
    }
    public void setSpeedY(double n){
        speedY=n;
    }
    public double getSpeedX(){
        return speedX;
    }
    public void setSpeedX(double n){
        speedX=n;
    }

    public double getShieldEnergy(){
        return shieldEnergy;
    }
    public void setShieldEnergy(double n){shieldEnergy=n;}
    public double getMaxShieldEnergy(){
        return maxShieldEnergy;
    }
    public double getShieldRadius(){return shieldRadius;}
    public void setShieldRadius(double n){shieldRadius=n;}
    public Rectangle getHitBox(){
        return hitBox;
    }
    public void setHitBox(Rectangle rect){
        hitBox =rect;
    }
    public Rectangle getFeetBox(){
        return feetBox;
    }
    public void setFeetBox(Rectangle rect){
        feetBox=rect;
    }
    public Rectangle getHeadBox(){
        return headBox;
    }
    public void setHeadBox(Rectangle rect){
        headBox=rect;
    }
    public Rectangle getLeftBox(){
        return leftBox;
    }
    public void setLeftBox(Rectangle rect){
        leftBox=rect;
    }
    public Rectangle getRightBox(){
        return rightBox;
    }
    public void setRightBox(Rectangle rect){
        rightBox=rect;
    }
    public int getNumOfBullets(){
        return numOfBullets;
    }
    public void setNumOfBullets(int n){
        numOfBullets=n;
    }
    public String getDir(){
        return dir;
    }
    public void setDir(String s){
        dir=s;
    }
    public LinkedList<Bullet> getBullets(){
        return bullets;
    }
    public int getLives(){return lives;}
    public void setLives(int n){lives=n;}
    public boolean isActiveShield(){return activeShield;}
    public void setActiveShield(Boolean b){activeShield=b;}
    public Rectangle getShieldHitBox(){
        return shieldHitBox;
    }
    public double getJumpMod(){
        return jumpMod;
    }
    public void setJumpMod(double n){jumpMod=n;}
    public ArrayList<String> getCurrentPowers(){
        return  currentPowers;
    }
    public double getShieldRechargeMod(){return shieldRechargeMod;}
    public double getDefaultSpeed(){return defaultSpeed;}
    public void setDefaultSpeed(double n){defaultSpeed=n;}
    public ArrayList<Image> getBulletPics(){return bulletPics;}
    public boolean isOnWall(){return onWall;}
    public void setOnWall(boolean b){onWall=b;}
    public boolean isWallJumping(){return wallJumping;}
    public void setWallJumping(boolean b){wallJumping=b;}
    public int getJumpCounter(){return jumpCounter;}
    public void setJumpCounter(int n){jumpCounter=n;}
    public boolean isInvincible(){return invincible;}

}
