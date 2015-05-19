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
public class TradeCard implements IActionCard,MouseListener{
    
    ImagePanel tradeCardPanel;
    boolean cardSelected = false;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    public TradeCard(String culture,boolean cardType)
    {
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_Trade.jpg";
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Trade.jpg";
        }
        //String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Trade.jpg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        tradeCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        tradeCardPanel.addMouseListener(this);        
        godCard = cardType;    
    }
    
    public ImagePanel returnCard()
    {
        return tradeCardPanel;
    }
    
    @Override
    public void selectCard(ImagePanel actionCard) {
        if(godCard)
            System.out.println("Trade god card selected");
        else
            System.out.println("Trade card selected");
        
        //AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
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
            System.out.println("Trade god card played");
        else
            System.out.println("Trade card played");
        
        if(godCard){
            BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            boolean hasResources = boardPanel.payResourcesForGodCard(1);
            if(!hasResources){
                godCard = false;
            }
            else{
                System.out.println("have resources");
                boardPanel.gainResourcesForGodTradeCard();
            }
        }
        
        TradeCardWindow tradeCardWindow = new TradeCardWindow(godCard);
        tradeCardWindow.play();
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
