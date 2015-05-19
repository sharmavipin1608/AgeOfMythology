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
public class Hoplite extends ABattleCard{
    public Hoplite(String culture) {
        super(culture);
        fixedNumOfDiceRolls=3;
    }

    @Override
    public String getName() {
        return "Hoplite";
    }

    @Override
    public HashMap<String, Integer> getCostForUnit() {
        HashMap<String,Integer> unitCost = new HashMap<String,Integer>()
        {
            {
                put(Constants.RESOURCE_CUBE_WOOD,1);
                put(Constants.RESOURCE_CUBE_FOOD,1);
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
        return Constants.MORTAL_UNIT;
    }

    @Override
    public int getNumberOfDiceRoll(ABattleCard enemy,int boardIndex,int roleInBattle) {
        int additionalRolls = 0;
        if(enemy.getUnitSpecialisation() == Constants.CAVALRY)
            additionalRolls = 3;
        if(enemy.getMilitaryUnitType()== Constants.MORTAL_UNIT)
            additionalRolls = 1;
        return fixedNumOfDiceRolls+additionalRolls;
    }

    @Override
    public int getUnitSpecialisation() {
        return Constants.WARRIOR;
    }

    @Override
    public void selectCardForBattle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
