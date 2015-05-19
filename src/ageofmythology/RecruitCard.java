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
public class RecruitCard implements IActionCard,MouseListener{
    boolean cardSelected = false;
    private ImagePanel recruitCardPanel;
    private boolean cardPlayed = false;
    private String playerCulture;
    private int numOfUnitsToBeRecruited;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    
    public RecruitCard(String culture,boolean cardType)
    {
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_Recruit.jpg";
            if(culture.equals("Norse")){
                numOfUnitsToBeRecruited = 3;
            }
            else
                numOfUnitsToBeRecruited = 4;
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Recruit.jpg";
            numOfUnitsToBeRecruited = 2;
        }
        playerCulture = culture;
        //String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Recruit.jpg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        recruitCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        recruitCardPanel.addMouseListener(this);
        godCard = cardType;
    }
    
    @Override
    public void selectCard(ImagePanel actionCard) {
        //AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
        if(godCard)
            System.out.println("Recruit god card selected");
        else
            System.out.println("Recruit card selected");
        
//        String image = Constants.IMAGES_PATH + "Cross.png";
//        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
//        imagePanel1.setOpaque(false);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
        AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
    }

    @Override
    public void playCard(ImagePanel actionCard) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(godCard)
            System.out.println("Recruit god card played");
        else
            System.out.println("Recruit card played");
        
        cardPlayed = true;
        
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        boardPanel.setNumOfBattleUnitsRecruited(numOfUnitsToBeRecruited);
        
        if(godCard){
            boolean hasResources;
            if(playerCulture.equals("Egypt"))
                hasResources = boardPanel.payResourcesForGodCard(2);
            else
                hasResources = boardPanel.payResourcesForGodCard(1);
            
            if(hasResources){
                boardPanel.recruitExtraUnitsForGodCard();
            }
        }
        
        RecruitCardWindow recruitWindow = new RecruitCardWindow(playerCulture);
        if(AgeOfMythology.getCurrentChanceIndex() == 0)
            recruitWindow.displayRecruitCardWindow();
        else
            recruitWindow.recruitRandomly(numOfUnitsToBeRecruited);
            //recruitWindow.recruitRandomly(numOfUnitsToBeRecruited);
        
        actionCard.removeMouseListener(this);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
        //AgeOfMythology.play();
    }

    @Override
    public ImagePanel returnCard() {
        return recruitCardPanel;
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
