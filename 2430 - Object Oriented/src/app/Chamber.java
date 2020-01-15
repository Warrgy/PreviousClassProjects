package app;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Exit;
import dnd.exceptions.UnusualShapeException;
import dnd.exceptions.NotProtectedException;
import java.util.ArrayList;
import dnd.die.D20;

public class Chamber extends Space implements java.io.Serializable {
    /** contents for chamber. */
    private ChamberContents myContents;
    /** shape for chamber. */
    private ChamberShape chamberShape;
    /** List of all treasure. */
    private ArrayList<Treasure> treasure = new ArrayList<Treasure>();
    /** List of all monsters. */
    private ArrayList<Monster> monster = new ArrayList<Monster>();
    /** List of all doors. */
    private ArrayList<Door> doors;
    /** List of all exits. */
    private ArrayList<Exit> exits;
    /** Chamber Constructor.
     */
    public Chamber() {
        chamberShape = ChamberShape.selectChamberShape(2);
        chamberShape.selectChamberShape(getRandomRoll());
        myContents = new ChamberContents();
        compareContents(myContents);
        exits = chamberShape.getExits();
        doors = new ArrayList<Door>();
        setDoors();
    }
    /**Constructor with ChamberShape and ChamberContents variables.
     @param theShape  input for shape
     @param theContents  input for contents
     */
    public Chamber(ChamberShape theShape, ChamberContents theContents) {
        chamberShape = theShape;
        myContents = theContents;
        compareContents(myContents);
        exits = chamberShape.getExits();
        doors = new ArrayList<Door>();
        setDoors();
    }
    /**Set the shape of chamber.
     @param theShape set chamber shape
     */
    public void setShape(ChamberShape theShape) {
        chamberShape = theShape;
    }
    /**Return all the doors in chamber.
     @return ArrayList<Door> return the list of doors
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }
    /**Add monster.
     @param theMonster add the monster
     @param i index
     */
    public void addMonster(Monster theMonster, int i) {
        monster.add(theMonster);
    }
    /**Get the monsters.
     @return ArrayList<Monster> return list of monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monster;
    }
    /**Add the treasure.
     @param theTreasure add the treasure specified
     */
    public void addTreasure(Treasure theTreasure) {
        treasure.add(theTreasure);
    }
    /**Get the list of the treasure.
     @return ArrayList<Treasure> return list of treasure
     */
    public ArrayList<Treasure> getTreasureList() {
        return treasure;
    }
    /**Create the entire description into a lovely readable string.
     @return String string of description for chamber
     */
    @Override
    public String getDescription() {
        String description = "";
        //Add chamber shape to string with header
        description += shapeContentsDescription();
        //Add exits locations in chamber to string
        description += exitDescription();
        //Add treasure to string
        description += treasureDescription();
        //Add monster to string
        description += monsterDescription();
        //Add header
        description += "\n" + header();
        //size,contents,traps,monsters
        return description;
    }
    /**Set the door for the chamber.
     @param newDoor door they want to add
     */
    @Override
    public void setDoor(Door newDoor) {
        doors.add(newDoor);
        //should add a door connection to this room
    }
    //Get random roll
    private int getRandomRoll() {
        D20 die = new D20();
        return die.roll();
    }
    //See whether monsters or treasure will get generated
    private void compareContents(ChamberContents contents) {
        Monster monster1 = new Monster();
        Treasure treasure1 = new Treasure();
        monster1.setType(getRandomRoll());
        treasure1.setContainer(getRandomRoll());
        treasure1.setDescription(getRandomRoll());
        contents.setDescription(getRandomRoll());
        if (contents.getDescription().compareTo("monster only") == 0) {
            addMonster(monster1, 0);
        } else if (contents.getDescription().compareTo("monster and treasure") == 0) {
            addMonster(monster1, 0);
            addTreasure(treasure1);
        } else if (contents.getDescription().compareTo("treasure") == 0) {
            addTreasure(treasure1);
        }
    }
    private void setDoors() {
        Door addDoor;
        int i;
        if ((chamberShape.getNumExits() == 0) || (chamberShape.getNumExits() == 5)) {
            chamberShape.setNumExits();
        }
        for (i = 0; i < chamberShape.getNumExits(); i++) {
            addDoor = new Door();
            doors.add(addDoor);
        }
    }
    //Will return the description of the monster.
    private String monsterDescription() {
        Monster  monst;
        String desc = "";
        int i;
        for (i = 0; i < monster.size(); i++) {
            monst = monster.get(i);
            desc += "\nMonster:\nDescription: " + monst.getDescription() + "\nAmount: " + monst.getMinNum() + " - " + monst.getMaxNum() + "\n";
        }
        return desc;
    }
    //Will return the description of the treasure.
    private String treasureDescription() {
        Treasure treas;
        int i;
        String desc = "";
        for (i = 0; i < treasure.size(); i++) {
            treas = treasure.get(i);
            desc += "\nTreasure: \nDescription: " + treas.getDescription() + "\nContainer: " + treas.getContainer();
            try {
                desc += "\nGuarded with: " + treas.getProtection() + "\n";
            } catch (NotProtectedException e) {
                desc += "\nThe treasure is not guarded.\n";
            }
        }
        return desc;
    }
    /**Get exit description.
     * @return String description
     */
    public String exitDescription() {
        int i;
        String desc = "";
        if (chamberShape.getExits().size() != 0) {
            desc += "\nExits:\n";
        }
        for (i = 0; i < exits.size(); i++) {
            desc += "\t" + exits.get(i).getLocation() + "\t " + exits.get(i).getDirection() + "\n";
        }
        return desc;
    }
    //Will return chamber shape description
    private String shapeContentsDescription() {
        int area;
        int width = 0;
        int length = 0;
        String desc = "";
        try {
            width = chamberShape.getWidth();
            length = chamberShape.getLength();
            area = length * width;
        } catch (UnusualShapeException a) {
            area = chamberShape.getArea();
        }
        desc += header() + "\nShape: " + chamberShape.getShape() + "\nWidth: " + width + "\t Length: " + length + "\nArea: " + area + "\nNumber of Exits: " + chamberShape.getNumExits();
        return desc;
    }
    private String header() {
        return "-=-=-=Chamber=-=-=-";
    }
    /**Remove the monster.
     * @param monst monster to be removed.
     */
    public void removeMonster(Monster monst) {
        int i;
        for (i = 0; i < monster.size(); i++) {
            if (monst == monster.get(i)) {
                monster.remove(i);
            }
        }
    }
    /**Remove the treasure.
     * @param treas treasure to be removed.
     */
    public void removeTreasure(Treasure treas) {
        int i;
        for (i = 0; i < treasure.size(); i++) {
            if (treas == treasure.get(i)) {
                treasure.remove(i);
            }
        }
    }
}
