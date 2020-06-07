//The player class has all of the variables associated with each player
//and has some mechanical functions like movement ans shooting
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private double speedX,speedY,defaultSpeed;//speeds
    private double jumpMod;//modifier for how high you jump
    private int numOfBullets;//ammo
    private int lives;
    private int jumpCounter,invincibleCounter;
    private String dir;//direction currently facing or aiming
    private int x,y;//location
    private boolean onGround,onWall,wallJumping,invincible;
    private Rectangle hitBox,feetBox,headBox,leftBox,rightBox,shieldHitBox; //hitboxes
    private LinkedList<Bullet> bullets;//fired bullets
    private double bulletSpeedMod;//modifier for how fast bullets move
    private double shieldEnergy;
    private double maxShieldEnergy;
    private double shieldRechargeMod;//how quickly shield recharges
    private double shieldRadius;//size of shield
    private Boolean activeShield;
    private int currentFrame;
    private int wait;
    private int delay;
    private ArrayList<Image> bulletPics;
    private String shootDir;

    private int playerChoice;

    private ArrayList<String> currentPowers;
    private int rightBtn,leftBtn,upBtn,downBtn,shootBtn,blockBtn,jumpBtn;

    private ArrayList<Image> spritePics;

    public Player(int playerNum, int playerchoice){
        //starting values for all variables
        wait=75;
        delay=0;
        lives=2;
        jumpCounter=0;
        invincibleCounter=0;
        speedX=4;
        speedY=0;
        defaultSpeed=4;
        onGround=false;
        onWall=false;
        activeShield=false;
        shieldRadius=40;
        jumpMod=1;
        numOfBullets=3;
        shieldEnergy=30;
        invincible=false;
        maxShieldEnergy=30;
        shieldRechargeMod=1;
        bulletSpeedMod=1;
        playerChoice =playerchoice;
        loadPics();
        if (playerNum==1){
            //spawn point
            x=200;
            y=125;
            //controls
            rightBtn=KeyEvent.VK_D;
            leftBtn=KeyEvent.VK_A;
            upBtn=KeyEvent.VK_W;
            downBtn=KeyEvent.VK_S;
            shootBtn= KeyEvent.VK_SHIFT;
            blockBtn=KeyEvent.VK_CONTROL;
            jumpBtn=KeyEvent.VK_SPACE;
            dir="right";
        }
        else if (playerNum==2){
            x=800;
            y=125;
            rightBtn=KeyEvent.VK_RIGHT;
            leftBtn=KeyEvent.VK_LEFT;
            upBtn=KeyEvent.VK_UP;
            downBtn=KeyEvent.VK_DOWN;
            shootBtn= KeyEvent.VK_K;
            blockBtn=KeyEvent.VK_L;
            jumpBtn=KeyEvent.VK_J;
            dir="left";
        }
        //initialize hitboxes
        hitBox =new Rectangle(x,y,15,40);
        feetBox=new Rectangle(x,y+35,15,5);
        headBox=new Rectangle(x,y-2,15,8);
        leftBox=new Rectangle(x-5,y,5,35);
        rightBox=new Rectangle(x+15,y,5,35);
        shieldHitBox=new Rectangle(x+7-(int)shieldRadius,y+20-(int)shieldRadius,2*(int)shieldRadius,2*(int)shieldRadius);
        bullets=new LinkedList<>();
        currentFrame=1;
        currentPowers=new ArrayList<>();
    }

    public void loadPics() {
        bulletPics = new ArrayList<>();
        //create the frames for bullets
        for (int i = 1; i < 9; i++) {
            Image bulletPic;
            bulletPic = new ImageIcon("Bullet/bullet" + i + ".png").getImage();
            if (i == 1 || i == 6) {
                //different orientations of bullets need different dimensions
                bulletPic = bulletPic.getScaledInstance(20, 40, Image.SCALE_SMOOTH);
            } else if (i == 4 || i == 5) {
                bulletPic = bulletPic.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
            } else {
                bulletPic = bulletPic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            }
            bulletPics.add(bulletPic);
        }
        spritePics = new ArrayList<>();
        if (playerChoice == 1) {
            //each of 6 characters has a different array of sprites
            for (int i = 0; i < 16; i++) {
                Image gigaPic;
                gigaPic = new ImageIcon("Sprites/Giga Guy/gigaGuy" + i + ".png").getImage();
                gigaPic = gigaPic.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                spritePics.add(gigaPic);
            }
        }
        if (playerChoice == 2) {
            for (int i = 0; i < 16; i++) {
                Image petaPic;
                petaPic = new ImageIcon("Sprites/Peta Pal/petaPal" + i + ".png").getImage();
                petaPic = petaPic.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                spritePics.add(petaPic);
            }
        }
        if (playerChoice == 3) {
            for (int i = 0; i < 19; i++) {
                Image tonicPic;
                tonicPic = new ImageIcon("Sprites/Tonic/tonic" + i + ".png").getImage();
                tonicPic = tonicPic.getScaledInstance(45, 80, Image.SCALE_SMOOTH);
                spritePics.add(tonicPic);
            }
        }
        if (playerChoice == 4) {
            for (int i = 0; i < 19; i++) {
                Image chronicPic;
                chronicPic = new ImageIcon("Sprites/Chronic/chronic" + i + ".png").getImage();
                chronicPic = chronicPic.getScaledInstance(45, 80, Image.SCALE_SMOOTH);
                spritePics.add(chronicPic);
            }
        }

        if (playerChoice == 5) {
            for (int i = 0; i < 17; i++) {
                Image lankPic;
                lankPic = new ImageIcon("Sprites/Lank/lank" + i + ".png").getImage();
                lankPic = lankPic.getScaledInstance(50, 100, Image.SCALE_SMOOTH);
                spritePics.add(lankPic);
            }
        }

        if (playerChoice == 6) {
            for (int i = 0; i < 17; i++) {
                Image darkLankPic;
                darkLankPic = new ImageIcon("Sprites/Dark Lank/darkLank" + i + ".png").getImage();
                darkLankPic = darkLankPic.getScaledInstance(50, 100, Image.SCALE_SMOOTH);
                spritePics.add(darkLankPic);
            }
        }

    }

    public void move(){
        x+=speedX;//move each direction
        y+=speedY;
        if (invincible){//invincibility is on a timer
            invincibleCounter+=1;
        }
        if (invincibleCounter>=280){
            invincibleCounter=0;
            invincible=false;
        }
    }
    public void jump(){//start moving upward
        speedY=-7;
        onGround=false;
    }

    public void shoot(){
        Image bulletPic =null;
        if (dir=="right"){
            //choose which frame to use depending on which direction the bullet is fired at
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
        bullets.add(new Bullet(dir,x,y-15,7*bulletSpeedMod,bulletPic));//make new bullet
        numOfBullets-=1;
    }

    public void setHitboxes(){
        //refresh hitboxes to keep up with movement, etc
        hitBox=new Rectangle(x-20,y-20,37,55);
        //being left vs right places the player in different parts of their frame, so to match where the sprite is
        //within its frame, the hitboxes must be altered too
        if (dir=="left") {
            feetBox = new Rectangle(x - 5, y + 35, 25, 5);
        }
        else{
            feetBox = new Rectangle(x - 15, y + 35, 25, 5);
        }
        leftBox=new Rectangle(x-10,y-25,5,60);
        if (dir=="left") {
            headBox = new Rectangle(x-10, y - 35, 25, 8);
        }
        else{
            headBox = new Rectangle(x-20, y - 35, 25, 8);
        }
        rightBox=new Rectangle(x+10,y-25,5,60);
        shieldHitBox=new Rectangle(x+5-(int)shieldRadius,y-(int)shieldRadius,2*(int)shieldRadius,2*(int)shieldRadius);
    }

    public void addPower(String name){//picks up an item
        if (name=="Jump Boost"){
            jumpMod=0.5;//alters gravity to be less forceful
        }
        else if (name=="Bigger Shield"){
            shieldRadius*=1.5;//increases size
        }
        else if (name=="Longer Shield"){
            shieldRechargeMod=0.4;//will deplete slower
        }
        else if (name=="Bullet Pack"){
            numOfBullets=Math.min(numOfBullets+3,6);//get more bullets but can't go over 6
        }
        else if (name=="Extra Life"){
            lives+=1;
        }
        else if (name=="Fast Bullets"){
            bulletSpeedMod=1.5;//bullets move faster
        }
        else if (name=="Single Bullet"){
            numOfBullets=Math.min(numOfBullets+1,6);//get an extra bullet
        }
        else if (name=="Invincibility"){
            invincible=true;
        }
        if (name!="Extra Life" && name!="Bullet Pack" && name!="Single Bullet" && name!="Invincibility") {
            //these powerups can be received more than once as they are not permanents
            currentPowers.add(name);
        }
    }
    public Image getFrame() {
        if (speedX != 0 && onGround) {//running on the ground
            if(currentFrame<13||currentFrame>16){
                currentFrame=13;//start running frame
            }
            delay += 1;
            if (delay % wait == 0) {//change frame
                currentFrame++;
                if (currentFrame == 16) {//loop through same frames
                    currentFrame = 13;
                }
            }
            return spritePics.get(currentFrame);
        }
        if (!onGround) {//mid air frame
            return spritePics.get(2);
        }
        if (speedX == 0) {//not moving
            return spritePics.get(1);
        }
        return spritePics.get(0);//default
    }

    public Image getShootFrame(String shootDir){

        //players have different frames for each way they are aiming

        if (onGround) {
            if (shootDir == "up") {
                return spritePics.get(11);
            }
            if (shootDir == "down") {
                return spritePics.get(9);
            }
            if (shootDir == "right" || shootDir == "left") {
                return spritePics.get(3);
            }
            if (shootDir == "up/right" || shootDir == "up/left") {
                return spritePics.get(5);
            }
            if (shootDir == "down/right" || shootDir == "down/left") {
                return spritePics.get(7);
            }
        }
        if(!onGround){
            if (shootDir == "up") {
                return spritePics.get(12);
            }
            if (shootDir == "down") {
                return spritePics.get(10);
            }
            if (shootDir == "right" || shootDir == "left") {
                return spritePics.get(4);
            }
            if (shootDir == "up/right" || shootDir == "up/left") {
                return spritePics.get(6);
            }
            if (shootDir == "down/right" || shootDir == "down/left") {
                return spritePics.get(8);
            }
        }
        return null;
    }

    //accessor methods
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
    public Rectangle getHitBox(){
        return hitBox;
    }
    public Rectangle getFeetBox(){
        return feetBox;
    }
    public Rectangle getHeadBox(){
        return headBox;
    }
    public Rectangle getLeftBox(){
        return leftBox;
    }
    public Rectangle getRightBox(){
        return rightBox;
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
    public ArrayList<String> getCurrentPowers(){
        return  currentPowers;
    }
    public double getShieldRechargeMod(){return shieldRechargeMod;}
    public double getDefaultSpeed(){return defaultSpeed;}
    public ArrayList<Image> getBulletPics(){return bulletPics;}
    public boolean isOnWall(){return onWall;}
    public void setOnWall(boolean b){onWall=b;}
    public boolean isWallJumping(){return wallJumping;}
    public void setWallJumping(boolean b){wallJumping=b;}
    public int getJumpCounter(){return jumpCounter;}
    public void setJumpCounter(int n){jumpCounter=n;}
    public boolean isInvincible(){return invincible;}

    public int getPlayerChoice(){
        return playerChoice;
    }

}
