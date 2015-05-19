/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofmythology;

import ageofmythology.BattleCards.ABattleCard;
import ageofmythology.BattleCards.Egypt.ChariotArcher;
import ageofmythology.BattleCards.Egypt.Elephant;
import ageofmythology.BattleCards.Egypt.Spearman;
import ageofmythology.BattleCards.Greek.Hippokon;
import ageofmythology.BattleCards.Greek.Hoplite;
import ageofmythology.BattleCards.Greek.Toxotes;
import ageofmythology.BattleCards.Norse.Huskarl;
import ageofmythology.BattleCards.Norse.Jarl;
import ageofmythology.BattleCards.Norse.ThrowingAxeman;
import static ageofmythology.Constants.buildingTiles;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author vipinsharma
 */
public class BoardPanel extends JPanel {

    JPanel[][] cityGridPanel = new JPanel[4][4];
    JPanel[][] prodGridPanel = new JPanel[4][4];
    int[][] productionGrid;
    int[][] cityAreaGrid = {{99, 99, 99, 99},
    {99, 99, 99, 99},
    {99, 99, 99, 99},
    {99, 99, 99, 99}
    };
    ;
    CustomPanel foodCubesPanel;
    CustomPanel favorCubesPanel;
    CustomPanel woodCubesPanel;
    CustomPanel goldCubesPanel;
    ArrayList<ResourceCube> foodCubes = new ArrayList<ResourceCube>();
    ArrayList<ResourceCube> favorCubes = new ArrayList<ResourceCube>();
    ArrayList<ResourceCube> woodCubes = new ArrayList<ResourceCube>();
    ArrayList<ResourceCube> goldCubes = new ArrayList<ResourceCube>();
    static PermanentActionCard permanentActionCard;
    HashMap<String, Integer> gatherByResource = new HashMap<String, Integer>();
    HashMap<String, HashMap<String, Integer>> gatherByResourceArea = new HashMap<String, HashMap<String, Integer>>();
    //ImagePanel holdingArea;
    CustomPanel holdingArea, recruits;
    JLabel cultureAge;
    private String culture;
    private int indexCulture;
    ArrayList<ImagePanel> cardsChosen = new ArrayList<ImagePanel>();
    JFrame chosenCards = null;
    ArrayList<IActionCard> actionCardsForTurn;
    ChosenCards chosenCardsWindow;

