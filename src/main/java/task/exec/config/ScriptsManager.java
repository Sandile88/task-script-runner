package task.exec.config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import task.exec.database.ExecutionLogger;
import task.exec.model.Script;

public class ScriptsManager {

    private final ExecutionLogger logger = new ExecutionLogger();
    private final Map<String, Script> scripts = new LinkedHashMap<>(); //preserves order of scripts

    public void loadScripts(String path) throws IOException {
        // check for existance of scripts.conf file
        Path confPath = Path.of(path);
        if (!Files.exists(confPath)) {
            System.out.println("scripts.conf file not found at: " + path);
            return;
        }

        List<String> lines = Files.readAllLines(Path.of(path));

        // check if script has contents or not
        if (lines.isEmpty()) {
        System.out.println("scripts.conf is empty.");
        return;
    }
        scripts.clear(); //removing all existing scripts before loading new ones

        String scriptName = null;
        Map<String, String> scriptDetails = new HashMap<>();

        for (String line : lines) {
            line = line.trim(); // assigning back for line to change value
            if (line.isEmpty() || line.startsWith("#")) continue;
    
            if (line.startsWith("[") && line.endsWith("]")) {
                if (scriptName != null) {
                    addScript(scriptName, scriptDetails); //storing previous script to map
                }

                scriptName = line.substring(1, line.length() - 1); //get script name inside brackets
                scriptDetails = new HashMap<>(); // preparing new map for script's data
            } else {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    scriptDetails.put(parts[0].trim(), parts[1].trim());
                }
            }

        }
        if(scriptName != null) {
            addScript(scriptName, scriptDetails);
        }

    }

    

    public void listScripts() {
        System.out.printf("%-3s | %-15s | %s%n",
                            "ID", "Name", "Description");
        System.out.println("----+-----------------+---------+-------------------------");
        scripts.values().forEach(s -> 
            System.out.printf("%-3d | %-15s | %s%n",
                                s.getId(), s.getName(), s.getDescription()));

    }

    public void runScript(Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Enter script name or ID: ");
        String key = scanner.nextLine();

        Script s = scripts.getOrDefault(key, null); //fetching script by name

        if (s == null) { //inserted id
            try {
                int id = Integer.parseInt(key);
                s = scripts.values() //looking through every script
                        .stream()
                        .filter(scpt -> scpt.getId() == id)
                        .findFirst()
                        .orElse(null);
            } catch (NumberFormatException e) {} //ignored when something other than number is inserted
        }
    

        if (s == null) {
            System.out.println("Script not found");
            return;
        }

        // checking if it needs extra args
        String command = s.getCommand();
        if(command.contains("{")) {
            System.out.println("Enter args for the script (e.g, filename): ");
            System.out.print("> ");
            String arg = scanner.nextLine();
            command = command.replace("{arg}", arg);
        }

        System.out.printf("→ Running \"%s\" …%n%n", s.getName());
        Process process = new ProcessBuilder() //launches shell cmd
                            .command(parseScriptCommand(command)) //building command list
                            .inheritIO() // able to see live output
                            .start();
        int exit = process.waitFor(); //wait for script to finishes
        System.out.printf("%nFinished with exit code %d%n", exit);

        logger.log(s.getName(), exit);

    }

    public void saveScripts(String path) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Path.of(path));

        for (Script s : scripts.values()) {

            writer.write("[" + s.getName() + "]\n");
            writer.write("command = " + s.getCommand() + "\n");
            writer.write("description = " + s.getDescription() + "\n\n");
        }

        writer.close();
    }

    public void addScriptFromUserInput(Scanner scanner) {
        System.out.print("Script name: ");
        String name = scanner.nextLine();

        System.out.print("Command: ");
        String command = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        int id = scripts.size() + 1;
        Script newScript = new Script(id, name, command, description);
        scripts.put(name, newScript);
        System.out.println("Script added.");
    }

    public void editScript(Scanner scanner) {
        System.out.print("Enter script name to edit: ");
        String name = scanner.nextLine();

        Script s = scripts.get(name);
        if (s == null) {
            System.out.println("Script not found");
            return;
        }

        System.out.print("New command (leave blank to keep current): ");
        String command = scanner.nextLine();

        if (!command.isBlank()) {
            s.setCommand(command);
        }

        System.out.print("New description (leave blank to keep current): ");
        String description = scanner.nextLine();

        if (!description.isBlank()) {
            s.setDescription(description);
        }

        System.out.println("Script updated");

    }

    public void deleteScript(Scanner scanner) {
        System.out.print("Enter script name to delete: ");
        String name = scanner.nextLine();

        if (scripts.remove(name) != null) {
            System.out.println("Script deleted.");
        } else {
            System.out.println("Script not found.");
        }
    }



    // helper methods:
    private void addScript(String name, Map<String, String> config) {
        int id = scripts.size() + 1; //script counter
        String command = config.getOrDefault("command", "");
        String description = config.getOrDefault("description", "");

        scripts.put(name, new Script(id, name, command, description)); //script obj
    }

    private List<String> parseScriptCommand(String command) {
        return Arrays.asList("bash", "-c", command); // forLinux/macOs
    }

    public void showLogs() {
        logger.showLogs();
    }
}
