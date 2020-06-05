import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class CharSelect extends JPanel implements MouseMotionListener, MouseListener {
    private Image back,charSelect;
    private Main mainFrame;
    private boolean left=false;
    private boolean right=false;
    private int leftChoice;
    private int rightChoice;
    private ArrayList<Image> choicePics;
    private ArrayList<Rectangle> characterSquares;
    private Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();
    private Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));


    public CharSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.png").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        charSelect = new ImageIcon("Screens/Character Screen/CharacterSelectScreen.png").getImage();
        mainFrame = m;
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
        loadSquares();
        loadPics();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }


    public void loadSquares(){
        characterSquares=new ArrayList<>();
        Rectangle rect1=new Rectangle(370,155,95,95);
        Rectangle rect2=new Rectangle(560,155,95,95);
        Rectangle rect3=new Rectangle(370,310,95,95);
        Rectangle rect4=new Rectangle(560,310,95,95);
        Rectangle rect5=new Rectangle(370,480,95,95);
        Rectangle rect6=new Rectangle(560,480,95,95);
        characterSquares.add(rect1);
        characterSquares.add(rect2);
        characterSquares.add(rect3);
        characterSquares.add(rect4);
        characterSquares.add(rect5);
        characterSquares.add(rect6);
    }

    public void loadPics(){
        choicePics=new ArrayList<>();
        Image pic1=new ImageIcon("Sprites/Giga Guy/Giga Guy sprite sheet002.png").getImage();
        Image pic2=new ImageIcon("Sprites/Peta Pal/Peta Pal sprite sheet002.png").getImage();
        Image pic3=new ImageIcon("Sprites/Tonic/tonic sprite sheet010.png").getImage();
        Image pic4=new ImageIcon("Sprites/Chronic/chronic sprite sheet010.png").getImage();
        Image pic5=new ImageIcon("Sprites/Lank/lank sprite sheet002.png").getImage();
        Image pic6=new ImageIcon("Sprites/Dark Lank/Dark Lank sprite sheet002.png").getImage();
        pic1=pic1.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        pic2=pic2.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        pic3=pic3.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        pic4=pic4.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        pic5=pic5.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        pic6=pic6.getScaledInstance(120,150,Image.SCALE_SMOOTH);
        choicePics.add(pic1);
        choicePics.add(pic2);
        choicePics.add(pic3);
        choicePics.add(pic4);
        choicePics.add(pic5);
        choicePics.add(pic6);
    }

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(charSelect,0,0,null);
        System.out.println("aaaa");
        if (left && right) {
            g.drawImage(next, 770, 690, null);
        }
        if (left){
            g.drawImage(choicePics.get(leftChoice),115,225,null);
        }
        if (right){
            g.drawImage(choicePics.get(rightChoice),780,225,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton()==MouseEvent.BUTTON3){
            for (Rectangle r:characterSquares){
                if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                    right=true;
                    rightChoice=characterSquares.indexOf(r);
                }
            }

        }
        if (mouseEvent.getButton()==MouseEvent.BUTTON1){
            for (Rectangle r:characterSquares){
                if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                    left=true;
                    leftChoice=characterSquares.indexOf(r);
                }
            }
            if (playButton.contains(mouseEvent.getX(),mouseEvent.getY()) && left && right){
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