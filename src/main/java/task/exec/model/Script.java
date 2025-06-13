package task.exec.model;

public class Script {
    public int id;
    public String name;
    public String command;
    public String description;

    public Script(int id, String name, String command, String description) {
        this.id = id;
        this.name = name;
        this.command = command;
        this.command = command;
    }

    public int getId(int id) {
        return id;
    }
    public String getName(String name) {
        return name;
    }
    public String getCommand(String command) {
        return command;
    }
    public String getDescription(String description) {
        return description;
    }

}
