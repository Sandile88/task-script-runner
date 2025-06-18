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

        ScriptsManager scriptsManager = new ScriptsManager();
        scriptsManager.loadScripts("scripts.conf");
 
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nCommands: list | run <id/name> | exit");
            System.out.println("> ");
            String input = sc.nextLine().trim();
            if (input.equals("exit")) break;
            if (input.equals("list")) { //change to switch case
                scriptsManager.listScripts();
            }if (input.startsWith("run")) {
                scriptsManager.runScript(input.substring(4).trim());
            }
        }
        
    }
}