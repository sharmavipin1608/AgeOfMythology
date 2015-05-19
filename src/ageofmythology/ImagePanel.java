/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author vipinsharma
 */
public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image image = null;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ImagePanel(Image image, int panelWidth, int panelHeight) {
        this(image,panelWidth,panelHeight,0,null);
    }

    public ImagePanel(Image image, int panelWidth, int panelHeight, int type) {
        this(image,panelWidth,panelHeight,type,null);
    }
    
    public ImagePanel(Image image, int panelWidth, int panelHeight, int type, Border border) {
        this.image = image;
        this.type = type;
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setMaximumSize(new Dimension(panelWidth, panelHeight));
        this.setMinimumSize(new Dimension(panelWidth, panelHeight));
        this.setBorder(border);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
