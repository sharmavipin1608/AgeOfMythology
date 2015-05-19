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
public class AttackCard implements IActionCard,MouseListener{
    boolean cardSelected = false;
    private ImagePanel attackCardPanel;
    private boolean cardPlayed = false;
    private boolean godCard = false;
    private int numOfUnitsForBattle = 4;
    
    public boolean ifCardPlayed()
    {
        return cardPlayed;
    }
    
    public AttackCard(String culture,boolean cardType)
    {
        String image;
        if(cardType)
        {
            image = Constants.IMAGES_PATH + culture + "_GodCard_Attack.jpg";
        }
        else
        {
            image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_Attack.jpg";
        }
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        attackCardPanel = new ImagePanel(tileImage,175,250,0,BorderFactory.createRaisedBevelBorder());
        attackCardPanel.addMouseListener(this);
        godCard = cardType;
    }
    @Override
    public void selectCard(ImagePanel actionCard) {
//        AgeOfMythology.returnCurrentBoardPlayer().addChosenCard(actionCard);
        cardSelected = true;
        
        if(godCard)
            System.out.println("Attack god card selected");
        else
            System.out.println("Attack card selected");
        
        String image = Constants.IMAGES_PATH + "Cross.png";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,6);
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
            System.out.println("Attack god card played");
        else
            System.out.println("Attack card played");
        
        AttackCardWindow battle;
        
        if(godCard){
            BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
            boolean hasResources = boardPanel.payResourcesForGodCard(3);
            if(!hasResources){
                System.out.println("Doesn't have resources");
                godCard = false;
                numOfUnitsForBattle = 6;
            }
            else{
                System.out.println("Doesn't have resources");
                numOfUnitsForBattle = 8;
            }
        }
        
        if(GameUtilities.currentPlayerBoard().getIndexCulture() == 0)
            battle = new AttackCardWindow(numOfUnitsForBattle,godCard);
        else
            AgeOfMythology.play();
        actionCard.removeMouseListener(this);
        actionCard.add(MarkCard.getInstance());
        actionCard.repaint();
        actionCard.revalidate();
//        JFrame chooseCultureToAttack = new JFrame("Choose culture to Attack");
//        int currentCultureIndex = AgeOfMythology.returnCurrentBoardPlayer().getIndexCulture();
//        int opponent1Index = currentCultureIndex-1;
//        if(opponent1Index == -1)
//            opponent1Index = 2;
//        int opponent2Index = (currentCultureIndex+1)%3;
////        if(opponent2Index == 3)
////            opponent2Index = 0;
//        String opponent1 = Constants.cultures[AgeOfMythology.playerShift[opponent1Index]];
//        String opponent2 = Constants.cultures[AgeOfMythology.playerShift[opponent2Index]];
//        System.out.println("Opponent1 : " + opponent1 + " Opponent2 : " + opponent2);
//        //chooseCultureToAttack.setLayout(new BoxLayout(chooseCultureToAttack.getContentPane(),BoxLayout.X_AXIS));
//        chooseCultureToAttack.setLayout(new GridLayout(1,2));
//        chooseCultureToAttack.setSize(300,100);
//        JButton culture1 = new JButton(opponent1);
//        JButton culture2 = new JButton(opponent2);
//        chooseCultureToAttack.add(culture1);
//        chooseCultureToAttack.add(culture2);
//        chooseCultureToAttack.setVisible(true);
//        chooseCultureToAttack.setLocationRelativeTo(null);
        
    }

    @Override
    public ImagePanel returnCard() {
        return attackCardPanel;
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
