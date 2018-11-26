package main.java.main;

import java.util.Scanner;
import java.util.Vector;

import main.java.main.Apiary;
import main.java.main.BeeHive;
import main.java.main.BeeHive.BeeHiveBuilder;
import main.java.main.BeeHive.BeeSpecies;

public class Controller {
    public static Apiary beeApiary = Apiary.getInstance("default");
    public static Vector<Bee> bees = new Vector<Bee>();
    public static int hiveIdCount = 1;
    public static int beeIdCount = 1;
    
    /**
     * Main method.
     * 
     * @param args String[] args
     */
    public static void main(String[] args) {
        boolean end = false;
        Scanner input = new Scanner(System.in);
        String command;
        
        // to do: add naming apiary
        
        help();
        
        
        
        while (!end) {
            System.out.print("Enter command: ");
            command = input.nextLine();
            String[] parsed = command.split(" ");
            
            if (parsed[0].equals("exit")) {
                end = true;
            } else if (parsed[0].equals("help")) {
                help();
            } else if (parsed[0].equals("spawn")) {
                spawn(parsed);
            } else if (parsed[0].equals("give")) {
                give(parsed);
            } else if (parsed[0].equals("summary")) {
                summary(parsed);
            } else if (parsed[0].equals("list")) {
                list(parsed);
            } else {
                System.out.println("Invalid command");
            }
        }
        
        input.close();
    }
    
    /**
     * Method for help command.
     */
    public static void help() {
        System.out.println("Bee Simulator Commands:");
        System.out.println("spawn <x location> <y location> <bee type>");
        System.out.println("   spawns new beehive at location");
        System.out.println("give <hive id> <resource> <amount>");
        System.out.println("   give specific bee hive a resource");
        System.out.println("summary <'bee' or 'hive'> <hive id>");
        System.out.println("   shows information for specific bee or bee hive");
        System.out.println("list <'bees' or 'hives'");
        System.out.println("   list all hives or all bees");
        System.out.println("help");
        System.out.println("   shows the list of commands");
        System.out.println("exit");
        System.out.println("   exit the simulator\n");
    }
    
    /**
     * Method for spawn command.
     * 
     * @param input String[] string from command line
     */
    public static void spawn(String[] input) {
        boolean created = true;
        
        if (input.length != 4) {
            System.out.println("invalid parameters for 'spawn' command, please use:");
            System.out.println("spawn <x location> <y location> <bee type>");
        } else {
            try {
                // Create bee hive builder
                BeeHiveBuilder newHiveBuilder = new BeeHive.BeeHiveBuilder(hiveIdCount, 
                        Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                
                // Added species to builder
                if (input[3].equals("honey")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Honey);
                } else if (input[3].equals("killer")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Killer);
                } else if (input[3].equals("carpenter")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Carpenter);
                } else if (input[3].equals("bumble")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Bumble);
                } else if (input[3].equals("super")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Super);
                } else if (input[3].equals("tiny")) {
                    newHiveBuilder = newHiveBuilder.setSpecies(BeeSpecies.Tiny);
                } else {
                    created = false;
                }
                
                // If hive true, create hive and add to apiary
                if (created) {
                    BeeHive newHive = newHiveBuilder.build();
                    beeApiary.addBeeHive(newHive);
                    System.out.println("Created " + input[3] + " bee hive at " 
                                + input[1] + "," + input[2] + " with id: " + hiveIdCount);
                    hiveIdCount++;
                } else {
                    System.out.println("invalid bee type, valid types:");
                    System.out.println("honey, killer, carpenter, bumble, super, tiny");
                }
                
            } catch (Exception ex) {
                System.out.println("invalid parameters for 'spawn' command, please use:");
                System.out.println("spawn <x location> <y location> <bee type>");
            }
        }
    }
    
    public static void give(String[] input) {
        
    }

    public static void summary(String[] input) {
           
    }
    
    public static void bee(String[] input) {
        
    }

    public static void list(String[] input) {
        System.out.println("Bee hives:");
        System.out.println(beeApiary.hiveList());
    }
}
