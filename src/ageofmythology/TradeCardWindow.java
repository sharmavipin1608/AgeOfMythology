/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author vipinsharma
 */
public class TradeCardWindow {
    public JFrame tradeCardWindowFrame = new JFrame();
    String[] resources = {Constants.RESOURCE_CUBE_FOOD,Constants.RESOURCE_CUBE_FAVOR,
                          Constants.RESOURCE_CUBE_WOOD,Constants.RESOURCE_CUBE_GOLD};
    HashMap<String,Integer> playerResources;
    HashMap<String,Integer> bankResources;
    Button trade;
    BoardPanel boardPanel;
    private boolean godCard = false;
            
    public void play()
    {
        int index = AgeOfMythology.getCurrentChanceIndex();
        BoardPanel boardPanel = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[index]];
        Boolean hasResources;
        
        if(godCard)
            hasResources = true;
        else
            hasResources = boardPanel.deductResourcesForTradeCard();
        
        if(index == 0)
        {
            if(hasResources)
                tradeCardWindowFrame.setVisible(true);
            else
                JOptionPane.showMessageDialog(new JFrame(), "Doesn't have enough resources to trade resources.");
        }
        else
        {
            if(hasResources)
                tradeResourcesRandomly();
            else
                System.out.println("Player doesn't have enough resources to play the trade card");
        }
        
