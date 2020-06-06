
import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
public class Main extends JFrame implements ActionListener{

    javax.swing.Timer myTimer;
    CardLayout cLayout = new CardLayout();
    JPanel cards;
    //all buttons



    //all pages
    StartMenu menuPage =new StartMenu(this);
    CharSelect charPage = new CharSelect(this);
    LevelSelect levelPage = new LevelSelect(this);

    public Main() {
        super("REFLEKT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,798);
        setLayout(new BorderLayout());
        myTimer = new javax.swing.Timer(10, this);// trigger every 10 ms

        add(menuPage);


        add(charPage);


        add(levelPage);



        cards = new JPanel(cLayout);//panel to hold the other panels
        cards.add(menuPage, "menu");
        cards.add(charPage ,"char");
        cards.add(levelPage,"level");
        add(cards);
        setResizable(false);
        setVisible(true);
    }


    public void start(){
        myTimer.start();
    }
    public void stop(){
        myTimer.stop();
    }
    public void actionPerformed(ActionEvent evt){
        //respond to each button
        cards.repaint();

    }
    public void showNewScreen(String name){
        cLayout.show(cards,name);//show the new panel
        if (name=="game"){
            Map newGame=new Map(levelPage.getVersion(),this,charPage.getP1(),charPage.getP2());
            add(newGame);
            newGame.setLayout(null);
            cards.add(newGame,"game");
            cLayout.show(cards,"game");
            newGame.requestFocusInWindow();//new panel needs focus


        }
        if (name == "char") {
            charPage.requestFocusInWindow();
        }
        if(name == "level"){
            levelPage.requestFocusInWindow();
        }
    }
    public static void main(String[] arguments) {
        Main frame = new Main();
    }
}

