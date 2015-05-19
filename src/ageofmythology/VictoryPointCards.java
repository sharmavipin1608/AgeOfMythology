/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class VictoryPointCards {
    private static JFrame victoryPointCardFrame;
    private static VictoryPointCards victoryPointCards = null;
    private JPanel[] victoryPointCard = new JPanel[4];
    private int cubesPlaced;
    private int startingPlayer;
    
    public void display()
    {
        victoryPointCardFrame.setVisible(true);
    }
    public void disableVictoryCards()
    {
        victoryPointCardFrame.setEnabled(false);
    }
    
    public void enableVictoryCards(int currentPlayer)
    {
        victoryPointCardFrame.setVisible(true);
        startingPlayer = currentPlayer;
        cubesPlaced = 0;
        victoryPointCardFrame.setEnabled(true);
    }
    
    private VictoryPointCards()
    {
        System.out.println("constructor called");
        victoryPointCardFrame = new JFrame("Victory Point Cards");
        victoryPointCardFrame.setLayout(new GridLayout(1,4));
        //victoryPointCardFrame.setSize(800, 200);
        
        for(int index = 0; index < 4; index++)
        {
            int victoryCardIndex = index;
            String image = Constants.IMAGES_PATH + "VictoryCard" + (index+1) + ".jpg";
            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
            ImagePanel imagePanel = new ImagePanel(tileImage,250,250,0,BorderFactory.createRaisedBevelBorder());
            victoryPointCard[index] = imagePanel;
            victoryPointCardFrame.add(victoryPointCard[index]);
            
            victoryPointCard[index].addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent me) {
//                    System.out.println("Card Clicked");
//                    ((JPanel)me.getSource()).add(new ResourceCube(Constants.RESOURCE_CUBE_VICTORY));
//                    ((JPanel)me.getSource()).repaint();
//                    ((JPanel)me.getSource()).revalidate();
                    addVictoryCube(victoryCardIndex);
                    cubesPlaced++;
                    placeCubesForTheTurn();
//                    int selection1 = Calendar.getInstance().getTime().getHours()%4;
//                    int selection2 = Calendar.getInstance().getTime().getMinutes()%4;
//                    addVictoryCube(selection1);
//                    addVictoryCube(selection2);
                    //disableVictoryCards();
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
            });
        }
        victoryPointCardFrame.pack();
        victoryPointCardFrame.setEnabled(false);
        victoryPointCardFrame.setVisible(true);
        victoryPointCardFrame.setLocationRelativeTo(null);
        victoryPointCardFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public static VictoryPointCards getInstance()
    {
        if(victoryPointCards == null)
            victoryPointCards = new VictoryPointCards();
        return victoryPointCards;
    }
    
    public void addVictoryCube(int victoryPointCardIndex)
    {
        System.out.println("Card Index : "+victoryPointCardIndex);
        JPanel resourceCube = new ResourceCube(Constants.RESOURCE_CUBE_VICTORY);
        victoryPointCard[victoryPointCardIndex].add(resourceCube);
        victoryPointCard[victoryPointCardIndex].repaint();
        victoryPointCard[victoryPointCardIndex].revalidate();
    }
    
    public void placeCubesForTheTurn()
    {
        for(int i=0;i<3;i++){
            if(cubesPlaced == 3){
                victoryPointCardFrame.setVisible(false);
                disableVictoryCards();
                break;
            }
            
            System.out.println("Player : " + Constants.cultures[AgeOfMythology.playerShift[startingPlayer]]);
            
            if(startingPlayer==0){
                startingPlayer = (startingPlayer+1)%3;
                break;
            }
            else{
                cubesPlaced++;
                int selection = (Calendar.getInstance().getTime().getSeconds()+cubesPlaced)%4;
                addVictoryCube(selection);
                startingPlayer = (startingPlayer+1)%3;
            }
        }
    }
    
    public int returnVictoryPointCubesIndex(int cardIndex)
    {
        int numOfCubesOnCard = victoryPointCard[cardIndex].getComponentCount();
        System.out.println("Victory point cubes : " + numOfCubesOnCard);
        victoryPointCard[cardIndex].removeAll();
        victoryPointCard[cardIndex].repaint();
        victoryPointCard[cardIndex].revalidate();
        return numOfCubesOnCard;
    }
}
