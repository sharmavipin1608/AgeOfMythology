/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology.BattleCards.Greek;

import ageofmythology.BattleCards.ABattleCard;
import ageofmythology.Constants;
import java.util.HashMap;

/**
 *
 * @author vipinsharma
 */
public class Cyclops extends ABattleCard{

    public Cyclops(String culture) {
        super(culture);
        fixedNumOfDiceRolls=6;
    }

    @Override
    public String getName() {
        return "Cyclops";
    }

    @Override
    public HashMap<String, Integer> getCostForUnit() {
        HashMap<String,Integer> unitCost = new HashMap<String,Integer>()
        {
            {
                put(Constants.RESOURCE_CUBE_FAVOR,3);
                put(Constants.RESOURCE_CUBE_FOOD,3);
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
        if(enemy.getMilitaryUnitType() == Constants.MORTAL_UNIT)
            additionalRolls = 4;
        return fixedNumOfDiceRolls;
    }

    @Override
    public int getUnitSpecialisation() {
        return Constants.GIANT;
    }

    @Override
    public void selectCardForBattle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
