/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class PermanentActionCard {
    private ArrayList<IActionCard> privateActionCardList = new ArrayList<>();
    private JFrame permanentActionCardFrame;
    
    public void displayPermanentActionCardWindow()
    {
        permanentActionCardFrame.setVisible(true);
    }
    
    public void destroyWindow()
    {
        System.out.println("Closing permanent window");
        System.out.println("Let's check : " + permanentActionCardFrame.getTitle());
        permanentActionCardFrame.setVisible(false);
        permanentActionCardFrame.dispose();
        System.out.println(permanentActionCardFrame.isVisible());
    }
    
    public ArrayList drawCards(int numOfCards,int indexCulture)
    {
        System.out.println("Draw Cards");
        System.out.println("Num of Cards : " + numOfCards);
        ArrayList<IActionCard> actionCardsSelected = new ArrayList<>();
        int[] rememberSelection = {1,1,1,1,1,1,1};
        Date date = Calendar.getInstance().getTime();
        int gap = (date.getSeconds()+indexCulture)%7;
        int start = (date.getMinutes()+indexCulture)%7;
        System.out.println("GAP : " + gap + " START : " + start);
        while(numOfCards > 0)
        {
            if(rememberSelection[start]>0)
            {
                actionCardsSelected.add(privateActionCardList.get(start));
                rememberSelection[start] = 0;
                numOfCards--;
                System.out.println(Constants.PERMANENT_ACTION_CARDS[start] + "Card selected.");
                start = (start + gap + 1)%7;
            }
            else
            {
                start = (start + gap + 2)%7;
            }
        }
        return actionCardsSelected;
    }
    
    public PermanentActionCard(String culture)
    {
        //String[] permActionCardArray = {"Build","Gather","Explore","Trade","Recruit","Attack","NextAge"};
        
        permanentActionCardFrame = new JFrame("Permanent Action Cards");
        permanentActionCardFrame.setLayout(new GridLayout(2,7));
        permanentActionCardFrame.setBackground(Color.black);
        
        IActionCard buildCard = new BuildCard(culture,false);
        privateActionCardList.add(buildCard);
        permanentActionCardFrame.add(buildCard.returnCard());
        
        IActionCard gatherCard = new GatherCard(culture,false);
        privateActionCardList.add(gatherCard);
        permanentActionCardFrame.add(gatherCard.returnCard());
        
        IActionCard exploreCard = new ExploreCard(culture,false);
        privateActionCardList.add(exploreCard);
        permanentActionCardFrame.add(exploreCard.returnCard());
        
        IActionCard tradeCard = new TradeCard(culture,false);
        privateActionCardList.add(tradeCard);
        permanentActionCardFrame.add(tradeCard.returnCard());
        
        IActionCard recruitCard = new RecruitCard(culture,false);
        privateActionCardList.add(recruitCard);
        permanentActionCardFrame.add(recruitCard.returnCard());
        
        IActionCard attackCard = new AttackCard(culture,false);
        privateActionCardList.add(attackCard);
        permanentActionCardFrame.add(attackCard.returnCard());
        
        IActionCard nextAgeCard = new NextAgeCard(culture,false);
        privateActionCardList.add(nextAgeCard);
        permanentActionCardFrame.add(nextAgeCard.returnCard());
        
        IActionCard godBuildCard = new BuildCard(culture,true);
        privateActionCardList.add(godBuildCard);
        permanentActionCardFrame.add(godBuildCard.returnCard());
        
        IActionCard godGatherCard = new GatherCard(culture,true);
        privateActionCardList.add(godGatherCard);
        permanentActionCardFrame.add(godGatherCard.returnCard());
        
        IActionCard godExploreCard = new ExploreCard(culture,true);
        privateActionCardList.add(godExploreCard);
        permanentActionCardFrame.add(godExploreCard.returnCard());
        
        IActionCard godTradeCard = new TradeCard(culture,true);
        privateActionCardList.add(godTradeCard);
        permanentActionCardFrame.add(godTradeCard.returnCard());
        
        IActionCard godRecruitCard = new RecruitCard(culture,true);
        privateActionCardList.add(godRecruitCard);
        permanentActionCardFrame.add(godRecruitCard.returnCard());
        
        IActionCard godAttackCard = new AttackCard(culture,true);
        privateActionCardList.add(godAttackCard);
        permanentActionCardFrame.add(godAttackCard.returnCard());
        
        IActionCard godNextAgeCard = new NextAgeCard(culture,true);
        privateActionCardList.add(godNextAgeCard);
        permanentActionCardFrame.add(godNextAgeCard.returnCard());
//        for(int index = 4; index < 7; index++)
//        {
//            String image = Constants.IMAGES_PATH + culture + "_PermanentActionCard_" + permActionCardArray[index] + ".jpg";
//            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//            //ImagePanel imagePanel = new ImagePanel(tileImage,175,250,index,BorderFactory.createEmptyBorder(20, 20, 20, 20));
//            ImagePanel imagePanel = new ImagePanel(tileImage,175,250,index,BorderFactory.createRaisedBevelBorder());
//            privateActionCardList[index] = imagePanel;
//            permanentActionCardFrame.add(imagePanel);
//            imagePanel.addMouseListener(new MouseListener() {
//                public void mouseClicked(MouseEvent me) {
//                    int cardIndex = ((ImagePanel)me.getSource()).getType();
//                    System.out.println("card index : " + cardIndex);
//                    String image = Constants.IMAGES_PATH + "Cross.png";
//                    Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
//                    //ImagePanel imagePanel = new ImagePanel(tileImage,175,250,index,BorderFactory.createEmptyBorder(20, 20, 20, 20));
//                    ImagePanel imagePanel1 = new ImagePanel(tileImage,175,250,0);
//                    imagePanel1.setOpaque(false);
//                    ((ImagePanel)me.getSource()).removeMouseListener(this);
//                    ((ImagePanel)me.getSource()).add(imagePanel1);
//                    ((ImagePanel)me.getSource()).repaint();
//                    ((ImagePanel)me.getSource()).revalidate();
//                    
//                }
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
//        }
        permanentActionCardFrame.setVisible(false);
        permanentActionCardFrame.setResizable(false);
        permanentActionCardFrame.setLocationRelativeTo(null);
        permanentActionCardFrame.pack();
        permanentActionCardFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
