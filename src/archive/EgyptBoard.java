/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import ageofmythology.ImagePanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class EgyptBoard implements Board{

    @Override
    public void initializeBoard() {
        System.out.println("Egypt");
        
//        JFrame board = new JFrame("Egypt Board");
//        board.setLayout(new GridLayout(3,3));
//        board.setSize(500, 500);
//        //board.setLayout(new GridLayout(3,1));
//        //board.setBackground(Color.yellow);
//        
//        //ImageIcon im = new ImageIcon("demo.jpg");
//        //Image img = new Image("demo.jpg");
//        
//        //board.seti
//        
//        //board.setIconImage(null);setIcon(im);
//        Image background = Toolkit.getDefaultToolkit().createImage("/Users/vipinsharma/Downloads/demo.jpg");
////////        board.setIconImage(background);
////////        
////////        
//        ImagePanel hold_area = new ImagePanel(background);
//        board.add(hold_area);
////        JPanel prod_area = new JPanel();
////        JPanel city_area = new JPanel();
////        
////        hold_area.setSize(0, 200);
////        hold_area.setBackground(Color.BLUE);
////        
////        board.add(hold_area);
////        board.add(prod_area);
////        board.add(city_area);
////        hold_area.d
//        //board.pack();
//        
//        board.setVisible(true);
        
        JFrame jf = new JFrame("jf");
    jf.setSize(500,500);

    Image background = Toolkit.getDefaultToolkit().createImage("/Users/vipinsharma/Downloads/demo.jpg");
    
    //ImagePanel ip = new ImagePanel(background);
    //jf.add(ip);
    //jf.setBackground(Color.yellow);
    

            //jf.setContentPane(new ImagePanel(background));
    jf.setLayout(new GridLayout(3,3));
//    jf.add(new ImagePanel(background));
    //jf.setIconImage(background);
    jf.setVisible(true);
    }
}
