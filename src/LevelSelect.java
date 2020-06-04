import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class LevelSelect extends JPanel implements MouseMotionListener, MouseListener {
    private Image back,arenaSelect;
    private Main mainFrame;
    Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();
    Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));
    private int version=0;
    private boolean picked=false;
    ArrayList<Rectangle> choices;




    public LevelSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        arenaSelect = new ImageIcon("Screens/Level Screen/LevelSelectScreen.png").getImage();
        mainFrame = m;
        addChoiceBoxes();
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void addChoiceBoxes(){
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
            g.drawImage(next, 770, 690, null);
        }
        System.out.println("levels");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (playButton.contains(mouseEvent.getX(),mouseEvent.getY()) && picked){
            mainFrame.showNewScreen("game");
        }
        for (Rectangle r:choices){
            if (r.contains(mouseEvent.getX(),mouseEvent.getY())){
                picked=true;
                version=choices.indexOf(r);
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

    public int getVersion(){return version;}
}