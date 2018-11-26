package main.java.main;

/**
 * BeeHive class to store imformation about a BeeHive object.
 * 
 * @author Jason Shawcross
 *
 */
public class BeeHive {
    
    public static enum BeeSpecies {
        Honey, Killer, Carpenter, Bumble, Tiny, Super;
    }
    
    private static final int FOODPERROOM = 10;
    private static final int EGGSPERROOM = 2;
    private static final int RESTPERROOM = 6;
    
    // Required parameters
    private int id;
    private int locationX;
    private int locationY;
        
    // Optional parameters
    private BeeSpecies species;
    private int foodAmount;
    private int eggAmount;
    private int roomAmount;
    private int workRequired;
    
    // Depends on number of rooms
    private int foodCapacity;
    private int eggCapacity;
    private int restCapacity;
    
    // Depends on Bee type
    private int foodModifier;
    private int eggModifier;
    private int restModifier;
    
    /**
     * Getter Method for id value.
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Getter Method for locationX value.
     * 
     * @return the xAxisLoc
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Getter Method for locationY value.
     * 
     * @return the yAxisLoc
     */
    public int getLocationY() {
        return locationY;
    }

    /**
     * Getter Method for species value.
     * 
     * @return the type
     */
    public BeeSpecies getSpecies() {
        return species;
    }
    
    /**
     * Getter Method for foodAmount value.
     * 
     * @return the foodAmount
     */
    public int getFoodAmount() {
        return foodAmount;
    }
    
    /**
     * Getter Method for eggAmount value.
     * 
     * @return the eggAmount
     */
    public int getEggAmount() {
        return eggAmount;
    }
    
    /**
     * Getter Method for roomAmount value.
     * 
     * @return the roomAmount
     */
    public int getRoomAmount() {
        return roomAmount;
    }
    
    /**
     * Getter Method for workRequired value.
     * 
     * @return the workRequired
     */
    public int getWorkRequired() {
        return workRequired;
    }
    
    /**
     * Getter Method for foodCapacity value.
     * 
     * @return the foodCapacity
     */
    public int getFoodCapacity() {
        return foodCapacity;
    }
    
    /**
     * Getter Method for eggCapacity value.
     * 
     * @return the eggCapacity
     */
    public int getEggCapacity() {
        return eggCapacity;
    }
    
    /**
     * Getter Method for restCapacity value.
     * 
     * @return the restCapacity
     */
    public int getRestCapacity() {
        return restCapacity;
    }
    
    /**
     * Getter Method for foodModifier value.
     * 
     * @return the foodModifier
     */
    public int getFoodModifier() {
        return foodModifier;
    }
    
    /**
     * Getter Method for eggModifier value.
     * 
     * @return the eggModifier
     */
    public int getEggModifier() {
        return eggModifier;
    }
    
    /**
     * Getter Method for restModifier value.
     * 
     * @return the restModifier
     */
    public int getRestModifier() {
        return restModifier;
    }
    
    /**
     * Method to add food to hive.
     * 
     * @param input Integer, food to add
     */
    public void giveFood(int input) {
        foodAmount = foodAmount + input;
    }
    
    /**
     * Method to add eggs to hive.
     * 
     * @param input Integer, eggs to add
     */
    public void giveEggs(int input) {
        eggAmount = eggAmount + input;
    }
    
    /** 
     * toString method that overrides regular toString method.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // Create string buffer
        StringBuffer outBuffer =  new StringBuffer();
        
        // Append BeeHive info to buffer
        outBuffer.append("BeeHive id: " + this.id + "\n");
        outBuffer.append("Location: " + this.locationX + ", " + this.locationY + "\n");
        outBuffer.append("Species: " + this.species + "\n");
        outBuffer.append("Rooms: " + this.roomAmount + "\n");
        outBuffer.append("Work until new room: " + this.workRequired + "\n");
        outBuffer.append("Food: " + this.foodAmount + "/" + this.foodCapacity + "\n");
        outBuffer.append("Eggs: " + this.eggAmount + "/" + this.eggCapacity + "\n");
        outBuffer.append("Space for resting: " + this.restCapacity);
        
        // Set buffer to output String
        String output = outBuffer.toString();
        
        return output;
    }
    
    /**
     * Method to return just species and ID.
     * 
     * @return String
     */
    public String toStringId() {
        // Create string buffer
        StringBuffer outBuffer =  new StringBuffer();
        
        // Append BeeHive info to buffer
        outBuffer.append("Species: " + this.species);
        outBuffer.append(" ID: " + this.id + "\n");
        
        // Set buffer to output String
        String output = outBuffer.toString();
        
        return output;
    }
    