    private int currentAge = 0;
    private int numberOfCardsAllowed = 4;
    HashMap<String, Boolean> buildingsCreated = new HashMap<String, Boolean>() {
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

    int numOfHouses = 0;
    private int numOfBattleUnitsRecruited = 0;
    private ArrayList<ABattleCard> battleUnits = new ArrayList<>();
    private int numOfFoodTiles = 0;
    //check if god build card has been played 
    private boolean godBuildCard = false;

    public boolean isGodBuildCard() {
        return godBuildCard;
    }

    public void setGodBuildCard(boolean godBuildCard) {
        this.godBuildCard = godBuildCard;
    }
    //end

    public int getNumOfBattleUnitsRecruited() {
        return numOfBattleUnitsRecruited;
    }

    public void setNumOfBattleUnitsRecruited(int numOfBattleUnitsRecruited) {
        this.numOfBattleUnitsRecruited = numOfBattleUnitsRecruited;
    }

    public ArrayList<ABattleCard> getBattleUnits() {
        return battleUnits;
    }

    public void removeBattleUnit(ABattleCard battleCard) {
        System.out.println("before : " + battleUnits.size());
        battleUnits.remove(battleCard);
        System.out.println("after : " + battleUnits.size());
        recruits.removeAll();
        for (ABattleCard selectedUnit : battleUnits) {
            JLabel unitName = new JLabel(selectedUnit.getName());
            unitName.setFont(new Font("Verdana", Font.ITALIC, 12));
            unitName.setForeground(Color.red);
            recruits.add(unitName);
            recruits.revalidate();
            recruits.repaint();
        }
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void recruit(ABattleCard selectedUnit) {
        //Pay cost for recruitment
        HashMap<String, Integer> cost = selectedUnit.getCostForUnit();
        for (String resource : cost.keySet()) {
            for (int i = 0; i < cost.get(resource); i++) {
                removeResourceCube(resource);
            }
        }

        //Add Battle Card to battle units
        battleUnits.add(selectedUnit);

        //Increment the number of battle units recruited this round
        numOfBattleUnitsRecruited--;

        //Add unit Name in holding Area
        JLabel unitName = new JLabel(selectedUnit.getName());
        unitName.setFont(new Font("Verdana", Font.ITALIC, 12));
        unitName.setForeground(Color.red);
        recruits.add(unitName);
        recruits.revalidate();
        recruits.repaint();
    }

    public void recruitExtraUnitsForGodCard() {
        if (culture.equals("Egypt") && currentAge == 0) {
            return;
        } else {
            battleUnits.addAll(GameUtilities.returnExtraUnitsForGodCard(culture, currentAge));
            recruits.removeAll();
            for (ABattleCard selectedUnit : battleUnits) {
                JLabel unitName = new JLabel(selectedUnit.getName());
                unitName.setFont(new Font("Verdana", Font.ITALIC, 12));
                unitName.setForeground(Color.red);
                recruits.add(unitName);
                recruits.revalidate();
                recruits.repaint();
            }
        }
    }

    public void setActionCardsForTurn(ArrayList actionCards) {
        actionCardsForTurn = new ArrayList<>();
        cardsChosen = new ArrayList<>();
        actionCardsForTurn.addAll(actionCards);
        for (IActionCard actionCard : actionCardsForTurn) {
            cardsChosen.add(actionCard.returnCard());
        }
        System.out.println("Chosen cards window initialised");
        chosenCardsWindow = new ChosenCards(cardsChosen);
    }

    public void playCard() {
        for (int i = 0; i < 3; i++) {
            if (actionCardsForTurn.get(i).ifCardPlayed() || cardsChosen.get(i).getType() == 6) {
                continue;
            } else {
                actionCardsForTurn.get(i).playCard(cardsChosen.get(i));
                break;
            }

        }
    }

    public void resetChosenCard() {
        cardsChosen = new ArrayList<>();
        chosenCardsWindow.destroyWindow();
        System.out.println("chosen cards reset : " + cardsChosen.size());
    }

    public void addChosenCard(ImagePanel cardChosen) {
        cardsChosen.add(cardChosen);
        if (cardsChosen.size() == numberOfCardsAllowed) {
            permanentActionCard.destroyWindow();
            for (ImagePanel card : cardsChosen) {
                if (card.getComponent(0) != null) {
                    card.remove(0);
                }
            }
            System.out.println("here");
            chosenCardsWindow = new ChosenCards(cardsChosen);
        }
    }

    public void displayChosenCardsWindow() {
        chosenCardsWindow.displayWindow();
    }

    public void destroyChosenCardWindow() {
        chosenCardsWindow.destroyWindow();
    }

    public ArrayList getChosenCards() {
        return cardsChosen;
    }

    public void setIndexCulture(int indexCulture) {
        this.indexCulture = indexCulture;
    }

    public String getCulture() {
        return culture;
    }

    public int getIndexCulture() {
        return indexCulture;
    }

    public void displayPermanentActionCardWindow() {
        permanentActionCard.displayPermanentActionCardWindow();
    }

    public JPanel getProdGridPanel(int i, int j) {
        return prodGridPanel[i][j];
    }

    public int numberOfCardsToBeDrawn() {
        return numberOfCardsAllowed;
    }

    public void promoteToNextAge(boolean ifGodPower) {
        //checking if player has resources to be promoted to the next age
        int costToPromoteToNextAge = 0;
        
        if(ifGodPower){
            costToPromoteToNextAge = 3;
            if(culture.equals("Egypt"))
                AgeOfMythology.cultureBoards[0].removeFoodTileFromProductionArea(true);
            else if (culture.equals("Greek"))
                AgeOfMythology.cultureBoards[1].removeFoodTileFromProductionArea(false);
        }
        else
            costToPromoteToNextAge = 4;
        
        int numberOfResourcesReqd = currentAge + costToPromoteToNextAge;
        boolean promoteToNextAge = true;
        HashMap<String, Integer> cultureResourceMap = cultureResources();
        for (String resource : cultureResourceMap.keySet()) {
            if (cultureResourceMap.get(resource) < numberOfResourcesReqd) {
                promoteToNextAge = false;
                break;
            }
        }

        if (promoteToNextAge) {
            System.out.println("Current age : " + currentAge + " Culture : " + culture);
            currentAge += 1;
            numberOfCardsAllowed = currentAge + 4;
            for (int i = 0; i < numberOfResourcesReqd; i++) {
                removeResourceCube(Constants.RESOURCE_CUBE_FOOD);
                removeResourceCube(Constants.RESOURCE_CUBE_FAVOR);
                removeResourceCube(Constants.RESOURCE_CUBE_WOOD);
                removeResourceCube(Constants.RESOURCE_CUBE_GOLD);
            }
            cultureAge.setText(Constants.CULTURE_AGE[currentAge]);
            holdingArea.repaint();
            holdingArea.revalidate();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Player doesn't have "
                    + "enough resources to promote to " + Constants.CULTURE_AGE[currentAge + 1]
                    + " Age");
        }
    }

    /*
     1. check if building already created.
     2. check counter for houses
     */
    public boolean checkForValidityOfBuildingByIndex(int buildingTileIndex) {
        System.out.println("checkForValidityOfBuildingByIndex");
        //check if building already created
        boolean isBuildingAlreadyCreated = buildingsCreated.get(Constants.buildingTiles[buildingTileIndex]);
        if (isBuildingAlreadyCreated) {
            return false;
        } else {
            //check if player has selected to build house and check the number of houses
            if (buildingTileIndex == 0 && numOfHouses == 10) {
                buildingsCreated.put(Constants.buildingTiles[buildingTileIndex], true);
                return false;
            } else {
                //check if the player has resources for the building
                boolean ifPlayerHasResouces = checkIfPlayerHasResouces(MapTileToResource.returnCostForBuildingTiles(buildingTileIndex));
                if (!ifPlayerHasResouces) {
                    return false;
                }
            }
        }
        return true;
    }

    //check if player has resources for the building
    public boolean checkIfPlayerHasResouces(HashMap<String, Integer> cost) {
        HashMap<String, Integer> cultureResourceMap = cultureResources();
        for (String resource : cost.keySet()) {
            if (cultureResourceMap.get(resource) < cost.get(resource)) {
                return false;
            }
        }
        return true;
    }

    public boolean placeTileInCityArea(int buildingTileIndex) {
        String image = Constants.IMAGES_PATH + Constants.buildingTiles[buildingTileIndex] + ".png";
        Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
        ImagePanel buildingTile = new ImagePanel(tileImage, 90, 90, buildingTileIndex);
        buildingTile.setBorder(BorderFactory.createLineBorder(Color.black, 7));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cityAreaGrid[i][j] == 99) {
                    System.out.println("i : " + i + " : j : " + j + " before : " + cityAreaGrid[i][j]);
                    cityAreaGrid[i][j] = buildingTileIndex;
                    System.out.println("i : " + i + " : j : " + j + " after : " + cityAreaGrid[i][j]);
                    if (buildingTileIndex != 0) {
                        buildingsCreated.put(Constants.buildingTiles[buildingTileIndex], true);
                    } else {
                        numOfHouses++;
                    }
                    payBuildingCost(buildingTileIndex);
                    if (godBuildCard) {
                        performGodCardAction();
                    }
                    cityGridPanel[i][j].add(buildingTile);
                    cityGridPanel[i][j].repaint();
                    cityGridPanel[i][j].revalidate();
                    return true;
                }
            }
        }
        return false;
    }

    public void performGodCardAction() {
        System.out.println("performGodCardAction");
        boolean housePlaced = false;
        if (culture.equals("Greek")) {
            String image = Constants.IMAGES_PATH + Constants.buildingTiles[0] + ".png";
            Image tileImage = Toolkit.getDefaultToolkit().createImage(image);
            ImagePanel buildingTile = new ImagePanel(tileImage, 90, 90, 0);
            buildingTile.setBorder(BorderFactory.createLineBorder(Color.black, 7));

            for (int i = 0; i < 4; i++) {
                if(housePlaced)
                    break;
                for (int j = 0; j < 4; j++) {
                    System.out.println("performGodCardAction : i : " + i + " : j : " + j);
                    if (cityAreaGrid[i][j] == 99) {
                        cityAreaGrid[i][j] = 0;
                        numOfHouses++;
                        cityGridPanel[i][j].add(buildingTile);
                        cityGridPanel[i][j].repaint();
                        cityGridPanel[i][j].revalidate();
                        housePlaced = true;
                        break;
                    }
                }
            }
        } else {
            int vengeanceIndex = (indexCulture + 1) % 3;
            BoardPanel vengeanceBoard = AgeOfMythology.cultureBoards[AgeOfMythology.playerShift[vengeanceIndex]];
            System.out.println("Culture : " + vengeanceBoard.culture);
            vengeanceBoard.removeBuildingFromCityArea();
        }
        godBuildCard = false;
    }

    //Pay building cost
    public void payBuildingCost(int buildingTileIndex) {
        HashMap<String, Integer> buildingCost = MapTileToResource.returnCostForBuildingTiles(buildingTileIndex);
        for (String resource : buildingCost.keySet()) {
            System.out.println("Resource : " + resource + " Cost : " + buildingCost.get(resource));
            for (int i = 0; i < buildingCost.get(resource); i++) {
                removeResourceCube(resource);
            }
        }
    }

    public boolean placeTileInProdArea(int resourceArea, JPanel tilePanel) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (productionGrid[i][j] == resourceArea) {
                    prodGridPanel[i][j].add(tilePanel);
                    prodGridPanel[i][j].repaint();
                    prodGridPanel[i][j].revalidate();

                    productionGrid[i][j] = 0;
                    updateBoardResources(Constants.prodKey[resourceArea], ((ImagePanel) tilePanel).getType(), 1);
                    return true;
                }
            }
        }
        return false;
    }

    public void updateBoardResources(String resourceArea, int tileType, int addOrDeleteResource) {
        HashMap<String, Integer> gatherByResourceTemp = new HashMap<String, Integer>();
        gatherByResourceTemp = MapTileToResource.returnResources(tileType);
        for (String resource : gatherByResourceTemp.keySet()) {
            if (gatherByResource.get(resource) != null) {
                int total = gatherByResource.get(resource) + (gatherByResourceTemp.get(resource) * addOrDeleteResource);
                gatherByResource.put(resource, total);
            } else {
                gatherByResource.putAll(gatherByResourceTemp);
            }

            if (gatherByResourceArea.get(resourceArea) != null) {
                if (gatherByResourceArea.get(resourceArea).get(resource) != null) {
                    int total = gatherByResourceArea.get(resourceArea).get(resource) + (gatherByResourceTemp.get(resource) * addOrDeleteResource);
                    gatherByResourceArea.get(resourceArea).put(resource, total);
                } else {
                    gatherByResourceArea.get(resourceArea).putAll(gatherByResourceTemp);
                }
            } else {
                HashMap<String, Integer> temp = new HashMap<String, Integer>();
                temp.putAll(gatherByResourceTemp);
                gatherByResourceArea.put(resourceArea, temp);
            }
        }

        if (tileType == 1 || tileType == 6 || tileType == 10 || tileType == 19) {
            numOfFoodTiles += addOrDeleteResource;
        }
    }

    public void gatherResourcesForGodCard(int numOfResourcesPerTile) {
        for (int i = 0; i < numOfFoodTiles * numOfResourcesPerTile; i++) {
            addResourceCube(Constants.RESOURCE_CUBE_FOOD);
        }
    }

    public void gatherByResource(String resource) {
        System.out.println("Gather" + resource + " " + gatherByResource.get(resource));
        if (gatherByResource.get(resource) != null) {
            int numOfResources = gatherByResource.get(resource);
            for (int index = 0; index < numOfResources; index++) {
                addResourceCube(resource);
            }
        }
    }

    public void gatherByArea(String area) {
        HashMap<String, Integer> temp = gatherByResourceArea.get(area);
        if (temp != null) {
            for (String resource : temp.keySet()) {
                System.out.println("Area : " + area + " Resource : " + resource + " Value : " + temp.get(resource));
                int numOfResources = temp.get(resource);
                for (int index = 0; index < numOfResources; index++) {
                    addResourceCube(resource);
                }
            }
        }
    }

    public boolean ifTileCanBePlacedInProdArea(int tileType) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (productionGrid[i][j] == tileType) {
                    return true;
                }
            }
        }
        return false;
    }

    BoardPanel(String culture, int[][] prodGrid) {
        this.culture = culture;
        productionGrid = prodGrid;

        permanentActionCard = new PermanentActionCard(culture);

        String holdingAreaImage = Constants.IMAGES_PATH + culture + "_holdingArea.jpg";
        String productionAreaImage = Constants.IMAGES_PATH + culture + "_productionArea.jpg";
        String cityAreaImage = Constants.IMAGES_PATH + culture + "_cityArea.jpg";

        Border panelBorder = BorderFactory.createRaisedBevelBorder();
        CustomPanel boardArea = new CustomPanel(Constants.BOARD_PLAY_AREA_WIDTH, Constants.BOARD_PLAY_AREA_HEIGHT, true);
        boardArea.setLayout(new BoxLayout(boardArea, BoxLayout.Y_AXIS));

//        Image holding = Toolkit.getDefaultToolkit().createImage(holdingAreaImage);
//        holdingArea = new ImagePanel(holding,Constants.HOLDING_AREA_WIDTH, Constants.HOLDING_AREA_HEIGHT);
//        holdingArea.setBorder(panelBorder);
        //trial
        Image holding = Toolkit.getDefaultToolkit().createImage(holdingAreaImage);
        ImagePanel holdingAreaBase = new ImagePanel(holding, Constants.HOLDING_AREA_WIDTH, Constants.HOLDING_AREA_HEIGHT);
        holdingAreaBase.setBorder(panelBorder);

        holdingAreaBase.setLayout(new BoxLayout(holdingAreaBase, BoxLayout.X_AXIS));
        holdingArea = new CustomPanel(600, Constants.HOLDING_AREA_HEIGHT, false);
        recruits = new CustomPanel(200, Constants.HOLDING_AREA_HEIGHT, false);

        recruits.setLayout(new BoxLayout(recruits, BoxLayout.Y_AXIS));
//        JLabel battleUnit = new JLabel("Mortal");
//        battleUnit.setFont(new Font("Verdana", Font.ITALIC, 12));
//        battleUnit.setForeground(Color.red);
//        recruits.add(battleUnit);

        holdingAreaBase.add(holdingArea);
        holdingAreaBase.add(recruits);
        /*placing resource cubes in holding area*/
        holdingArea.setLayout(new BoxLayout(holdingArea, BoxLayout.Y_AXIS));
        foodCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH, 50, false);
        foodCubesPanel.setLayout(new BoxLayout(foodCubesPanel, BoxLayout.X_AXIS));

