
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Instructions extends JPanel implements MouseMotionListener, MouseListener {
    private Image back;
    private Main mainFrame;
    Image next = new ImageIcon("Screens/Main Menu/next.png").getImage();
    Rectangle playButton=new Rectangle(770,690,next.getWidth(null),next.getHeight(null));


    public Instructions(Main m){
        back = new ImageIcon("Screens/Instructions Page/instructions.png").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
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
        g.drawImage(next, 770, 690, null);
    }

    public void mouseClicked(MouseEvent mouseEvent) {

    }
    public void mousePressed(MouseEvent mouseEvent) {
        if (playButton.contains(mouseEvent.getX(),mouseEvent.getY())){
            mainFrame.showNewScreen("menu");
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {

    }

    public void mouseEntered(MouseEvent mouseEvent) {

    }

    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void mouseDragged(MouseEvent mouseEvent) {

    }

    public void mouseMoved(MouseEvent mouseEvent) {

    }
}