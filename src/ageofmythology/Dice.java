/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

/**
 *
 * @author vipinsharma
 */
public class Dice {
    
    public static int rollDice(int numOfRolls){
        System.out.println("Num of rolls : " + numOfRolls);
        int diceValue;
        int numOf6 = 0;
        for(int i=0;i<numOfRolls;i++){
            int rand = (int)Math.random();
            diceValue = (((int)(Math.random()*10))%6)+1;
            System.out.println(rand + " " + diceValue);
            if(diceValue == 6)
                numOf6++;
        }
        System.out.println("Num of 6's : " + numOf6);
        return numOf6;
    }
    
}