//        foodCubesPanel.add(new ResourceCube("FOOD"));
//        foodCubesPanel.add(new ResourceCube("FOOD"));
//        foodCubesPanel.add(new ResourceCube("FOOD"));
//        foodCubesPanel.add(new ResourceCube("FOOD"));
//        foodCubesPanel.add(new ResourceCube("FOOD"));
        favorCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH, 50, false);
        favorCubesPanel.setLayout(new BoxLayout(favorCubesPanel, BoxLayout.X_AXIS));

        woodCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH, 50, false);
        woodCubesPanel.setLayout(new BoxLayout(woodCubesPanel, BoxLayout.X_AXIS));

        goldCubesPanel = new CustomPanel(Constants.HOLDING_AREA_WIDTH, 50, false);
        goldCubesPanel.setLayout(new BoxLayout(goldCubesPanel, BoxLayout.X_AXIS));

        holdingArea.add(foodCubesPanel);
        holdingArea.add(favorCubesPanel);
        holdingArea.add(woodCubesPanel);
        holdingArea.add(goldCubesPanel);

        distributeInitialTiles();

        CustomPanel agePanel = new CustomPanel(100, 50, false);
//        agePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cultureAge = new JLabel("ARCHAIC");
        cultureAge.setFont(new Font("Verdana", Font.ITALIC, 12));
        cultureAge.setForeground(Color.red);
        agePanel.add(cultureAge);
        holdingArea.add(agePanel);

        CustomPanel prodAndCityBase = new CustomPanel(Constants.BOARD_PLAY_AREA_WIDTH, Constants.CITY_AREA, true);
        prodAndCityBase.setLayout(new BoxLayout(prodAndCityBase, BoxLayout.X_AXIS));

        Image production = Toolkit.getDefaultToolkit().createImage(productionAreaImage);
        ImagePanel productionArea = new ImagePanel(production, Constants.CITY_AREA, Constants.CITY_AREA);
        productionArea.setLayout(new GridLayout(4, 4));
        productionArea.setBorder(panelBorder);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                prodGridPanel[i][j] = new CustomPanel(Constants.PANEL_SIZE, Constants.PANEL_SIZE, prodGrid[i][j]);
                productionArea.add(prodGridPanel[i][j]);
            }
        }

        Image city = Toolkit.getDefaultToolkit().createImage(cityAreaImage);
        ImagePanel cityArea = new ImagePanel(city, Constants.CITY_AREA, Constants.CITY_AREA);
        cityArea.setLayout(new GridLayout(4, 4));
        cityArea.setBorder(panelBorder);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                CustomPanel cityPanel = new CustomPanel(Constants.PANEL_SIZE, Constants.PANEL_SIZE, false);
                cityPanel.setBorder(panelBorder);
                cityGridPanel[i][j] = cityPanel;
                cityArea.add(cityGridPanel[i][j]);
            }
        }

        prodAndCityBase.add(productionArea);
        prodAndCityBase.add(cityArea);

        //boardArea.add(holdingArea);
        boardArea.add(holdingAreaBase);
        boardArea.add(prodAndCityBase);

        CustomPanel bankPanel = new CustomPanel(Constants.BANK_WIDTH, Constants.BANK_HEIGHT, true);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(boardArea);
        this.add(bankPanel);

        recruitInitialUnits(culture);
    }

    public void recruitInitialUnits(String culture) {
        if (culture.equals("Egypt")) {
            ABattleCard spearMan1 = new Spearman(culture);
            battleUnits.add(spearMan1);

            ABattleCard spearMan2 = new Spearman(culture);
            battleUnits.add(spearMan2);

            ABattleCard elephant1 = new Elephant(culture);
            battleUnits.add(elephant1);

            ABattleCard elephant2 = new Elephant(culture);
            battleUnits.add(elephant2);

            ABattleCard chariotArcher1 = new ChariotArcher(culture);
            battleUnits.add(chariotArcher1);

            ABattleCard chariotArcher2 = new ChariotArcher(culture);
            battleUnits.add(chariotArcher2);
        } else if (culture.equals("Norse")) {
            ABattleCard jarl1 = new Jarl(culture);
            battleUnits.add(jarl1);

            ABattleCard jarl2 = new Jarl(culture);
            battleUnits.add(jarl2);

            ABattleCard huskarl1 = new Huskarl(culture);
            battleUnits.add(huskarl1);

            ABattleCard huskarl2 = new Huskarl(culture);
            battleUnits.add(huskarl2);

            ABattleCard throwingAxeman1 = new ThrowingAxeman(culture);
            battleUnits.add(throwingAxeman1);

            ABattleCard throwingAxeman2 = new ThrowingAxeman(culture);
            battleUnits.add(throwingAxeman2);
        } else if (culture.equals("Greek")) {
            ABattleCard toxotes1 = new Toxotes(culture);
            battleUnits.add(toxotes1);

            ABattleCard toxotes2 = new Toxotes(culture);
            battleUnits.add(toxotes2);

            ABattleCard hoplite1 = new Hoplite(culture);
            battleUnits.add(hoplite1);

            ABattleCard hoplite2 = new Hoplite(culture);
            battleUnits.add(hoplite2);

            ABattleCard hippokon1 = new Hippokon(culture);
            battleUnits.add(hippokon1);

            ABattleCard hippokon2 = new Hippokon(culture);
            battleUnits.add(hippokon2);
        }

        for (ABattleCard selectedUnit : battleUnits) {
            JLabel unitName = new JLabel(selectedUnit.getName());
            unitName.setFont(new Font("Verdana", Font.ITALIC, 12));
            unitName.setForeground(Color.red);
            recruits.add(unitName);
            recruits.revalidate();
            recruits.repaint();
        }
    }

    public void distributeInitialTiles() {
        for (int index = 0; index < 5; index++) {
            foodCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_FOOD));
            favorCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_FAVOR));
            woodCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_WOOD));
            goldCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_GOLD));

            foodCubesPanel.add(foodCubes.get(index));
            favorCubesPanel.add(favorCubes.get(index));
            woodCubesPanel.add(woodCubes.get(index));
            goldCubesPanel.add(goldCubes.get(index));
        }
    }

    public void addResourceCube(String resource) {
        Bank bank = Bank.getInstance();
        bank.removeResource(resource);
        addResourceCubeToBoard(resource);
    }

    public void addResourceCubeToBoard(String resource) {
        if (resource.equals(Constants.RESOURCE_CUBE_FOOD)) {
            foodCubes.add(new ResourceCube(resource));
            foodCubesPanel.add(foodCubes.get(foodCubes.size() - 1));
        } else if (resource.equals(Constants.RESOURCE_CUBE_FAVOR)) {
            favorCubes.add(new ResourceCube(resource));
            favorCubesPanel.add(favorCubes.get(favorCubes.size() - 1));
        } else if (resource.equals(Constants.RESOURCE_CUBE_WOOD)) {
            woodCubes.add(new ResourceCube(resource));
            woodCubesPanel.add(woodCubes.get(woodCubes.size() - 1));
        } else if (resource.equals(Constants.RESOURCE_CUBE_GOLD)) {
            goldCubes.add(new ResourceCube(resource));
            goldCubesPanel.add(goldCubes.get(goldCubes.size() - 1));
        }

        holdingArea.repaint();
        holdingArea.revalidate();
    }

    public void removeResourceCube(String resourceType) {
        Bank bank = Bank.getInstance();
        bank.addResource(resourceType);
        removeResourceCubeFromBoard(resourceType);
    }

    public void removeResourceCubeFromBoard(String resourceType) {
        if (resourceType.equals(Constants.RESOURCE_CUBE_FOOD)) {
            foodCubesPanel.remove(foodCubes.get(foodCubes.size() - 1));
            foodCubes.remove(foodCubes.size() - 1);
        } else if (resourceType.equals(Constants.RESOURCE_CUBE_FAVOR)) {
            favorCubesPanel.remove(favorCubes.get(favorCubes.size() - 1));
            favorCubes.remove(favorCubes.size() - 1);
        } else if (resourceType.equals(Constants.RESOURCE_CUBE_WOOD)) {
            woodCubesPanel.remove(woodCubes.get(woodCubes.size() - 1));
            woodCubes.remove(woodCubes.size() - 1);
        } else if (resourceType.equals(Constants.RESOURCE_CUBE_GOLD)) {
            goldCubesPanel.remove(goldCubes.get(goldCubes.size() - 1));
            goldCubes.remove(goldCubes.size() - 1);
        }

        holdingArea.repaint();
        holdingArea.revalidate();
    }

    public HashMap cultureResources() {
        HashMap<String, Integer> cultureResourceMap = new HashMap<>();
        cultureResourceMap.put(Constants.RESOURCE_CUBE_FOOD, foodCubes.size());
        cultureResourceMap.put(Constants.RESOURCE_CUBE_FAVOR, favorCubes.size());
        cultureResourceMap.put(Constants.RESOURCE_CUBE_WOOD, woodCubes.size());
        cultureResourceMap.put(Constants.RESOURCE_CUBE_GOLD, goldCubes.size());
        return cultureResourceMap;
    }

    public HashMap extractResourcesDueToLossInBattle() {
        System.out.println("extractResourcesDueToLossInBattle : " + culture);
        int resourcesExtractedCount = 0;
        HashMap<String, Integer> resourcesExtracted = new HashMap<>();
        //HashMap<String,Integer> CultureResources = cultureResources();

        while (foodCubes.size() > 0 && resourcesExtractedCount < 5) {
            if (resourcesExtracted.get(Constants.RESOURCE_CUBE_FOOD) == null) {
                resourcesExtracted.put(Constants.RESOURCE_CUBE_FOOD, 0);
            }
            removeResourceCubeFromBoard(Constants.RESOURCE_CUBE_FOOD);
            resourcesExtractedCount++;
            int current = resourcesExtracted.get(Constants.RESOURCE_CUBE_FOOD);
            resourcesExtracted.put(Constants.RESOURCE_CUBE_FOOD, current + 1);
        }

        while (favorCubes.size() > 0 && resourcesExtractedCount < 5) {
            if (resourcesExtracted.get(Constants.RESOURCE_CUBE_FAVOR) == null) {
                resourcesExtracted.put(Constants.RESOURCE_CUBE_FAVOR, 0);
            }
            removeResourceCubeFromBoard(Constants.RESOURCE_CUBE_FAVOR);
            resourcesExtractedCount++;
            int current = resourcesExtracted.get(Constants.RESOURCE_CUBE_FAVOR);
            resourcesExtracted.put(Constants.RESOURCE_CUBE_FAVOR, current + 1);
        }

        while (goldCubes.size() > 0 && resourcesExtractedCount < 5) {
            if (resourcesExtracted.get(Constants.RESOURCE_CUBE_WOOD) == null) {
                resourcesExtracted.put(Constants.RESOURCE_CUBE_WOOD, 0);
            }
            removeResourceCubeFromBoard(Constants.RESOURCE_CUBE_WOOD);
            resourcesExtractedCount++;
            int current = resourcesExtracted.get(Constants.RESOURCE_CUBE_WOOD);
            resourcesExtracted.put(Constants.RESOURCE_CUBE_WOOD, current + 1);
        }

        while (foodCubes.size() > 0 && resourcesExtractedCount < 5) {
            if (resourcesExtracted.get(Constants.RESOURCE_CUBE_GOLD) == null) {
                resourcesExtracted.put(Constants.RESOURCE_CUBE_GOLD, 0);
            }
            removeResourceCubeFromBoard(Constants.RESOURCE_CUBE_GOLD);
            resourcesExtractedCount++;
            int current = resourcesExtracted.get(Constants.RESOURCE_CUBE_GOLD);
            resourcesExtracted.put(Constants.RESOURCE_CUBE_GOLD, current + 1);
        }
        return resourcesExtracted;
    }

    public void addResourcesWonInBattle(HashMap<String, Integer> resourcesWon) {
        System.out.println("addResourcesWonInBattle");
        for (String resource : resourcesWon.keySet()) {
            System.out.println("Resource : " + resource + " num : " + resourcesWon.get(resource));
            for (int i = 0; i < resourcesWon.get(resource); i++) {
                addResourceCubeToBoard(resource);
            }
        }
    }

    public String removeBuildingFromCityArea() {
        System.out.println("removeBuildingFromCityArea");
        boolean buildingDestroyed = false;
        String buildingName = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cityAreaGrid[i][j] != 99) {
                    System.out.println("building found : " + Constants.buildingTiles[cityAreaGrid[i][j]]);

                    buildingsCreated.put(Constants.buildingTiles[cityAreaGrid[i][j]], false);
                    buildingName = Constants.buildingTiles[cityAreaGrid[i][j]];
                    cityAreaGrid[i][j] = 99;
                    cityGridPanel[i][j].remove(0);
                    cityGridPanel[i][j].repaint();
                    cityGridPanel[i][j].revalidate();
                    buildingDestroyed = true;
                    break;
                }
            }
            if (buildingDestroyed) {
                break;
            }
        }
        System.out.println("Building destroyed : " + buildingName);
        return buildingName;
    }

    public Set<Integer> availableAreasOnPlayerBoard() {
        Set<Integer> availableAreas = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (productionGrid[i][j] != 0) {
                    availableAreas.add(productionGrid[i][j]);
                }
            }
        }
        System.out.println("availableAreasOnPlayerBoard");
        for (Integer trial : availableAreas) {
            System.out.println(trial);
        }
        return availableAreas;
    }

    public ImagePanel removeReosurceTileFromProductionArea(Set<Integer> availableAreas) {
        ImagePanel productionTile = null;
        boolean tileFound = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (productionGrid[i][j] == 0) {
                    System.out.println("culture : " + culture + prodGridPanel[i][j].getComponents().length);

                    for (Component trial : prodGridPanel[i][j].getComponents()) {
                        System.out.println(trial.getClass());
                    }
                    productionTile = (ImagePanel) (prodGridPanel[i][j].getComponent(1));
                    int areaType = ((CustomPanel) prodGridPanel[i][j]).getTerrainType();//GameUtilities.convertResourceTileToAreaType(productionTile.getType());
                    //productionTile.setType(areaType);
                    System.out.println("Area type : " + areaType);
                    if (availableAreas.contains(areaType)) {
                        updateBoardResources(Constants.prodKey[areaType], productionTile.getType(), -1);
                        productionGrid[i][j] = areaType;
                        prodGridPanel[i][j].remove(1);
                        prodGridPanel[i][j].revalidate();
                        prodGridPanel[i][j].repaint();
                        System.out.println("Tile : " + productionTile.getType() + " Area : " + areaType + " -- production grid area type : " + productionTile.getType());

                    }
                    tileFound = true;
                    break;
                }
            }
            if (tileFound) {
                System.out.println("Tile found");
                break;
            }
        }
        return productionTile;
    }

    public boolean deductResourcesForTradeCard() {
        System.out.println("deductResourcesForTradeCard");
        HashMap<String, Integer> resources = cultureResources();
        int totalResources = 0, resourcesRemoved = 0;
        boolean hasResources = false;

        if (buildingsCreated.get("Market")) {
            return true;
        } else {
            System.out.println("deductResourcesForTradeCard - else");
            for (String resourceCube : resources.keySet()) {
                totalResources = totalResources + resources.get(resourceCube);
            }

            if (totalResources >= 2) {
                System.out.println("deductResourcesForTradeCard - if");
                hasResources = true;
                for (String resourceCube : resources.keySet()) {
                    if (resourcesRemoved == 2) {
                        break;
                    }
                    if (resources.get(resourceCube) > 1) {
                        System.out.println("deductResourcesForTradeCard : " + resourceCube);
                        removeResourceCube(resourceCube);
                        resourcesRemoved++;
                    }
                }
            }
        }
        return hasResources;
    }

    public void addVictoryCubesToHoldingArea(int num) {
        JPanel victoryCubes = new JPanel();
        victoryCubes.setLayout(new BoxLayout(victoryCubes, BoxLayout.X_AXIS));
        for (int i = 0; i < num; i++) {
            victoryCubes.add(new ResourceCube(Constants.RESOURCE_CUBE_VICTORY));
        }
        holdingArea.add(victoryCubes);
        holdingArea.repaint();
        holdingArea.revalidate();
    }

    public void payResourcesForGodCard() {
        if (payResourcesForGodCard(1)) {
            godBuildCard = true;
        }
    }

    public boolean payResourcesForGodCard(int costOfGodCard) {
        System.out.println("payResourcesForGodCard : " + favorCubes.size());
        if (favorCubes.size() >= costOfGodCard) {
            for (int i = 0; i < costOfGodCard; i++) {
                removeResourceCube(Constants.RESOURCE_CUBE_FAVOR);
            }
            return true;
        }
        return false;
    }

    public void gainResourcesForGodTradeCard() {
        for (int i = 0; i < 4; i++) {
            addResourceCube(Constants.RESOURCE_CUBE_FAVOR);
        }
    }

    public boolean removeFoodTileFromProductionArea(boolean removeFoodTile) {
        System.out.println("removeFoodTileFromProductionArea : " + culture + " : food tile to be removed : " + removeFoodTile);
        ImagePanel productionTile = null;
        boolean tileFound = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (productionGrid[i][j] == 0) {
                    System.out.println("culture : " + culture + prodGridPanel[i][j].getComponents().length);
                    productionTile = (ImagePanel) (prodGridPanel[i][j].getComponent(1));
                    int areaType = ((CustomPanel) prodGridPanel[i][j]).getTerrainType();//GameUtilities.convertResourceTileToAreaType(productionTile.getType());
                    int tileType = productionTile.getType();

                    if (removeFoodTile) {
                        if (tileType != 1 && tileType != 6 && tileType != 10 && tileType != 19) {
                            continue;
                        }
                    }

                    System.out.println("Area type : " + areaType);
                    productionGrid[i][j] = areaType;
                    prodGridPanel[i][j].remove(1);
                    prodGridPanel[i][j].revalidate();
                    prodGridPanel[i][j].repaint();
                    System.out.println("Tile : " + productionTile.getType() + " Area : " + areaType + " -- production grid area type : " + productionTile.getType());
                    tileFound = true;

                    //start
                    updateBoardResources(Constants.prodKey[areaType], tileType, -1);
                    //end
                    break;

                }
            }

            if (tileFound) {
                System.out.println("Tile found");
                break;
            }
        }
        return tileFound;
    }
}
