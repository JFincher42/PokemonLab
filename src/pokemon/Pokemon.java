package pokemon;

/**
 * Pokemon
 * Implements a Pokemon for our game
 * Extends PokemonType, which extends PokemonBase
 * 
 * @author jon
 *
 */
public class Pokemon extends PokemonType {

	// Inheritable fields
	protected double critChance;			// What is our chance for a critical hit?
	
	// Static fields
	public static final String POKEMON[][] = { {"Bulbasaur",  "Bellsprout", "Oddish"},		// Grass types
											   {"Charmander", "Ninetails",  "Ponyta"},		// Fire types
											   {"Squirtle",   "Psyduck",    "Polywag"}};	// Water types

	/**
	 * Pokemon()
	 * Constructs a Pokemon by calling the default super constructor, then setting the critical hit chance
	 */
	public Pokemon() {
		super();
		this.critChance = 0.0;
	}

	/**
	 * Pokemon
	 * Constructs a Pokemon more completely.
	 * Certain Pokemon have default health, attack, defense, and speed ratings.
	 * Also sets a reasonably small critical hit chance
	 * @param name The name of our Pokemon
	 * @param type The type of our Pokemon
	 */
	public Pokemon(String name, String type) {
		super(name, type, 0, 0, 0, 0);
		this.critChance = (Math.random())/100;		// Reasonably small, < 1% chance
		// Generate default settings for each type of Pokemon we support
		switch (name) {
			case "Bulbasaur":  this.health = 60;  this.attack = 40;  this.defense = 10;  this.speed = 10;  break;
			case "Bellsprout": this.health = 40;  this.attack = 60;  this.defense = 10;  this.speed = 10;  break;
			case "Oddish":	   this.health = 50;  this.attack = 50;  this.defense = 10;  this.speed = 10;  break;
			case "Charmander": this.health = 25;  this.attack = 70;  this.defense = 10;  this.speed = 10;  break;
			case "Ninetails":  this.health = 30;  this.attack = 50;  this.defense = 10;  this.speed = 10;  break;
			case "Ponyta":	   this.health = 40;  this.attack = 60;  this.defense = 10;  this.speed = 10;  break;
			case "Squirtle":   this.health = 80;  this.attack = 20;  this.defense = 10;  this.speed = 10;  break;
			case "Psyduck":    this.health = 70;  this.attack = 40;  this.defense = 10;  this.speed = 10;  break;
			case "Polywag":    this.health = 50;  this.attack = 50;  this.defense = 10;  this.speed = 10;  break;
		}

	}
	
	/**
	 * Pokemon()
	 * Completely set everything about our Pokemon
	 * 
	 * @param name        The name of our Pokemon
	 * @param type        The type of our Pokemon
	 * @param critChance  The critical hit chance
	 * @param health      How much health do we start with?
	 * @param attack      How strong is our attack?
	 * @param defense     How strong is our defense?
	 * @param speed       How fast are we?
	 */
	public Pokemon(String name, String type, double critChance, int health, int attack, int defense, int speed) {
		super(name, type, health, attack, defense, speed);
		this.critChance = critChance;
	}

	// Accessors
	public double getCritChance() {
		return this.critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	// What do we look like to the outside world? 
	public String toString() {
		return super.toString();
	}

	/**
	 * criticalHit
	 * Did we score a critical hit?  If so, we should double our attack
	 * 
	 * @return true if we scored a critical hit, false otherwise
	 */
	public boolean criticalHit() {
		return (Math.random() <= this.critChance);
	}

}
