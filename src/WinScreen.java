
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WinScreen extends JPanel implements MouseMotionListener, MouseListener {
    private Image back, title;
    private Main mainFrame;
    Image start = new ImageIcon("Screens/Main Menu/startButton.png").getImage();
    Image Instructions = new ImageIcon("Screens/Main Menu/instructionsButton.png").getImage();
    Rectangle startButton = new Rectangle(350,370,start.getWidth(null),start.getHeight(null));
    Rectangle instructionsBtn=new Rectangle(150,600,Instructions.getWidth(null),Instructions.getHeight(null));



    public WinScreen(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        mainFrame = m;
        setSize(1024,768);
        addMouseMotionListener(this);
        addMouseListener(this);
        title = new ImageIcon("Screens/Main Menu/title.png").getImage();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(title, 200,200, null);
        g.drawImage(start,350,370,null);
        g.drawImage(Instructions,175,500,null);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
    }
    
    public void mousePressed(MouseEvent mouseEvent) {
        if (startButton.contains(mouseEvent.getX(),mouseEvent.getY())){
            mainFrame.showNewScreen("char");
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