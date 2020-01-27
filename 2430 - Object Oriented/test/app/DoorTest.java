package app;

import dnd.models.Trap;
import dnd.models.Exit;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class DoorTest {
    /**Default constructor.
     */
    public DoorTest() {
    }
    /**Test door constructor.
     */
    @Test
    public void testDoor1() {
        System.out.println("Door");
        Door door = new Door();
        if (door.isArchway()) {
            assertTrue(door.isOpen());
        }
    }
    /**Test other door constructor.
     */
    @Test
    public void testDoor2() {
        System.out.println("Door");
        Door instance = new Door(new Exit());
        if (instance.isArchway()) {
            assertFalse(instance.isTrapped());
        }
    }
    /**
     * Test the setTrapped method.
     */
    @Test
    public void testSetTrapped() {
        System.out.println("setTrapped");
        Door instance = new Door();
        instance.setArchway(false);
        instance.setTrapped(true);
        assertEquals(true,instance.isTrapped());
    }
    /**Test the setOpen method.
     */
    @Test
    public void testSetOpenOne() {
        System.out.println("setOpen");
        Door instance = new Door();
        instance.setArchway(false);
        instance.setOpen(false);
        assertEquals(false, instance.isOpen());
    }
    /**Test the setArchway method.
     */
    @Test
    public void testSetArchway() {
        System.out.println("setArchway");
        Door instance = new Door();
        instance.setArchway(true);
        assertEquals(instance.isOpen(), instance.isArchway());
    }

    /**Test the isTrapped method.
     */
    @Test
    public void testIsTrapped() {
        System.out.println("isTrapped");
        Door instance = new Door();
        instance.setArchway(true);
        instance.setTrapped(true);
        assertTrue(instance.isTrapped());
    }
    /**Test the isOpen method.
     */
    @Test
    public void testIsOpen() {
        System.out.println("isOpen");
        Door instance = new Door();
        instance.setArchway(true);
        instance.setOpen(false);
        assertTrue(instance.isOpen());
    }
    /**Test the isArchway method.
     */
    @Test
    public void testIsArchway() {
        System.out.println("isArchway");
        Door instance = new Door();
        instance.setArchway(true);
        assertTrue(instance.isArchway());
    }
    /**Test the getTrapDescription method.
     */
    @Test
    public void testGetTrapDescription() {
        System.out.println("getTrapDescription");
        String result = "Chute down 1 level (cannot be ascended in any manner).";
        Door instance = new Door();
        instance.setTrapped(true, 20);
        assertTrue(instance.getTrapDescription().contains(result));
    }
   
    /**Test the getSpaces method.
     */
    @Test
    public void testGetSpaces() {
        System.out.println("getSpaces");
        Chamber chamber = new Chamber();
        Passage pass = new Passage();
        Door instance = new Door();
        instance.setSpaces(chamber, pass);
        assertEquals(2,instance.getSpaces().size());
    }

    /**Test the setSpaces method.
    */ 
    @Test
    public void testSetSpaces() {
        System.out.println("setSpaces");
        Chamber chamber = new Chamber();
        Passage pass = new Passage();
        Door instance = new Door();
        instance.setSpaces(chamber, pass);
        instance.setSpaces(pass,chamber);
        assertEquals(chamber,instance.getSpaces().get(3));
    }
    /**Test the getDescription method.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Door instance = new Door();
        instance.setArchway(true);
        String arch = "Archway = true";
        String trap = "Trapped = false";
        String result = instance.getDescription();
        assertEquals(result.contains(arch), result.contains(trap));
    }
    
}
