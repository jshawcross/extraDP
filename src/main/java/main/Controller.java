package main.java.main;

import java.util.Scanner;
import java.util.Vector;

import main.java.main.Apiary;
import main.java.main.BeeHive;
import main.java.main.BeeHive.BeeHiveBuilder;
import main.java.main.BeeHive.BeeSpecies;

public class Controller {
    private static Apiary beeApiary = Apiary.getInstance("default");
    private static Vector<Bee> bees = new Vector<Bee>();
    private static int hiveIdCount = 1;
    private static int beeIdCount = 1;
    
    /**
     * Main method.
     * 
     * @param args String[] args
     */
    public static void main(String[] args) {
        boolean end = false;
        Scanner input = new Scanner(System.in, "UTF-8");
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
     * @param input String[] parsed string from command line
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
                
                if (created) {
                    // create hive and add to apiary
                    BeeHive newHive = newHiveBuilder.build();
                    beeApiary.addBeeHive(newHive);
                    System.out.println("Spawned " + input[3] + " bee hive at " 
                                + input[1] + "," + input[2] + " with id: " + hiveIdCount);
                    
                    // create queen bee for hive
                    Bee newBee = BeeFactory.getBee("Queen", beeIdCount, hiveIdCount, 
                            beeApiary.getBeeHive(hiveIdCount).getSpecies());
                    bees.add(newBee);
                    
                    // iterate hive id and bee id number
                    hiveIdCount++;
                    beeIdCount++;
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
    
    /**
     * Method for give command.
     * 
     * @param input String[] parsed string from command line
     */
    public static void give(String[] input) {
        // Check for valid parameter amount
        if (input.length != 4) {
            System.out.println("invalid parameters for 'give' command, please use:");
            System.out.println("give <hive id> <resource> <amount>");
        } else {
            try {
                // Check if bee hive with this id exists
                if (beeApiary.getBeeHive(Integer.parseInt(input[1])) == null) {
                    System.out.println("No bee hive exists with that id");
                } else {
                    if (input[2].equals("worker")) {
                        for (int i = 1; i <= Integer.parseInt(input[3]); i++) {
                            Bee newBee = BeeFactory.getBee("Worker", beeIdCount, 
                                    Integer.parseInt(input[1]), 
                                    beeApiary.getBeeHive(Integer.parseInt(input[1])).getSpecies());
                            bees.addElement(newBee);
                            beeIdCount++;
                        }
                        System.out.println("Spawned " + Integer.parseInt(input[3])
                                + " worker bees for hive id: " + Integer.parseInt(input[1]));
                    } else if (input[2].equals("warrior")) {
                        for (int i = 1; i <= Integer.parseInt(input[3]); i++) {
                            Bee newBee = BeeFactory.getBee("Warrior", beeIdCount, 
                                    Integer.parseInt(input[1]), 
                                    beeApiary.getBeeHive(Integer.parseInt(input[1])).getSpecies());
                            bees.addElement(newBee);
                            beeIdCount++;
                        }
                        System.out.println("Spawned " + Integer.parseInt(input[3])
                                + " warrior bees for hive id: " + Integer.parseInt(input[1]));
                    } else if (input[2].equals("drone")) {
                        for (int i = 1; i <= Integer.parseInt(input[3]); i++) {
                            Bee newBee = BeeFactory.getBee("Drone", beeIdCount, 
                                    Integer.parseInt(input[1]), 
                                    beeApiary.getBeeHive(Integer.parseInt(input[1])).getSpecies());
                            bees.addElement(newBee);
                            beeIdCount++;
                        }
                        System.out.println("Spawned " + Integer.parseInt(input[3])
                                + " drone bees for hive id: " + Integer.parseInt(input[1]));
                    } else if (input[2].equals("food")) {
                        beeApiary.getBeeHive(Integer.parseInt(input[1]))
                                .giveFood(Integer.parseInt(input[3]));
                        System.out.println("Gave hive id: " + Integer.parseInt(input[1]) + " "
                                + Integer.parseInt(input[3]) + " food");
                    } else if (input[2].equals("egg")) {
                        beeApiary.getBeeHive(Integer.parseInt(input[1]))
                                .giveEggs(Integer.parseInt(input[3]));
                        System.out.println("Gave hive id: " + Integer.parseInt(input[1]) + " "
                                + Integer.parseInt(input[3]) + " eggs");
                    } else {
                        System.out.println("invalid item to give to bee hive, valid items:");
                        System.out.println("worker, warrior, drone, food, egg");
                    }
                }
            } catch (Exception ex) {
                System.out.println("invalid parameters for 'give' command, please use:");
                System.out.println("give <hive id> <resource> <amount>");
            }
        }
    }

    /**
     * Method for summary command.
     * 
     * @param input String[] parsed string from command line
     */
    public static void summary(String[] input) {
        // Check for valid parameter amount
        if (input.length != 3) {
            System.out.println("invalid parameters for 'summary' command, please use:");
            System.out.println("summary <hive> <hive id> or summary <bees> <bee id>");
        } else {
            // Try block to catch if integer not used for id number
            try {
                if (input[1].equals("hive")) {
                    // Check if bee hive exists with this id or not
                    if (beeApiary.getBeeHive(Integer.parseInt(input[2])) == null) {
                        System.out.println("No bee hive exists with that id");
                    } else {
                        System.out.print(beeApiary.getBeeHive(Integer.parseInt(input[2])) + "\n");
                    }
                } else if (input[1].equals("bee")) {   
                    boolean found = false;
                    
                    // Loop through vector and look for bee with this id
                    for (int i = 0; i < bees.size(); i++) {
                        // Check if id matches bee at current index
                        if (bees.elementAt(i).getId() == Integer.parseInt(input[2])) {
                            System.out.println(bees.elementAt(i));
                            found = true;
                        }
                    }

                    // Check if a bee was found with this id
                    if (!found) {
                        System.out.println("No bee exists with that id");
                    }
                } else {
                    System.out.println("invalid parameters for 'summary' command, please use:");
                    System.out.println("list <hives> or list <bees>");
                }
            } catch (Exception ex) {
                System.out.println("einvalid parameters for 'summary' command, please use:");
                System.out.println("summary <hive> <hive id> or summary <bees> <bee id>");
            }
        }
    }
    
    /**
     * Method for list command.
     * 
     * @param input String[] parsed string from command line
     */
    public static void list(String[] input) {
        // Check for valid parameter amount
        if (input.length != 2) {
            System.out.println("invalid parameters for 'list' command, please use:");
            System.out.println("list <hives> or list <bees>");
        } else {
            // If hive output hives, if bees output bees
            if (input[1].equals("hives")) {
                System.out.println("Bee hives:");
                System.out.print(beeApiary.hiveList());
            } else if (input[1].equals("bees")) {
                System.out.println("Bees:");
                
                // Loop through vector and output each bee
                for (int i = 0; i < bees.size(); i++) {
                    System.out.println(bees.elementAt(i).toStringId());
                }
            } else {
                System.out.println("invalid parameters for 'list' command, please use:");
                System.out.println("list <hives> or list <bees>");
            }
        }     
    } 
}
