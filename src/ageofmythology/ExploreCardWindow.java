/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class ExploreCardWindow {
    private static JFrame tilesFrame;
    private static List<ImagePanel> tileList = new ArrayList<ImagePanel>();
    private static int tilesPlayed = 0;
    private boolean godCard;
    private int numberOfTilesForTheRound = 0;
    
    public void displayExploreCardWindow()
    {
        //boolean userCanPlayTile = false;
        tilesFrame.setVisible(true);
//        for(JPanel tile : tileList)
//        {
//            int tileType = GameUtilities.convertResourceTileToAreaType(((ImagePanel)tile).getType());
//            Boolean ifTileCanBePlaced = AgeOfMythology.returnCurrentBoardPlayer().ifTileCanBePlacedInProdArea(tileType);
//            if(ifTileCanBePlaced)
//            {
//                userCanPlayTile = true;
//                break;
//            }
//        }
//        if(!userCanPlayTile)
//            JOptionPane.showMessageDialog(new JFrame(), "None of the tiles drawn can be played.");
    }
    
    public void play()
    {
        tilesFrame.setVisible(true);
        int index = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
        System.out.println("Playing explore : " + index);
        tilesPlayed = 0;
        BoardPanel boardPanel = AgeOfMythology.cultureBoards[index];
        placeTiles(boardPanel);
    }
    
    public void playNext()
    {
        int index;
        tilesFrame.setVisible(true);
        if(godCard){
            index = 0;
            godCard = false;
        }
        else
        {
            index = 1;
        }
        BoardPanel boardPanel = (BoardPanel)AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[index]];
        placeTiles(boardPanel);
    }
    
    private void placeTiles(BoardPanel boardPanel)
    {
        System.out.println("Place tiles");
        //looop here
        for(int i = tilesPlayed; i<4; i++)
        {
            System.out.println("Tiles Played : " + tilesPlayed);
            //tilesPlayed++;
            if(boardPanel.getIndexCulture() == 0)
            {
                break;
            }
            else if(boardPanel.getIndexCulture() != 0)
            {
                for(ImagePanel tile : tileList)
                {
                    if(tile.getType() != 0)
                    {
                        int tileAreaType = GameUtilities.convertResourceTileToAreaType(tile.getType());
                        boolean ifTilePlaced = boardPanel.placeTileInProdArea(tileAreaType, tile);
                        if(ifTilePlaced)
                        {
                            tile.setType(0);
                            //tilesPlayed++;
                            tilesFrame.remove(tile);
                            tilesFrame.revalidate();
                            tilesFrame.repaint();
                            tilesFrame.pack();
                            break;
                        }
                    }
                }
                tilesPlayed++;
            }
            
            if(godCard){
                godCard = false;
            }
            else{
                if(boardPanel.getIndexCulture() != 2)
                    boardPanel = (BoardPanel)AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[boardPanel.getIndexCulture()+1]];
                else
                    boardPanel = (BoardPanel)AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[0]];
            }
        }
        
        if(tilesPlayed == 4)
        {
            tilesFrame.dispose();
            AgeOfMythology.play();
        }
    }
            
    public ExploreCardWindow(int[] chosenTiles,boolean cardType){
        godCard = cardType;
        tilesFrame = new JFrame("Explore");

        tilesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tilesFrame.setSize(360, 100);
        tilesFrame.setLayout(new GridLayout(1, 4));
        tilesFrame.setVisible(false);
        tilesFrame.setLocationRelativeTo(null);
        
        BoardPanel boardPanel = GameUtilities.currentPlayerBoard();
        
        if(boardPanel.equals("Norse") && godCard){
            JOptionPane.showMessageDialog(new JFrame(),"Norse played the god card");
            numberOfTilesForTheRound = Constants.NUMBER_OF_TILES_GODCARD_NORSE;
        }
        else
            numberOfTilesForTheRound = Constants.NUMBER_OF_TILES;
                    
        for(int index=0;index<chosenTiles.length;index++)
        {
            int tileChosen = chosenTiles[index];
            String image = Constants.IMAGES_PATH + "Tile" + chosenTiles[index] + ".png";
            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
            ImagePanel tilePanel = new ImagePanel(tileImage, 90, 90, chosenTiles[index]);
            tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 7));
            tilesFrame.add(tilePanel);
            tileList.add(tilePanel);
            
            tilePanel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent me) {
                    BoardPanel boardPanel = AgeOfMythology.returnCurrentBoardPlayer();
                    int tileType = GameUtilities.convertResourceTileToAreaType(tileChosen);
                    Boolean ifTileCanBePlaced = ((BoardPanel) boardPanel).placeTileInProdArea(tileType, (JPanel) me.getSource());
                    if (ifTileCanBePlaced) {
                        ((ImagePanel) me.getSource()).setType(0);
                        tilesPlayed++;
                        tilesFrame.remove((JPanel) me.getSource());
                        tilesFrame.revalidate();
                        tilesFrame.repaint();
                        tilesFrame.pack();
                        playNext();
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "No " + Constants.prodKey[tileType] + " on the board to place the tile.");
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        
        Button skip = new Button("Skip");
        skip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tilesPlayed++;
                playNext();
            }
        });
            
        tilesFrame.add(skip);
    }
}
