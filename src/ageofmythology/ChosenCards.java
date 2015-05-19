/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class ChosenCards {

    JFrame chosenCards = new JFrame("Play Cards");

    public ChosenCards(ArrayList<ImagePanel> cardsChosen) {
        System.out.println("Play Cards");
//        for (ImagePanel imagePanel : AgeOfMythology.returnCurrentBoardPlayer().cardsChosen) {
//            chosenCards.add(imagePanel);
//        }
        for(ImagePanel card : cardsChosen){
            chosenCards.add(card);
//            card.remove(0);
        }
            
        chosenCards.setVisible(false);
        chosenCards.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chosenCards.setLayout(new BoxLayout(chosenCards.getContentPane(), BoxLayout.X_AXIS));
        chosenCards.pack();
    }
    
    public void displayWindow()
    {
        chosenCards.setVisible(true);
    }
    
    public void destroyWindow(){
        chosenCards.dispose();
        //chosenCards.setVisible(false);
    }
}
