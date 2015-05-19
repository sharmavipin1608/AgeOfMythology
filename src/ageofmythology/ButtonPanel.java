/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class ButtonPanel {
    JPanel buttonPanel = new JPanel();
    public ButtonPanel()
    {
        buttonPanel.setPreferredSize(new Dimension(1050, 50));
        buttonPanel.setMaximumSize(new Dimension(1050, 50));
        buttonPanel.setMinimumSize(new Dimension(1050, 50));
        buttonPanel.setBackground(Color.black);

        JButton button1 = new JButton("Next Player");
        button1.setText("Next Player");
//        button1.setPreferredSize(new Dimension(525, 25));
//        button1.setMaximumSize(new Dimension(525, 25));
//        button1.setMinimumSize(new Dimension(525, 25));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
                AgeOfMythology.setNextPlayerInWindow();
//                System.out.println("load next player");
//                if (cpIndex == 2) {
//                    cpIndex = 0;
//                } else {
//                    cpIndex++;
//                }
//                System.out.println("Index : " + cpIndex);
//
//                boardPanel = cultureBoards[cpIndex];
//
//                frame.getContentPane().removeAll();
//                frame.getContentPane().revalidate();
//                frame.getContentPane().repaint();
//
//                frame.setTitle(cultures[cpIndex]);
//
//                frame.add(boardPanel);
//                frame.add(buttonPanel);
            }
        });

        JButton button2 = new JButton("Tiles");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
                System.out.println("Display tiles");
                AgeOfMythology.returnTilesFrame().setVisible(true);
            }
        });
        
        JButton button3 = new JButton("Victory Point Cards");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
                System.out.println("Display Victory point Cards");
                VictoryPointCards vc = VictoryPointCards.getInstance();
                vc.display();
            }
        });
        
        JButton button4 = new JButton("Permanent Action Cards");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
                System.out.println("Permanent Action Cards");
                //button4.setEnabled(false);
                Turn.drawCardsForTheTurn();
                //((BoardPanel)AgeOfMythology.returnCurrentBoardPlayer()).displayPermanentActionCardWindow();
            }
        });
        
        JButton button5 = new JButton("Bank");
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
                System.out.println("Bank");
                Bank bank = Bank.getInstance();
                bank.displayBankWindow();
                
//                BoardPanel board = AgeOfMythology.returnCurrentBoardPlayer();
//                board.removeFoodTileFromProductionArea(false);
////                board.removeBuildingFromCityArea();
//                
//                
//                ImagePanel panel = board.removeReosurceTileFromProductionArea(board.availableAreasOnPlayerBoard());
//                BoardPanel trial = AgeOfMythology.cultureBoards[0];
//                int resourceArea = GameUtilities.convertResourceTileToAreaType(panel.getType());
//                System.out.println("hello : " + resourceArea + " : : " + panel.getType());
//                trial.placeTileInProdArea(resourceArea, panel);
//                VictoryPointCards vc = VictoryPointCards.getInstance();
//                int num = vc.returnVictoryPointCubesIndex(2);
//                AgeOfMythology.returnCurrentBoardPlayer().addVictoryCubesToHoldingArea(num);
            }
        });
        
        JButton button6 = new JButton("Play Cards");
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent nextPlayer) {
//                System.out.println("Play Cards");
//                JFrame trial = new JFrame("Play Cards");
//                for(ImagePanel imagePanel : AgeOfMythology.returnCurrentBoardPlayer().cardsChosen)
//                {
//                    //imagePanel.remove(0);
//                    trial.add(imagePanel);
//                }
//                trial.setVisible(true);
//                trial.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//                trial.setLayout(new BoxLayout(trial.getContentPane(),BoxLayout.X_AXIS));
//                trial.pack();
                //((BoardPanel)boardPanel).displayPermanentActionCardWindow();
                AgeOfMythology.incrementTurn();
            }
        });
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setLayout(new GridLayout(2,3));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
    }
    
    public JPanel getButtonPanel()
    {
        return buttonPanel;
    }
}
