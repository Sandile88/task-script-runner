# Task Script Runner (CLI-based Automation Tool)

> A simple, config-driven automation tool built with Java that lets you run predefined scripts from your terminal, edit or add new ones, and track usage logs.

---

## Project Description

Task Script Runner is a **Java-based command-line tool** designed to execute automation tasks (like cleaning temp files, pinging websites, compiling code, etc.) based on definitions stored in a .conf file. It's built with the goal of **learning core Java concepts** like file I/O, ProcessBuilder, JDBC with SQLite, and CLI interaction, while still being a functional utility that can be extended further.

---

## Features

- **.conf-based configuration** — No hardcoded scripts; easily editable
- **Run scripts by name or ID**
- **Add, edit, or delete** scripts from the terminal
- **Dynamic arguments** with {arg} placeholders (e.g., touch {arg})
- **Execution logging** via SQLite (with timestamps + exit codes)
- **Neat console output** for viewing available scripts
- Cross-platform support (Linux/macOS supported, Windows possible with small tweaks)

---

## Requirements

- Java 17+
- Maven
- SQLite (CLI optional, used for viewing logs)

---

## How to Run

1. **Clone the repo**
```bash
git clone https://github.com/Sandile88/task-script-runner.git
cd task-script-runner
```

2. **Edit or review your scripts in** `scripts.conf`

Example entry:
```ini
[create_file]
command = touch {arg}
description = Create a file with the name you type
```

3. **Compile with Maven**
```bash
mvn clean compile
```

4. **Run the CLI**
```bash
java -cp target/classes task.exec.Main
```

5. **Interact using commands**
```
Commands: list | run | add | edit | delete | logs | save | exit
```

---

## What I Learned

This project helped me understand and apply:

- Java file I/O (BufferedReader, BufferedWriter)
- String parsing and config processing
- `ProcessBuilder` to run system commands
- Java's try-with-resources for safe resource handling
- JDBC with SQLite for logging and persistence
- Clean separation of concerns using packages

---

## Project Structure

```
task-script-runner/
├── README.md
├── data/
│   ├── logs.db                     # SQLite database for logs
│   └── scripts.conf                # Script definitions
├── pom.xml                         # Maven config
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── task/
│   │   │       └── exec/
│   │   │           ├── Main.java
│   │   │           ├── config/
│   │   │           │   └── ScriptsManager.java             # Core class for loading, editing, running, and saving scripts
│   │   │           ├── database/
│   │   │           │   └── ExecutionLogger.java            # SQLite database storing execution logs
│   │   │           └── model/
│   │   │               └── Script.java                     # POJO representing a script
│   │   └── resources/
│   └── test/
│       └── java/
└── target/                         # Compiled classes (generated)
    ├── classes/
    └── test-classes/
```

---

## Contributions Welcome!

This project started as a personal learning journey but anyone is welcome to:

- Suggest features
- Add new script types
- Extend with a GUI or web interface
- Port to another language

Feel free to fork and build upon it!

---

## License

This project is licensed under the [MIT License](https://opensource.org/license/mit).

---