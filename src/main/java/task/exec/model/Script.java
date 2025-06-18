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
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCommand() {
        return command;
    }
    public String getDescription() {
        return description;
    }

    public void setCommand(String command) {
        this.command = command;
    }

     public void setDescription(String description) {
        this.description = description;
    }

}
