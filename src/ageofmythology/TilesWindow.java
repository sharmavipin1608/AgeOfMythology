/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author vipinsharma
 */
public class TilesWindow {

    private static List<JPanel> tileList = new ArrayList<JPanel>();
    private static JFrame tilesFrame;
    private static int currentTileCounter=18;
    private static int playerChances = 1;
    private static int tilesPlacedByPlayer = 0;

    public static JFrame initializeTilesWindow(int[] chosenTiles) {

        tilesFrame = new JFrame("Tiles");

        tilesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tilesFrame.setSize(570, 300);
        tilesFrame.setLayout(new GridLayout(3, 6));
        Border panelBorder = BorderFactory.createRaisedBevelBorder();
//        int i =0;
        for (int i = 0; i < 18; i++) {
            int index = chosenTiles[i];
            int placeHolder = i;
            String image = Constants.IMAGES_PATH + "Tile" + index + ".png";
            //System.out.println("Image : " + image);
            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
            ImagePanel tilePanel = new ImagePanel(tileImage, 90, 90, index);
            tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 7));
            tilesFrame.add(tilePanel);
            tileList.add(placeHolder, tilePanel);

            tilePanel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent me) {
                    BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
                    System.out.println("Mouse Clicked on tile : " + index);
                    int tileType = GameUtilities.convertResourceTileToAreaType(index);
                    System.out.println("Tile to be placed on area : " + Constants.prodKey[tileType]);
                    Boolean ifTileCanBePlaced = ((BoardPanel) boardPanel).placeTileInProdArea(tileType, (JPanel) me.getSource());
                    if (ifTileCanBePlaced) {
                        //tileList.add(placeHolder,null);
                        //tileList.remove(placeHolder);
                        
                        //((ImagePanel) me.getSource()).setType(0);
                        tileList.remove(((JPanel) me.getSource()));
                        tilesFrame.remove((JPanel) me.getSource());
                        currentTileCounter--;
                        tilesPlacedByPlayer++;
                        if(currentTileCounter==0 || tilesPlacedByPlayer ==6)
                        {
                            tilesFrame.dispose();
                            System.out.println("Dispose tiles frame");
                            JOptionPane.showMessageDialog(new JFrame(), "Game is Ready to Play.");
                            AgeOfMythology.placeVictoryPointCubes(0);
                        }
                            
                        tilesFrame.revalidate();
                        tilesFrame.repaint();
                        tilesFrame.pack();
                        nextPlay();
                    } else {
                        System.out.println("Can't place tile");
                        JOptionPane.showMessageDialog(new JFrame(), "No " + Constants.prodKey[tileType] + " on the board to place the tile.");
                    }
                    System.out.println("remove panel");
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
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        
        //new piece of code - be ready to remove in case of any issues
        
        return tilesFrame;
    }

    public static void addSkipButton()
    {
        JButton skipButton = new JButton("Skip");
        skipButton.setVisible(false);
        tilesFrame.add(skipButton);
    }
    
