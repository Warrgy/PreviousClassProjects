package app;


import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializable {

    private void saveFile() {
        int i;
        try {
            FileOutputStream fileOut = new FileOutputStream("/tmp/space.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (i = 0; i < 5; i++) {
                System.out.println("Attempting to write.");
                out.writeInt(3);
                //out.writeObject(new Chamber()/*theController.getChamber(i)*/);
                System.out.println("Wrote chamber.");
            }
            System.out.println("Closing.");
            fileOut.close();
            System.out.println("Closed.");
        } catch (IOException e) {
            System.out.println("Failed.");
        }
    }



}
