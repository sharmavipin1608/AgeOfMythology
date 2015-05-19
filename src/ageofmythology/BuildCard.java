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
public class BuildCard implements IActionCard,MouseListener{
    //private BuildingTiles buildingTiles;
    ImagePanel buildCardPanel;
    private boolean cardSelected = false;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    
    public BuildCard(String culture, boolean cardType)
    {
        String image;
        if(cardType)
            image = Constants.IMAGES_PATH + culture + "_GodCard_Build.jpg";
        else
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Build.jpg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        buildCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        buildCardPanel.addMouseListener(this);    
        godCard = cardType;
    }
    
    public ImagePanel returnCard()
    {
        return buildCardPanel;
    }
    
    @Override
    public void selectCard(ImagePanel actionCard) {
        //AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
        if(godCard)
            System.out.println("Build god card selected");
        else
            System.out.println("Build card selected");
        
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
        int numOfBuidings = 3;
        BuildingTiles_GodCard buildingTilesGod;
        BuildingTiles buildingTiles = new BuildingTiles();
        
        if(godCard)
            System.out.println("Build god card played");
        else
            System.out.println("Build card played");
        
        int cultureIndex = AgeOfMythology.getCurrentChanceIndex();
        
        if(godCard){
            BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            boardPanel.payResourcesForGodCard();
            if(boardPanel.getCulture().equals("Norse"))
                numOfBuidings = 4;
            buildingTilesGod = new BuildingTiles_GodCard(numOfBuidings);
            if(cultureIndex == 0)
                buildingTilesGod.displayBuildingTilesWindow();
            else
                buildingTilesGod.playCard();
        }
        else{
            if(cultureIndex == 0)
                buildingTiles.displayBuildingTilesWindow();
            else
                buildingTiles.playCard();
        }
//
//        String image = Constants.IMAGES_PATH + "Cross.png";
//        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
//        imagePanel1.setOpaque(false);
        actionCard.removeMouseListener(this);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
        
//        AgeOfMythology.play();
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
