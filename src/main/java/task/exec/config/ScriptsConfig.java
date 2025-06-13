package task.exec.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptsConfig {

    // testing
     //writing to a file
    
    public void writeFile() {
        try {
            String[] names = {"John", "Carl", "Jerry"};
            // i think i will put my scripts in a file and they will be read as an array

            BufferedWriter writer = new BufferedWriter(new FileWriter("scripts.conf"));
            writer.write("Writing to a file");
            writer.write("\nAnother line");

            for (String name : names) {
                writer.write("\n" + name);
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }    
    }
}
