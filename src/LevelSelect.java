//The levelselect class is a JPanel used as a menu to select
//which level will be played on by the players
//all interactions with buttons and such in the level menu is in this class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class LevelSelect extends JPanel implements MouseMotionListener, MouseListener {
    private Image back,arenaSelect;//background pic and UI for the menu
    private Main mainFrame;
    Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();//pic for next button
    Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));
    //hitbox of the next button
    private int version=0;//default version
    private boolean picked=false;//player has not picked
    private Rectangle choice;//currently highlighted hitbox
    private ArrayList<Rectangle> choices;//all hitboxes that the player can click on to choose a map
    private ArrayList<Image> boldImages;//when the player chooses a level, a higher quality version of the map
    //is displayed there, as they are normally blurry

    public LevelSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        arenaSelect = new ImageIcon("Screens/Level Screen/LevelSelectScreen.png").getImage();
        mainFrame = m;
        //add hitboxes and all pictures that aren't already in the level select UI
        addChoiceBoxes();
        loadPics();
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void loadPics(){
        //get all pictures into the class (the higher quality pictures of all the maps)
        boldImages=new ArrayList<>();
        Image pic1=new ImageIcon("Screens/Level Screen/SewerSelected.png").getImage();
        Image pic2=new ImageIcon("Screens/Level Screen/ForestSelected.png").getImage();
        Image pic3=new ImageIcon("Screens/Level Screen/UnderwaterSelected.png").getImage();
        Image pic4=new ImageIcon("Screens/Level Screen/DesertSelected.png").getImage();
        Image pic5=new ImageIcon("Screens/Level Screen/CitySelected.png").getImage();
        Image pic6=new ImageIcon("Screens/Level Screen/CastleSelected.png").getImage();
        boldImages.add(pic1);
        boldImages.add(pic2);
        boldImages.add(pic3);
        boldImages.add(pic4);
        boldImages.add(pic5);
        boldImages.add(pic6);
    }

    public void addChoiceBoxes(){
        //add the hitboxes for the buttons that select the maps
        choices=new ArrayList<>();
        Rectangle rect1=new Rectangle(50,130,260,200);
        Rectangle rect2=new Rectangle(380,130,260,200);
        Rectangle rect3=new Rectangle(710,130,260,200);
        Rectangle rect4=new Rectangle(50,440,260,200);
        Rectangle rect5=new Rectangle(380,440,260,200);
        Rectangle rect6=new Rectangle(710,440,260,200);
        choices.add(rect1);
        choices.add(rect2);
        choices.add(rect3);
        choices.add(rect4);
        choices.add(rect5);
        choices.add(rect6);
    }

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(arenaSelect,0,0,null);
        if (picked) {
            //only display the next button if a map has been selected
            g.drawImage(next, 770, 690, null);
            g.drawImage(boldImages.get(choices.indexOf(choice)),choice.x,choice.y,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (playButton.contains(mouseEvent.getX(),mouseEvent.getY()) && picked){
            //only move forward if the player has picked a map
            mainFrame.showNewScreen("game");
        }
        for (Rectangle r:choices){
            if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                //if the player chooses a map, save their choice
                picked=true;
                version=choices.indexOf(r);
                choice=r;
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

    //accessor to know which version to use when creating the map
    public int getVersion(){return version;}
}