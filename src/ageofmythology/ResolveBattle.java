/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import ageofmythology.BattleCards.ABattleCard;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class ResolveBattle {
    private ArrayList<ABattleCard> unitsChosenByAttackerForCurrentBattle;
    private ArrayList<ABattleCard> unitsChosenByDefenderForCurrentBattle;
    private int attackerIndex;
    private int defenderIndex;
    private int areaOfAttack = 0;
    private int attackNumber = 0;
    private ABattleCard currentAttacker;
    private ABattleCard currentDefender;
    private int attacksWonByAttacker = 0;
    private int attacksWonByDefender = 0;
    private String attacker,defender;
    private JFrame battleWindow;
    private boolean extraDiceRollForNorse = false;
    private JCheckBox trial;
    
    public ResolveBattle(int attackerInd, int defenderInd, ArrayList attackerUnits, ArrayList defenderUnits,int areaOfAttackChosen, boolean extraDiceRoll){
        unitsChosenByAttackerForCurrentBattle = attackerUnits;
        unitsChosenByDefenderForCurrentBattle = defenderUnits;
        attackerIndex = attackerInd;
        defenderIndex = defenderInd;
        areaOfAttack = areaOfAttackChosen;
        extraDiceRollForNorse = extraDiceRoll;
        System.out.println("Area of attack : " + areaOfAttack);
    }
    
    public void displayChoices(){
        attacker = Constants.cultures[AgeOfMythology.playerShift[attackerIndex]];
        defender = Constants.cultures[AgeOfMythology.playerShift[defenderIndex]];
        BoardPanel attackerBoard = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[attackerIndex]];
        BoardPanel defenderBoard = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[defenderIndex]];
        
        System.out.println("===================");
        System.out.println("Attacker : "+attacker + " Defender :  "+defender);
        System.out.println("Attack size = "+unitsChosenByAttackerForCurrentBattle.size());
        System.out.println("Defender size = "+unitsChosenByDefenderForCurrentBattle.size());
        System.out.println("===================");
        
        battleWindow = new JFrame("Fight");
        battleWindow.setLayout(new BoxLayout(battleWindow.getContentPane(),BoxLayout.Y_AXIS));
