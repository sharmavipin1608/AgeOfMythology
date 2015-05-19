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
public class Centaur extends ABattleCard{

    public Centaur(String culture) {
        super(culture);
        fixedNumOfDiceRolls=5;
    }

    @Override
    public String getName() {
        return "Centaur";
    }

    @Override
    public HashMap<String, Integer> getCostForUnit() {
        HashMap<String,Integer> unitCost = new HashMap<String,Integer>()
        {
            {
                put(Constants.RESOURCE_CUBE_FAVOR,1);
                put(Constants.RESOURCE_CUBE_WOOD,3);
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
    public int getNumberOfDiceRoll(ABattleCard enemy,int boardIndex,int rollInBattle) {
        int additionalRolls = 0;
        if(enemy.getUnitSpecialisation() == Constants.ARCHER)
            additionalRolls = 3;
        if(enemy.getUnitSpecialisation() == Constants.FLYER)
            additionalRolls = 3;
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
