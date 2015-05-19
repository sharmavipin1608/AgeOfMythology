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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vipinsharma
 */
public class BuildingTiles {
    private JFrame buildingTilesWindow;
    private ArrayList<ImagePanel> buildingTilesList = new ArrayList<>();
    
    public BuildingTiles()
    {
        buildingTilesWindow = new JFrame("Building Tiles");
        buildingTilesWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        buildingTilesWindow.setSize(630, 200);
        buildingTilesWindow.setLayout(new GridLayout(2, 7));
        
        for(int index = 0; index < 14; index++)
        {
            String image = Constants.IMAGES_PATH + Constants.buildingTiles[index] + ".png";
            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
            ImagePanel tilePanel = new ImagePanel(tileImage, 90, 90, index);
            tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 7));
            buildingTilesWindow.add(tilePanel);
            buildingTilesList.add(tilePanel);
            
            int cultureIndex = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
            BoardPanel boardPanel = AgeOfMythology.cultureBoards[cultureIndex];
            if(!boardPanel.checkForValidityOfBuildingByIndex(index))
                tilePanel.add(disablePanel());
            else{
                tilePanel.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent me) {

                        BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
                        //JPanel buildingCard = (JPanel)me.getSource();
                        boolean tilePlaced = boardPanel.placeTileInCityArea(((ImagePanel)me.getSource()).getType());
                        if(tilePlaced)
                        {
                            System.out.println("tile placed : "+ ((ImagePanel)me.getSource()).getType() );
                            if(((ImagePanel)me.getSource()).getType() != 0)
                            {
    //                            String image = Constants.IMAGES_PATH + "Cross_1.png";
    //                            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
    //                            ImagePanel imagePanel1 = new ImagePanel(tileImage,75,75,0);
    //                            imagePanel1.setOpaque(false);
                                ImagePanel imagePanel1 = disablePanel();
                                ((ImagePanel)me.getSource()).add(imagePanel1);
                            }
                            ((ImagePanel)me.getSource()).removeMouseListener(this);
                            ((ImagePanel)me.getSource()).repaint();
                            ((ImagePanel)me.getSource()).revalidate();
                            buildingTilesWindow.setEnabled(false);
                            buildingTilesWindow.dispose();
                            AgeOfMythology.play();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(new JFrame(), "No new tiles can be placed in the city area.");
                        }
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
        }
        buildingTilesWindow.pack();
        buildingTilesWindow.setVisible(false);
        buildingTilesWindow.setLocationRelativeTo(null);
    }
    
    public void displayBuildingTilesWindow()
    {
        buildingTilesWindow.setVisible(true);
    }
    
    public ImagePanel disablePanel()
    {
        String image = Constants.IMAGES_PATH + "Cross_1.png";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        ImagePanel imagePanel1 = new ImagePanel(tileImage,75,75,0);
        imagePanel1.setOpaque(false);
        return imagePanel1;
    }
    
    public void playCard()
    {
        boolean tilePlaced = false;
        int cultureIndex = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
        BoardPanel boardPanel = AgeOfMythology.cultureBoards[cultureIndex];
        
        System.out.println("Play Card " + cultureIndex + " culture " + boardPanel.getCulture());
        
        for(ImagePanel buildingTile : buildingTilesList){
            System.out.println(Constants.buildingTiles[buildingTile.getType()]);
            if(boardPanel.checkForValidityOfBuildingByIndex(buildingTile.getType()))
            {
                tilePlaced = boardPanel.placeTileInCityArea(buildingTile.getType());
                if(tilePlaced)
                {
                    System.out.println(boardPanel.getCulture() + " placed : "+ Constants.buildingTiles[buildingTile.getType()]);
                    break;
                }
            }
        }
        
        AgeOfMythology.play();
    }
}
