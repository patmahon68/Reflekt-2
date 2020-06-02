import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class LevelSelect extends JPanel implements ActionListener {
    private Image back,arenaSelect;
    private Main mainFrame;



    public LevelSelect(Main m){
        back = new ImageIcon("Screens/Main Menu/storm.gif").getImage();
        back = back.getScaledInstance(1024,768,Image.SCALE_SMOOTH);
        arenaSelect = new ImageIcon("Screens/Level Screen/LevelSelectScreen.png").getImage();
        mainFrame = m;
        setSize(1024,768);
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
        g.drawImage(arenaSelect,0,0,null);
    }
}