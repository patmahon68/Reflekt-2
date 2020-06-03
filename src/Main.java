
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


    Icon next = new ImageIcon("Screens/Main Menu/next.png");

    JButton nextButton=new JButton(next);

    //all pages
    StartMenu menuPage =new StartMenu(this);
    CharSelect charPage = new CharSelect(this);
    LevelSelect levelPage = new LevelSelect(this);
    //Map newGame=new Map(0,this,2);

    public Main() {
        super("REFLEKT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,798);
        setLayout(new BorderLayout());
        myTimer = new javax.swing.Timer(10, this);// trigger every 10 ms

        add(menuPage);


        add(charPage);
        nextButton.addActionListener(this);
        charPage.add(nextButton);//start is for pvp

        add(levelPage);

        //add(newGame);
        //newGame.setLayout(null);



        cards = new JPanel(cLayout);//panel to hold the other panels
        cards.add(menuPage, "menu");
        //cards.add(newGame,"game");
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
        Object source = evt.getSource();

        if(source==nextButton){
            showNewScreen("level");
        }

        cards.repaint();

    }
    public void showNewScreen(String name){
        cLayout.show(cards,name);//show the new panel
        if (name=="game"){
            System.out.println("game time");
            Map newGame=new Map(levelPage.getVersion(),this,2);
            add(newGame);
            newGame.setLayout(null);
            cards.add(newGame,"game");
            cLayout.show(cards,"game");
            newGame.requestFocusInWindow();//new panel needs focus

        }
        if (name == "char") {
            System.out.println();
            charPage.requestFocusInWindow();
            System.out.println(charPage.isVisible());
        }
        if(name == "level"){
            levelPage.requestFocusInWindow();
        }
    }


    public static void main(String[] arguments) {
        Main frame = new Main();
    }
}

