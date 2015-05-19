/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import ageofmythology.BattleCards.ABattleCard;
import ageofmythology.BattleCards.Egypt.Pharaoh;
import ageofmythology.BattleCards.Egypt.Priest;
import ageofmythology.BattleCards.Egypt.SonOfOsiris;
import ageofmythology.BattleCards.Greek.Toxotes;
import ageofmythology.BattleCards.Norse.Huskarl;
import ageofmythology.BattleCards.Norse.Jarl;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author vipinsharma
 */
public class GameUtilities {
    /*
    * Choose 18 tiles randomly from 90 tiles in resourceTiles
    * Logic : Start with hour and hop by the minutes until 18   
    * tiles have been chosen
    */
    public static int[] selectResourceTiles(int[] tiles, int numOfTiles) {
        int[] chosenTiles = new int[numOfTiles];
        int startIndex = Calendar.getInstance().getTime().getHours();
        int hopIndex = Calendar.getInstance().getTime().getMinutes();

        for (int i = 0; i < numOfTiles; i++) {
            while (tiles[startIndex] < 1) {
                startIndex=(startIndex+1)%90;
            }
            chosenTiles[i] = tiles[startIndex];
            tiles[startIndex] = 0;
            startIndex = (startIndex+hopIndex)%90;
//            if (startIndex >= 90) {
//                startIndex -= 90;
//            }
        }
        return chosenTiles;
    }
    
    /*
    * Convert resource tile index to Area type index on which it can be placed
    */
    public static int convertResourceTileToAreaType(int resourceTileIndex)
    {
        if(resourceTileIndex >= 1 && resourceTileIndex <=4)
            return 4;
        else if(resourceTileIndex >= 5 && resourceTileIndex <=8)
            return 3;
        else if(resourceTileIndex >= 9 && resourceTileIndex <=12)
            return 5;
        else if(resourceTileIndex >= 13 && resourceTileIndex <=15)
            return 6;
        else if(resourceTileIndex >= 16 && resourceTileIndex <=17)
            return 1;
        else if(resourceTileIndex >= 18 && resourceTileIndex <=20)
            return 2;
        return 0;
    }
    
    /*
    Return BoardPanel for the current player during the turns
    */
    public static BoardPanel currentPlayerBoard()
    {
        int index = AgeOfMythology.playerShift[AgeOfMythology.getCurrentChanceIndex()];
        return AgeOfMythology.cultureBoards[index];
    }
    
    /*
    Return extra units to be recruited because god card has been played
    */
    public static ArrayList<ABattleCard> returnExtraUnitsForGodCard(String culture,int currentAge)
    {
        ArrayList<ABattleCard> battleUnits = new ArrayList<>();
        if(culture.equals("Egypt")){
            ABattleCard currentAgeHero = null;
            switch (currentAge) {
                case 0:
                    break;
                case 1:
                    currentAgeHero = new Priest(culture);
                    break;
                case 2:
                    currentAgeHero = new Pharaoh(culture);
                    break;
                case 3:
                    currentAgeHero = new SonOfOsiris(culture);
                    break;
            }
            battleUnits.add(currentAgeHero);
        }
        else if(culture.equals("Norse")){
            ABattleCard jarl = new Jarl(culture);
            ABattleCard huskarl = new Huskarl(culture);
            battleUnits.add(jarl);
            battleUnits.add(huskarl);
        }
        else if(culture.equals("Greek")){
            ABattleCard toxotes1 = new Toxotes(culture);
            ABattleCard toxotes2 = new Toxotes(culture);
            battleUnits.add(toxotes1);
            battleUnits.add(toxotes2);
        }
        return battleUnits;
    }
}
