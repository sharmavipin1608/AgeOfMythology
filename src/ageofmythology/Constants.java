/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import java.awt.Color;
import java.util.HashMap;
/**
 *
 * @author vipinsharma
 */
public class Constants {

    public static String CULTURE_GREEK = "Greek";
    public static String CULTURE_NORSE = "Norse";
    public static String CULTURE_EGYPT = "Egypt";

    public static int BOARD_FRAME_WIDTH = 1050;
    public static int BOARD_FRAME_HEIGHT = 675;

    public static int BOARD_PANEL_WIDTH = 1050;
    public static int BOARD_PANEL_HEIGHT = 650;

    public static int BOARD_PLAY_AREA_WIDTH = 800;
    public static int BOARD_PLAY_AREA_HEIGHT = 650;

    public static int BANK_WIDTH = 250;
    public static int BANK_HEIGHT = 650;

    public static int HOLDING_AREA_WIDTH = 800;
    public static int HOLDING_AREA_HEIGHT = 250;

    public static int CITY_AREA = 400;

    public static int PANEL_SIZE = 100;

    //Path of the images folder
    public static String IMAGES_PATH = "/Users/vipinsharma/NetBeansProjects/AgeOfMythology/src/Images/";
    
    //Production grid characteristics of the boards.
    //These numbers refer to the type of area and can be decoded using "prodKey"
    public static int[][] egyptProdGrid = {{1, 1, 2, 2},
    {3, 1, 4, 4},
    {1, 1, 4, 4},
    {1, 5, 4, 5}
    };

    public static int[][] greekProdGrid = {{4, 4, 3, 2},
    {5, 6, 4, 3},
    {5, 5, 5, 5},
    {1, 5, 5, 5}
    };

    public static int[][] norseProdGrid = {{4, 6, 6, 6},
    {4, 3, 5, 6},
    {5, 2, 3, 5},
    {1, 3, 3, 4}
    };
    
    public static int[][] cityAreaGrid = {{0,0,0,0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    };
    
    //Key for mapping different area types in production area
    //public static String[] prodKey = {"null", "Desert", "Swamp", "Forest", "Fertile", "Hills", "Mountains"};
    public static String[] prodKey = {"null", "DESERT", "SWAMP", "FOREST", "FERTILE", "HILLS", "MOUNTAINS"};

    //Resource resourceTiles grid containing 90 cards in total of 20 different kinds 
    public static int[] resourceTiles = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1, 5, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 1, 5, 13, 16, 17, 1, 5, 13, 16, 17, 1, 5, 16, 17, 1, 5, 1, 5, 1, 1, 1};

    public static  String RESOURCE_CUBE_FOOD = "FOOD";
    public static String RESOURCE_CUBE_FAVOR = "FAVOR";
    public static String RESOURCE_CUBE_WOOD = "WOOD";
    public static String RESOURCE_CUBE_GOLD = "GOLD";
    public static String RESOURCE_CUBE_VICTORY = "VICTORY";
    
    public static HashMap<String,Color> RESOURCE_BG_COLOR = new HashMap<String, Color>()
    {
        {
            put(RESOURCE_CUBE_FOOD, Color.GREEN);
            put(RESOURCE_CUBE_FAVOR, Color.BLUE);
            put(RESOURCE_CUBE_WOOD, new Color(156, 93, 82));
            put(RESOURCE_CUBE_GOLD, Color.YELLOW);
            put(RESOURCE_CUBE_VICTORY, Color.RED);
        }
    };
    
    public static String[] CULTURE_AGE = {"ARCHAIC","CLASSICAL","HEROIC","MYTHIC"};
    
    public static int ARCHAIC_AGE_INDEX = 0;
    public static int CLASSICAL_AGE_INDEX = 1;
    public static int HEROIC_AGE_INDEX = 2;
    public static int MYTHIC_AGE_INDEX = 3;
    public static int ALL_AGE = 9;
    
    public static String[] cultures = {"Greek", "Norse", "Egypt"};
    
    public static String[] buildingTiles = {"House","Wall","Tower","Market","Storehouse","Armory",
        "Quarry","Monument","Granary","WoodWork","GoldMint","SiegeWork","GreatTemple","Wonder"};
   
    public static String[] PERMANENT_ACTION_CARDS = {"Build","Gather","Explore","Trade","Recruit","Attack","NextAge"};

    public static String[] gatherOptionsArray = {"DESERT", "SWAMP", "FOREST", "FERTILE", "HILLS", "MOUNTAINS","FOOD","FAVOR","WOOD","GOLD"};
    
    public static HashMap<String,Boolean> CHECK_BUILDING = new HashMap<String, Boolean>()
    {
        {
            put(buildingTiles[0], false);
            put(buildingTiles[1], false);
            put(buildingTiles[2], false);
            put(buildingTiles[3], false);
            put(buildingTiles[4], false);
            put(buildingTiles[5], false);
            put(buildingTiles[6], false);
            put(buildingTiles[7], false);
            put(buildingTiles[8], false);
            put(buildingTiles[9], false);
            put(buildingTiles[10], false);
            put(buildingTiles[11], false);
            put(buildingTiles[12], false);
            put(buildingTiles[13], false);
        }
    };
    
    public static String[] EGPYT_BATTLE_CARDS = {"Wadjet","Phoenix","Spearman","Mummy",
        "Elephant","Priest","ChariotArcher","SonOfOsiris","Anubite","Sphinx",
        "ScorpionMan","Pharaoh"};
    
    public static String[] GREEK_BATTLE_CARDS;
    
    public static String[] NORSE_BATTLE_CARDS;
            
    //Military units
    public static int MORTAL_UNIT = 1;
    public static int MYTH_UNIT = 2;
    public static int HERO_UNIT = 3;
    
    //Military Unit specialisation
    public static int WARRIOR = 1;
    public static int FLYER = 2;
    public static int GIANT = 3;
    public static int CAVALRY = 4;
    public static int GIANT_KILLER = 5;
    public static int ARCHER = 6;
    public static int HERO = 7;
    
    //Areas on the board
    public static int BOARD_HOLDING_AREA = 1;
    public static int BOARD_PRODUCTION_AREA = 2;
    public static int BOARD_CITY_AREA = 3;
    
    public static int NUMBER_OF_TILES = 4;
    public static int NUMBER_OF_TILES_GODCARD_NORSE = 2;
   
}