        if(!hasResources)
            AgeOfMythology.play();
    }
    
    public TradeCardWindow(boolean godPower)
    {
        int index = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
        boardPanel = AgeOfMythology.cultureBoards[index];
        
        Bank bank = Bank.getInstance();
        
        playerResources = boardPanel.cultureResources();
        bankResources = bank.bankResources();
        
        godCard = godPower;
        //Resources of the player
        CustomPanel playerResourcesPanel = new CustomPanel(410,250);
        playerResourcesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Player Resources"));
        playerResourcesPanel.setLayout(new BoxLayout(playerResourcesPanel,BoxLayout.X_AXIS));
        
        JList playerFoodCubes = returnList(playerResources.get(Constants.RESOURCE_CUBE_FOOD));
        JScrollPane playerFoodPane = new JScrollPane(playerFoodCubes);
        playerFoodPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_FOOD));
        playerFoodCubes.setSelectedIndex(0);
        
        JList playerFavorCubes = returnList(playerResources.get(Constants.RESOURCE_CUBE_FAVOR));
        JScrollPane playerFavorPane = new JScrollPane(playerFavorCubes);
        playerFavorPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_FAVOR));
        playerFavorCubes.setSelectedIndex(0);
        
        JList playerWoodCubes = returnList(playerResources.get(Constants.RESOURCE_CUBE_WOOD));
        JScrollPane playerWoodPane = new JScrollPane(playerWoodCubes);
        playerWoodPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_WOOD));
        playerWoodCubes.setSelectedIndex(0);
        
        JList playerGoldCubes = returnList(playerResources.get(Constants.RESOURCE_CUBE_GOLD));
        JScrollPane playerGoldPane = new JScrollPane(playerGoldCubes);
        playerGoldPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_GOLD));
        playerGoldCubes.setSelectedIndex(0);
        
        playerResourcesPanel.add(playerFoodPane);
        playerResourcesPanel.add(playerFavorPane);
        playerResourcesPanel.add(playerWoodPane);
        playerResourcesPanel.add(playerGoldPane);
        
        //Resources of Bank
        CustomPanel bankResourcesPanel = new CustomPanel(410,250);
        bankResourcesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Bank Resources"));
        bankResourcesPanel.setLayout(new BoxLayout(bankResourcesPanel,BoxLayout.X_AXIS));
        
        JList bankFoodCubes = returnList(bankResources.get(Constants.RESOURCE_CUBE_FOOD));
        JScrollPane bankFoodPane = new JScrollPane(bankFoodCubes);
        bankFoodPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_FOOD));
        bankFoodCubes.setSelectedIndex(0);
        
        JList bankFavorCubes = returnList(bankResources.get(Constants.RESOURCE_CUBE_FAVOR));
        JScrollPane bankFavorPane = new JScrollPane(bankFavorCubes);
        bankFavorPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_FAVOR));
        bankFavorCubes.setSelectedIndex(0);
        
        JList bankWoodCubes = returnList(bankResources.get(Constants.RESOURCE_CUBE_WOOD));
        JScrollPane bankWoodPane = new JScrollPane(bankWoodCubes);
        bankWoodPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_WOOD));
        bankWoodCubes.setSelectedIndex(0);
        
        JList bankGoldCubes = returnList(bankResources.get(Constants.RESOURCE_CUBE_GOLD));
        JScrollPane bankGoldPane = new JScrollPane(bankGoldCubes);
        bankGoldPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),Constants.RESOURCE_CUBE_GOLD));
        bankGoldCubes.setSelectedIndex(0);
        
        bankResourcesPanel.add(bankFoodPane);
        bankResourcesPanel.add(bankFavorPane);
        bankResourcesPanel.add(bankWoodPane);
        bankResourcesPanel.add(bankGoldPane);
        
        trade = new Button("Trade");
        trade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println(playerFoodCubes.getSelectedValue());
                int playerFoodValue = Integer.parseInt(playerFoodCubes.getSelectedValue().toString());
                int playerFavorValue = Integer.parseInt(playerFavorCubes.getSelectedValue().toString());
                int playerWoodValue = Integer.parseInt(playerWoodCubes.getSelectedValue().toString());
                int playerGoldValue = Integer.parseInt(playerGoldCubes.getSelectedValue().toString());
                
                int bankFoodValue = Integer.parseInt(bankFoodCubes.getSelectedValue().toString());
                int bankFavorValue = Integer.parseInt(bankFavorCubes.getSelectedValue().toString());
                int bankWoodValue = Integer.parseInt(bankWoodCubes.getSelectedValue().toString());
                int bankGoldValue = Integer.parseInt(bankGoldCubes.getSelectedValue().toString());
                
                int playerResources = playerFoodValue+playerFavorValue+playerWoodValue+playerGoldValue;
                int bankResources = bankFoodValue+bankFavorValue+bankWoodValue+bankGoldValue;
                
                if(playerResources != bankResources)
                    JOptionPane.showMessageDialog(new JFrame(), "The number of resources selected to trade should match.");
                
                else
                {
                    if(playerFoodValue > 0)
                        submitResourceCubes(Constants.RESOURCE_CUBE_FOOD,playerFoodValue);
                    
                    if(playerFavorValue > 0)
                        submitResourceCubes(Constants.RESOURCE_CUBE_FAVOR,playerFavorValue);
                    
                    if(playerWoodValue > 0)
                        submitResourceCubes(Constants.RESOURCE_CUBE_WOOD,playerWoodValue);
                    
                    if(playerGoldValue > 0)
                        submitResourceCubes(Constants.RESOURCE_CUBE_GOLD,playerGoldValue);
                    
                    if(bankFoodValue > 0)
                        takeResourceCubes(Constants.RESOURCE_CUBE_FOOD,bankFoodValue);
                    
                    if(bankFavorValue > 0)
                        takeResourceCubes(Constants.RESOURCE_CUBE_FAVOR,bankFavorValue);
                    
                    if(bankWoodValue > 0)
                        takeResourceCubes(Constants.RESOURCE_CUBE_WOOD,bankWoodValue);
                    
                    if(bankGoldValue > 0)
                        takeResourceCubes(Constants.RESOURCE_CUBE_GOLD,bankGoldValue);
                    
                    tradeCardWindowFrame.dispose();
                    AgeOfMythology.play();
                }
            }
        });
        
        tradeCardWindowFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tradeCardWindowFrame.setLocationRelativeTo(null);
        tradeCardWindowFrame.setSize(500, 500);
        tradeCardWindowFrame.setVisible(false);
        tradeCardWindowFrame.setLayout(new BoxLayout(tradeCardWindowFrame.getContentPane(),BoxLayout.Y_AXIS));
        
        tradeCardWindowFrame.add(playerResourcesPanel);
        tradeCardWindowFrame.add(bankResourcesPanel);
        tradeCardWindowFrame.add(trade);
        
        tradeCardWindowFrame.pack();
    }
    
    public JList returnList(int numOfResources)
    {
        JList tempList = new JList();
        DefaultListModel tempModel = new DefaultListModel();
        tempList.setModel(tempModel);
        
        for(int i=0;i<=numOfResources;i++)
        {
            tempModel.addElement(i);
        }
        
        tempList.setPreferredSize(new Dimension(100,250));
        tempList.setMaximumSize(new Dimension(100,250));
        tempList.setMinimumSize(new Dimension(100,250));
        return tempList;
    }
    
    public void submitResourceCubes(String resourceType,int numOfResources)
    {
//        BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
        
        for(int i=0;i<numOfResources;i++)
        {
            boardPanel.removeResourceCube(resourceType);
        }
    }
    
    public void takeResourceCubes(String resourceType,int numOfResources)
    {
//        BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
        
        for(int i=0;i<numOfResources;i++)
        {
            boardPanel.addResourceCube(resourceType);
        }
    }
    
    public void tradeResourcesRandomly()
    {
        int numOfFoodResources = playerResources.get(Constants.RESOURCE_CUBE_FOOD);
//        int numOfFavorResources = playerResources.get(Constants.RESOURCE_CUBE_FAVOR);
//        int numOfWoodResources = playerResources.get(Constants.RESOURCE_CUBE_WOOD);
        int numOfGoldResources = playerResources.get(Constants.RESOURCE_CUBE_GOLD);
        
        if(numOfFoodResources > 1 && numOfGoldResources > 1)
        {
            submitResourceCubes(Constants.RESOURCE_CUBE_GOLD,1);
            takeResourceCubes(Constants.RESOURCE_CUBE_FOOD,1);
        }
        AgeOfMythology.play();
    }
}