    /**
     * Private constructor called from builder class to create BeeHive object.
     * 
     * @param builder BeeHiveBuilder, Builder object created for parameter
     */
    private BeeHive(BeeHiveBuilder builder) {
        this.id = builder.id;
        this.locationX = builder.locationX;
        this.locationY = builder.locationY;
        this.species = builder.species;
        this.foodAmount = builder.foodAmount;
        this.eggAmount = builder.eggAmount;
        this.roomAmount = builder.roomAmount;
        this.workRequired = builder.workRequired;
        this.foodCapacity = builder.foodCapacity;
        this.eggCapacity = builder.eggCapacity;
        this.restCapacity = builder.restCapacity;
        this.foodModifier = builder.foodModifier;
        this.eggModifier = builder.eggModifier;
        this.restModifier = builder.restModifier;
    }
    
    /**
     * Builder class for BeeHive class.
     * 
     * @author Jason Shawcross
     *
     */
    public static class BeeHiveBuilder {
        // Required parameters
        private int id;
        private int locationX;
        private int locationY;
            
        // Optional parameters
        private BeeSpecies species;
        private int foodAmount;
        private int eggAmount;
        private int roomAmount;
        private int workRequired;
        
        // Depends on number of rooms
        private int foodCapacity;
        private int eggCapacity;
        private int restCapacity;
        
        // Depends on Bee type
        private int foodModifier;
        private int eggModifier;
        private int restModifier;
        
        /**
         * Constructor used to create default BeeHiveBuilder object.
         * 
         * @param inputId Integer, Id passed as parameter
         * @param inputX Integer, X axis location passed as parameter
         * @param inputY Integer, Y axis location passed as parameter
         */
        public BeeHiveBuilder(int inputId, int inputX, int inputY) {
            // Required parameters
            this.id = inputId;
            this.locationX = inputX;
            this.locationY = inputY;
            
            // Default parameters
            this.species = BeeSpecies.Honey;
            this.foodAmount = 10;
            this.eggAmount = 0;
            this.roomAmount = 3;
            this.workRequired = 100;
            this.foodModifier = 0;
            this.eggModifier = 0;
            this.restModifier = 0;
            
            // Set the capacities for the bee hive
            updateCapacity();
        }
        
        /**
         * Method to set the type of the bee on the Builder object.
         * 
         * @param inputType BeeType, type of bee
         * @return
         */
        public BeeHiveBuilder setSpecies(BeeSpecies inputType) {
            this.species = inputType;
            
            // Update modifiers based on Bee Type
            if (inputType == BeeSpecies.Killer) {
                this.restModifier = 2;
            } else if (inputType == BeeSpecies.Carpenter) {
                this.foodModifier = 3;
            } else if (inputType == BeeSpecies.Bumble) {
                this.eggModifier = 1;
            } else if (inputType == BeeSpecies.Tiny) {
                this.foodModifier = -3;
                this.eggModifier = -1;
                this.restModifier = 2;
            } else if (inputType == BeeSpecies.Super) {
                this.foodModifier = 2;
                this.eggModifier = 1;
                this.restModifier = 1;
            } else {
                this.foodModifier = 0;
                this.eggModifier = 0;
                this.restModifier = 0;
            }
            
            // Modifiers have been updated so capacities need to be updated
            updateCapacity();
            
            return this;
        }
        
        /**
         * Method to set the foodAmount on the Builder object.
         * 
         * @param inputFood Integer, Amount of food to set for the BeeHive
         * @return
         */
        public BeeHiveBuilder setFoodAmount(int inputFood) {
            this.foodAmount = inputFood;
            return this;
        }
        
        /**
         * Method to set the eggAmount on the Builder object.
         * 
         * @param inputEgg Integer, Amount of eggs to set for the BeeHive
         * @return
         */
        public BeeHiveBuilder setEggAmount(int inputEgg) {
            this.eggAmount = inputEgg;
            return this;
        }
        
        /**
         * Method to set the roomAmount on the Builder object.
         * 
         * @param inputRoom Integer, Amount of rooms to set for the BeeHive
         * @return
         */
        public BeeHiveBuilder setRoomAmount(int inputRoom) {
            this.roomAmount = inputRoom;
            
            // Rooms have changed so capacities needs to be updated
            updateCapacity();
            
            return this;
        }
        
        /**
         * Method to set the workRequired on the Builder object.
         * 
         * @param inputWork Integer, Amount of work required for a new room set for the BeeHive
         * @return
         */
        public BeeHiveBuilder setWorkRequired(int inputWork) {
            this.workRequired = inputWork;
            return this;
        }
        
        /**
         * Method to build the BeeHive from the Builder object.
         * 
         * @return
         */
        public BeeHive build() {
            return new BeeHive(this);
        }
        
        /**
         *  Method to update the capacity values in case either the modifiers
         *  or the room amount is changed from default.
         */
        private void updateCapacity() {
            // Use modifiers and number of rooms to determine BeeHive capacity
            this.foodCapacity = (FOODPERROOM + this.foodModifier) * this.roomAmount;
            this.eggCapacity = (EGGSPERROOM + this.eggModifier) * this.roomAmount;
            this.restCapacity = (RESTPERROOM + this.restModifier) * this.roomAmount;
        }
    }
}
