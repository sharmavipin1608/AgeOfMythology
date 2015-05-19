/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author vipinsharma
 */
public class GatherCardWindow {
    private JFrame gatherCardFrame = new JFrame("Gather Resources");
    JList gatherOptions = new JList();
    private boolean gatherResourceForAllCultures = true;
    
    public GatherCardWindow()
    {
        DefaultListModel gatherOptionsList = new DefaultListModel();
        gatherOptions.setModel(gatherOptionsList);
        gatherOptions.setFont(new Font("Verdana", Font.ITALIC, 12));
        gatherOptions.setPreferredSize(new Dimension(190,180));
        gatherOptions.setMaximumSize(new Dimension(190,180));
        gatherOptions.setMinimumSize(new Dimension(190,180));
        for(int index=0;index<10;index++)
        {
            gatherOptionsList.addElement(Constants.gatherOptionsArray[index]);
        }
        
        JButton button1 = new JButton("GATHER");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gatherCardFrame.dispose();
                int index = gatherOptions.getSelectedIndex();
                if(gatherResourceForAllCultures)
                    gatherResources(index);
                else
                    gatherResourcesForPlayerOnly(index);
                AgeOfMythology.play();
//                BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
//                if(index >= 0 && index <=5)
//                    boardPanel.gatherByArea(gatherOptions.getSelectedValue().toString());
//                else
//                    boardPanel.gatherByResource(gatherOptions.getSelectedValue().toString());
            }
        });
        gatherCardFrame.setLayout(new BoxLayout(gatherCardFrame.getContentPane(),BoxLayout.Y_AXIS));
        //gatherCardFrame.setLayout(new GridLayout(2,1));
        gatherCardFrame.add(gatherOptions);
        gatherCardFrame.add(button1);
        gatherCardFrame.setVisible(false);
        gatherCardFrame.setLocationRelativeTo(null);
        gatherCardFrame.setSize(200,230);
        gatherCardFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //gatherCardFrame.pack();
    }
    
    public void displayGatherWindow(boolean gatherResourcesForAll)
    {
        gatherResourceForAllCultures = gatherResourcesForAll;
        gatherCardFrame.setVisible(true);
    }
    
    public void gatherResources(int selectionIndex)
    {
        for(BoardPanel boardPanel : AgeOfMythology.cultureBoards)
        {
            if(selectionIndex >= 0 && selectionIndex <=5)
                boardPanel.gatherByArea(gatherOptions.getSelectedValue().toString());
            else
                boardPanel.gatherByResource(gatherOptions.getSelectedValue().toString());
        }
    }
    
    public void gatherResourcesRandomly(boolean gatherResourcesForAll)
    {
        int index = Calendar.getInstance().getTime().getSeconds()%10;
        System.out.println("Resources gathered by : " + Constants.gatherOptionsArray[index]);
        gatherOptions.setSelectedIndex(index);
        //gatherResources(index);
        if (gatherResourcesForAll)
            gatherResources(index);
        else
            gatherResourcesForPlayerOnly(index);
        AgeOfMythology.play();
    }
    
    public void gatherResourcesForPlayerOnly(int selectionIndex)
    {
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        if (selectionIndex >= 0 && selectionIndex <= 5)
            boardPanel.gatherByArea(gatherOptions.getSelectedValue().toString());
        else
            boardPanel.gatherByResource(gatherOptions.getSelectedValue().toString());
    }
    
    public void gatherFoodResources(boolean gatherResourcesForAll)
    {
        if(gatherResourcesForAll){
            for(BoardPanel boardPanel : AgeOfMythology.cultureBoards)
                boardPanel.gatherByResource(Constants.RESOURCE_CUBE_FOOD);
        }
        else{
            BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            boardPanel.gatherByResource(Constants.RESOURCE_CUBE_FOOD);
        }
    }
    
    public void gatherFoodForGodCard(int numOfResourcesPerTile)
    {
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        boardPanel.gatherResourcesForGodCard(numOfResourcesPerTile);
        AgeOfMythology.play();
    }
}
