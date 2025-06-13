package task.exec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import task.exec.config.ScriptsConfig;

public class Main {
    public static void main(String[] args) {

        ScriptsConfig config = new ScriptsConfig();
        config.writeFile();
        
        System.out.println("--------------- reading...");

        config.readFile();
    }
}