//    public static void nextPlay() {
////        AgeOfMythology.nextPlayerBoard();
//        System.out.println("next play");
//        int placeHolder = 0;
//        int index = 0;
//        JPanel boardPanel = new JPanel();
//        boolean changeBoard = true;
//        for (JPanel tilePanel : tileList) {
//            //System.out.println("========================starting loop====================================");
//            //JPanel tilePanel = tileList.get(0);
//            //placeHolder = 0;
////            for(JPanel tilePanel:tileList)
////            {
//            index = ((ImagePanel) tilePanel).getType();
//            //System.out.println("lets see index : " + index);
//            if (index == 0) {
//                //System.out.println("should never come here");
//                placeHolder++;
//                continue;
//            } else {
//                //System.out.println("placeholder : " + placeHolder);
//                if (changeBoard) {
//                    boardPanel = (BoardPanel) AgeOfMythology.returnNextPlayer();
//                }
//
//                if (!AgeOfMythology.getAutomate()) {
//                    break;
//                }
//                //System.out.println("Mouse Clicked on tile : " + index);
////                    index = ((ImagePanel)tilePanel).getType();
//                //System.out.println("check if type is correct : " + index);
//                int tileType = GameUtilities.convertResourceTileToAreaType(index);
//                System.out.println("Tile to be placed on area : " + Constants.prodKey[tileType]);
//                Boolean ifTileCanBePlaced = ((BoardPanel) boardPanel).placeTileInProdArea(tileType, tilePanel);
//                if (ifTileCanBePlaced) {
//                    changeBoard = true;
//                    ((ImagePanel) tilePanel).setType(0);
//                    //tileList.add(placeHolder,null);
////                        tileList.remove(0);
//                    tilesFrame.remove(tilePanel);
//                    currentTileCounter--;
//                    //System.out.println("----breking-----");
//                        //sbreak;
////                        tilesFrame.revalidate();
////                        tilesFrame.repaint();
////                        tilesFrame.pack();
//                    //nextPlay();
//                } else {
//                    System.out.println("Can't place tile : " + Constants.prodKey[tileType]);
//                    changeBoard = false;
////                        JOptionPane.showMessageDialog(new JFrame(),"No "+ Constants.prodKey[tileType] + " on the board to place the tile.");
//                    continue;
//                }
//                //System.out.println("remove panel");
////                    System.out.println("lets play");
//            }
//            //}
//            //System.out.println("-----reached here------");
//
//        }
//        //System.out.println("-----reached here------");
//        tilesFrame.revalidate();
//        tilesFrame.repaint();
//        tilesFrame.pack();
//    }
    
    public static void nextPlay() {
//        AgeOfMythology.nextPlayerBoard();
        System.out.println("next play");
        //int placeHolder = 0;
        int index = 0;
        JPanel boardPanel = new JPanel();
        AgeOfMythology.setAutomate(true);
        boolean startGame = false;
        //boolean changeBoard = true;
        
        if(currentTileCounter==0)
        {
            return;
        }
        
        while(true)
        {
            System.out.println("------outer------");
            boardPanel = (BoardPanel) AgeOfMythology.returnNextPlayer();
            if(!AgeOfMythology.getAutomate())
            {
                playerChances++;
                System.out.println("%%%%%%%%%%%%%Chance : " + playerChances);
                for (JPanel tilePanel : tileList) {
                    index = ((ImagePanel) tilePanel).getType();
                    if (index > 0) {
                        int tileType = GameUtilities.convertResourceTileToAreaType(index);
                        Boolean ifTileCanBePlaced = ((BoardPanel) boardPanel).ifTileCanBePlacedInProdArea(tileType);
                        if(ifTileCanBePlaced)
                        {
                            startGame = true;
                            break;
                        }
                    }
                }
                
                if(!startGame)
                {
                    System.out.println("not coming true____________+++++++");
                    if(playerChances >= 6 || tilesPlacedByPlayer == 6)
                    {
                        tilesFrame.dispose();
                        JOptionPane.showMessageDialog(new JFrame(), "Game is Ready to Play.");
                        AgeOfMythology.placeVictoryPointCubes(0);
                        //break;
                    }
                    else
                    {
                        System.out.println("out here to chk where it goes next");
                        continue;
                    }
                        
                }
                
                break;
            }
            for (JPanel tilePanel : tileList) {
                System.out.println("------inner------");
                index = ((ImagePanel) tilePanel).getType();
                if (index > 0) {
                    int tileType = GameUtilities.convertResourceTileToAreaType(index);
                    System.out.println("Tile to be placed on area : " + Constants.prodKey[tileType]);
                    Boolean ifTileCanBePlaced = ((BoardPanel) boardPanel).placeTileInProdArea(tileType, tilePanel);
                    
                    if (ifTileCanBePlaced) {
                        //((ImagePanel) tilePanel).setType(0);
                        tileList.remove(tilePanel);
                        tilesFrame.remove(tilePanel);
                        currentTileCounter--;
                        break;
                    } else {
                        System.out.println("Can't place tile : " + Constants.prodKey[tileType]);
                        continue;
                    }
                }
            }
        }
        
        tilesFrame.revalidate();
        tilesFrame.repaint();
        tilesFrame.pack();
        
        
    }
}
