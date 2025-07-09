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
            System.out.print("\nCommands: list | run | add | edit | delete | save | exit\n> ");

            System.out.println("> ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "list" -> scriptsManager.listScripts();
                case "run" -> scriptsManager.runScript(sc);
                case "add" -> scriptsManager.addScriptFromUserInput(sc);
                case "edit" -> scriptsManager.editScript(sc);
                case "delete" -> scriptsManager.deleteScript(sc);
                case "exit" -> {
                    scriptsManager.saveScripts(path);
                    System.out.println("Changes saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
        
    }
}