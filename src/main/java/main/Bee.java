package main.java.main;

public abstract class Bee {
    
    public static enum BeeState {
        Nothing, Search, Forage, Rest, Attack, Return, Lay, Hatch, Build;
    }
    
    public static enum BeeSpeciesF {
        Honey, Killer, Carpenter, Bumble, Tiny, Super;
    }
    
    // Bee Information
    protected int id;
    protected int beeHiveId;
    protected BeeState currentState;
    protected BeeSpeciesF species;
    
    // Bee Stats
    protected int attack;
    protected int health;
    protected int healthMax;
    protected int stamina;
    protected int staminaMax;
    
    /**
     * Getter method for id.
     * 
     * @return
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Getter method for beeHiveId.
     * 
     * @return
     */
    public int getBeeHiveId() {
        return this.beeHiveId;
    }
    
    /**
     * Setter method for beeHiveId.
     * 
     * @param inputBeeHiveId Integer, beeHiveId to set
     */
    public void setBeeHiveId(int inputBeeHiveId) {
        this.beeHiveId = inputBeeHiveId;
    }
    
    /**
     * Getter method for current bee state.
     * 
     * @return
     */
    public BeeState getCurrentState() {
        return this.currentState;
    }
    
    /**
     * Getter method for bee species.
     * 
     * @return
     */
    public BeeSpeciesF getSpecies() {
        return this.species;
    }
    
    /**
     * Getter method for attack stat.
     * 
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Getter method for health stat.
     * 
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getter method for max health stat.
     * 
     * @return the healthMax
     */
    public int getHealthMax() {
        return healthMax;
    }

    /**
     * Getter method for stamina stat.
     * 
     * @return the stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * Getter method for max stamina stat.
     * 
     * @return the staminaMax
     */
    public int getStaminaMax() {
        return staminaMax;
    }

    /**
     * Setter method for bee state. Defined by sub-class.
     * 
     * @param inputState BeeState, state to set the bee to
     */
    public abstract void setState(BeeState inputState);
    
    /**
     * Method to perform action. Defined by sub-class.
     * 
     * @return
     */
    public abstract String doAction();
    
    /**
     * Method to return type of bee.  Defined by sub-class.
     * 
     * @return
     */
    public abstract String getType();
    
    /**
     * Method to output string representation of bee object.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // Create string buffer
        StringBuffer outBuffer =  new StringBuffer();
        
        // Append BeeHive info to buffer
        outBuffer.append("Bee id: " + id + "\n");
        outBuffer.append("BeeHive id: " + beeHiveId + "\n");
        outBuffer.append("Species: " + species + "\n");
        outBuffer.append("Type: " + this.getType() + "\n");
        outBuffer.append("Attack: " + attack + "\n");
        outBuffer.append("Health: " + health + "/" + healthMax + "\n");
        outBuffer.append("Stamina: " + stamina + "/" + staminaMax);
        
        // Set buffer to output String
        String output = outBuffer.toString();
        
        return output;
    }
    
}
