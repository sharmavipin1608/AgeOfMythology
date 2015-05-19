/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author vipinsharma
 */
public class MarkCard {
    
    public static ImagePanel getInstance()
    {
        String image = Constants.IMAGES_PATH + "Cross.png";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        ImagePanel imagePanel = new ImagePanel(tileImage,175,250,0);
        imagePanel.setOpaque(false);
        return imagePanel;
    }
}
