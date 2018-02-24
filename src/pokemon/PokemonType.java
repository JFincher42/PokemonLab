package pokemon;

/**
 * PokemonType
 * Implements a PokemonType, extending the PokemonBase object
 * @author jon
 *
 */
public class PokemonType extends PokemonBase {

	// Inheritable fields
	protected String type;			// What type of Pokemon?

	// Static field holding valid Pokemon Types
	public static final String POKEMONTYPES[] = {"Grass", "Fire", "Water"};

	/**
	 * PokemonType
	 * Constructs a PokemonType by throwing defaults at the more complete constructor
	 */
	public PokemonType() {
		this("", "Grass", 0,0,0,0);
	}

	/**
	 * PokemonType
	 * Constructs a PokemonType object
	 * Calls the super constructor first
	 *  
	 * @param name     Name of the Pokemon
	 * @param type     Type of the Pokemon (should be listed in POKEMONTYPES) 
	 * @param health   How much health do we start with?
	 * @param attack   How strong is our attack?
	 * @param defense  How strong is our defense?
	 * @param speed    How fast are we?
	 */
	public PokemonType(String name, String type, int health, int attack, int defense, int speed) {
		super(name, health, attack, defense, speed);
		this.type = type;
	}

	// Accessors
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return this.name + ", " + this.type + " type, (" + this.attack + "/" + this.defense + "), H:" + this.health;
	}

	// Public methods
	/**
	 * advantage
	 * Calculates our advantage over an enemy Pokemon.  This is used during attacks
	 * as a multiplier of damage done.
	 * 
	 * @param defender The enemy we are attacking
	 * @return         Multiplier representing our advantage
	 */
	public double advantage(PokemonType defender) {
		// What type are we? Return the appropriate multiplier
		switch (this.type) {
		case "Grass":
			if (defender.getType().equals("Fire")) 	return 0.5;
			if (defender.getType().equals("Water")) return 2.0;
			break;
		case "Fire":
			if (defender.getType().equals("Water")) return 0.5;
			if (defender.getType().equals("Grass")) return 2.0;
			break;			
		case "Water":
			if (defender.getType().equals("Grass")) return 0.5;
			if (defender.getType().equals("Fire"))  return 2.0;
			break;
		}
		// We're attacking the same type, so ou advantage is 1.
		return 1.0;
	}

	/**
	 * attack()
	 * Overrides the PokemonBase attack method
	 * Takes our advantage over other PokemonTypes into account
	 * 
	 * @param defender The enemy weare attacking (as a PokemonBase object)
	 */
	public int attack(PokemonBase defender) {
		// We do a normal attack against the defender by calling super.attack()
		// Then we multiply that by our advantage, cast to an int, and return
		return (int)(super.attack(defender) * this.advantage((PokemonType)defender));
	}
}