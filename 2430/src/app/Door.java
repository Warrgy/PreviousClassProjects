package app;

import dnd.models.Exit;
import dnd.models.Trap;
import java.util.ArrayList;
import dnd.die.D20;
import dnd.die.D10;
import dnd.die.D6;


public class Door {
    /**Trap.*/
    private Trap trap;
    /**ArrayList of spaces.*/
    private ArrayList<Space> spaces;
    /**boolean for trap.*/
    private boolean trapped;
    /**boolean for locked.*/
    private boolean locked;
    /**boolean for open.*/
    private boolean open;
    /**boolean for archway.*/
    private boolean archway;
    /**boolean for if a chamber was generated using this Door. */
    private boolean checkChamber;
    /**String to hold description of door. */
    private String description;


    /**Door constructor.
     */
    public Door() {
        trapped = defaultTrap();
        trap = new Trap();
        locked = defaultLocked();
        open = defaultOpen();
        archway = defaultArchway();
        spaces = new ArrayList<Space>();
        description = new String();
    }
    /**Door constructor.
     @param theExit for exit
     */
    public Door(Exit theExit) {
        trapped = defaultTrap();
        trap = new Trap();
        trap.chooseTrap(getRandomRoll20());
        locked = defaultLocked();
        open = defaultOpen();
        archway = defaultArchway();
        spaces = new ArrayList<Space>();
        description = new String();
    }
    /**Set door to be trapped.
     @param flag to determine if true or false
     @param roll to set trap
    */
    public void setTrapped(boolean flag, int...roll) {
        trapped = flag;
        if (trapped) {
            if (roll.length > 0) {
                for (int c: roll) {
                    trap.chooseTrap(c);
                }
            }
            trap.chooseTrap(getRandomRoll20());
        }
    }
    /**Set door to be open or not.
     @param flag will set open depending on flag
     */
    public void setOpen(boolean flag) {
        if (!(isArchway())) {
            open = flag;
        } else {
            open = true;
        }
    }
    /**Set archway.
     @param flag will set archway depending on flag
     */
    public void setArchway(boolean flag) {
        archway = flag;
        if (archway) {
            setOpen(true);
            setTrapped(false);
        }
    }
    /**See if door is trapped.
     @return boolean returns whether it is trapped
     */
    public boolean isTrapped() {
        return trapped;
    }
    /**See if door is locked.
     @return boolean returns if it is locked
     */
    public boolean isLocked() {
        return locked;
    }
    /**See if door is open.
     @return boolean returns whether it is open
     */
    public boolean isOpen() {
        return open;
    }
    /**See if door is archway.
     @return boolean returns whether it is archway
     */
    public boolean isArchway() {
        return archway;
    }
    /**Get the trap description.
     @return String returns description of trap
     */
    public String getTrapDescription() {
        if (isTrapped()) {
            return trap.getDescription();
        } else {
            return null;
        }
    }
    /**Return the list of all spaces.
     @return ArrayList<Space> will return a list of Space
     */
    public ArrayList<Space> getSpaces() {
        return spaces;
    }
    //Set a single space
    private void setOneSpace(Space space) {
        spaces.add(space);
//        space.setDoor(this);
    }
    /**Set the space on each side of the door.
     @param spaceOne on one side of door
     @param spaceTwo on other side of door
     */
    public void setSpaces(Space spaceOne, Space spaceTwo) {
        //identifies the two spaces with the door
        // this method should also call the addDoor method from Space
        setOneSpace(spaceOne);
        setOneSpace(spaceTwo);
    }
    /**Return the contents of the door.
     @return String returns description of door
     */
    public String getDescription() {
        description = header();
        description += "\nTrapped = " + isTrapped() + "\n";
        if (isTrapped()) {
            description += "Trap = " + getTrapDescription() + "\n";
        }
        description += "Locked = " + isLocked() + "\n";
        description += "Open = " + isOpen() + "\n";
        description += "Archway = " + isArchway() + "\n";
        description += header();
        return description;
    }
    private int getRandomRoll20() {
        D20 die = new D20();
        return die.roll();
    }
    private int getRandomRoll10() {
        D10 die = new D10();
        return die.roll();
    }
    private int getRandomRoll6() {
        D6 die = new D6();
        return die.roll();
    }
    //50% chance and unlocked
    private boolean defaultOpen() {
        int random = getRandomRoll10();
        if (random > 0 && random < 6 && !isLocked()) {
            return true;
        }
        return false;
    }
    //1 in 6 chance
    private boolean defaultArchway() {
        if (getRandomRoll10() == 1 && !isTrapped() && !isLocked() && isOpen()) {
            return true;
        }
        return false;
    }
    //1 in 6 chance
    private boolean defaultLocked() {
        if (getRandomRoll6() == 1) {
            return true;
        }
        return false;
    }
    //1 in 20 chance
    private boolean defaultTrap() {
        if (getRandomRoll20() == 1) {
            return true;
        }
        return false;
    }
    private String header() {
        return "-=-=Door=-=-";
    }
}
