package app;

import dnd.die.D20;
import dnd.models.Monster;
import java.util.ArrayList;
import dnd.models.Treasure;


public class Level {

    /**Integrate table 1 for generation.
     @return String from table
     */
    public String generateLevel() {
        int die = randomNumber();
        if (die < 10) {
            return tenLess(die);
        } else if (die > 10) {
            return tenEqMore(die);
        } else {
            return null;
        }
    }
    private String tenLess(int die) {
        if (die >= 1 && die <= 2) {
            return "passage goes straight for 10 ft";
        } else if (die >= 3 && die <= 5) {
            return "passage ends in Door to a Chamber";
        } else if (die >= 6 && die <= 7) {
            return "archway (door) to right (main passage continues straight for 10 ft)";
        } else if (die >= 8 && die <= 9) {
            return "archway (door) to left (main passage continues straight for 10 ft)";
        } else {
            return null;
        }
    }
    private String tenEqMore(int die) {
         if (die >= 10 && die <= 11) {
            return "passage turns to left and continues for 10 ft";
        } else if (die >= 12 && die <= 13) {
            return "passage turns to right and continues for 10 ft";
        } else if (die >= 14 && die <= 16) {
            return "passage ends in archway (door) to chamber";
        } else if (die == 17) {
            return "Stairs, (passage continues straight for 10 ft)";
        } else if (die >= 18 && die <= 19) {
            return "Dead end";
        } else if (die == 20) {
            return "Wandering Monster (passage continues straight for 10 ft)";
        } else {
            return null;
        }
    }
    private int randomNumber() {
        D20 die = new D20();
        return die.roll();
    }
    /** Get the monsters.
     *
     * @return ArrayList<String> list of each monster
     */
    public ArrayList<String> allMonsterDesc() {
        int i;
        Monster monst = new Monster();
        ArrayList<String> monsters = new ArrayList<String>();
        for (i = 0; i < 100; i++) {
            monst.setType(i + 1);
            if (i == 0 || i == 2 || i == 4 || i == 14 || i == 15 || i == 17 || i == 18 || i == 19 || i == 21 || i == 28 || i == 33 || i == 48 || i == 54 || i == 66 || i == 70 || i == 83 || i == 85 || i == 96 || i == 98) {
                monsters.add(monst.getDescription());
            }
        }
        return monsters;
    }
    /** Get the monster associaetd with the monster description.
     * @param desc description of monster
     * @return Monster the monster
     */
    public Monster monstDescToMonst(String desc) {
        int i;
        Monster monst = new Monster();
        for (i = 0; i < 100; i++) {
            monst.setType(i + 1);
            if (desc.equals(monst.getDescription())) {
                return monst;
            }
        }
        return null;
    }
    /** Get the list of each treasure.
     * @return ArrayList<String> list of descriptions
     */
    public ArrayList<String> allTreasureDesc() {
        int i;
        Treasure treasure = new Treasure();
        ArrayList<String> desc = new ArrayList<String>();
        for (i = 0; i < 100; i++) {
            treasure.chooseTreasure(i + 1);
            if (i == 0 || i == 25 || i == 50 || i == 65 || i == 80 || i == 90 || i == 94 || i == 97) {
                desc.add(treasure.getDescription());
            }
        }
        return desc;
    }

    /** Get the treasure associated with the treasure description.
     * @param desc description of the treasure
     * @return Treasure the treasure the corresponds
     */
    public Treasure treasDescToTreas(String desc) {
        int i;
        Treasure treas = new Treasure();
        for (i = 0; i < 100; i++) {
            treas.chooseTreasure(i + 1);
            if (desc.equals(treas.getDescription())) {
                treas.setContainer(randomNumber());
                return treas;
            }
        }
        return null;
    }
}
