package main.java.main;

import java.util.Random;

/**
 * Drone bee type class that extends Bee.
 * 
 * @author Jason Shawcross
 *
 */
public class Drone extends Bee {

    // Stat constants
    private static final int DEFAULT_ATTACK = 5;
    private static final int DEFAULT_HEALTH = 5;
    private static final int DEFAULT_STAMINA = 30;
    private static final int BONUS_ATTACK = 2;
    private static final int BONUS_HEALTH = 2;
    private static final int BONUS_STAMINA = 6;
    
    private Random rdm = new Random();
    
    /**
     * Drone sub-class of Bee class.
     * 
     * @param inputId Integer, id to set on bee
     * @param inputHiveId Integer, hiveId to set on bee
     * @param inputSpecies BeeSpecies, species to set on bee
     */
    public Drone(int inputId, int inputHiveId, BeeSpeciesF inputSpecies) {
        // Set bee information
        this.id = inputId;
        this.beeHiveId = inputHiveId;
        this.species = inputSpecies;
        this.currentState = BeeState.Nothing;
        
        // Initialize bonus
        int bonusAttack = 0;
        int bonusHealth = 0;
        int bonusStamina = 0;
        
        // Set bonus based on species
        if (species == BeeSpeciesF.Killer) {
            bonusAttack = BONUS_ATTACK;
            bonusStamina = -BONUS_STAMINA;
        } else if (species == BeeSpeciesF.Carpenter) {
            bonusHealth = -BONUS_HEALTH;
            bonusStamina = BONUS_STAMINA;
        } else if (species == BeeSpeciesF.Bumble) {
            bonusAttack = -BONUS_ATTACK;
            bonusHealth = BONUS_HEALTH;
        } else if (species == BeeSpeciesF.Tiny) {
            bonusAttack = -BONUS_ATTACK;
            bonusHealth = -BONUS_HEALTH;
            bonusStamina = -BONUS_STAMINA;
        } else if (species == BeeSpeciesF.Super) {
            bonusAttack = BONUS_ATTACK;
            bonusHealth = BONUS_HEALTH;
            bonusStamina = BONUS_STAMINA;
        }
        
        // Set stats based on bonus
        this.attack = DEFAULT_ATTACK + bonusAttack;
        this.healthMax = DEFAULT_HEALTH + bonusHealth;
        this.health = this.healthMax;
        this.staminaMax = DEFAULT_STAMINA + bonusStamina;
        this.stamina = this.staminaMax;
    }

    @Override
    public void setState(BeeState input) {
        if (input == BeeState.Nothing || input == BeeState.Search || input == BeeState.Forage 
                || input == BeeState.Return || input == BeeState.Rest) {
            this.currentState = input;
        }
    }

    @Override
    public String doAction() {
        String output = "Nothing";
        
        // Actions done based on current state
        if (currentState == BeeState.Nothing) {
            currentState = BeeState.Search;
        } else if (currentState == BeeState.Search) {
            stamina = stamina - 1;
            output = "search";
            // If not out of stamina keeps searching unless food is found
            if (stamina == 0) {
                currentState = BeeState.Return;
            } else {
                if (rdm.nextBoolean()) {
                    currentState = BeeState.Forage;
                }
            }
        } else if (currentState == BeeState.Forage) {
            stamina = stamina - 1;
            output = "+food";
            // If not out of stamina searches for more food
            if (stamina == 0) {
                currentState = BeeState.Return;
            } else {
                if (rdm.nextBoolean()) {
                    currentState = BeeState.Search;
                }
            }
        } else if (currentState == BeeState.Return) {
            output = "return";
            currentState = BeeState.Rest;
        } else if (currentState == BeeState.Rest) {
            stamina = staminaMax;
            currentState = BeeState.Search;
            output = "-food";
        } 
        
        return output;
    }

    @Override
    public String getType() {
        return "Drone";
    }

}
