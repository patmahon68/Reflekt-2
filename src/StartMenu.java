
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class StartMenu extends JPanel implements ActionListener {
    private Image back, title;
    private Main mainFrame;



    public StartMenu(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        mainFrame = m;
        setSize(1024,768);

        title = new ImageIcon("Screens/Main Menu/title.png").getImage();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();

    }


    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,null);
        g.drawImage(title, 200,200, null);
    }
}