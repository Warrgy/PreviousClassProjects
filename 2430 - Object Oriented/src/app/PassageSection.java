package app;

import dnd.models.Monster;
import dnd.die.D20;

/* Represents a 10 ft section of passageway */

public class PassageSection {
    /** Set a monster variable. */
    private Monster monster;
    /** Set a door variable. */
    private Door door;
    /** Set a level variable. */
    private Level generation;
    /** Set a string variable from Level. */
    private String section;
    /** Variable to show if there is a monster or not. */
    private boolean monsterCheck = false;
    /** String to hold description.*/
    private String description;
    /**Set constructor.
     */
    public PassageSection() {
        monster = new Monster();
        monster.setType(getRandomRoll());
        door = new Door();
        generation = new Level();
        section = generation.generateLevel();
        description = new String();
    }
    /**Set constructor.
     @param desc the description for the passage
     */
    public PassageSection(String desc) {
        monster = new Monster();
        monster.setType(getRandomRoll());
        door = new Door();
        section = desc;
        description = new String();
    }
    /**Get the door and return it.
     @return Door return the door you get
     */
    public Door getDoor() {
        if (section.compareTo("passage ends in archway (door) to chamber") == 0 || section.compareTo("archway (door) to left (main passage continues straight for 10 ft)") == 0 || (section.compareTo("archway (door) to right (main passage continues straight for 10 ft)") == 0)) {
            door.setArchway(true);
            return door;
        } else if (section.compareTo("passage ends in Door to a Chamber") == 0) {
            return door;
        } else {
            return null;
        }
    }
    /**Get monster and return it.
     @return Monster return the monster you get
     */
    public Monster getMonster() {
        if (section.compareTo("Wandering Monster (passage continues straight for 10 ft)") == 0) {
            monsterCheck = true;
        }
        if (monsterCheck) {
            return monster;
        }
        return null;
    }
    /**Get description and return it.
     @return String Description of passage section.
     */
    public String getDescription() {
        description = getSection() + "\n";
        if ((section.compareTo("archway (door) to right (main passage continues straight for 10 ft)") == 0) || (section.compareTo("archway (door) to left (main passage continues straight for 10 ft)") == 0)) {
            door = getDoor();
            description += door.getDescription();
        }
        if ((section.compareTo("Wandering Monster (passage continues straight for 10 ft)") == 0) || monsterCheck) {
            description += "Monster: " + monster.getMinNum() + " - " + monster.getMaxNum() + "  ";
            description += monster.getDescription() + "\n";
        }
        return description;
    }
    /**Add Monster to section.
     @param newMonster Add monster
     */
    public void addMonster(Monster newMonster) {
        monsterCheck = true;
        monster = newMonster;
    }
    /**Give the description of the section.
     @return String description of section
     */
    public String getSection() {
        return section;
    }
    //Get random roll
    private int getRandomRoll() {
        D20 die = new D20();
        return die.roll();
    }
}
