package main.java.main;

import java.util.Random;

import main.java.main.BeeHive.BeeSpecies;

/**
 * Worker bee type class that extends Bee.
 * 
 * @author Jason Shawcross
 *
 */
public class Worker extends Bee {

    // Stat constants
    private static final int DEFAULT_ATTACK = 10;
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_STAMINA = 30;
    private static final int BONUS_ATTACK = 2;
    private static final int BONUS_HEALTH = 2;
    private static final int BONUS_STAMINA = 10;
    
    private Random rdm = new Random();
    
    /**
     * Worker sub-class of Bee class.
     * 
     * @param inputId Integer, id to set on bee
     * @param inputHiveId Integer, hiveId to set on bee
     * @param inputSpecies BeeSpecies, species to set on bee
     */
    public Worker(int inputId, int inputHiveId, BeeSpecies inputSpecies) {
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
        if (species == BeeSpecies.Killer) {
            bonusAttack = BONUS_ATTACK;
            bonusStamina = -BONUS_STAMINA;
        } else if (species == BeeSpecies.Carpenter) {
            bonusHealth = -BONUS_HEALTH;
            bonusStamina = BONUS_STAMINA;
        } else if (species == BeeSpecies.Bumble) {
            bonusAttack = -BONUS_ATTACK;
            bonusHealth = BONUS_HEALTH;
        } else if (species == BeeSpecies.Tiny) {
            bonusAttack = -BONUS_ATTACK;
            bonusHealth = -BONUS_HEALTH;
            bonusStamina = -BONUS_STAMINA;
        } else if (species == BeeSpecies.Super) {
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
        if (input == BeeState.Nothing || input == BeeState.Build 
                || input == BeeState.Hatch || input == BeeState.Rest) {
            this.currentState = input;
        }
    }

    @Override
    public String doAction() {
        String output = "Nothing";
        
        // Actions done based on current state
        if (currentState == BeeState.Nothing) {
            // Randomly choose first action
            if (rdm.nextBoolean()) {
                currentState = BeeState.Build;
            } else {
                currentState = BeeState.Hatch;
            }
        } else if (currentState == BeeState.Build) {
            stamina = stamina - 1;
            output = "+work";
            // If not out of stamina then alternate to hatch
            if (stamina == 0) {
                currentState = BeeState.Rest;
            } else {
                currentState = BeeState.Hatch;
            }
        } else if (currentState == BeeState.Hatch) {
            stamina = stamina - 1;
            output = "-egg";
            // If not out of stamina then alternate to build
            if (stamina == 0) {
                currentState = BeeState.Rest;
            } else {
                currentState = BeeState.Build;
            }
        } else if (currentState == BeeState.Rest) {
            stamina = staminaMax;
            // After resting, randomly choose next action
            if (rdm.nextBoolean()) {
                currentState = BeeState.Build;
            } else {
                currentState = BeeState.Hatch;
            }
            output = "-food";
        } 
        
        return output;
    }

    @Override
    public String getType() {
        return "Worker";
    }

}
