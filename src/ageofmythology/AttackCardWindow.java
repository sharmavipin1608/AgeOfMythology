/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import ageofmythology.BattleCards.ABattleCard;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class AttackCardWindow {
    private JFrame chooseCultureToAttack;
    private int opponentChosen;
    private int areaOfAttack;
    private int opponent1Index;
    private int opponent2Index;
    private int numOfUnitsChosenForBattle =0;
    private int numOfAllowedUnits;
    private boolean godCardPower = false;
    private boolean extraDiceRollForNorse = false;
    private ArrayList<ABattleCard> unitsChosenByAttackerForCurrentBattle = new ArrayList<>();
    private ArrayList<ABattleCard> unitsChosenByDefenderForCurrentBattle = new ArrayList<>();
    
    public AttackCardWindow(int numOfUnitsForBattle,boolean godCard){
        System.out.println("Attack card window : " + numOfUnitsForBattle + godCard);
        numOfAllowedUnits  = numOfUnitsForBattle;
        godCardPower = godCard;
        
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        
        chooseCultureToAttack = new JFrame("Choose culture to Attack");
        //BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        
        //check if Norse has played the card
        if(boardPanel.getCulture().equals("Norse") && godCardPower)
            extraDiceRollForNorse = true;
        
        int currentCultureIndex = boardPanel.getIndexCulture();
        opponent1Index = currentCultureIndex-1;
        if(opponent1Index == -1)
            opponent1Index = 2;
        opponent2Index = (currentCultureIndex+1)%3;
        String opponent1 = Constants.cultures[AgeOfMythology.playerShift[opponent1Index]];
        String opponent2 = Constants.cultures[AgeOfMythology.playerShift[opponent2Index]];
        System.out.println("Opponent1 : " + opponent1 + " Opponent2 : " + opponent2);
        chooseCultureToAttack.setLayout(new BoxLayout(chooseCultureToAttack.getContentPane(),BoxLayout.Y_AXIS));
        //chooseCultureToAttack.setLayout(new GridLayout(4,1));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,2));
        chooseCultureToAttack.setSize(200,500);
        chooseCultureToAttack.add(panel1);
        JButton culture1 = new JButton(opponent1);
        culture1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opponent1 challanged");
                opponentChosen = opponent1Index;
                panel2.setEnabled(true);
                panel1.setEnabled(false);
//                chooseBattleUnitsForSelectedPlayer();
            }
        });
        
        JButton culture2 = new JButton(opponent2);
        culture2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opponent2 challanged");
                opponentChosen = opponent2Index;
                panel2.setEnabled(true);
                panel1.setEnabled(false);
//                chooseBattleUnitsForSelectedPlayer();
            }
        });
        
        panel1.add(culture1);
        panel1.add(culture2);
        chooseCultureToAttack.add(panel1);
        
        panel2.setLayout(new GridLayout(1,3));
        JButton holdingArea = new JButton("Holding Area");
        holdingArea.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                areaOfAttack = Constants.BOARD_HOLDING_AREA;
                panel2.setEnabled(false);
                panel3.setEnabled(true);
            }
        });
        
        JButton productionArea = new JButton("Production Area");
        productionArea.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                areaOfAttack = Constants.BOARD_PRODUCTION_AREA;
                panel2.setEnabled(false);
                panel3.setEnabled(true);
            }
        });
        
        JButton cityArea = new JButton("City Area");
        cityArea.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                areaOfAttack = Constants.BOARD_CITY_AREA;
                panel2.setEnabled(false);
                panel3.setEnabled(true);
            }
        });
        
        panel2.add(holdingArea);
        panel2.add(productionArea);
        panel2.add(cityArea);
        panel2.setEnabled(false);
        chooseCultureToAttack.add(panel2);
        
        panel3.setEnabled(false);
        panel3.setLayout(new GridLayout(boardPanel.getBattleUnits().size(),1));
        for(ABattleCard battleUnit : boardPanel.getBattleUnits())
        {
            //System.out.println(battleUnit.getName());
            JCheckBox checkBox = new JCheckBox(battleUnit.getName());
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            checkBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(((JCheckBox)e.getSource()).isSelected()){
                        System.out.println("Selected : "+battleUnit.getName());
                        numOfUnitsChosenForBattle++;
                        if(numOfUnitsChosenForBattle == numOfAllowedUnits)
                            panel4.setEnabled(true);
                        unitsChosenByAttackerForCurrentBattle.add(battleUnit);
                        System.out.println("Size : " + unitsChosenByAttackerForCurrentBattle.size());
                    }
                    else{
                        System.out.println("UnSelected : "+battleUnit.getName());
                        numOfUnitsChosenForBattle--;
                        if(numOfUnitsChosenForBattle < numOfAllowedUnits)
                            panel4.setEnabled(false);
                        unitsChosenByAttackerForCurrentBattle.remove(battleUnit);
                    }
                        
                }

               
            });
            panel3.add(checkBox);
        }
        chooseCultureToAttack.add(panel3);
        
        panel4.setEnabled(false);
        panel4.setLayout(new GridLayout(1,1));
        JButton battle = new JButton("Start Battle");
        battle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(numOfUnitsChosenForBattle > numOfAllowedUnits){
                    JOptionPane.showMessageDialog(new JFrame(), "Number of Units chosen is more than the "+
                            "number of allowed units.");
                }
                else
                {
                    if(chooseBattleUnitsForDefendingdPlayer())
                    {
                        //start battle here
                        chooseCultureToAttack.setVisible(false);
                        ResolveBattle resolveBattle = new ResolveBattle(currentCultureIndex,opponentChosen,unitsChosenByAttackerForCurrentBattle,unitsChosenByDefenderForCurrentBattle,areaOfAttack,extraDiceRollForNorse);
                        resolveBattle.displayChoices();
                    }
                }
            }
        });
        panel4.add(battle);
        chooseCultureToAttack.add(panel4);
        
        chooseCultureToAttack.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chooseCultureToAttack.pack();
        chooseCultureToAttack.setVisible(true);
        chooseCultureToAttack.setLocationRelativeTo(null);
    }
    
    public boolean chooseBattleUnitsForDefendingdPlayer(){
        ArrayList<ABattleCard> battleUnits = new ArrayList<>();
        BoardPanel boardPanel = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[opponentChosen]];
        int numOfAllowedUnitsForDefender;
        if(godCardPower)
            numOfAllowedUnitsForDefender = numOfAllowedUnits - 2;
        else
            numOfAllowedUnitsForDefender = numOfAllowedUnits;
        int countUnits = 0;
        //Pass the number of units 
        battleUnits.addAll(boardPanel.getBattleUnits());
//        if(battleUnits.size() < numOfAllowedUnits)
//        {
//            System.out.println(boardPanel.getCulture() + " lost the battle because of insufficient units for the battle");
//            return false;
//        }
//        else
//        {
            for(ABattleCard battleCard : battleUnits){
                if(countUnits != numOfAllowedUnitsForDefender){
                    unitsChosenByDefenderForCurrentBattle.add(battleCard);
                    countUnits++;
                }
                else
                    break;
            }
//        }
        System.out.println("unitsChosenByDefenderForCurrentBattle : " +unitsChosenByDefenderForCurrentBattle.size());
        return true;
    }
}
