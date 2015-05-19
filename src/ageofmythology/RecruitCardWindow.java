/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import ageofmythology.BattleCards.ABattleCard;
import ageofmythology.BattleCards.Egypt.*;
import ageofmythology.BattleCards.Norse.*;
import ageofmythology.BattleCards.Greek.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author vipinsharma
 */
public class RecruitCardWindow {
    private JFrame recruitCardSelectionWindow;
    private ArrayList<ABattleCard> battleUnitsList = new ArrayList<>();
    
    public RecruitCardWindow(String culture){
        recruitCardSelectionWindow = new JFrame("Recruit Battle Units");
        recruitCardSelectionWindow.setLayout(new GridLayout(3, 4));
        recruitCardSelectionWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        recruitCardSelectionWindow.setVisible(false);
        recruitCardSelectionWindow.setLocationRelativeTo(null);
        
        if(culture.equals("Egypt"))
        {
            ABattleCard phoenixCard = new Phoenix(culture);
            battleUnitsList.add(phoenixCard);
            recruitCardSelectionWindow.add(phoenixCard.returnCard());

            ABattleCard wadjetCard = new Wadjet(culture);
            battleUnitsList.add(wadjetCard);
            recruitCardSelectionWindow.add(wadjetCard.returnCard());
            
            ABattleCard elephantCard = new Elephant(culture);
            battleUnitsList.add(elephantCard);
            recruitCardSelectionWindow.add(elephantCard.returnCard());

            ABattleCard mummyCard = new Mummy(culture);
            battleUnitsList.add(mummyCard);
            recruitCardSelectionWindow.add(mummyCard.returnCard());
            
            ABattleCard priestCard = new Priest(culture);
            battleUnitsList.add(priestCard);
            recruitCardSelectionWindow.add(priestCard.returnCard());

            ABattleCard spearmanCard = new Spearman(culture);
            battleUnitsList.add(spearmanCard);
            recruitCardSelectionWindow.add(spearmanCard.returnCard());
            
            ABattleCard chariotArcherCard = new ChariotArcher(culture);
            battleUnitsList.add(chariotArcherCard);
            recruitCardSelectionWindow.add(chariotArcherCard.returnCard());
            
            ABattleCard sonOfOsirisCard = new SonOfOsiris(culture);
            battleUnitsList.add(sonOfOsirisCard);
            recruitCardSelectionWindow.add(sonOfOsirisCard.returnCard());
            
            ABattleCard anubiteCard = new Anubite(culture);
            battleUnitsList.add(anubiteCard);
            recruitCardSelectionWindow.add(anubiteCard.returnCard());
            
            ABattleCard sphinxCard = new Sphinx(culture);
            battleUnitsList.add(sphinxCard);
            recruitCardSelectionWindow.add(sphinxCard.returnCard());
            
            ABattleCard scorpionManCard = new ScorpionMan(culture);
            battleUnitsList.add(scorpionManCard);
            recruitCardSelectionWindow.add(scorpionManCard.returnCard());
            
            ABattleCard pharaohCard = new Pharaoh(culture);
            battleUnitsList.add(pharaohCard);
            recruitCardSelectionWindow.add(pharaohCard.returnCard());
        }
        
        if(culture.equals("Norse")){
            ABattleCard dwarfCard = new Dwarf(culture);
            battleUnitsList.add(dwarfCard);
            recruitCardSelectionWindow.add(dwarfCard.returnCard());

            ABattleCard huskarlCard = new Huskarl(culture);
            battleUnitsList.add(huskarlCard);
            recruitCardSelectionWindow.add(huskarlCard.returnCard());
            
            ABattleCard jarlCard = new Jarl(culture);
            battleUnitsList.add(jarlCard);
            recruitCardSelectionWindow.add(jarlCard.returnCard());

            ABattleCard nidhoggCard = new Nidhogg(culture);
            battleUnitsList.add(nidhoggCard);
            recruitCardSelectionWindow.add(nidhoggCard.returnCard());
            
            ABattleCard trollCard = new Troll(culture);
            battleUnitsList.add(trollCard);
            recruitCardSelectionWindow.add(trollCard.returnCard());

            ABattleCard valkyrieCard = new Valkyrie(culture);
            battleUnitsList.add(valkyrieCard);
            recruitCardSelectionWindow.add(valkyrieCard.returnCard());
            
            ABattleCard throwingAxemanCard = new ThrowingAxeman(culture);
            battleUnitsList.add(throwingAxemanCard);
            recruitCardSelectionWindow.add(throwingAxemanCard.returnCard());

            ABattleCard classicalNorseHeroCard = new ClassicalNorseHero(culture);
            battleUnitsList.add(classicalNorseHeroCard);
            recruitCardSelectionWindow.add(classicalNorseHeroCard.returnCard());
            
            ABattleCard heroicNorseHeroCard = new HeroicNorseHero(culture);
            battleUnitsList.add(heroicNorseHeroCard);
            recruitCardSelectionWindow.add(heroicNorseHeroCard.returnCard());

            ABattleCard mythicNorseHeroCard = new MythicNorseHero(culture);
            battleUnitsList.add(mythicNorseHeroCard);
            recruitCardSelectionWindow.add(mythicNorseHeroCard.returnCard());
            
            ABattleCard frostGiantCard = new FrostGiant(culture);
            battleUnitsList.add(frostGiantCard);
            recruitCardSelectionWindow.add(frostGiantCard.returnCard());
        }
        
        if(culture.equals("Greek")){
            ABattleCard centaurCard = new Centaur(culture);
            battleUnitsList.add(centaurCard);
            recruitCardSelectionWindow.add(centaurCard.returnCard());

            ABattleCard classicalGreekHeroCard = new ClassicalGreekHero(culture);
            battleUnitsList.add(classicalGreekHeroCard);
            recruitCardSelectionWindow.add(classicalGreekHeroCard.returnCard());
            
            ABattleCard cyclopsCard = new Cyclops(culture);
            battleUnitsList.add(cyclopsCard);
            recruitCardSelectionWindow.add(cyclopsCard.returnCard());

            ABattleCard heroicGreekHeroCard = new HeroicGreekHero(culture);
            battleUnitsList.add(heroicGreekHeroCard);
            recruitCardSelectionWindow.add(heroicGreekHeroCard.returnCard());
            
            ABattleCard manticoreCard = new Manticore(culture);
            battleUnitsList.add(manticoreCard);
            recruitCardSelectionWindow.add(manticoreCard.returnCard());

            ABattleCard toxotesCard = new Toxotes(culture);
            battleUnitsList.add(toxotesCard);
            recruitCardSelectionWindow.add(toxotesCard.returnCard());
            
            ABattleCard hydraCard = new Hydra(culture);
            battleUnitsList.add(hydraCard);
            recruitCardSelectionWindow.add(hydraCard.returnCard());

            ABattleCard minotaurCard = new Minotaur(culture);
            battleUnitsList.add(minotaurCard);
            recruitCardSelectionWindow.add(minotaurCard.returnCard());
            
            ABattleCard mythicalGreekHeroCard = new MythicalGreekHero(culture);
            battleUnitsList.add(mythicalGreekHeroCard);
            recruitCardSelectionWindow.add(mythicalGreekHeroCard.returnCard());

            ABattleCard hippokonCard = new Hippokon(culture);
            battleUnitsList.add(hippokonCard);
            recruitCardSelectionWindow.add(hippokonCard.returnCard());
            
            ABattleCard hopliteCard = new Hoplite(culture);
            battleUnitsList.add(hopliteCard);
            recruitCardSelectionWindow.add(hopliteCard.returnCard());

            ABattleCard medusaCard = new Medusa(culture);
            battleUnitsList.add(medusaCard);
            recruitCardSelectionWindow.add(medusaCard.returnCard());
        }
        
        recruitCardSelectionWindow.pack();
    }
    
    public void displayRecruitCardWindow()
    {
        recruitCardSelectionWindow.setVisible(true);
    }
    
    public void recruitRandomly(int numOfUnitsToBeRecruited)
    {
        int numOfUnits = numOfUnitsToBeRecruited;
        int numOfAttempts = 0;
        while(numOfUnits > 0 && numOfAttempts < 10)
        {
            System.out.println("recruit randomly : " + Math.random()%6 + " Name " + battleUnitsList.get((int) (Math.random()%6)).getName());
            if(battleUnitsList.get((int) (Math.random()*10)%6).addCard())
                numOfUnits--;
            numOfAttempts++;
        }
        AgeOfMythology.play();
    }
}
