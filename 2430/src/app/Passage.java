package app;

import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.exceptions.NotProtectedException;
import java.util.ArrayList;

/*
 A passage begins at a door and ends at a door.  It may have many other doors along
 the way.
 You will need to keep track of which door is the "beginning" of the passage
 so that you know how to.
 */

public class Passage extends Space implements java.io.Serializable {
    /** list of PassageSections. */
    private ArrayList<PassageSection> thePassageSection = new ArrayList<PassageSection>();
    /** list of Door. */
    private ArrayList<Door> doors = new ArrayList<Door>();
    /** List of monsters. */
    private ArrayList<Monster> monster = new ArrayList<Monster>();
    /** List of treasures. */
    private ArrayList<Treasure> treasure = new ArrayList<Treasure>();
    /** Description of Passage.*/
    private String description = new String();
    /**Get door arraylist.
     @return ArrayList<Door> return the doors
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }
    /**Get specific door.
     @param i section number
     @return Door return door
     */
    public Door getDoor(int i) {
        //returns the door in section 'i'. If there is no door, returns null
        if (i >= doors.size()) {
            return null;
        }
        return doors.get(i);
    }
    /**Add monster to arraylist.
     @param theMonster monster to add
     @param i section in passage
     */
    public void addMonster(Monster theMonster, int i) {
        //thePassageSection.get(i).addMonster(theMonster);
        monster.add(theMonster);
    }
    /**Get speific monster.
     @param i section in passage
     @return Monster return the monster
     */
    public Monster getMonster(int i) {
        //returns Monster door in section 'i'. If there is no Monster, returns null
        return thePassageSection.get(i).getMonster();
    }
    /**Add a passagesection.
     @param toAdd add the passage section
     */
    public void addPassageSection(PassageSection toAdd) {
        //adds the passage section to the passageway
        thePassageSection.add(toAdd);
    }
    /**Set the door they gave us.
     @param newDoor set the door
     */
    @Override
    public void setDoor(Door newDoor) {
        doors.add(newDoor);
        //should add a door connection to the current Passage Section
    }
    /**Get the description.
     @return String return the description of passage
     */
    @Override
    public String getDescription() {
        int i;
        description = header(true);
        for (i = 0; i < thePassageSection.size(); i++) {
            description += "Section: " + thePassageSection.get(i).getDescription();
        }
        treasureDescription();
        monsterDescription();
        description += header(false);
        return description;
    }
    //Will return the description of the monster.
    private void monsterDescription() {
        int i;
        for (i = 0; i < monster.size(); i++) {
            Monster monst = monster.get(i);
            description += "\nMonster:\nDescription: " + monst.getDescription() + "\nAmount: " + monst.getMinNum() + " - " + monst.getMaxNum() + "\n";
        }
    }
    private void treasureDescription() {
        int i;
        for (i = 0; i < treasure.size(); i++) {
            Treasure treas = treasure.get(i);
            description += "\nTreasure: \nDescription: " + treas.getDescription() + "\nContainer: " + treas.getContainer();
            try {
                description += "\nGuarded with: " + treas.getProtection() + "\n";
            } catch (NotProtectedException e) {
                description += "\nThe treasure is not guarded.\n";
            }
        }
    }
    private String header(boolean start) {
        if (start) {
            return "-=-=-=-=-=Passage START=-=-=-=-=-\n";
        } else {
            return "-=-=-=-=-=Passage END=-=-=-=-=-";
        }
    }
    /**Add a treasure.
     * @param theTreasure treasure being added.
     */
    public void addTreasure(Treasure theTreasure) {
        treasure.add(theTreasure);
    }
    /**Get the monsters.
     * @return ArrayList<Monster> of the monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monster;
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
    /**Get the treasure.
     * @return ArrayList<Treasure> get the list of treasure
     */
    public ArrayList<Treasure> getTreasureList() {
        return treasure;
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
    /**Return the list of all PassageSections.
     * @return ArrayList<PassageSection> list of sections
     */
    public ArrayList<PassageSection> getThePassageSection() {
        return thePassageSection;
    }
}
