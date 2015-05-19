/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

/**
 *
 * @author vipinsharma
 */
public class NextAgeCard implements IActionCard,MouseListener{
    boolean cardSelected = false;
    private ImagePanel nextAgeCardPanel;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    
    public NextAgeCard(String culture,boolean cardType)
    {
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_NextAge.jpg";
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_NextAge.jpg";
        }
        //String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_NextAge.jpg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        nextAgeCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        nextAgeCardPanel.addMouseListener(this);
        godCard = cardType;
    }
    @Override
    public void selectCard(ImagePanel actionCard) {
        
        cardSelected = true;
        
        if(godCard)
            System.out.println("Next Age god card selected");
        else
            System.out.println("Next Age card selected");
        
        String image = Constants.IMAGES_PATH + "Cross.png";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
        imagePanel1.setOpaque(false);
        actionCard.add(imagePanel1);
        actionCard.repaint();
        actionCard.revalidate();
        AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
    }

    @Override
    public void playCard(ImagePanel actionCard) {
        int index = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
        BoardPanel boardPanel = AgeOfMythology.cultureBoards[index];
        int godCardCost = 0;
        
        if(godCard)
            System.out.println("Next Age god card played");
        else
            System.out.println("Next Age card played");
        
        if(boardPanel.getCulture().equals("Greek"))
            godCardCost = 2;
        else
            godCardCost = 1;
        
        if(godCard){
            boolean hasResources = boardPanel.payResourcesForGodCard(godCardCost);
            if(!hasResources){
                System.out.println("Doesn't have resources");
                godCard = false;
            }
            else if(boardPanel.getCulture().equals("Norse"))
                AgeOfMythology.norseNextAgeGodCardPlayed = true;
        }
        
        boardPanel.promoteToNextAge(godCard);
        cardPlayed = true;
        
//        String image = Constants.IMAGES_PATH + "Cross.png";
//        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
//        imagePanel1.setOpaque(false);
        
        actionCard.removeMouseListener(this);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
        
        AgeOfMythology.play();
    }

    @Override
    public ImagePanel returnCard() {
        return nextAgeCardPanel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(cardSelected)
            playCard((ImagePanel)me.getSource());
        else
            selectCard((ImagePanel)me.getSource());
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
