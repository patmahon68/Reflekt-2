//Alex MacKay and Pat Mahon
//The Main class holds all the other JPanels of the game as cards and is what allows switching
//between every part of the game and the initialization of every part
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
    //layout to hold all the cards (pages) of the game
    JPanel cards;
    //all buttons



    //all pages that can be initialized from the start (the map class needs information from charPage
    //and levelPage before it can be made as the choices the user makes in them are parameters for the map)

    //all pages

    StartMenu menuPage =new StartMenu(this);
    CharSelect charPage = new CharSelect(this);
    LevelSelect levelPage = new LevelSelect(this);
    Instructions instructions = new Instructions(this);

    public Main() {
        super("REFLEKT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,798);
        setLayout(new BorderLayout());
        myTimer = new javax.swing.Timer(10, this);// trigger every 10 ms

        //add the pages that currently exist
        add(menuPage);

        add(instructions);

        add(charPage);

        add(levelPage);


        //initialize the card layout and add what pages exist so far to it


        cards = new JPanel(cLayout);//panel to hold the other panels
        cards.add(menuPage, "menu");
        cards.add(instructions,"ins");
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
        cards.repaint();//repaint the current active card

    }
    public void showNewScreen(String name){
        cLayout.show(cards,name);//show the new panel
        if (name=="game"){
            //time for the map to be created
            Map newGame=new Map(levelPage.getVersion(),this,charPage.getP1(),charPage.getP2());
            //create the map using the info from the previous 2 menus
            add(newGame);
            newGame.setLayout(null);
            //add the map to cards and initialize it
            cards.add(newGame,"game");
            //switch to showing the game
            cLayout.show(cards,"game");
            newGame.requestFocusInWindow();//new panel needs focus
        }
        if (name == "char") {//basic changing of windows between menu pages
            charPage.requestFocusInWindow();
        }
        if(name == "level"){
            levelPage.requestFocusInWindow();
        }
        if(name == "ins"){
            instructions.requestFocusInWindow();
        }
    }
    public static void main(String[] arguments) {
        Main frame = new Main();//creating the Main to go through the constructor
    }
}

