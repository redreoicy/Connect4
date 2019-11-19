//Eric Yoder
//Making connect 4
//this java will have gui and simple testing main for now
//display will be based on two player game with new game button and winner display text only

package connect4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Connect4 extends JFrame{
    
    //let's make everything public to make things simpler - it's simple enough this should hopefully be safe 
    public JButton[] moveSelect = new JButton[7];
    public JTextField[][] boardSquares = new JTextField[6][7]; //To begin with, the display will use colored text fields, white, red, or black
    public JPanel board;
    public JPanel moves;
    public JButton newGame;
    public JTextField winnerText;
    
    
    //constructor
    public Connect4(){
        createBoard();
    }
    
    //board initializer
    private void createBoard(){
        //outer pane
        Container display = getContentPane();
        display.setLayout(null);
        
        
        //board panel
        board = new JPanel();
        board.setLayout(null);
        board.setBounds(200, 300, 700, 600);
        display.add(board);
        
        //text squares (circles later), display board position 
        
        for (int i = 0; i < 6; i++) {   //for now using strict 6 by 7 array to hold board
            for (int j = 0; j < 7; j++) {
                boardSquares[i][j] = new JTextField();
                boardSquares[i][j].setBounds(j*100, i*100, 100, 100);
                boardSquares[i][j].setEditable(false);
                boardSquares[i][j].setBackground(Color.WHITE);
                board.add(boardSquares[i][j]);
            }
        }
        
        //move panel
        moves = new JPanel();
        moves.setLayout(null);
        moves.setBounds(200, 150, 700, 100);
        display.add(moves);
        
        //move Options
        for (int i = 0; i < 7; i++) {
            moveSelect[i] = new JButton();
            moveSelect[i].setBounds(i*100, 0, 100, 100);
            moveSelect[i].setBackground(Color.BLACK);
            moves.add(moveSelect[i]);
        }
        
        //new game button
        newGame = new JButton();
        newGame.setBounds(50, 50, 200, 50);
        newGame.setText("New Game");
        display.add(newGame);
        
        //winner text 
        winnerText = new JTextField();
        winnerText.setBounds(400, 50, 400, 50);
        winnerText.setText("No Winner Yet");
        winnerText.setHorizontalAlignment(JLabel.CENTER);
        display.add(winnerText);
        
        setSize(1000,1000); // AHAHA - must set visible at the end or things aren't visible
        setVisible(true);
        
    }
    
    

    public static void main(String[] args) {
        Connect4 application = new Connect4();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
