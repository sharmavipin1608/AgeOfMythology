/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vipinsharma
 */

/*
    1. Placement of victory point cubes.
       Being done in VictoryCardWindow.java
    2. Action Card draw.
       Select from permanent action cards.
    3. Three rounds of action card play.
    4. Resource spoilage.
    5. Discarding.
    6. Starting player rotation
    */
public class Turn {
    private int startingPlayer;
    private int turnNumber = 0;
    private int currentPlayer = 0;
    private int round = 0;
    private boolean roundContinuing = true;
    boolean skip = false;
    
    public Turn(int turnNum)
    {
        turnNumber = turnNum;
        setStartingPlayer();
        System.out.println("Starting player : " + startingPlayer);
        currentPlayer = startingPlayer;
    }
    
    //1. Set players in order
    public void setStartingPlayer()
    {
//        if(turnNumber == 0)
//            startingPlayer = 0;
//        else
//            startingPlayer = (startingPlayer==2)?0:startingPlayer+1;
        startingPlayer = turnNumber % 3;
    }
    
    //2.Draw Cards
    public static void drawCardsForTheTurn()
    {
        for(BoardPanel boardPanel : AgeOfMythology.cultureBoards)
        {
            PermanentActionCard permActionCard = new PermanentActionCard(boardPanel.getCulture());
            boardPanel.permanentActionCard = permActionCard;
            if(boardPanel.getIndexCulture() == 0){
                System.out.println("Coming here");
                permActionCard.displayPermanentActionCardWindow();
            }
            else{
                System.out.println("Culture : " + boardPanel.getCulture());
                boardPanel.setActionCardsForTurn(permActionCard.drawCards(boardPanel.numberOfCardsToBeDrawn(),boardPanel.getIndexCulture()));
            }
        }
    }
    
    //3. start rounds
    public void play()
    {
        if(skip){
            operationsToBePerformedAtEndOfTurn();
            return;
        }
        System.out.println("Current player : " + currentPlayer + " Strtng : " + startingPlayer);
        AgeOfMythology.setCurrentChanceIndex(currentPlayer);
        if(currentPlayer == startingPlayer)
        {
            round++;
            System.out.println("Round : " + round);
            if(round > 3){
                roundContinuing = false;
                if(!AgeOfMythology.norseNextAgeGodCardPlayed)
                {
                    operationsToBePerformedAtEndOfTurn();
                }
                else
                {
                    checkIfNorseNextAgeGodCardPlayed();
                    skip = true;
                }
                    
            }
        }
        
        if(roundContinuing)
        {
            System.out.println(Constants.cultures[AgeOfMythology.playerShift[currentPlayer]]);

            if(currentPlayer == 0)
            {
                currentPlayer = (currentPlayer + 1)%3;
                AgeOfMythology.returnCurrentBoardPlayer().displayChosenCardsWindow();
            }
            else{
                BoardPanel boardPanel = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[currentPlayer]];
                currentPlayer = (currentPlayer + 1)%3;
                boardPanel.playCard();
            }
        }
        
        //System.out.println("Current player : " + currentPlayer);
    }
    
    private void operationsToBePerformedAtEndOfTurn()
    {
        AgeOfMythology.returnCurrentBoardPlayer().resetChosenCard();
        JOptionPane.showMessageDialog(new JFrame(), "Turn " + (turnNumber + 1) + " completed."
                + " \n1. Place victory point cube on Victory Point Cards."
                + "\n2. Select Action Cards for the turn."
                + "\n3. Play Cards."
                + "\n\nStarting Player for the turn : " + Constants.cultures[AgeOfMythology.playerShift[(startingPlayer + 1) % 3]]);
        AgeOfMythology.placeVictoryPointCubes((turnNumber + 1) % 3);
        //AgeOfMythology.returnCurrentBoardPlayer().setNumOfBattleUnitsRecruited(0);
        spoilage();
    }
    
    //true if no card is to be played
    public void checkIfNorseNextAgeGodCardPlayed()
    {
//        if (AgeOfMythology.returnCurrentBoardPlayer().getCulture().equals("Norse")) {
//            AgeOfMythology.returnCurrentBoardPlayer().displayChosenCardsWindow();
//        } else {
//            for (BoardPanel boardPanel : AgeOfMythology.cultureBoards) {
//                if (boardPanel.getCulture().equals("Norse")) {
//                    boardPanel.playCard();
//                }
//            }
//        }
        System.out.println("checkIfNorseNextAgeGodCardPlayed");
        for (BoardPanel boardPanel : AgeOfMythology.cultureBoards) {
                if (boardPanel.getCulture().equals("Norse")) {
                    currentPlayer = boardPanel.getIndexCulture();
                    System.out.println("current player : " + 0);
                    if(currentPlayer == 0)
                        AgeOfMythology.returnCurrentBoardPlayer().displayChosenCardsWindow();
                    else
                        boardPanel.playCard();
                }
            }
        System.out.println("Came here");
        AgeOfMythology.norseNextAgeGodCardPlayed = false;
        
    }
    
    public static void nextRound()
    {
        
    }
    
    public void spoilage(){
        int spoilageLimit = 5;
        for(BoardPanel boardPanel : AgeOfMythology.cultureBoards){
            if(boardPanel.buildingsCreated.get("Storehouse")){
                spoilageLimit = 8;
            }
            HashMap<String,Integer> cultureResourceMap = boardPanel.cultureResources();
            for(String resource : cultureResourceMap.keySet()){
                if(cultureResourceMap.get(resource) > spoilageLimit){
                    int gap = cultureResourceMap.get(resource) - spoilageLimit;
                    for(int index = 0;index < gap;index++){
                        boardPanel.removeResourceCube(resource);
                    }
                }
            }
        }
    }
}
