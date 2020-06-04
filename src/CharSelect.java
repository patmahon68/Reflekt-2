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
    private ArrayList<Rectangle> characterSquares;
    Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();
    private Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));


    public CharSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        charSelect = new ImageIcon("Screens/Character Screen/CharacterSelectScreen.png").getImage();
        mainFrame = m;
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
        loadSquares();
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

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(charSelect,0,0,null);
        if (left && right) {
            g.drawImage(next, 770, 690, null);
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
        System.out.println(mouseEvent.getX()+" "+mouseEvent.getY()+" "+mouseEvent.getButton());
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