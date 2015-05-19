/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.util.HashMap;

/**
 *
 * @author vipinsharma
 */
public class MapTileToResource {
    public static HashMap returnResources(int resourceTile)
    {
        HashMap<String,Integer> resources = new HashMap<String,Integer>();
        switch(resourceTile)
        {
            case 1:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 2);
                break;
            case 2:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 1);
                break;
            case 3:
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 1);
                break;
            case 4: 
                resources.put(Constants.RESOURCE_CUBE_GOLD, 1);
                break;
            case 5:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 2);
                break;
            case 6:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 1);
                break;
            case 7:
                resources.put(Constants.RESOURCE_CUBE_GOLD, 1);
                break;
            case 8: 
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 1);
                break;
            case 9:
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 10:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 1);
                break;
            case 11:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 1);
                break;
            case 12: 
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 1);
                break;
            case 13:
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 14:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 1);
                break;
            case 15:
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 1);
                break;
            case 16: 
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 2);
                break;
            case 17:
                resources.put(Constants.RESOURCE_CUBE_GOLD, 1);
                break;
            case 18:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 1);
                break;
            case 19:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 1);
                break;
            case 20: 
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 1);
                break;
        }
        return resources;
    }
    
    public static HashMap returnCostForBuildingTiles(int buildingTile)
    {
        HashMap<String,Integer> resources = new HashMap<String,Integer>();
        switch(buildingTile)
        {
            case 0:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 2);
                resources.put(Constants.RESOURCE_CUBE_WOOD, 2);
                break;
            case 1:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 3);
                break;
            case 2:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 3);
                break;
            case 3:
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 2);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 3);
                break;
            case 4:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 2);
                resources.put(Constants.RESOURCE_CUBE_WOOD, 2);
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 2);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 5:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 6:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 4);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 1);
                break;
            case 7:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 8:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 2);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 3);
                break;
            case 9:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 2);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 3);
                break;
            case 10:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_WOOD, 2);
                break;
            case 11:
                resources.put(Constants.RESOURCE_CUBE_WOOD, 3);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 2);
                break;
            case 12:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 4);
                resources.put(Constants.RESOURCE_CUBE_WOOD, 4);
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 4);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 4);
                break;
            case 13:
                resources.put(Constants.RESOURCE_CUBE_FOOD, 10);
                resources.put(Constants.RESOURCE_CUBE_WOOD, 10);
                resources.put(Constants.RESOURCE_CUBE_FAVOR, 10);
                resources.put(Constants.RESOURCE_CUBE_GOLD, 10);
                break;            
        }
        return resources;
    }
}
