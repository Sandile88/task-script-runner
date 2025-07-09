package task.exec;

import java.nio.file.Path;
import java.util.Scanner;

import task.exec.config.ScriptsManager;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ScriptsManager scriptsManager = new ScriptsManager();
        String path = Path.of("").toAbsolutePath().resolve("scripts.conf").toString(); //detect project root

        scriptsManager.loadScripts(path);
 
        while(true) {
            System.out.print("\nCommands: list | run | add | edit | delete | save | logs | exit\n> ");

            String input = sc.nextLine().trim();

            switch (input) {
                case "list" -> scriptsManager.listScripts();
                case "run" -> scriptsManager.runScript(sc);
                case "add" -> scriptsManager.addScriptFromUserInput(sc);
                case "edit" -> scriptsManager.editScript(sc);
                case "delete" -> scriptsManager.deleteScript(sc);
                case "logs" -> scriptsManager.showLogs();
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