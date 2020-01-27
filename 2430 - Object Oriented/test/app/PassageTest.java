package app;

import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class PassageTest {
    /**Default constructor.
     */
    public PassageTest() {
    }
    /**Test for the getDoors method.
     */
    @Test
    public void testGetDoors() {
        System.out.println("getDoors");
        Passage instance = new Passage();
        Door one = new Door();
        instance.setDoor(new Door());
        instance.setDoor(one);
        //Check to make sure the door you added, you get back
        ArrayList<Door> doors = instance.getDoors();
        assertEquals(one,doors.get(1));
    }
    /**Test for the getDoor method.
     */
    @Test
    public void testGetDoor() {
        System.out.println("getDoor");
        Passage instance = new Passage();
        Door four = new Door();
        //Set archway for the door, then add doors, followed by the specific door
        four.setArchway(true);
        instance.setDoor(new Door());
        instance.setDoor(new Door());
        instance.setDoor(new Door());
        instance.setDoor(new Door());
        instance.setDoor(four);
        //Get door and then test if archway still applies
        Door door = instance.getDoor(4);
        boolean arch = door.isArchway();
        assertEquals(true, arch);
    }
    /**Test for the addMonster method.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonster");
        Passage instance = new Passage();
        Monster theMonster = new Monster();
        PassageSection section = new PassageSection();
        //Add 4 passages, then add new section.
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(section);
        //Add monster to section and do assertion
        instance.addMonster(theMonster, 4);
        String description = theMonster.getDescription();
        String monster = instance.getMonster(4).getDescription();
        assertEquals(description, monster);
    }
    /**Test for the getMonster method.
     */
    @Test
    public void testGetMonster() {
        System.out.println("getMonster");
        Passage instance = new Passage();
        Monster myMonster = new Monster();
        PassageSection section = new PassageSection();
        section.addMonster(myMonster);
        instance.addPassageSection(section);
        Monster get = instance.getMonster(0);
        assertEquals(myMonster.getDescription(), get.getDescription());
    }
    /**Test for the addPassageSection method.
     */
    @Test
    public void testAddPassageSection() {
        System.out.println("addPassageSection");
        Passage instance = new Passage();
        PassageSection section = new PassageSection("This is a test.");
        instance.addPassageSection(section);
        assertTrue(instance.getDescription().contains("This is a test."));
    }
    /**Test for the setDoor method.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        Passage instance = new Passage();
        Door myDoor = new Door();
        instance.setDoor(new Door());
        instance.setDoor(myDoor);
        Door door = instance.getDoor(1);
        assertEquals(myDoor.isOpen(), door.isOpen());
    }
    /** Test for the getDescription method.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Passage instance = new Passage();
        PassageSection section1 = new PassageSection("This is a test of 1.");
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(new PassageSection());
        instance.addPassageSection(section1);
        assertTrue(instance.getDescription().contains("This is a test of 1."));
    }
}
