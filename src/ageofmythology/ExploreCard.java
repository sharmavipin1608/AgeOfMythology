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
public class ExploreCard implements IActionCard,MouseListener{
    
    ImagePanel exploreCardPanel;
    boolean cardSelected = false;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    public ExploreCard(String culture,boolean cardType)
    {
        //String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Explore.jpg";
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_Explore.jpg";
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Explore.jpg";
        }
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        exploreCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        exploreCardPanel.addMouseListener(this);      
        godCard = cardType;
    }
    
    public ImagePanel returnCard()
    {
        return exploreCardPanel;
    }
    
    @Override
    public void selectCard(ImagePanel actionCard) {
        //AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
        if(godCard)
            System.out.println("Explore god card selected");
        else
            System.out.println("Explore card selected");
        
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
        cardPlayed = true;
        
        if(godCard)
            System.out.println("Explore god card played");
        else
            System.out.println("Explore card played");
        
        if(godCard){
            BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            boolean hasResources = boardPanel.payResourcesForGodCard(1);
            if(!hasResources)
                godCard = false;
        }
        
        ExploreCardWindow exploreCardWindow = new ExploreCardWindow(AgeOfMythology.selectResourceTiles(4),godCard);
        exploreCardWindow.play();
//        
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
