import javafx.scene.shape.Circle;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.lang.reflect.Array;
import java.util.*;

public class Map extends JPanel implements KeyListener, ActionListener {
    private ArrayList<Rectangle> walls;
    private ArrayList<Image> itemPics;
    private String[] powerups=new String[]{"Bullet Pack","Longer Shield","Bigger Shield","Extra Life","Fast Bullets","Invincibility","Jump Boost","Single Bullet"};
    private LinkedList<Powerup> items;
    private int[][] spawnPoints;
    private int[][] possibleLocations;
    private Image bulletPic, heartPic, eyePic;
    private Image background;
    private int itemCounter,version, energyCounter;
    private Block block;
    private ArrayList<Image> arrowPics;
    private Image blockPic;
    private ArrayList<Player> players;
    private ArrayList<EnergyBall> energyBalls;
    private boolean []keys;
    private Main mainFrame;
    javax.swing.Timer myTimer;

    public Map(int version, Main m, int numOfPlayers){
        keys = new boolean[KeyEvent.KEY_LAST+1];
        this.version=version;
        block=new Block();
        energyBalls=new ArrayList<>();
        mainFrame = m;
        itemCounter=0;
        energyCounter=0;
        myTimer = new javax.swing.Timer(10, this);	 // trigger every 10 ms
        addKeyListener(this);
        items=new LinkedList<>();
        if (version==0){
            background = new ImageIcon("Level 1/Level1.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(0);
            possibleLocations=new int[][]{new int[]{250,360,0},new int[]{770,360,0},new int[]{955,260,0},new int[]{50,260,0},new int[]{500,550,0}};
            spawnPoints=new int[][]{new int[]{84,240},new int[]{84,495},new int[]{915,240},new int[]{915,495}};
        }
        if (version==1){
            background = new ImageIcon("Level 2/Level2.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(1);
            possibleLocations=new int[][]{new int[]{50,300,0},new int[]{950,300,0},new int[]{50,650,0},new int[]{500,580,0},new int[]{950,650,0}};
            spawnPoints=new int[][]{new int[]{114,275},new int[]{114,628},new int[]{895,270},new int[]{895,625}};
        }
        if (version==2){
            background = new ImageIcon("Level 3/Level3.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(2);
            possibleLocations=new int[][]{new int[]{500,260,0},new int[]{348,390,0},new int[]{660,390,0},new int[]{430,645,0},new int[]{590,645,0}};
            spawnPoints=new int[][]{new int[]{180,275},new int[]{180,655},new int[]{825,275},new int[]{825,655}};
        }
        if (version==3){
            background = new ImageIcon("Level 4/Level4.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(3);
            possibleLocations=new int[][]{new int[]{250,360,0},new int[]{770,360,0},new int[]{955,260,0},new int[]{50,260,0},new int[]{500,550,0}};
            spawnPoints=new int[][]{new int[]{84,240},new int[]{84,495},new int[]{915,240},new int[]{915,495}};
        }
        if (version==4){
            background = new ImageIcon("Level 5/Level5.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(4);
            possibleLocations=new int[][]{new int[]{50,300,0},new int[]{950,300,0},new int[]{50,300,0},new int[]{500,580,0},new int[]{950,650,0}};
            spawnPoints=new int[][]{new int[]{114,275},new int[]{114,628},new int[]{895,270},new int[]{895,625}};
        }
        if (version==5){
            background = new ImageIcon("Level 6/Level6.png").getImage();
            background = background.getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
            walls=makeWalls(5);
            possibleLocations=new int[][]{new int[]{500,260,0},new int[]{348,390,0},new int[]{660,390,0},new int[]{430,645,0},new int[]{590,645,0}};
            spawnPoints=new int[][]{new int[]{180,275},new int[]{180,655},new int[]{825,275},new int[]{825,655}};}

        players=new ArrayList<>();
        for (int i=1;i<numOfPlayers+1;i++){
            players.add(new Player(i));
        }
        loadPics();
    }

    public void loadPics(){
        arrowPics=new ArrayList<>();
        for (int i=0;i<8;i++){
            Image pic= new ImageIcon("Arrow/Arrow"+i+".png").getImage();
            if (i==0 || i==7){
                pic=pic.getScaledInstance(10,35,Image.SCALE_SMOOTH);
            }
            else if (i==1 || i==4){
                pic=pic.getScaledInstance(35,10,Image.SCALE_SMOOTH);
            }
            else{
                pic=pic.getScaledInstance(35,35,Image.SCALE_SMOOTH);
            }
            arrowPics.add(pic);
        }
        itemPics=new ArrayList<>();
        Image mag=new ImageIcon("items/magazine.png").getImage();
        itemPics.add(mag);
        Image time=new ImageIcon("items/timeShield.png").getImage();
        itemPics.add(time);
        Image shield=new ImageIcon("items/shield.png").getImage();
        itemPics.add(shield);
        Image heart=new ImageIcon("items/heart.png").getImage();
        itemPics.add(heart);
        Image fast=new ImageIcon("items/speedBullet.png").getImage();
        itemPics.add(fast);
        Image star=new ImageIcon("items/star.png").getImage();
        itemPics.add(star);
        Image jump=new ImageIcon("items/boots.png").getImage();
        itemPics.add(jump);
        bulletPic=new ImageIcon("Bullet/bullet6.png").getImage();
        bulletPic=bulletPic.getScaledInstance(8,15,Image.SCALE_SMOOTH);
        heartPic=new ImageIcon("items/heart.png").getImage();
        heartPic=heartPic.getScaledInstance(12,12,Image.SCALE_SMOOTH);
        eyePic=new ImageIcon("eyebot.png").getImage();
        eyePic=eyePic.getScaledInstance(45,45,Image.SCALE_SMOOTH);
        blockPic=new ImageIcon("items/block.png").getImage();
        blockPic=blockPic.getScaledInstance(130,130,Image.SCALE_SMOOTH);

    }

    public ArrayList<Rectangle> makeWalls(int version){
        ArrayList<Rectangle> walls=new ArrayList<>();
        if (version==0 || version==3){
            Rectangle r0=new Rectangle(0,447,62,117);
            walls.add(r0);
            Rectangle r2=new Rectangle(46,543,130,27);
            walls.add(r2);
            Rectangle r3=new Rectangle(158,515,64,115);
            walls.add(r3);
            Rectangle r4=new Rectangle(206,608,82,51);
            walls.add(r4);
            Rectangle r5=new Rectangle(286,639,33,180);
            walls.add(r5);
            Rectangle r6=new Rectangle(415,735,189,84);
            walls.add(r6);
            Rectangle r7=new Rectangle(449,575,128,34);
            walls.add(r7);
            Rectangle r8=new Rectangle(704,640,53,178);
            walls.add(r8);
            Rectangle r9=new Rectangle(736,608,85,128);
            walls.add(r9);
            Rectangle r10=new Rectangle(736,608,85,128);
            walls.add(r10);
            Rectangle r11=new Rectangle(800,510,65,123);
            walls.add(r11);
            Rectangle r12=new Rectangle(850,545,134,34);
            walls.add(r12);
            Rectangle r13=new Rectangle(962,447,62,121);
            walls.add(r13);
            Rectangle r14=new Rectangle(962,447,62,121);
            walls.add(r14);
            Rectangle r15=new Rectangle(223,383,97,34);
            walls.add(r15);
            Rectangle r16=new Rectangle(288,323,67,31);
            walls.add(r16);
            Rectangle r17=new Rectangle(288,322,32,128);
            walls.add(r17);
            Rectangle r18=new Rectangle(670,318,67,34);
            walls.add(r18);
            Rectangle r19=new Rectangle(705,383,97,34);
            walls.add(r19);
            Rectangle r20=new Rectangle(704,320,34,128);
            walls.add(r20);
            Rectangle r21=new Rectangle(158,192,94,32);
            walls.add(r21);
            Rectangle r22=new Rectangle(319,161,129,32);
            walls.add(r22);
            Rectangle r23=new Rectangle(575,159,134,34);
            walls.add(r23);
            Rectangle r24=new Rectangle(769,192,99,34);
            walls.add(r24);
            Rectangle r25=new Rectangle(0,290,95,64);
            walls.add(r25);
            Rectangle r26=new Rectangle(94,291,34,34);
            walls.add(r26);
            Rectangle r27=new Rectangle(704,320,34,128);
            walls.add(r27);
            Rectangle r31=new Rectangle(0,127,29,160);
            walls.add(r31);
            Rectangle r32=new Rectangle(0,49,62,79);
            walls.add(r32);
            Rectangle r33=new Rectangle(45,18,49,46);
            walls.add(r33);
            Rectangle r34=new Rectangle(72,-50,249,84);
            walls.add(r34);
            Rectangle r35=new Rectangle(415,-50,192,114);
            walls.add(r35);
            Rectangle r36=new Rectangle(702,-50,245,84);
            walls.add(r36);
            Rectangle r37=new Rectangle(932,-50,64,114);
            walls.add(r37);
            Rectangle r38=new Rectangle(415,-50,192,114);
            walls.add(r38);
            Rectangle r39=new Rectangle(962,44,61,84);
            walls.add(r39);
            Rectangle r40=new Rectangle(996,129,28,180);
            walls.add(r40);
            Rectangle r41=new Rectangle(930,287,94,64);
            walls.add(r41);
            Rectangle r42=new Rectangle(898,287,34,34);
            walls.add(r42);
            Rectangle r43=new Rectangle(447,159,130,130);
            walls.add(r43);

        }
        if (version==1||version==4){
            Rectangle r0=new Rectangle(0,672,289,146);
            walls.add(r0);
            Rectangle r2=new Rectangle(0,542,30,132);
            walls.add(r2);
            Rectangle r3=new Rectangle(0,318,64,224);
            walls.add(r3);
            Rectangle r4=new Rectangle(64,320,36,67);
            walls.add(r4);
            Rectangle r5=new Rectangle(96,319,67,35);
            walls.add(r5);
            Rectangle r6=new Rectangle(0,191,32,129);
            walls.add(r6);
            Rectangle r7=new Rectangle(0,102,99,91);
            walls.add(r7);
            Rectangle r8=new Rectangle(63,43,65,88);
            walls.add(r8);
            Rectangle r9=new Rectangle(102,-50,184,114);
            walls.add(r9);
            Rectangle r10=new Rectangle(226,-50,63,147);
            walls.add(r10);
            Rectangle r11=new Rectangle(258,95,160,34);
            walls.add(r11);
            Rectangle r12=new Rectangle(768,-50,159,116);
            walls.add(r12);
            Rectangle r13=new Rectangle(962,447,62,121);
            walls.add(r13);
            Rectangle r14=new Rectangle(962,447,62,121);
            walls.add(r14);
            Rectangle r15=new Rectangle(160,509,97,37);
            walls.add(r15);
            Rectangle r16=new Rectangle(127,479,36,30);
            walls.add(r16);
            Rectangle r17=new Rectangle(161,480,95,50);
            walls.add(r17);
            Rectangle r18=new Rectangle(254,224,65,291);
            walls.add(r18);
            Rectangle r19=new Rectangle(320,350,67,63);
            walls.add(r19);
            Rectangle r20=new Rectangle(303,400,50,47);
            walls.add(r20);
            Rectangle r21=new Rectangle(382,704,257,112);
            walls.add(r21);
            Rectangle r22=new Rectangle(446,607,129,121);
            walls.add(r22);
            Rectangle r23=new Rectangle(383,-50,258,82);
            walls.add(r23);
            Rectangle r24=new Rectangle(480,32,64,32);
            walls.add(r24);
            Rectangle r25=new Rectangle(737,670,287,151);
            walls.add(r25);
            Rectangle r26=new Rectangle(992,520,32,165);
            walls.add(r26);
            Rectangle r27=new Rectangle(963,320,61,230);
            walls.add(r27);
            Rectangle r28=new Rectangle(931,318,34,66);
            walls.add(r28);
            Rectangle r29=new Rectangle(862,318,69,33);
            walls.add(r29);
            Rectangle r30=new Rectangle(993,172,31,165);
            walls.add(r30);
            Rectangle r31=new Rectangle(930,102,93,91);
            walls.add(r31);
            Rectangle r32=new Rectangle(897,7,83,118);
            walls.add(r32);
            Rectangle r33=new Rectangle(738,-50,65,145);
            walls.add(r33);
            Rectangle r34=new Rectangle(610,94,158,35);
            walls.add(r34);
            Rectangle r35=new Rectangle(638,352,96,61);
            walls.add(r35);
            Rectangle r36=new Rectangle(706,220,64,288);
            walls.add(r36);
            Rectangle r37=new Rectangle(670,395,56,56);
            walls.add(r37);
            Rectangle r38=new Rectangle(767,480,99,70);
            walls.add(r38);
            Rectangle r39=new Rectangle(861,478,35,33);
            walls.add(r39);

        }
        if (version==2||version==5){
            Rectangle r0=new Rectangle(335,674,147,146);
            walls.add(r0);
            Rectangle r2=new Rectangle(258,576,130,192);
            walls.add(r2);
            Rectangle r3=new Rectangle(76,701,220,67);
            walls.add(r3);
            Rectangle r4=new Rectangle(37,611,94,121);
            walls.add(r4);
            Rectangle r5=new Rectangle(96,319,67,35);
            walls.add(r5);
            Rectangle r6=new Rectangle(0,450,64,187);
            walls.add(r6);
            Rectangle r7=new Rectangle(33,409,66,75);
            walls.add(r7);
            Rectangle r8=new Rectangle(73,320,221,94);
            walls.add(r8);
            Rectangle r9=new Rectangle(257,190,98,64);
            walls.add(r9);
            Rectangle r10=new Rectangle(308,418,81,35);
            walls.add(r10);
            Rectangle r11=new Rectangle(290,190,66,97);
            walls.add(r11);
            Rectangle r12=new Rectangle(34,223,97,129);
            walls.add(r12);
            Rectangle r13=new Rectangle(4,62,62,193);
            walls.add(r13);
            Rectangle r14=new Rectangle(24,0,74,96);
            walls.add(r14);
            Rectangle r15=new Rectangle(95,-50,387,81);
            walls.add(r15);
            Rectangle r16=new Rectangle(322,-50,97,115);
            walls.add(r16);
            Rectangle r17=new Rectangle(479,287,66,68);
            walls.add(r17);
            Rectangle r18=new Rectangle(542,672,111,146);
            walls.add(r18);
            Rectangle r19=new Rectangle(639,578,131,190);
            walls.add(r19);
            Rectangle r20=new Rectangle(736,703,191,66);
            walls.add(r20);
            Rectangle r21=new Rectangle(894,608,116,160);
            walls.add(r21);
            Rectangle r22=new Rectangle(955,455,67,185);
            walls.add(r22);
            Rectangle r23=new Rectangle(926,389,79,98);
            walls.add(r23);
            Rectangle r24=new Rectangle(735,321,220,94);
            walls.add(r24);
            Rectangle r25=new Rectangle(703,192,64,259);
            walls.add(r25);
            Rectangle r26=new Rectangle(637,416,67,34);
            walls.add(r26);
            Rectangle r27=new Rectangle(672,192,34,95);
            walls.add(r27);
            Rectangle r28=new Rectangle(892,224,95,125);
            walls.add(r28);
            Rectangle r29=new Rectangle(957,69,67,192);
            walls.add(r29);
            Rectangle r30=new Rectangle(925,-50,83,142);
            walls.add(r30);
            Rectangle r31=new Rectangle(546,-50,384,79);
            walls.add(r31);
            Rectangle r32=new Rectangle(607,-50,96,113);
            walls.add(r32);
            Rectangle r33=new Rectangle(258,190,66,257);
            walls.add(r33);

        }
        return walls;
    }


    public void addNotify() {
        super.addNotify();
        myTimer.start();
    }


    public void actionPerformed(ActionEvent actionEvent) {
        if (isVisible()) {
            repaint();
            move();
            moveBullets();
            if (version==0 || version==3){
                moveBlock();
            }
            if (version==2 || version==5){
                energyCounter+=1;
                if (energyCounter%550==0){
                    spawnBall();
                }
                moveBalls();
            }
            moveItems();
            checkTeleport();
            checkWallJump();
            checkShoot();
            checkShield();
            checkWallCollisions();
            checkStomp();
            checkPickUps();
            spawnItems();
        }
    }

    public void move(){
        for (Player p:players){
            if (!p.isOnGround()){
                p.setSpeedY(Math.min(p.getSpeedY()+0.2*p.getJumpMod(),9));
            }
            if (!keys[p.getShootBtn()] && !keys[p.getBlockBtn()] && !p.isWallJumping()) {
                if (keys[p.getRightBtn()]) {
                    p.setDir("right");
                    p.setSpeedX(p.getDefaultSpeed());
                }
                else if (keys[p.getLeftBtn()]){
                    p.setDir("left");
                    p.setSpeedX(p.getDefaultSpeed()*-1);
                }
            }
            if (!keys[p.getRightBtn()] && !keys[p.getLeftBtn()] && !p.isWallJumping()){
                p.setSpeedX(0);
            }
            p.move();
            p.setHitboxes();
        }
    }

    public void moveBullets(){
        for (Player p:players) {
            for (int i=0;i<p.getBullets().size();i++) {
                if (p.getBullets().size()>0 && i<p.getBullets().size()) {
                    p.getBullets().get(i).move("L/R");
                    checkBulletCollisions("L/R");
                }
                if (p.getBullets().size()>0 && i<p.getBullets().size()) {
                    p.getBullets().get(i).move("U/D");
                    checkBulletCollisions("U/D");
                }
            }
        }
    }

    public void moveBlock(){
        block.setCounter(block.getCounter()+1);
        System.out.println(block.getCounter());
        if (block.getCounter()%290==0){
            block.setMoving(!block.isMoving());
            if (block.isMoving()){
                block.setVel(block.getVel()*-1);
            }
        }
        block.move();
        walls.remove(walls.size()-1);
        walls.add(block.getHitbox());
        for (Player p:players){
            if (block.getHitbox().intersects(new Rectangle(p.getX(),p.getY()+15,p.getHitBox().width,10))){
                p.setLives(p.getLives()-1);
                checkDeath(p);
            }
        }
    }

    public void spawnBall(){
        double shortest=100000;
        int shortestIndex=0;
        for (int i=0;i<players.size();i++){
            double dist=Math.sqrt(Math.pow(players.get(i).getX()-490,2)+Math.pow(players.get(i).getY()-500,2));
            if (dist<shortest){
                shortest=dist;
                shortestIndex=i;
            }
        }
        energyBalls.add(new EnergyBall(players.get(shortestIndex)));
    }

    public void moveBalls(){
        for (int i = energyBalls.size() - 1; i >= 0; i--) {
            System.out.println("a");
            energyBalls.get(i).move();
            System.out.println("b");
            for (Rectangle w : walls) {
                if (energyBalls.size()>0 && i<energyBalls.size()) {
                    if (energyBalls.get(i).getHitbox().intersects(w)) {
                        energyBalls.remove(i);
                    }
                }
            }
            for (int j = players.size() - 1; j >= 0; j--) {
                if (energyBalls.size()>0 && i<energyBalls.size()) {
                    if (energyBalls.get(i).getHitbox().intersects(players.get(j).getHitBox())) {
                        players.get(j).setLives(players.get(j).getLives() - 1);
                        checkDeath(players.get(j));
                        energyBalls.remove(i);
                    }
                }
            }
        }
    }

    public void moveItems(){
        for (Powerup p:items){
            p.setBottomBox(new Rectangle(p.getX(),p.getY()+25,25,10));
            p.setHitbox(new Rectangle(p.getX(),p.getY(),25,35));
            if (!p.isOnGround()){
                p.move();
            }
            p.setOnGround(false);
            for (Rectangle r:walls){
                if (p.getBottomBox().intersects(r)){
                    p.setY(r.y-34);
                    p.setVely(0);
                    p.setOnGround(true);
                }
            }
        }
    }

    public void checkTeleport(){
        for (Player p:players){
            if (p.getY()<-40){
                p.setY(768);
            }
            else if (p.getY()>768){
                p.setY(-40);
            }
            else if (p.getX()<0){
                p.setX(1024);
            }
            else if (p.getX()>1024){
                p.setX(0);
            }
            for (Bullet b:p.getBullets()){
                if (b.getY()<-40){
                    b.setY(768);
                }
                else if (b.getY()>768){
                    b.setY(-40);
                }
                else if (b.getX()<0){
                    b.setX(1024);
                }
                else if (b.getX()>1024){
                    b.setX(0);
                }
            }
        }
        for (Powerup p:items){
            if (p.getY()<-40){
                p.setY(768);
            }
            else if (p.getY()>768){
                p.setY(-40);
            }
        }
    }

    public void checkWallJump(){
        for (Player p:players){
            p.setOnWall(false);
            for (Rectangle wall:walls){
                if (p.getLeftBox().intersects(wall)){
                    p.setOnWall(true);
                    p.setSpeedY(2);
                }
                else if (p.getRightBox().intersects(wall)){
                    p.setOnWall(true);
                    p.setSpeedY(2);
                }
            }
            if (p.isOnWall() && !p.isOnGround()){
                if (keys[p.getJumpBtn()]){
                    p.setWallJumping(true);
                    p.setX(p.getX()-(int)p.getSpeedX());
                    p.setSpeedX((p.getSpeedX()*-1)/2);
                    p.setSpeedY(-5);
                }
            }
            if (p.isWallJumping()){
                p.setJumpCounter(p.getJumpCounter()+1);
                if (p.getJumpCounter()>20){
                    p.setWallJumping(false);
                    p.setJumpCounter(0);
                }
            }
        }
    }

    public void checkShoot(){
        for (Player p:players){
            if ((keys[p.getShootBtn()] && p.getNumOfBullets()>0)){
                if (keys[p.getRightBtn()] && !keys[p.getUpBtn()] && !keys[p.getDownBtn()]){
                    p.setDir("right");
                }
                else if (keys[p.getLeftBtn()] && !keys[p.getUpBtn()] && !keys[p.getDownBtn()]){
                    p.setDir("left");
                }
                else if (keys[p.getDownBtn()] && !keys[p.getRightBtn()] && !keys[p.getLeftBtn()]){
                    p.setDir("down");
                }
                else if (keys[p.getUpBtn()] && !keys[p.getRightBtn()] && !keys[p.getLeftBtn()]){
                    p.setDir("up");
                }
                else if (keys[p.getRightBtn()] && keys[p.getUpBtn()]){
                    p.setDir("up/right");
                }
                else if (keys[p.getRightBtn()] && keys[p.getDownBtn()]){
                    p.setDir("down/right");
                }
                else if (keys[p.getLeftBtn()] && keys[p.getUpBtn()]){
                    p.setDir("up/left");
                }
                else if (keys[p.getLeftBtn()] && keys[p.getDownBtn()]){
                    p.setDir("down/left");
                }
            }
        }
    }

    public void checkShield(){
        for (Player p:players){
            if (keys[p.getBlockBtn()] && p.getShieldEnergy()>0){
                System.out.println(p.getShieldEnergy());
                p.setActiveShield(true);
                p.setShieldEnergy(p.getShieldEnergy()-0.4*p.getShieldRechargeMod());
                if (p.getShieldEnergy()<=0){
                    p.setActiveShield(false);
                    p.setShieldEnergy(0);
                }
            }
            else{
                p.setActiveShield(false);
            }
            if (!p.isActiveShield() && !keys[p.getBlockBtn()]) {
                p.setShieldEnergy(Math.min(p.getShieldEnergy() + 0.1,p.getMaxShieldEnergy()));
            }
        }
    }


    public void checkWallCollisions(){
        for (Player p:players){
            p.setOnGround(false);
            for (Rectangle wall:walls){
                if (p.getFeetBox().intersects(wall)){
                    p.setOnGround(true);
                    p.setSpeedY(0);
                    p.setY(wall.y-39);
                }
                if (p.getLeftBox().intersects(wall)){
                    p.setX(p.getX()+(int)Math.abs(p.getSpeedX()));
                    p.setHitboxes();
                }
                else if (p.getRightBox().intersects(wall)){
                    p.setX(p.getX()-(int)p.getSpeedX());
                    p.setHitboxes();
                }
                if (p.getHeadBox().intersects(wall)){
                    p.setSpeedY(0.2);
                    p.setY(p.getY()+3);
                }
            }
        }
    }

    public void checkStomp(){
        for (Player p:players){
            for (Player q:players){
                if (p.getFeetBox().intersects(q.getHeadBox())){
                    q.setLives(q.getLives()-1);
                    checkDeath(q);
                }
            }
        }
    }

    public void checkBulletCollisions(String direction) {
        for (Player shootingPlayer : players) {
            for (int i=0;i<shootingPlayer.getBullets().size();i++) {
                for (Player p : players) {
                    if (shootingPlayer.getBullets().size()>0 && i<shootingPlayer.getBullets().size()) {
                        if (shootingPlayer.getBullets().get(i).isColliding(p.getHitBox()) && !p.equals(shootingPlayer) && !p.isActiveShield() && !p.isInvincible()) {
                            p.setLives(p.getLives() - 1);
                            shootingPlayer.getBullets().remove(shootingPlayer.getBullets().get(i));
                            checkDeath(p);
                        }
                    }
                    if (shootingPlayer.getBullets().size()>0 && i<shootingPlayer.getBullets().size()) {
                        if (shootingPlayer.getBullets().get(i).isColliding(p.getShieldHitBox()) && !p.equals(shootingPlayer) && p.isActiveShield()) {
                            shootingPlayer.getBullets().remove(shootingPlayer.getBullets().get(i));
                            p.setNumOfBullets(p.getNumOfBullets() + 1);
                        }
                    }
                }
                for (Rectangle wall : walls) {
                    if (shootingPlayer.getBullets().size()>0 && i<shootingPlayer.getBullets().size()) {
                        if (shootingPlayer.getBullets().get(i).isColliding(wall)) {
                            if (direction == "L/R") {
                                shootingPlayer.getBullets().get(i).bounce("L/R",shootingPlayer.getBulletPics());
                            } else {
                                shootingPlayer.getBullets().get(i).bounce("U/D",shootingPlayer.getBulletPics());
                            }
                            if (shootingPlayer.getBullets().get(i).getNumOfBounces()==2) {
                                Powerup power=new Powerup("Single Bullet",(int)shootingPlayer.getBullets().get(i).getX(),
                                        (int)shootingPlayer.getBullets().get(i).getY(),shootingPlayer.getBulletPics().get(5));
                                items.add(power);
                                shootingPlayer.getBullets().remove(shootingPlayer.getBullets().get(i));
                            }
                            else {
                                shootingPlayer.getBullets().get(i).setNumOfBounces(shootingPlayer.getBullets().get(i).getNumOfBounces() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkDeath(Player p){
        if (p.getLives()>0){
            Random rand=new Random();
            int n=rand.nextInt(spawnPoints.length);
            p.setX(spawnPoints[n][0]);
            p.setY(spawnPoints[n][1]);
        }
        else{
            players.remove(p);
        }
        checkOver();
    }

    ///////////////////////////////////////////////

    public void checkOver(){
        if (players.size()==1){
            System.out.println("winner");
        }
    }
    ///////////////////////////////////////////////

    public void checkPickUps(){
        for (int i=0;i<items.size();i++){
            for (Player p:players){
                if (items.size()>0 && i<items.size()) {
                    if (p.getHitBox().intersects(items.get(i).getHitbox()) && !p.getCurrentPowers().contains(items.get(i).getName())) {
                        System.out.println("collided");
                        p.addPower(items.get(i).getName());
                        items.remove(items.get(i));
                    }
                }
            }
        }
    }

    public void spawnItems(){
        if (itemCounter>=1750){
            itemCounter=0;
            boolean empty=false;
            for (int i=0;i<possibleLocations.length;i++){
                if (possibleLocations[i][2]==0){
                    empty=true;
                }
            }
            if (empty) {
                while (true) {
                    Random rand = new Random();
                    int n = rand.nextInt(possibleLocations.length);
                    if (possibleLocations[n][2] == 0) {
                        possibleLocations[n][2] = 1;
                        int j = rand.nextInt(powerups.length - 1);
                        Powerup newPower = new Powerup(powerups[j], possibleLocations[n][0], possibleLocations[n][1], itemPics.get(j));
                        items.add(newPower);
                        break;
                    }
                }
            }
        }
        else{
            itemCounter+=1;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        for (Player p:players){
            if (e.getKeyCode()==p.getLeftBtn()){
                p.setDir("left");
            }
            else if (e.getKeyCode()==p.getRightBtn()){
                p.setDir("right");
            }
            if (e.getKeyCode()==p.getJumpBtn() && p.isOnGround()){
                p.jump();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        for (Player p:players){
            if (e.getKeyCode()==p.getShootBtn() && p.getNumOfBullets()>0){
                p.shoot();
            }
        }
    }

    public void paintComponent(Graphics g){
        drawBackground(g);
        drawArrows(g);
        drawPlayers(g);
        if (version==2 || version==5){
            drawBalls(g);
        }
        drawAmmo(g);
        drawHealth(g);
        drawShields(g);
        drawItems(g);
    }
    public void drawBackground(Graphics g){
        g.drawImage(background,0,0,null);
        if (version==0 || version==3){
            g.drawImage(blockPic,block.getX(),block.getY(),null);
        }
        if (version==2 || version==5){
            g.drawImage(eyePic,488,500,null);
        }


    }

    public void drawArrows(Graphics g){
        for (Player p:players){
            if (keys[p.getShootBtn()]){
                if (p.getDir()=="up"){
                    g.drawImage(arrowPics.get(7),p.getX()+2,p.getY()-35, null);
                }
                else if (p.getDir()=="right"){
                    g.drawImage(arrowPics.get(4),p.getX()+15,p.getY()+15, null);
                }
                else if (p.getDir()=="down"){
                    g.drawImage(arrowPics.get(0),p.getX()+2,p.getY()+35, null);
                }
                else if (p.getDir()=="left"){
                    g.drawImage(arrowPics.get(1),p.getX()-35,p.getY()+15, null);
                }
                else if (p.getDir()=="up/right"){
                    g.drawImage(arrowPics.get(6),p.getX()+15,p.getY()-35, null);
                }
                else if (p.getDir()=="down/right"){
                    g.drawImage(arrowPics.get(5),p.getX()+15,p.getY()+35, null);
                }
                else if (p.getDir()=="up/left"){
                    g.drawImage(arrowPics.get(2),p.getX()-35,p.getY()-35, null);
                }
                else if (p.getDir()=="down/left"){
                    g.drawImage(arrowPics.get(3),p.getX()-35,p.getY()+35, null);
                }
            }
        }

    }

    public void drawPlayers(Graphics g){
        for (Player p:players) {
            System.out.println(players.get(0).getX()+" "+players.get(0).getY());
            g.setColor(Color.RED);
            g.fillRect(p.getX(), p.getY(), 15, 40);
            g.setColor(Color.YELLOW);
            g.fillRect(p.getFeetBox().x, p.getFeetBox().y, p.getFeetBox().width, p.getFeetBox().height);

            if (p.isInvincible()){
                g.setColor(new Color(252,255,45,75));
                g.fillOval(p.getX()+7-(int)p.getShieldRadius(),p.getY()+20-(int)p.getShieldRadius(),2*(int)p.getShieldRadius(),2*(int)p.getShieldRadius());
            }

            for (Bullet b:p.getBullets()){
                if (b.getDir()=="up" || b.getDir()=="down" || b.getDir()=="left" || b.getDir()=="right" || b.getDir()=="down/right") {
                    g.drawImage(b.getPic(), (int) b.getX(), (int) b.getY(), null);
                }
                else if (b.getDir()=="up/right"){
                    g.drawImage(b.getPic(), (int) b.getX()-5, (int) b.getY()-35, null);
                }
                else if (b.getDir()=="up/left"){
                    g.drawImage(b.getPic(), (int) b.getX()-35, (int) b.getY()-35, null);
                }
                else{
                    g.drawImage(b.getPic(), (int) b.getX()-40, (int) b.getY(), null);
                }
                //g.setColor(new Color(0,255,0,70));
                //g.fillPolygon(b.getHitbox());
            }
        }
    }

    public void drawBalls(Graphics g){
        for (EnergyBall ball:energyBalls){
            g.drawImage(ball.getFrames().get(ball.getCurrentFrame()),ball.getX(),ball.getY(),null);
        }
    }

    public void drawAmmo(Graphics g){
        for (Player p:players){
            for (int i=0;i<p.getNumOfBullets();i++){
                g.drawImage(bulletPic,p.getX()-10+9*i,p.getY()-25,null);
            }
        }
    }

    public void drawHealth(Graphics g){
        for (Player p:players){
            for (int i=0;i<p.getLives();i++){
                g.drawImage(heartPic,p.getX()-10+12*i,p.getY()-38,null);
            }
        }
    }

    public void drawShields(Graphics g){
        g.setColor(new Color(36, 146, 255,100));
        for (Player p:players){
            int length=(int)((p.getShieldEnergy() / p.getMaxShieldEnergy()) * (25));
            g.fillRect(p.getX()-7,p.getY()-7,length,4);
            if (p.isActiveShield()) {
                g.fillOval(p.getX()+7-(int)p.getShieldRadius(),p.getY()+20-(int)p.getShieldRadius(),2*(int)p.getShieldRadius(),2*(int)p.getShieldRadius());
            }
        }
    }

    public void drawItems(Graphics g){
        for (Powerup power:items){
            g.drawImage(power.getPicture(), power.getX(), power.getY(), null);
        }
    }
}
