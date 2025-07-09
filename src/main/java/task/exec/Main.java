package task.exec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import task.exec.config.ScriptsManager;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ScriptsManager scriptsManager = new ScriptsManager();
        String path = "scripts.conf";

        scriptsManager.loadScripts(path);
 
        while(true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. List scripts");
            System.out.println("2. Run a script");
            System.out.println("3. Add new script");
            System.out.println("4. Edit a script");
            System.out.println("5. Delete a script");
            System.out.println("6. Save and exit");

            System.out.println("> ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "1" -> scriptsManager.listScripts();
                case "2" -> scriptsManager.runScript(sc);
                case "3" -> scriptsManager.addScriptFromUserInput(sc);
                case "4" -> scriptsManager.editScript(sc);
                case "5" -> scriptsManager.deleteScript(sc);
                case "6" -> {
                    scriptsManager.saveScripts(path);
                    System.out.println("Changes saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
        
    }
}