/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class Bank {
    private static Bank bank = null;
    private static JFrame bankWindow = new JFrame("Bank");
    ImagePanel imagePanel;
    private CustomPanel foodCubesPanel;
    private CustomPanel favorCubesPanel;
    private CustomPanel woodCubesPanel;
    private CustomPanel goldCubesPanel;
    private CustomPanel victoryCubesPanel;
    private CustomPanel victoryCubesPanel1;
    private ArrayList<ResourceCube> foodCubes = new ArrayList<ResourceCube>();
    private ArrayList<ResourceCube> favorCubes = new ArrayList<ResourceCube>();
    private ArrayList<ResourceCube> woodCubes = new ArrayList<ResourceCube>();
    private ArrayList<ResourceCube> goldCubes = new ArrayList<ResourceCube>();
    private ArrayList<ResourceCube> victoryCubes = new ArrayList<ResourceCube>();
    
    private Bank()
    {
        String image = Constants.IMAGES_PATH + "Bank.jpg";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        imagePanel = new ImagePanel(tileImage,800,494,0,BorderFactory.createRaisedBevelBorder());
        
        bankWindow.setContentPane(imagePanel);
        
        imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.Y_AXIS));
        foodCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        foodCubesPanel.setLayout(new BoxLayout(foodCubesPanel,BoxLayout.X_AXIS));
        
        favorCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        favorCubesPanel.setLayout(new BoxLayout(favorCubesPanel,BoxLayout.X_AXIS));
        
        woodCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        woodCubesPanel.setLayout(new BoxLayout(woodCubesPanel,BoxLayout.X_AXIS));
        
        goldCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        goldCubesPanel.setLayout(new BoxLayout(goldCubesPanel,BoxLayout.X_AXIS));
        
        victoryCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        victoryCubesPanel.setLayout(new BoxLayout(victoryCubesPanel,BoxLayout.X_AXIS));
        
        victoryCubesPanel1 = new CustomPanel(Constants.HOLDING_AREA_WIDTH,50,false);
        victoryCubesPanel1.setLayout(new BoxLayout(victoryCubesPanel1,BoxLayout.X_AXIS));
        
        //victoryCubesPanel.setLayout(new GridLayout(2,25));
        
        imagePanel.add(foodCubesPanel);
        imagePanel.add(favorCubesPanel);
        imagePanel.add(woodCubesPanel);
        imagePanel.add(goldCubesPanel);
        imagePanel.add(victoryCubesPanel);
        imagePanel.add(victoryCubesPanel1);
        
        distributeInitialTiles();
        
        bankWindow.setSize(800, 494);
        bankWindow.setVisible(false);
        bankWindow.setLocationRelativeTo(null);
        bankWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public static Bank getInstance()
    {
        if(bank == null)
           bank = new Bank();
        return bank;
    }
    
    public void displayBankWindow()
    {
        bankWindow.setVisible(true);
    }
    
    public void distributeInitialTiles()
    {
        for(int index = 0; index < 25; index++)
        {
            foodCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_FOOD));
            favorCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_FAVOR));
            woodCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_WOOD));
            goldCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_GOLD));
            victoryCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_VICTORY));
            
            foodCubesPanel.add(foodCubes.get(index));
            favorCubesPanel.add(favorCubes.get(index));
            woodCubesPanel.add(woodCubes.get(index));
            goldCubesPanel.add(goldCubes.get(index));
            victoryCubesPanel.add(victoryCubes.get(index));
        }
        
        for(int index = 25; index < 30; index++)
        {
            victoryCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_VICTORY));
            victoryCubesPanel1.add(victoryCubes.get(index));
        }
    }
    
    public void addResource(String resourceType)
    {
        ResourceCube resource = new ResourceCube(resourceType);
        if(resourceType.equals(Constants.RESOURCE_CUBE_FOOD))
        {
            foodCubes.add(resource);
            foodCubesPanel.add(foodCubes.get(foodCubes.size()-1));
            
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_FAVOR))
        {
            favorCubes.add(resource);
            favorCubesPanel.add(favorCubes.get(favorCubes.size()-1));
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_WOOD))
        {
            woodCubes.add(resource);
            woodCubesPanel.add(woodCubes.get(woodCubes.size()-1));
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_GOLD))
        {
            goldCubes.add(resource);
            goldCubesPanel.add(goldCubes.get(goldCubes.size()-1));
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_VICTORY))
        {
            victoryCubes.add(resource);
            if(victoryCubes.size() > 25)
                victoryCubesPanel1.add(victoryCubes.get(victoryCubes.size()-1));
            else
                victoryCubesPanel.add(victoryCubes.get(victoryCubes.size()-1));
        }
        imagePanel.repaint();
        imagePanel.revalidate();
    }
    
    public void removeResource(String resourceType)
    {
        if(resourceType.equals(Constants.RESOURCE_CUBE_FOOD))
        {
            foodCubesPanel.remove(foodCubes.get(foodCubes.size()-1));
            foodCubes.remove(foodCubes.size()-1);
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_FAVOR))
        {
            favorCubesPanel.remove(favorCubes.get(favorCubes.size()-1));
            favorCubes.remove(favorCubes.size()-1);
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_WOOD))
        {
            woodCubesPanel.remove(woodCubes.get(woodCubes.size()-1));
            woodCubes.remove(woodCubes.size()-1);
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_GOLD))
        {
            goldCubesPanel.remove(goldCubes.get(goldCubes.size()-1));
            goldCubes.remove(goldCubes.size()-1);
        }
        else if(resourceType.equals(Constants.RESOURCE_CUBE_VICTORY))
        {
            if(victoryCubes.size() > 25)
                victoryCubesPanel1.remove(victoryCubes.get(victoryCubes.size()-1));
            else
                victoryCubesPanel.remove(victoryCubes.get(victoryCubes.size()-1));
            victoryCubes.remove(victoryCubes.size()-1);
        }
        imagePanel.repaint();
        imagePanel.revalidate();
    }
    
    public HashMap bankResources()
    {
        HashMap<String,Integer> bankResourceMap = new HashMap<>();
        bankResourceMap.put(Constants.RESOURCE_CUBE_FOOD,foodCubes.size());
        bankResourceMap.put(Constants.RESOURCE_CUBE_FAVOR,favorCubes.size());
        bankResourceMap.put(Constants.RESOURCE_CUBE_WOOD,woodCubes.size());
        bankResourceMap.put(Constants.RESOURCE_CUBE_GOLD,goldCubes.size());
        return bankResourceMap;
    }
}
