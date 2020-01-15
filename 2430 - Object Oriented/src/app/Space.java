package app;

import java.util.ArrayList;
import dnd.models.Monster;
import dnd.models.Treasure;


public abstract class Space {

    //Set variable for description and setDoor
    /** Get description.
     @return String return description
     */
    public abstract  String getDescription();
    /** Set the door.
     @param theDoor door to set
     */
    public abstract void setDoor(Door theDoor);
    /**Get the doors.
     * @return ArrayList<Door> the Doors list being returned
     */
    public abstract ArrayList<Door> getDoors();
    /** Add a monster.
     * @param theMonster monster to be added
     * @param i position.
     */
    public abstract void addMonster(Monster theMonster, int i);
    /**Add a treasure.
     * @param theTreasure treasure to be added
     */
    public abstract void addTreasure(Treasure theTreasure);
    /**Get the monsters.
     *
     * @return ArrayList<Monster> list of monsters
     */
    public abstract ArrayList<Monster> getMonsters();
    /**Remove the monster.
     * @param monst monster to be removed.
     */
    public abstract void removeMonster(Monster monst);
    /**Get the treasure.
     * @return ArrayList<Treasure> get the list of treasure
     */
    public abstract ArrayList<Treasure> getTreasureList();
    /**Remove the treasure.
     * @param treas treasure to be removed.
     */
    public abstract void removeTreasure(Treasure treas);
}
