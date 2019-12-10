//Eric Yoder
//Making connect 4
//this java will have gui and simple testing main for now
//display will be based on two player game with new game button and winner display text only
//to do: refresh display is abomination, will be used for testing only.  instead on click, square will be updated.
//make click method, then add listener new game also done CHECK CHECK
//currently doing: board state and winner finder hmmm.

package connect4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class Connect4 extends JFrame{
    
    //let's make everything public to make things simpler - it's simple enough this should hopefully be safe 
    //GUI vars
    public JButton[] moveSelect = new JButton[7];
    public JTextField[][] boardSquares = new JTextField[6][7]; //To begin with, the display will use colored text fields, white, red, or black
    public JPanel board;
    public JPanel moves;
    public JButton newGame;
    public JTextField winnerText;
    
    //board vars
    public int[][] boardRep = new int[6][7];
    public int[] currentFilled = new int[7]; //the height of each column, starting at 0
    public boolean blackToPlay = true;
    public int[][] horizontal4s = new int[6][4]; //all the winning 4 spots. keep tally -4 to 4, so that iteration isn't required
    public int[][] vertical4s = new int[3][7];
    public int[][] diagonalTLBR4s = new int[3][4];
    public int[][] diagonalTRBL4s = new int[3][4];
    public int winner = 0; //don't forget to edit new game as well 
    
    //method to reset board
    public void resetBoard(){
        for(int i = 0;i<6;i++){  //empty board 
            for (int j = 0; j < 7; j++) {
                boardRep[i][j] = 0;
            }
        }
        
        for (int i = 0; i < 7; i++) { // set column heights to 0
            currentFilled[i] = 0;
        }
        
        blackToPlay = true;  //black goes first for now
        refreshDisplay(); 
        
    }
    
    //method to refreshDisplay after changes have been made
    public void refreshDisplay(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                switch (boardRep[i][j]) { //maybe not best style to have default red but whatever (change board
                    case 0:
                        boardSquares[i][j].setBackground(Color.WHITE);
                        break;
                    case 1:
                        boardSquares[i][j].setBackground(Color.BLACK);
                        break;
                    default:
                        boardSquares[i][j].setBackground(Color.RED);
                        break;
                }
            }
        }
        
        if (blackToPlay){  //change to move squares
            for (int i = 0; i < 7; i++) {
                moveSelect[i].setBackground(Color.BLACK);
            }
        } else{
            for (int i = 0; i < 7; i++) {
                moveSelect[i].setBackground(Color.RED);
            }
        }
            
        
    }
    
    
    //Let's add listeners to the moveSelect buttons
    
    //add button clicked method Done? YESish
    
    private void buttonClicked(int c){
        boardRep[currentFilled[c]][c] = blackToPlay ? 1:-1; //adjust board rep
        if (blackToPlay){
            boardSquares[5-currentFilled[c]][c].setBackground(Color.BLACK);
            for (int i = 0; i < 7; i++) {
                moveSelect[i].setBackground(Color.RED);
            }
            int y = 5-currentFilled[c];
            
            
            for (int i = 0; i < 4; i++) { //check horizontal for black win
                if ((c-3 + i)>-1 && c-3+i<4){
                    horizontal4s[y][c-3+i]++;
                    if (horizontal4s[y][c-3+i] == 4){
                        winnerText.setText("BLACK WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check vertical for black win
                if (y-3+i>-1 && y-3+i<3){
                    vertical4s[y-3+i][c]++;
                    if (vertical4s[y-3+i][c] == 4){
                        winnerText.setText("BLACK WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check TLBR diag for black win
                if (y-3+i>-1 && y-3+i<3 && c-3+i>-1 && c-3+i<4){
                    diagonalTLBR4s[y-3+i][c-3+i]++;
                    if (diagonalTLBR4s[y-3+i][c-3+i] == 4){
                        winnerText.setText("BLACK WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check TRBL diag for black win
                if (y-3+i>-1 && y-3+i<3 && c+3-i>-1 && c+3-i < 4){
                    diagonalTRBL4s[y-3+i][c+3-i]++;
                    if (diagonalTRBL4s[y-3+i][c+3-i] == 4){
                        winnerText.setText("BLACK WINS");
                        disableMoves();
                    }
                }
            }
            
            
            
        }
        else{
            boardSquares[5-currentFilled[c]][c].setBackground(Color.RED);
            for (int i = 0; i < 7; i++) {
                moveSelect[i].setBackground(Color.BLACK);
            }
            
            int y = 5-currentFilled[c];
            
            
            for (int i = 0; i < 4; i++) { //check horizontal for black win
                if ((c-3 + i)>-1 && c-3+i<4){
                    horizontal4s[y][c-3+i]--;
                    if (horizontal4s[y][c-3+i] == -4){
                        winnerText.setText("RED WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check vertical for black win
                if (y-3+i>-1 && y-3+i<3){
                    vertical4s[y-3+i][c]--;
                    if (vertical4s[y-3+i][c] == -4){
                        winnerText.setText("RED WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check TLBR diag for black win
                if (y-3+i>-1 && y-3+i<3 && c-3+i>-1 && c-3+i<4){
                    diagonalTLBR4s[y-3+i][c-3+i]--;
                    if (diagonalTLBR4s[y-3+i][c-3+i] == -4){
                        winnerText.setText("RED WINS");
                        disableMoves();
                    }
                }
            }
            
            for (int i = 0; i < 4; i++) { //check TRBL diag for black win
                if (y-3+i>-1 && y-3+i<3 && c+3-i>-1 && c+3-i < 4){
                    diagonalTRBL4s[y-3+i][c+3-i]--;
                    if (diagonalTRBL4s[y-3+i][c+3-i] == -4){
                        winnerText.setText("RED WINS");
                        disableMoves();
                    }
                }
            }
            
            
        }
        blackToPlay = !blackToPlay;
        currentFilled[c]++;
        if(currentFilled[c] == 6)
            moveSelect[c].setEnabled(false);
    }
    
    private void disableMoves(){
        for (int i = 0; i < 7; i++) {
            moveSelect[i].setEnabled(false);
        }
    }
    
    private void newGame(){ //adding new game method.  The turn to play will carry over
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                boardSquares[i][j].setBackground(Color.WHITE);
                boardRep[i][j] = 0;
                
            }
        }
        for (int i = 0; i < 7; i++) {
            currentFilled[i] = 0;
            moveSelect[i].setEnabled(true);
        }
        winnerText.setText("No Winner Yet"); //clear winner text and 4s tracker
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                horizontal4s[i][j] = 0;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                vertical4s[i][j] = 0;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                diagonalTLBR4s[i][j] = 0;
                diagonalTRBL4s[i][j] = 0;
            }
        }
    }
    
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
            int x = i; 
            moveSelect[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    buttonClicked(x);
                }
            });
        }
        
        //new game button
        newGame = new JButton();
        newGame.setBounds(50, 50, 200, 50);
        newGame.setText("New Game");
        display.add(newGame);
        newGame.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent event){
                newGame();
            }
            
        });
        
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
