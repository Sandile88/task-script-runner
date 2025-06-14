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
            System.out.println("\nCommands: list"); 
            System.out.println("> ");
            String input = sc.nextLine().trim();
            if(input.equals("list")) { //change to switch case
                scriptsManager.listScripts();
            }
        }
        
    }
}