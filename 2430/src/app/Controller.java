package app;

import java.util.ArrayList;
import java.util.HashMap;
import dnd.models.Monster;
import dnd.models.Treasure;

public class Controller extends ChamberPrint {
    /**GUI variable.*/
    private ChamberPrint myGui;
    /**Main variable.*/
    private Main main;
    /**List of chambers.*/
    private ArrayList<Chamber> chambers;
    /**The map.*/
    private HashMap<Door, ArrayList<Chamber>> map;
    /**Setup controller.
     * @param theGui pointer to gui
     */
    public Controller(ChamberPrint theGui) {
        myGui = theGui;
        main = new Main();
        map = getMapList();
        chambers = setupCham();
    }
    /**Return a single chamber.
     * @param i index
     * @return Chamber the chamber
     */
    public Chamber getChamber(int i) {
        return chambers.get(i);
    }
    private ArrayList<Chamber> setupCham() {
        ArrayList<Chamber> cham = new ArrayList<Chamber>();
        int i;
        for (i = 0; i < 5; i++) {
            cham.add(new Chamber());
        }
        return cham;
    }
    //get the map
    private HashMap<Door, ArrayList<Chamber>> getMapList() {
        return main.mapAllChambers();
    }
    /**Get the amount of passages.
     * @return int passages
     */
    public int passAmount() {
        return main.getPassCount();
    }
    /**Create a default passage.
     * @return Passage default
     */
    public Passage createPassage() {
        Passage passage = new Passage();
        passage.addPassageSection(new PassageSection("passage goes straight for 10 ft"));
        passage.addPassageSection(new PassageSection("Wandering Monster (passage continues straight for 10 ft)"));
        passage.addPassageSection(new PassageSection("passage ends in Door to a Chamber"));
        return passage;
    }
    /**Get the certain monster description.
     * @param i which monster to get
     * @return String description
     */
    public String getMonstDesc(int i) {
        Level level = new Level();
        ArrayList<String> monsters = level.allMonsterDesc();
        return monsters.get(i);
    }
    /** Change the monster.
     * @param desc description of monster
     * @param space where to be added
     */
    public void changeMonster(String desc, Space space) {
        Level level = new Level();
        Monster monster = level.monstDescToMonst(desc);
        if (monster != null) {
            space.addMonster(monster, 0);
        }
    }
    /**Remove the monster.
     * @param desc description of monster
     * @param space where to be removed
     */
    public void removeMonstDesc(String desc, Space space) {
        int i;
        ArrayList<Monster> monsters = space.getMonsters();
        for (i = 0; i < monsters.size(); i++) {
            if (desc.equals(monsters.get(i).getDescription())) {
                space.removeMonster(monsters.get(i));
            }
        }
    }
    /**Get all the monsters.
     * @param space where to get from
     * @return ArrayList<String> list of monster
     */
    public ArrayList<String> getMonsters(Space space) {
        int i;
        ArrayList<Monster> monsters = space.getMonsters();
        ArrayList<String> string = new ArrayList<String>();
        for (i = 0; i < monsters.size(); i++) {
            string.add(monsters.get(i).getDescription());
        }
        return string;
    }
    /**Remove the treasure.
     * @param desc description of treasure
     * @param space where to be removed
     */
    public void removeTreasDesc(String desc, Space space) {
        int i;
        ArrayList<Treasure> treasure = space.getTreasureList();
        for (i = 0; i < treasure.size(); i++) {
            if (desc.equals(treasure.get(i).getDescription())) {
                space.removeTreasure(treasure.get(i));
            }
        }
    }
    /**Get all the treasure.
     * @param space where to get it from.
     * @return ArrayList<String> list of treasure
     */
    public ArrayList<String> getTreasure(Space space) {
        int i;
        ArrayList<Treasure> treasure = space.getTreasureList();
        ArrayList<String> string = new ArrayList<String>();
        for (i = 0; i < treasure.size(); i++) {
            string.add(treasure.get(i).getDescription());
        }
        return string;
    }
    /**Get the treasure description.
     * @param i treasure to get
     * @return String description
     */
    public String getTreasDesc(int i) {
        Level level = new Level();
        ArrayList<String> treasures = level.allTreasureDesc();
        return treasures.get(i);
    }
    /**Change the treasure.
     * @param desc description of treasure
     * @param space where to be removed
     */
    public void changeTreasure(String desc, Space space) {
        Level level = new Level();
        Treasure treasure = level.treasDescToTreas(desc);
        if (treasure != null) {
            space.addTreasure(treasure);
        }
    }
    /**Get all the passage sections.
     * @param space passage to get from
     * @return ArrayList<String> list of passages.
     */
    public ArrayList<String> getPassageSections(Space space) {
        int i;
        Passage pass = (Passage) space;
        ArrayList<PassageSection> sections = pass.getThePassageSection();
        ArrayList<String> desc = new ArrayList<String>();
        for (i = 0; i < sections.size(); i++) {
            desc.add(sections.get(i).getDescription());
        }
        return desc;
    }
}
