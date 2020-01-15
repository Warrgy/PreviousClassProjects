package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class Main {
    /**The first chamber. */
    private Chamber c1;
    /**The second chamber. */
    private Chamber c2;
    /**The third chamber. */
    private Chamber c3;
    /**The fourth chamber. */
    private Chamber c4;
    /**The fifth chamber. */
    private Chamber c5;
    /**The map to hold all records.*/
    private HashMap<Door, ArrayList<Chamber>> map;
    /**Passage count.*/
    private int passCount;
    /**Constructor to set 5 new chambers.
     */
    public Main() {
        c1 = new Chamber();
        c2 = new Chamber();
        c3 = new Chamber();
        c4 = new Chamber();
        c5 = new Chamber();
        map = new HashMap<Door, ArrayList<Chamber>>();
        passCount = 0;
    }

    /**Map all chambers.
     * @return hash maap of mapped values.
     */
    public HashMap<Door, ArrayList<Chamber>> mapAllChambers() {
        Main main = new Main();
        Passage passage;
        int i;
        int j;
        int nextChamberAmount = 0;
        int counter = 0;
        ArrayList<Chamber> cham = new ArrayList<Chamber>();
        ArrayList<Chamber> chambers = main.sortedChambers();
        //Array to show how many doors each chamber has
        Integer[] doorNumber = {chambers.get(0).getDoors().size(), chambers.get(1).getDoors().size(), chambers.get(2).getDoors().size(), chambers.get(3).getDoors().size(), chambers.get(4).getDoors().size()};
        //Array used to keep track of how mnay chambers have how many doors
        Integer[] check = {0, 0, 0, 0, 0};
        //Loop 5 times for each chamber, and create the hashmap of records
        for (i = 0; i < 5; i++) {
            nextChamberAmount = i;
            counter = 0;
            //Loop the amount of doors there are. Put each door in the map, with the corresponding next chamber
            for (j = 0; j < main.numDoors(chambers.get(i)); j++) {
                passage = main.createPassage();
                //Check to see if there have already been enough doors connected to this chamber
                if (doorNumber[i] > check[i]) {
                    check[i]++;
                    counter++;
                    if (i == 4) {
                        //It will go down and add the current section
                        counter = counter;
                    } else if (i + counter <= 4) {
                        //Add 1 to counter for the next chamber counter
                        check[i + counter]++;
                    } else {
                        break;
                    }
                } else {
                    //Same, thus go to next one
                    break;
                }
                //Increment the offset of the chamber amount by 1. If becomes 5, bring it to 0 so it is in range.
                nextChamberAmount++;
                if (nextChamberAmount >= 5) {
                    nextChamberAmount = 0;
                }
                //Make arraylist with the chamber the door will point to
                cham = new ArrayList<Chamber>();
                cham.add(chambers.get(nextChamberAmount));
                //Put each door in the chamber into the map with the next chamber
                Door curDoor = chambers.get(i).getDoors().get(j);
                curDoor.setSpaces(chambers.get(i), passage);
                main.map.put(curDoor, cham);
            }
        }
        passCount = main.print();
        return map;
    }

    /**Get the amount of passages.
     * @return the passages
     */
    public int getPassCount() {
        return passCount;
    }
    private Passage createPassage() {
        Passage passage = new Passage();
        passage.addPassageSection(new PassageSection("passage goes straight for 10 ft"));
        passage.addPassageSection(new PassageSection("passage ends in Door to a Chamber"));
        return passage;
    }
    //Iterate through map, and print each index
    private int print() {
        int i;
        Door curDoor;
        ArrayList<Chamber> cham;
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        for (i = 0; i < map.size(); i++) {
            //System.out.println("\nConnection #" + (i + 1));
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            //Set the door and chamber arraylist for the current hashmap index
            curDoor = (Door) mapEntry.getKey();
            cham = (ArrayList<Chamber>) mapEntry.getValue();
            //printSpecifics(curDoor, cham);
        }
        //System.out.println("\n\n" + i + " " + "Connections printed.");
        return i;
    }
    //Print off the contents for the current connection in the index
    private void printSpecifics(Door curDoor, ArrayList<Chamber> cham) {
        System.out.println(curDoor.getSpaces().get(0).getDescription());
        System.out.println(curDoor.getDescription());
        System.out.println(curDoor.getSpaces().get(1).getDescription());
        System.out.println(cham.get(0).getDoors().get(0).getDescription());
        System.out.println(cham.get(0).getDescription());
        System.out.println("\n");
    }
    //Loads the arraylist of chamber that i am working with, into a sorted arraylist
    private ArrayList<Chamber> sortedChambers() {
        int i;
        boolean cham1 = false;
        boolean cham2 = false;
        boolean cham3 = false;
        boolean cham4 = false;
        boolean cham5 = false;
        ArrayList<Chamber> chambers = new ArrayList<Chamber>();
        //Array of the door amount for each doors
        Integer[] cham = {numDoors(c1), numDoors(c2), numDoors(c3), numDoors(c4), numDoors(c5)};
        //Sort array
        Arrays.sort(cham, Collections.reverseOrder());
        //add chambers to the arraylist, via the sorted array of door amount
        for (i = 0; i < 5; i++) {
            if ((cham[i] == numDoors(c1)) && !cham1) {
                cham1 = true;
                chambers.add(c1);
            } else if ((cham[i] == numDoors(c2)) && !cham2) {
                cham2 = true;
                chambers.add(c2);
            } else if ((cham[i] == numDoors(c3)) && !cham3) {
                cham3 = true;
                chambers.add(c3);
            } else if ((cham[i] == numDoors(c4)) && !cham4) {
                cham4 = true;
                chambers.add(c4);
            } else if ((cham[i] == numDoors(c5)) && !cham5) {
                cham5 = true;
                chambers.add(c5);
            }
        }
        return chambers;
    }
    private int numDoors(Chamber chamber) {
        return chamber.getDoors().size();
    }
}
