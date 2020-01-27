package app;

import dnd.models.Exit;
import dnd.models.Monster;
import org.junit.Test;
import static org.junit.Assert.*;

public class PassageSectionTest {
    /**Test the first constructor.
     */
    @Test
    public void testPassageSection1() {
        System.out.println("passageSection");
        PassageSection instance = new PassageSection();
        String desc = instance.getSection();
        if (desc.contains("passage")) {
            assertTrue(instance.getSection().contains("passage"));
        } else if (desc.contains("Dead")) {
            assertTrue(instance.getSection().contains("Dead end"));
        } else {
            fail("Incorrect passage section.");
        }
    }
    /**Test the second constructor.
     */
    @Test
    public void testPassageSection2() {
        System.out.println("passageSection");
        String test = "This is a string that will be used for testing my second constructor";
        PassageSection instance = new PassageSection(test);
        assertEquals(test, instance.getSection());
    }
    /**Test for the getDoor method.
     */
    @Test
    public void testGetDoor() {
        System.out.println("getDoor");
        String section = "passage ends in archway (door) to chamber";
        PassageSection instance = new PassageSection(section);
        Door theDoor = instance.getDoor();
        if (theDoor == null) {
            fail("No door in this passage section.");
        } else {
            assertTrue(theDoor.getSpaces().size() >= 0);
        }
    }
    /**Test for the getMonster method.
     */
    @Test
    public void testGetMonster() {
        System.out.println("getMonster");
        String section = "Wandering Monster (passage continues straight for 10 ft)";
        PassageSection instance = new PassageSection(section);
        Monster monster = instance.getMonster();
        assertFalse(monster == null);
    }
    /**Test for the getDescription method.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String section = "archway (door) to right (main passage continues straight for 10 ft)";
        PassageSection instance = new PassageSection(section);
        String doorDesc = "Archway = true";
        assertTrue(instance.getDescription().contains("archway (door) to right (main passage continues straight for 10 ft)"));
        assertTrue(instance.getDescription().contains(doorDesc));
    }
    /** Test for the addMonster method.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonster");
        Monster monster = new Monster();
        String description = monster.getDescription();
        PassageSection instance = new PassageSection();
        instance.addMonster(monster);
        monster = instance.getMonster();
        assertEquals(description, monster.getDescription());
    }
    /**Test for the getSection method.
     */
    @Test
    public void testGetSection() {
        System.out.println("getSection");
        String section = "passage goes straight for 10 ft";
        PassageSection instance = new PassageSection(section);
        assertEquals(section, instance.getSection());
    }
}
