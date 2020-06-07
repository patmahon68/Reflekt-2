
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WinScreen extends JPanel implements MouseMotionListener, MouseListener {
    private Image back, title;
    private Main mainFrame;
    Image rematch = new ImageIcon("Screens/Win Screens/Rematch.png").getImage();
    Rectangle startButton = new Rectangle(600,690, rematch.getWidth(null), rematch.getHeight(null));




    public WinScreen(Main m){
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
        g.drawImage(rematch,600,690,null);
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