package main.java.main;

import java.util.Vector;

import main.java.main.BeeHive;

/**
 * Apiary class used to store singleton apiary object.
 * 
 * @author Jason Shawcross
 *
 */
public class Apiary {
    
    private static Apiary apiaryInstance = null;
    
    private String name;
    private Vector<BeeHive> hives;

    /**
     * Private constructor used to create singleton instance.
     * 
     * @param inputName String, Name to be used for the apiary
     */
    private Apiary(String inputName) {
        name = inputName;
        hives = new Vector<BeeHive>();
    }
    
    /**
     * Method used to get the instance of the apiary singleton.  Creates a new
     * instance if there isn't currently one.
     * 
     * @param inputName String, Name to be used for the apiary
     * @return Instance of apiary class
     */
    public static Apiary getInstance(String inputName) {
        if (apiaryInstance == null) {
            apiaryInstance = new Apiary(inputName);
        }
        
        return apiaryInstance;
    }

    /**
     * Method to get the name of the apiary instance.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the name of the apiary instance.
     * 
     * @param inputName String, Name to set on the instance
     */
    public void setName(String inputName) {
        this.name = inputName;
    }
    
    /**
     * Method to add a beehive.
     * 
     * @param input BeeHive, Object to add to the vector
     */
    public boolean addBeeHive(BeeHive input) {
        boolean exists = false;
        boolean wasAdded = false;
        
        for (int i = 0; i < hives.size(); i++) {
            if (hives.elementAt(i).getId() == input.getId()) {
                exists = true;
            }
        }
        
        if (!exists) {
            hives.addElement(input);
            wasAdded = true;
        }
        
        return wasAdded;
    }
    
    /**
     * Method to remove a Beehive.
     * 
     * @param input Integer, id number of BeeHive to remove
     */
    public boolean removeBeeHive(int input) {
        boolean wasRemoved = false;
        
        for (int i = 0; i < hives.size(); i++) {
            if (hives.elementAt(i).getId() == input) {
                hives.remove(i);
                wasRemoved = true;
            }
        }
        
        return wasRemoved;
    }
    
    /**
     * Method to return BeeHive object.
     * 
     * @param input Integer, id number of BeeHive to return
     * @return BeeHive
     */
    public BeeHive getBeeHive(int input) {
        BeeHive output =  new BeeHive.BeeHiveBuilder(0, 0, 0).build();
        
        for (int i = 0; i < hives.size(); i++) {
            if (hives.elementAt(i).getId() == input) {
                output = hives.elementAt(i);
            } else {
                output = null;
            }
        }
        
        return output;
    }
    
    /**
     * Method to return string representing contents of apiary.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer outBuffer =  new StringBuffer();
        outBuffer.append("Apiary name: " + name + "\n");
        outBuffer.append("Current Beehives:\n");
        
        for (int i = 0; i < hives.size(); i++) {
            outBuffer.append(hives.elementAt(i).toStringId() + "\n");
        }
        
        String output = outBuffer.toString();
        
        return output;
    }
    
    /**
     * Method to list out hives.
     * 
     * @return string
     */
    public String hiveList() {
        StringBuffer outBuffer =  new StringBuffer();
                
        for (int i = 0; i < hives.size(); i++) {
            outBuffer.append(hives.elementAt(i).toStringId());
        }
        
        String output = outBuffer.toString();
        
        return output;
    }
    
    public int countBeeHives() {
        return hives.size();
    }
    
}