//        battleWindow.setLayout(new FlowLayout());
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new BoxLayout(basePanel,BoxLayout.X_AXIS));
        
        JPanel attackerPanel = new JPanel();
        attackerPanel.setLayout(new BoxLayout(attackerPanel,BoxLayout.Y_AXIS));
        attackerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),attacker));
        //CheckboxGroup battleUnits = new CheckboxGroup();
        for(ABattleCard battleCard : unitsChosenByAttackerForCurrentBattle){
            JCheckBox checkBox = new JCheckBox(battleCard.getName());
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            checkBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(((JCheckBox)e.getSource()).isSelected()){
                        currentAttacker = battleCard;
                        trial = (JCheckBox)e.getSource();
                    }
                }
            });
            attackerPanel.add(checkBox);
        }
        
        JPanel defenderPanel = new JPanel();
        defenderPanel.setLayout(new BoxLayout(defenderPanel,BoxLayout.Y_AXIS));
        defenderPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),defender));
        for(ABattleCard battleCard : unitsChosenByDefenderForCurrentBattle){
            JLabel label = new JLabel(battleCard.getName());
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            defenderPanel.add(label);
        }
        basePanel.add(attackerPanel);
        basePanel.add(defenderPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        
        JButton button = new JButton("Attack");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentDefender = unitsChosenByDefenderForCurrentBattle.get(attacksWonByAttacker);
                while(true){
                    int attackerRollCount;
                    //If Norse has played the god card then Norse should get 2 extra dice rolls for every battle
                    if(extraDiceRollForNorse)
                        attackerRollCount = Dice.rollDice(currentAttacker.getNumberOfDiceRoll(currentDefender,defenderIndex,0)+2);
                    else
                        attackerRollCount = Dice.rollDice(currentAttacker.getNumberOfDiceRoll(currentDefender,defenderIndex,0));
                    
                    int defenderRollCount = Dice.rollDice(currentDefender.getNumberOfDiceRoll(currentAttacker,attackerIndex,1));
                    String attackTitle = currentAttacker.getName() + "(Attacker) vs " + currentDefender.getName() + "(Defender)";
                    
                    if(attackerRollCount==defenderRollCount)
                        continue;
                    else{
                        if(attackerRollCount > defenderRollCount){
                            defenderBoard.removeBattleUnit(currentDefender);
                            System.out.println("Attacker won the battle ");
                            trial.setSelected(false);
                            attacksWonByAttacker++;
                            if(attacksWonByAttacker == unitsChosenByDefenderForCurrentBattle.size())
                                finishBattle(0);
                            else
                                JOptionPane.showMessageDialog(new JFrame(), attackTitle + "\n" + attacker + " won the attack.");
                            break;
                        }
                        else{
                            attackerBoard.removeBattleUnit(currentAttacker);
                            System.out.println("Defender won the battle ");
                            trial.setSelected(false);
                            trial.setEnabled(false);
                            attacksWonByDefender++;
                            if(attacksWonByDefender > 1)
                                retreat(1);
                            else{
                                if(attacksWonByDefender == unitsChosenByAttackerForCurrentBattle.size())
                                    finishBattle(1);
                                else
                                    JOptionPane.showMessageDialog(new JFrame(), attackTitle + "\n" + defender + " won the attack.");
                            }
                            break;
                        }
                    }
                }
            }
        });
        buttonPanel.add(button);
        
        battleWindow.add(basePanel);
        battleWindow.add(buttonPanel);
        
        battleWindow.setVisible(true);
        battleWindow.pack();
        battleWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    //Give resources to the winner and adjust resources and tiles of the looser
    /*
    0 - Attacker
    1 - Defender
    */
    public void finishBattle(int winningPlayerIndex)
    {
        String player = (winningPlayerIndex==0)?attacker:defender;
        JOptionPane.showMessageDialog(new JFrame(), player + " won the battle.");
        battleWindow.dispose();
        adjustResourcesAccordingToBattleResult();
        AgeOfMythology.play();
    }
    
    /*
    0 - Attacker
    1 - Defender
    */
    public void retreat(int retreatingPlayerIndex){
        String player = (retreatingPlayerIndex==0)?attacker:defender;
        JOptionPane.showMessageDialog(new JFrame(), player + " retreated from the battle.");
        battleWindow.dispose();
        adjustResourcesAccordingToBattleResult();
        AgeOfMythology.play();
    }
    
    public void adjustResourcesAccordingToBattleResult()
    {
        System.out.println("adjustResourcesAccordingToBattleResult");
        BoardPanel attackerBoard = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[attackerIndex]];
        BoardPanel defenderBoard = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[defenderIndex]];
        
        if(areaOfAttack == Constants.BOARD_HOLDING_AREA)
        {
            HashMap<String,Integer> resourceCubes = defenderBoard.extractResourcesDueToLossInBattle();
            attackerBoard.addResourcesWonInBattle(resourceCubes);
        }
        else if(areaOfAttack == Constants.BOARD_CITY_AREA)
        {
            String buildingName = defenderBoard.removeBuildingFromCityArea();
            System.out.println("City Area attacked : building name : " + buildingName);
            if(null == buildingName)
                JOptionPane.showMessageDialog(new JFrame(), "No tile in the City Area of " + defender);
            else
                JOptionPane.showMessageDialog(new JFrame(), "Building '" + buildingName + "' destroyed in the city area of " + defender);
        }
        else if(areaOfAttack == Constants.BOARD_PRODUCTION_AREA)
        {
            Set<Integer> availableAreas = attackerBoard.availableAreasOnPlayerBoard();
            ImagePanel panel = defenderBoard.removeReosurceTileFromProductionArea(availableAreas);
            attackerBoard.placeTileInProdArea(panel.getType(), panel);
        }
        VictoryPointCards vc = VictoryPointCards.getInstance();
        int num = vc.returnVictoryPointCubesIndex(2);
        attackerBoard.addVictoryCubesToHoldingArea(num);
    }
        
}
