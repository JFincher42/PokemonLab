package pokemon;

public class PokemonType extends PokemonBase {

	protected String type;					// What type of Pokemon?
											// Valid types are "Grass", "Fire", and "Water"

	public PokemonType() {
		// Add implementation here
	}

	public PokemonType(String name, String type, int health, int attack, int defense, int speed) {
		// Add implementation here
	}

	public String getType() {
		// Add implementation here
	}

	public void setType(String type) {
		// Add implementation here
	}

	public String toString() {
		// Add implementation here
	}

	public double advantage(PokemonType defender) {
		// Add implementation here
	}

	public int attack(PokemonBase defender) {
		// Add implementation here
		// Note the parameter type and handle it accordingly
	}
}