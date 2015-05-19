/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology.BattleCards.Norse;

import ageofmythology.BattleCards.ABattleCard;
import ageofmythology.Constants;
import java.util.HashMap;

/**
 *
 * @author vipinsharma
 */
public class Valkyrie extends ABattleCard{

    public Valkyrie(String culture) {
        super(culture);
        fixedNumOfDiceRolls=5;
    }

    @Override
    public String getName() {
        return "Valkyrie";
    }

    @Override
    public HashMap<String, Integer> getCostForUnit() {
        HashMap<String,Integer> unitCost = new HashMap<String,Integer>()
        {
            {
                put(Constants.RESOURCE_CUBE_GOLD,1);
                put(Constants.RESOURCE_CUBE_FAVOR,3);
            }        
        };
        return unitCost;
    }

    @Override
    public int getAgeRequirements() {
        return Constants.ALL_AGE;
    }

    @Override
    public int getMilitaryUnitType() {
        return Constants.MYTH_UNIT;
    }

    @Override
    public int getNumberOfDiceRoll(ABattleCard enemy,int boardIndex, int roleInBattle) {
        int additionalRolls = 0;
        if(enemy.getUnitSpecialisation() == Constants.ARCHER)
            additionalRolls = 4;
        return fixedNumOfDiceRolls+additionalRolls;
    }

    @Override
    public int getUnitSpecialisation() {
        return Constants.CAVALRY;
    }

    @Override
    public void selectCardForBattle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
