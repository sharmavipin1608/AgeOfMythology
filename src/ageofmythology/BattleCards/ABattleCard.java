/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology.BattleCards;

import ageofmythology.AgeOfMythology;
import ageofmythology.BoardPanel;
import ageofmythology.Constants;
import ageofmythology.GameUtilities;
import ageofmythology.ImagePanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author vipinsharma
 */
public abstract class ABattleCard implements MouseListener{
    protected ImagePanel actionCard;
    protected boolean cardSelected = false;
    public int fixedNumOfDiceRolls;
    
    public abstract String getName();
    
    public abstract HashMap<String,Integer> getCostForUnit();
    
    public abstract int getAgeRequirements();
    
    //Mortal,Myth,Hero
    public abstract int getMilitaryUnitType();
    
    /*
    roleInBattle - 
    0 - Attacker
    1 - Defender
    */
    public abstract int getNumberOfDiceRoll(ABattleCard enemy,int boardIndex, int roleInBattle);
    
    //Warrior, flyer etc
    public abstract int getUnitSpecialisation();
    
    public abstract void selectCardForBattle();
    
    public boolean addCard(){
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        boolean haveResources = boardPanel.checkIfPlayerHasResouces(getCostForUnit());
        boolean isAtCorrectAge = (boardPanel.getCurrentAge() == getAgeRequirements()
                || getAgeRequirements() == Constants.ALL_AGE)?true:false;
        
        if(!haveResources){
            if(boardPanel.getIndexCulture() == 0)
                JOptionPane.showMessageDialog(new JFrame(), boardPanel.getCulture() 
                    + " can't recruit " + getName() + " card because of insufficient resources.");
            return false;
        }
        else
        {
            if(isAtCorrectAge){
                boardPanel.recruit(this);
                //Disable the frame once the number of battle units have been selected
                if(boardPanel.getNumOfBattleUnitsRecruited() == 0 && boardPanel.getIndexCulture() == 0)
                {
                    JFrame f = (JFrame) SwingUtilities.getRoot(returnCard());
                    f.setEnabled(false);
                    //f.setVisible(false);
                    AgeOfMythology.play();
                }
            }
            else
            {
                if(boardPanel.getIndexCulture() == 0)
                    JOptionPane.showMessageDialog(new JFrame(), boardPanel.getCulture() 
                        + " can't recruit " + getName() + " at current age.");
                return false;
            }
        }
        return true;
    }
    
    public ABattleCard(String culture)
    {
        String image = Constants.IMAGES_PATH + "/"+culture+"/"+ getName() +".jpeg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        actionCard = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        actionCard.addMouseListener(this); 
    }
    
    public ImagePanel returnCard(){
        return actionCard;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(getName() + " Card selected");
        addCard();
        cardSelected = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
