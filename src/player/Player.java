package player;

import pokemon.Pokemon;

/**
 * Player
 * Implements player functionality in our game
 * 
 * @author jon
 *
 */
public class Player {
	
	// Inheritable fields
	protected String name;			// The player's name
	protected boolean human;		// Is the player human?
	protected Pokemon[] pokemon;	// The list of the player's Pokemon in the battle
	
	// Private fields
	private int currentPokemon;		// Which Pokemon is currently selected?
	private int count;				// How many Pokemon are available to use?

	/**
	 * Player
	 * Construct a player object.
	 * By default, we construct a computer player 
	 */
	public Player() {
		this("Computer", false);
	}
	
	/**
	 * Player
	 * Construct a full player object
	 * 
	 * @param name  What is the player's name? 
	 * @param human Is this player human?
	 */
	public Player(String name, boolean human) {
		this.name = name;
		this.human = human;
		this.pokemon = new Pokemon[3];
		
		this.currentPokemon = 0;
		this.count = 0;
	}
	
	// Accessors
	public String getName() {
		return this.name;
	}
	
	public boolean isHuman() {
		return human;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
	
	// Get the currently selected Pokemon
	public Pokemon getPokemon() {
		return this.pokemon[this.currentPokemon];
	}
	
	// What does a player look like?  It's their name, followed by the list of Pokemon
	// The currently selected Pokemon is bracketed
	public String toString() {
		String output = "";
		if (this.isHuman()){
			output += "Player " + this.name;
		} else {
			output += "Computer";
		}
		output += " has the following Pokemon:\n";
		for (int i=0; i<3; i++) {
			if (i==this.currentPokemon)
				output+="[" + (i+1) + "]: ";
			else
				output+="" + (i+1) + ": ";
			output += this.pokemon[i];
			if (this.pokemon[i].getHealth()==0) output += " (downed)";
			output += "\n";
		}
		return output;
	}
	
	/**
	 * selectPokemon
	 * Change the currently selected Pokemon
	 * TODO: Add logic to disallow changing to a downed Pokemon
	 * TODO: Add logic to disallow changing to the current Pokemon
	 * TODO: Change return value to boolean if we didn't change due to above additions
	 * 
	 * @param currentPokemon Index of the Pokemon to switch to
	 */
	public void selectPokemon(int currentPokemon) {
		this.currentPokemon = currentPokemon;
	}
	
	/**
	 * addPokemon
	 * Adds a Pokemon to the current player's roster
	 * TODO: Change return value to boolean in case we didn't add the Pokemon 
	 * 
	 * @param pokemon A Pokemon to add
	 */
	public void addPokemon(Pokemon pokemon) {
		if (this.count < 3) {
			this.pokemon[count] = pokemon;
			count += 1;
		}
	}
	
	/**
	 * removePokemon
	 * Removes a Pokemon from the given position in the list
	 * 
	 * @param position Index of the Pokemon to remove
	 */
	public void removePokemon(int position) {
		this.pokemon[position] = null;
		this.consolidate();
		this.count -= 1;
	}
	
	/**
	 * consolidate
	 * If we have less than 3 Pokemon, moves the blanks to the end of the list.
	 * Used when we remove a Pokemon from earlier in the list
	 * TODO: Profile to see if we ever call this function (I don't think we do)
	 */
	private void consolidate() {
		int i=0, j=1;
		while (j<3) {
			if (this.pokemon[i]==null) {
				while (j<3 && this.pokemon[j]==null) j++;
				if (j<3) {
					this.pokemon[i] = this.pokemon[j];
					this.pokemon[j] = null;
				}
			}
			i += 1;
			j += 1;
		}
	}

	/**
	 * hasActivePokemon
	 * 
	 * Returns true if this player has any active Pokemon left, false otherwise.
	 * A downed Pokemon is not active.
	 * @return boolean indicating whether any active Pokemon exist.
	 */
	public boolean hasActivePokemon() {
		for (int i=0; i<3; i++) {
			if (this.pokemon[i].getHealth()>0) return true;
		}
		return false;
	}
	
	/**
	 * showSwapPokemon
	 * 
	 * Shows the Pokemon the current user can switch to
	 * Highlights the current Pokemon, and excludes any downed Pokemon 
	 */
	public void showSwapPokemon() {
		for (int i=0; i<3; i++) {
			if (!pokemon[i].isDown()) {
				if (i == this.currentPokemon)
					System.out.println("[" + (i+1) + "]: " + pokemon[i]);
				else
					System.out.println("" + (i+1) + ": " + pokemon[i]);
			}
		}
	}
}
