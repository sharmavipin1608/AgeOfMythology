/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author vipinsharma
 */
public class ResourceCube extends JPanel{
    public ResourceCube(String resourceType)
    {
        this.setPreferredSize(new Dimension(30,30));
        this.setMaximumSize(new Dimension(30,30));
        this.setMinimumSize(new Dimension(30,30));
        this.setBackground(Constants.RESOURCE_BG_COLOR.get(resourceType));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
