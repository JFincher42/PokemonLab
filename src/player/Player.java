package player;

import pokemon.Pokemon;

public class Player {
	
	protected String name;
	protected boolean human;
	protected Pokemon[] pokemon;
	private int currentPokemon;
	private int count;

	public Player() {
		this("Computer", false);
	}
	
	public Player(String name, boolean human) {
		this.name = name;
		this.human = human;
		this.pokemon = new Pokemon[3];
		this.currentPokemon = 0;
		this.count = 0;
	}
	
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
	
	public Pokemon getPokemon() {
		return this.pokemon[this.currentPokemon];
	}
	
	public String toString() {
		String output = "";
		if (this.isHuman()){
			output += "Player " + this.name;
		} else {
			output += "Computer";
		}
		output += " has the following Pokemon:\n";
		for (int i=0; i<3; i++) {
			output+="" + (i+1) + ": " + this.pokemon[i];
			if (this.pokemon[i].getHealth()==0) output += " (downed)";
			output += "\n";
		}
		return output;
	}
	
	public void selectPokemon(int currentPokemon) {
		this.currentPokemon = currentPokemon;
	}
	
	public void addPokemon(Pokemon pokemon) {
		if (this.count < 3) {
			this.pokemon[count] = pokemon;
			count += 1;
		}
	}
	
	public void removePokemon(int position) {
		this.pokemon[position] = null;
		this.consolidate();
		this.count -= 1;
	}
	
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

	// Does this player have any active Pokemon still? 
	public boolean hasActivePokemon() {
		for (int i=0; i<3; i++) {
			if (this.pokemon[i].getHealth()>0) return true;
		}
		return false;
	}
	
	public void showSwapPokemon() {
		for (int i=0; i<3; i++) {
			if (i != this.currentPokemon && !pokemon[i].isDown()) {
				System.out.println((i+1) + ": " + pokemon[i]);
			}
		}
	}
}
