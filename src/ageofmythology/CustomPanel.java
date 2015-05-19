/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author vipinsharma
 */
public class CustomPanel extends JPanel {
    private int terrainType;
    
    /*
    * Main Constructor
    */
    public CustomPanel(int panelWidth, int panelHeight, boolean opacity, boolean border, int terrainType)
    {
        this.terrainType = terrainType;
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setMaximumSize(new Dimension(panelWidth,panelHeight));
        this.setOpaque(opacity);
        if(terrainType > 0)
        {
            Border panelBorder = BorderFactory.createRaisedBevelBorder();
            JLabel panelLabel = new JLabel(Constants.prodKey[terrainType]);
            panelLabel.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.add(panelLabel,BorderLayout.PAGE_END);
            this.setBorder(panelBorder);
        }
    }
    
    /*
    * Create a custom panel of required size
    */
    public CustomPanel(int panelWidth, int panelHeight, boolean opacity)
    {
        this(panelWidth,panelHeight,opacity,false,0);
    }
    
    /*
    * List the places used in 
    *   1. Rendering panels for the production Area
    */
    public CustomPanel(int panelWidth, int panelHeight, int terrainType)
    {
        this(panelWidth,panelHeight,false,true,terrainType);
    }
    
    /*
    * List the places used in 
    *   1. Rendering panels for the city Area
    */
    public CustomPanel(int panelWidth, int panelHeight)
    {
        this(panelWidth,panelHeight,false,true,0);
    }
    
    public int getTerrainType()
    {
        return this.terrainType;
    }
}
