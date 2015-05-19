/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class AgeOfMythology {//implements ActionListener {

    static JFrame culture;
    static JFrame frame, tilesFrame;
    static BoardPanel greekBoard, norseBoard, egyptBoard;
    static BoardPanel[] cultureBoards = new BoardPanel[3];
    static int cpIndex;
    static String[] cultures = {"Greek", "Norse", "Egypt"};
    static boolean check = true;
    static JPanel boardPanel;
    static HashMap<String, Object> prodGrid = new HashMap<String, Object>();
    static int[] resourceTilesDeck;
    static int[] chosenTiles;
    static int currentPlayer = 1;
    static int[] playerShift = new int[3];
    static boolean forward = true;
    static boolean automate = true;
    static SelectCulture selectedCulture = new SelectCulture();
    static ButtonPanel buttonPanel = new ButtonPanel();
    int round = 0;
    static int turnNumber = 0;
    static Turn turn;
    static int currentChanceIndex;
    static boolean norseNextAgeGodCardPlayed = false;
    
    public static int getCurrentChanceIndex()
    {
        return currentChanceIndex;
    }
    
    public static void setCurrentChanceIndex(int index)
    {
        currentChanceIndex = index;
    }
    
    public void setRound(int number)
    {
        round = number;
    }
    
    public int getRound()
    {
        return round;
    }

    public static boolean getAutomate() {
        return automate;
    }
    
    public static void setAutomate(boolean mode)
    {
        automate = mode;
    }

    public static BoardPanel returnCurrentBoardPlayer() {
        return (BoardPanel) boardPanel;
    }

    public static void main(String[] args) {
        AgeOfMythology aom = new AgeOfMythology();
    }
    
    public static int[] selectResourceTiles(int numOfTiles)
    {
        return GameUtilities.selectResourceTiles(resourceTilesDeck,numOfTiles);
    }
    
    public AgeOfMythology() {
        resourceTilesDeck = Constants.resourceTiles;
        chosenTiles = selectResourceTiles(18);
        tilesFrame = TilesWindow.initializeTilesWindow(chosenTiles);
    }

    public static void setPlayersInOrder(String cultureChosen, int start)
    {
        cpIndex = start;
        System.out.println("cpIndex : " + cpIndex);
        for (int i = 0; i < 3; i++) {
            playerShift[i] = start;
            start++;
            if (start > 2) {
                start = 0;
            }
        }
        intializeBoards(cultureChosen);
    }
    
    public static void intializeBoards(String cultureChosen)
    {        
        frame = new JFrame(cultureChosen);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 800);

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        greekBoard = new BoardPanel("Greek", Constants.greekProdGrid);
        norseBoard = new BoardPanel("Norse", Constants.norseProdGrid);
        egyptBoard = new BoardPanel("Egypt", Constants.egyptProdGrid);

        cultureBoards[0] = greekBoard;
        cultureBoards[1] = norseBoard;
        cultureBoards[2] = egyptBoard;
        
        for(int i=0;i<3;i++)
        {
            ((BoardPanel)cultureBoards[playerShift[i]]).setIndexCulture(i);
            //System.out.println(((BoardPanel)cultureBoards[playerShift[i]]).getCulture() + ":" + ((BoardPanel)cultureBoards[playerShift[i]]).getIndexCulture());
        }
        
        boardPanel = cultureBoards[cpIndex];
        frame.add(boardPanel);
        frame.add(buttonPanel.getButtonPanel());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void setNextPlayerForExplore()
    {
        
    }
    
    public static void setNextPlayerInWindow()
    {
        //System.out.println("load next player");
        if (cpIndex == 2) {
            cpIndex = 0;
        } else {
            cpIndex++;
        }
        //System.out.println("Index : " + cpIndex);

        boardPanel = cultureBoards[cpIndex];

        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        frame.setTitle(cultures[cpIndex]);

        frame.add(boardPanel);
        frame.add(buttonPanel.getButtonPanel());
    }
    
    public static JPanel returnNextPlayer() {
        int boardIndex = playerShift[currentPlayer];
        //System.out.println("Current board : " + cultures[boardIndex]);
        if (currentPlayer == 0) {
            automate = false;
        } else {
            automate = true;
        }
        if (forward) {
            boardPanel = cultureBoards[boardIndex];
            if (currentPlayer == 2) {
                forward = false;
            } else {
                currentPlayer++;
            }
        } else {
            boardPanel = cultureBoards[boardIndex];
            if (currentPlayer == 0) {
                forward = true;
            } else {
                currentPlayer--;
            }
        }
        return boardPanel;
    }
    
    public static JPanel currentBoard()
    {
        return boardPanel;
    }
    
    public static JFrame returnTilesFrame()
    {
        return tilesFrame;
    }
    
    public static void play()
    {
        System.out.println("AgeOfMythology.play()");
        turn.play();
    }
    
    public static void incrementTurn()
    {
        System.out.println("Turn : " + turnNumber);
        turn = new Turn(turnNumber);
        turnNumber++;
        play();
    }
    
    public static void placeVictoryPointCubes(int startingIndex){
        VictoryPointCards vc = VictoryPointCards.getInstance();
        vc.enableVictoryCards(startingIndex);
        vc.placeCubesForTheTurn();
    }
}
