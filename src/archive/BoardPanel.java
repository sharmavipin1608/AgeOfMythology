///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package archive;
//
//import ageofmythology.Constants;
//import ageofmythology.CustomPanel;
//import ageofmythology.ImagePanel;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Toolkit;
//import javax.swing.BoxLayout;
//import javax.swing.JPanel;
//
///**
// *
// * @author vipinsharma
// */
//public class BoardPanel 
//{
//    public static JPanel[][] cityGrid = new JPanel[4][4];
//    
////    public JPanel getCityGrid(int i, int j)
////    {
////        return cityGrid[i][j];
////    }
//    
//    public static JPanel setupBoard(String culture) 
//    {
//        String holdingAreaImage = Constants.IMAGES_PATH+culture+"_holdingArea.jpg";
//        String productionAreaImage = Constants.IMAGES_PATH+culture+"_productionArea.jpg";
//        String cityAreaImage = Constants.IMAGES_PATH+culture+"_cityArea.jpg";
//        
//        System.out.println(holdingAreaImage);
//        
//        //CustomPanel boardPanel = new CustomPanel(0,Constants.BOARD_PANEL_WIDTH, Constants.BOARD_PANEL_HEIGHT,true,false);
//        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.X_AXIS));
//
//        CustomPanel boardArea = new CustomPanel(0,Constants.BOARD_PLAY_AREA_WIDTH, Constants.BOARD_PLAY_AREA_HEIGHT,true,false);
////        JPanel boardArea = new JPanel();
////        boardArea.setPreferredSize(boardPlayDimension);
////        boardArea.setMaximumSize(boardPlayDimension);
////        boardArea.setMinimumSize(boardPlayDimension);
////        boardArea.setBackground(Color.red);
//
//        boardArea.setLayout(new BoxLayout(boardArea, BoxLayout.Y_AXIS));
//
//        Image holding = Toolkit.getDefaultToolkit().createImage(holdingAreaImage);
//        ImagePanel holdingArea = new ImagePanel(holding,Constants.HOLDING_AREA_WIDTH, Constants.HOLDING_AREA_HEIGHT);
////        holdingArea.setPreferredSize(holdingAreaDimension);
////        holdingArea.setMaximumSize(holdingAreaDimension);
////        holdingArea.setMinimumSize(holdingAreaDimension);
//
//        CustomPanel prodAndCityBase = new CustomPanel(0,Constants.BOARD_PLAY_AREA_WIDTH, Constants.CITY_AREA,true,false);
////        JPanel prodAndCityBase = new JPanel();
////        prodAndCityBase.setPreferredSize(cityProdDimension);
////        prodAndCityBase.setMaximumSize(cityProdDimension);
////        prodAndCityBase.setMinimumSize(cityProdDimension);
////        prodAndCityBase.setBackground(Color.black);
//        prodAndCityBase.setLayout(new BoxLayout(prodAndCityBase, BoxLayout.X_AXIS));
//
//        //trying to put image the boxes
//        Image production = Toolkit.getDefaultToolkit().createImage(productionAreaImage);
//        ImagePanel productionArea = new ImagePanel(production,Constants.CITY_AREA, Constants.CITY_AREA);
////        productionArea.setPreferredSize(cityAreaDimension);
////        productionArea.setMaximumSize(cityAreaDimension);
////        productionArea.setMinimumSize(cityAreaDimension);
//
//        Image city = Toolkit.getDefaultToolkit().createImage(cityAreaImage);
//        ImagePanel cityArea = new ImagePanel(city,Constants.CITY_AREA, Constants.CITY_AREA);
////        cityArea.setPreferredSize(cityAreaDimension);
////        cityArea.setMaximumSize(cityAreaDimension);
////        cityArea.setMinimumSize(cityAreaDimension);
////        cityArea.setBackground(Color.LIGHT_GRAY);
//        cityArea.setLayout(new GridLayout(4,4));
//        
//        for(int i=0;i<4;i++)
//        {
//            for(int j=0;j<4;j++)
//            {
//                cityGrid[i][j] = new CustomPanel(0,Constants.PANEL_SIZE, Constants.PANEL_SIZE,false,true);
//                productionArea.add(cityGrid[i][j]);
//            }
//        }
//        prodAndCityBase.add(productionArea);
//        prodAndCityBase.add(cityArea);
//
//        boardArea.add(holdingArea);
//        boardArea.add(prodAndCityBase);
//
//        CustomPanel bankPanel = new CustomPanel(0,Constants.BANK_WIDTH, Constants.BANK_HEIGHT,true,false);
////        JPanel bankPanel = new JPanel();
////        bankPanel.setPreferredSize(bankAreaDimension);
////        bankPanel.setMaximumSize(bankAreaDimension);
////        bankPanel.setMinimumSize(bankAreaDimension);
////        bankPanel.setBackground(Color.yellow);
//
//        boardPanel.add(boardArea);
//        boardPanel.add(bankPanel);
//        System.out.println("return");
//        return boardPanel;
//    }
//}
