import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CharSelect extends JPanel implements MouseMotionListener, MouseListener {
    private Image back,charSelect;
    private Main mainFrame;
    private boolean left=false;
    private boolean right=false;


    public CharSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        charSelect = new ImageIcon("Screens/Character Screen/CharacterSelectScreen.png").getImage();
        mainFrame = m;
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }


    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(charSelect,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton()==MouseEvent.BUTTON3){

        }
        if (mouseEvent.getButton()==MouseEvent.BUTTON1){

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