//the CharSelect class is the JPanel that serves as a menu for
//choosing which characters will be used by the player
//all functionality of buttons and graphics are in this class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class CharSelect extends JPanel implements MouseMotionListener, MouseListener {
    private Image back,charSelect;//background pic and UI for the menu
    private Main mainFrame;
    private boolean left=false;//whether player 1 and player 2 have chosen their characters yet
    //(left and right mouse buttons)
    private boolean right=false;
    //index of each selection to save which character has been chosen
    private int leftChoice;
    private int rightChoice;
    private ArrayList<Image> choicePics;//all possible choices
    private ArrayList<Image> p1Selects;//highlighted pictures of each character to show that they have been chosen
    private ArrayList<Image> p2Selects;
    private ArrayList<Rectangle> characterSquares;//hitboxes to click on to select the players
    private Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();
    private Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));
    private Rectangle choiceR, choiceL;//the button that has been chosen for each player


    public CharSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.png").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        charSelect = new ImageIcon("Screens/Character Screen/CharacterSelectScreen.png").getImage();
        mainFrame = m;
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
        //get the hitboxes and pictures
        loadSquares();
        loadPics();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }


    public void loadSquares(){
        //initialize all hitboxes
        characterSquares=new ArrayList<>();
        Rectangle rect1=new Rectangle(365,155,95,95);
        Rectangle rect2=new Rectangle(560,155,95,95);
        Rectangle rect3=new Rectangle(365,310,95,95);
        Rectangle rect4=new Rectangle(560,310,95,95);
        Rectangle rect5=new Rectangle(365,480,95,95);
        Rectangle rect6=new Rectangle(560,480,95,95);
        characterSquares.add(rect1);
        characterSquares.add(rect2);
        characterSquares.add(rect3);
        characterSquares.add(rect4);
        characterSquares.add(rect5);
        characterSquares.add(rect6);
    }

    public void loadPics(){
        //initialize all pictures
        choicePics=new ArrayList<>();
        Image pic1=new ImageIcon("Sprites/Giga Guy/gigaGuy0.png").getImage();
        Image pic2=new ImageIcon("Sprites/Peta Pal/petaPal0.png").getImage();
        Image pic3=new ImageIcon("Sprites/Tonic/tonic0.png").getImage();
        Image pic4=new ImageIcon("Sprites/Chronic/chronic0.png").getImage();
        Image pic5=new ImageIcon("Sprites/Lank/lank0.png").getImage();
        Image pic6=new ImageIcon("Sprites/Dark Lank/darkLank0.png").getImage();
        pic1=pic1.getScaledInstance(200,220,Image.SCALE_SMOOTH);
        pic2=pic2.getScaledInstance(200,220,Image.SCALE_SMOOTH);
        pic3=pic3.getScaledInstance(200,300,Image.SCALE_SMOOTH);
        pic4=pic4.getScaledInstance(200,300,Image.SCALE_SMOOTH);
        pic5=pic5.getScaledInstance(200,250,Image.SCALE_SMOOTH);
        pic6=pic6.getScaledInstance(200,250,Image.SCALE_SMOOTH);
        choicePics.add(pic1);
        choicePics.add(pic2);
        choicePics.add(pic3);
        choicePics.add(pic4);
        choicePics.add(pic5);
        choicePics.add(pic6);
        p1Selects= new ArrayList<>();
        Image p1pic1=new ImageIcon("Screens/Character Screen/GigaGuySelected1.png").getImage();
        Image p1pic2=new ImageIcon("Screens/Character Screen/PetaPalSelected1.png").getImage();
        Image p1pic3=new ImageIcon("Screens/Character Screen/TonicSelected1.png").getImage();
        Image p1pic4=new ImageIcon("Screens/Character Screen/ChronicSelected1.png").getImage();
        Image p1pic5=new ImageIcon("Screens/Character Screen/LankSelected1.png").getImage();
        Image p1pic6=new ImageIcon("Screens/Character Screen/DarkLankSelected1.png").getImage();
        p1pic1=p1pic1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1pic2=p1pic2.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1pic3=p1pic3.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1pic4=p1pic4.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1pic5=p1pic5.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1pic6=p1pic6.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p1Selects.add(p1pic1);
        p1Selects.add(p1pic2);
        p1Selects.add(p1pic3);
        p1Selects.add(p1pic4);
        p1Selects.add(p1pic5);
        p1Selects.add(p1pic6);
        p2Selects= new ArrayList<>();
        Image p2pic1=new ImageIcon("Screens/Character Screen/GigaGuySelected2.png").getImage();
        Image p2pic2=new ImageIcon("Screens/Character Screen/PetaPalSelected2.png").getImage();
        Image p2pic3=new ImageIcon("Screens/Character Screen/TonicSelected2.png").getImage();
        Image p2pic4=new ImageIcon("Screens/Character Screen/ChronicSelected2.png").getImage();
        Image p2pic5=new ImageIcon("Screens/Character Screen/LankSelected2.png").getImage();
        Image p2pic6=new ImageIcon("Screens/Character Screen/DarkLankSelected2.png").getImage();
        p2pic1=p2pic1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2pic2=p2pic2.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2pic3=p2pic3.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2pic4=p2pic4.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2pic5=p2pic5.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2pic6=p2pic6.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        p2Selects.add(p2pic1);
        p2Selects.add(p2pic2);
        p2Selects.add(p2pic3);
        p2Selects.add(p2pic4);
        p2Selects.add(p2pic5);
        p2Selects.add(p2pic6);
    }

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(charSelect,0,0,null);
        if (left && right) {//only display the next button if both players have chosen a character
            g.drawImage(next, 770, 690, null);
        }
        if (left){
            //if the player has chosen, display the appropriate highlighted box around the choice
            //and display the selected image of the character that they have chosen
            g.drawImage(choicePics.get(leftChoice),85,400,null);
            g.drawImage(p1Selects.get(leftChoice), choiceL.x, choiceL.y,null);
        }
        if (right){//same but for player 2
            g.drawImage(choicePics.get(rightChoice),770,350,null);
            g.drawImage(p2Selects.get(rightChoice), choiceR.x, choiceR.y,null);
        }
    }

    //accessors to know which player sprites to use when making the Map and Players

    public int getP1(){
        return leftChoice+1;
    }

    public int getP2(){
        return rightChoice+1;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton()==MouseEvent.BUTTON3){
            for (Rectangle r:characterSquares){
                //player 2 chooses
                if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                    right=true;//player 2 has chosen
                    //save the choice that has been mae
                    rightChoice=characterSquares.indexOf(r);
                    choiceR =r;
                }
            }

        }
        if (mouseEvent.getButton()==MouseEvent.BUTTON1){//same as right click but for left click (player 1)
            for (Rectangle r:characterSquares){
                if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                    left=true;
                    leftChoice=characterSquares.indexOf(r);
                    choiceL =r;
                }
            }
            if (playButton.contains(mouseEvent.getX(),mouseEvent.getY()) && left && right){
                //pressed next after each player has chosen a character
                mainFrame.showNewScreen("level");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }

}