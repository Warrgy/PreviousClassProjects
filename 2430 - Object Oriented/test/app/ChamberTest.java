package app;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/** Test the Chamber Class
 */
public class ChamberTest {
    /** Instance variable to hold a basic chamber shape. */
    private ChamberShape theShape;
    /** Instance variable to hold the basic chamber contents. */
    private ChamberContents theContents;
    /** Default constructor
     */
    public ChamberTest() {
    }
    /** Setup instance variables.
     */
    @Before
    public void setup() {
        theShape = new ChamberShape();
        theShape.setShape();
        theShape.setNumExits();
        theContents = new ChamberContents();
        theContents.setDescription();
    }
    /**Test the first constructor.
     */
    @Test
    public void testChamber1() {
        System.out.println("chamber");
        Chamber instance = new Chamber();
        int result = instance.getDoors().size();
        String exit = "Number of Exits: " + result;
        assertTrue(instance.getDescription().contains(exit));
    }
    /**Test the second constructor.
     */
    @Test
    public void testChamber2() {
        System.out.println("chamber");
        Chamber instance = new Chamber(theShape,theContents);
        String description = theContents.getDescription();
        if (description.contains("monster")) {
            assertTrue(instance.getDescription().contains("Monster:"));
        } else {
            assertTrue(instance.getDescription().contains("Area:"));
        }
    }
    /** Test setShape method.
     */
    @Test
    public void testSetShape() {
        System.out.println("setShape");
        ChamberShape shape = new ChamberShape();
        ChamberContents contents = new ChamberContents();
        shape.setShape(3);
        shape.setNumExits(1);
        Chamber instance = new Chamber(shape,contents);
        instance.setShape(shape);
        //Set number of exits and the target amount, then compare them.
        int exitNum = instance.getDoors().size();
        int target = shape.getNumExits();
        assertEquals(target, exitNum);
    }
    /**Test the getDoors method.
     */
    @Test    
    public void testGetDoors() {
        System.out.println("getDoors");
        ChamberShape shape = new ChamberShape();
        ChamberContents contents = new ChamberContents();
        shape.setShape(1);
        shape.setNumExits(5);
        Chamber instance = new Chamber(shape,contents);
        //Set number of exits and the amount of doors, then compare them.
        int doorNum = instance.getDoors().size();
        int exitNum = shape.getNumExits();
        assertTrue(exitNum <= doorNum);
    }
    /**Test addMonster method.
     */
    @Test
    public void testAddMonster() {
        System.out.println("addMonsters");
        Monster monster = new Monster();
        String description = monster.getDescription();
        Chamber instance = new Chamber();
        int size = instance.getMonsters().size();
        instance.addMonster(monster);
        instance.addMonster(new Monster());
        //Put monsters in ArrayList then compare the specific item with the description
        ArrayList<Monster> monsters = instance.getMonsters();
        assertEquals(description, monsters.get(size).getDescription());
    }
    /**Test getMonsters method.
     */
    @Test
    public void testGetMonsters() {
        System.out.println("getMonsters");
        Monster monster = new Monster();
        String description = monster.getDescription();
        Chamber instance = new Chamber();
        int size = instance.getMonsters().size();
        //Add some monster, with the known one in the middle
        instance.addMonster(new Monster());
        instance.addMonster(new Monster());
        instance.addMonster(monster);
        instance.addMonster(new Monster());
        instance.addMonster(new Monster());
        //Put monsters in ArrayList then compare the specific item with the description
        ArrayList<Monster> monsters = instance.getMonsters();
        assertEquals(description, monsters.get(size + 2).getDescription());
    }
    /**Test addTreasure method.
     */
    @Test
    public void testAddTreasure() {
        System.out.println("addTreasure");
        Treasure treas = new Treasure();
        String description = treas.getDescription();
        Chamber instance = new Chamber();
        int treasSize = instance.getTreasureList().size();
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        instance.addTreasure(treas);
        //Put treasures in ArrayList then compare size and description
        ArrayList<Treasure> treasures = instance.getTreasureList();
        assertEquals(treasSize + 5, treasures.size());
        assertEquals(description, treasures.get(treasSize + 4).getDescription());
    }
    /**Test the getTreasureList method.
     */
    @Test
    public void testGetTreasureList() {
        System.out.println("getTreasureList");
        Chamber instance = new Chamber();
        int size = instance.getTreasureList().size();
        instance.addTreasure(new Treasure());
        instance.addTreasure(new Treasure());
        int newSize = instance.getTreasureList().size();
        assertEquals(size + 2, newSize);
    }
    /**Test the getDescription method.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String newShape = theShape.getShape();
        String newContents = theContents.getDescription();
        Chamber instance = new Chamber(theShape, theContents);
        String description = instance.getDescription();
        assertTrue(description.contains(newShape));
        assertTrue(description.contains(newContents));
    }
    /**Test the setDoor method.
     */
    @Test
    public void testSetDoor() {
        System.out.println("setDoor");
        Door door = new Door();
        Chamber instance = new Chamber(theShape, theContents);
        Passage passage = new Passage();
        //Set door to chamber and then set a passage and chamber beside it and then compare descriptions
        instance.setDoor(door);
        door.setSpaces(passage, instance);
        String result = door.getSpaces().get(1).getDescription();
        assertTrue(result.contains(theShape.getShape()));
    }
}
