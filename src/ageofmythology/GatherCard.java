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
public class GatherCard implements IActionCard,MouseListener{
    private GatherCardWindow gatherCardWindow = new GatherCardWindow();
    private ImagePanel gatherCardPanel;
    private boolean cardSelected = false;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    
    public GatherCard(String culture, boolean cardType)
    {
        //String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Gather.jpg";
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_Gather.jpg";
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Gather.jpg";
        }
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        gatherCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        gatherCardPanel.addMouseListener(this);     
        godCard = cardType;
    }
    
    public ImagePanel returnCard()
    {
        return gatherCardPanel;
    }

    @Override
    public void selectCard(ImagePanel actionCard) {
        //AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
        if(godCard)
            System.out.println("Gather god card selected");
        else
            System.out.println("Gather card selected");
        
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
        
        if(godCard)
            System.out.println("Gather god card played");
        else
            System.out.println("Gather card played");
        
        cardPlayed = true;
        
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            
        if(godCard){
            boolean hasResources = boardPanel.payResourcesForGodCard(2);
            if(hasResources){
                if(boardPanel.getCulture().equals("Egypt")){
                    gatherCardWindow.gatherFoodResources(true);
                    gatherCardWindow.gatherFoodForGodCard(2);
                }
                else if(boardPanel.getCulture().equals("Norse")){
                    if (AgeOfMythology.getCurrentChanceIndex() == 0) {
                        gatherCardWindow.displayGatherWindow(false);
                    } else {
                        gatherCardWindow.gatherResourcesRandomly(false);
                    }
                }
                else if(boardPanel.getCulture().equals("Greek")){
                    gatherCardWindow.gatherFoodResources(false);
                    gatherCardWindow.gatherFoodForGodCard(1);
                }
            }
            else{
                if(AgeOfMythology.getCurrentChanceIndex() == 0)
                    gatherCardWindow.displayGatherWindow(true);
                else
                    gatherCardWindow.gatherResourcesRandomly(true);
            }
        }
        else{
            if(AgeOfMythology.getCurrentChanceIndex() == 0)
                gatherCardWindow.displayGatherWindow(true);
            else
                gatherCardWindow.gatherResourcesRandomly(true);
        }
                
//        String image = Constants.IMAGES_PATH + "Cross.png";
//        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
//        imagePanel1.setOpaque(false);
        actionCard.removeMouseListener(this);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
        
        //AgeOfMythology.play();
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
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
