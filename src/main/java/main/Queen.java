package main.java.main;

import main.java.main.BeeHive.BeeSpecies;

/**
 * Queen bee type class that extends Bee.
 * 
 * @author Jason Shawcross
 *
 */
public class Queen extends Bee {

    // Stat constants
    private static final int DEFAULT_ATTACK = 15;
    private static final int DEFAULT_HEALTH = 50;
    private static final int DEFAULT_STAMINA = 20;
    private static final int BONUS_ATTACK = 5;
    private static final int BONUS_HEALTH = 15;
    private static final int BONUS_STAMINA = 8;
    
    /**
     * Queen sub-class of Bee class.
     * 
     * @param inputId Integer, id to set on bee
     * @param inputHiveId Integer, hiveId to set on bee
     * @param inputSpecies BeeSpecies, species to set on bee
     */
    public Queen(int inputId, int inputHiveId, BeeSpecies inputSpecies) {
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
        // if state is a valid state for Queen then change
        if (input == BeeState.Nothing || input == BeeState.Lay || input == BeeState.Rest) {
            this.currentState = input;
        }
    }

    @Override
    public String doAction() {
        String output = "Nothing";
        
        // Actions done based on current state
        if (currentState == BeeState.Nothing) {
            currentState = BeeState.Lay;
        } else if (currentState == BeeState.Lay) {
            stamina = stamina - 1;
            output = "+egg";
            if (stamina == 0) {
                currentState = BeeState.Rest;
            }   
        } else if (currentState == BeeState.Rest) {
            stamina = staminaMax;
            currentState = BeeState.Lay;
            output = "-food";
        }
        
        return output;
    }

    @Override
    public String getType() {
        return "Queen";
    }

}
