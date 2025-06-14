package task.exec.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import task.exec.model.Script;

public class ScriptsManager {

    private final Map<String, Script> scripts = new LinkedHashMap<>(); //preserves order of scripts

    public void loadScripts(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));

        String scriptName = null;
        Map<String, String> scriptDetails = new HashMap<>();

        for (String line : lines) {
            line = line.trim(); // assigning back for line to change value
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

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

            if(scriptName != null) {
                addScript(scriptName, scriptDetails);
            }
        }
    }

    private void addScript(String name, Map<String, String> config) {
        int id = scripts.size() + 1; //script counter
        String command = config.getOrDefault("command", "");
        String description = config.getOrDefault("description", "");

        scripts.put(name, new Script(id, name, command, description)); //script obj
    }
}
