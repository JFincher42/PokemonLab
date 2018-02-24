package pokemon;

/**
 * PokemonBase
 * 
 * Implements the basic Pokemon characteristics.
 * Used as a super class for later classes
 *   
 */

public class PokemonBase {
	// Inheritable fields
	protected String name;			// The name of the Pokemon
	protected int health;			// Current health
	protected int attack;			// Attack Strength
	protected int defense;			// Defense strength (not used)
	protected int speed;			// OPTIONAL: used for figuring out who goes first

	// Private fields
	private int initialHealth;		// Where did the Pokemon start life?
	private boolean healed;			// Have we healed during this battle yet?

	// Constructors
	/**
	 * PokemonBase
	 * Constructs a PokemonBase object
	 * 
	 * @param name     The name of the Pokemon
	 * @param health   How much health do we start with?
	 * @param attack   How strong is it's attack?
	 * @param defense  How strong is it's defense?
	 * @param speed    How fast is it?
	 */
	public PokemonBase(String name, int health, int attack, int defense, int speed) {
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;

		this.initialHealth = health;		// So we know how far we can heal
		this.healed = false;				// If we've already healed, we can't do it again
	}

	/**
	 * PokemonBase
	 * Constructs a PokemonBase object by throwing some defaults at the more complete constructor
	 */
	public PokemonBase() {
		this("", 0, 0, 0, 0);
	}


	
	// Accessors
	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public boolean isDown() {
		return this.health==0;
	}

	public boolean hasHealed() {
		return this.healed;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// What does our Pokemon look like to the outside world?
	public String toString() {
		return this.name + " (" + this.attack + "/" + this.defense + "), H:" + this.health;
	}

	
	// Public methods
	
	/**
	 * attack()
	 * Implements an attack on another PokemonBase object.
	 * Calculates a random value between 1 and this objects attack field
	 * Subtracts the defense of the provided enemy
	 * 
	 * @param  enemy A PokemonBase object representing the enemy we are attacking
	 * @return 		 An integer representing the strength of our attack   
	 * 
	 */
	public int attack(PokemonBase enemy) {
		// Calculate the proper damage amount
		int damageAmt = (int)(Math.random()*this.attack) + 1;
		damageAmt -= (int)(Math.random()*enemy.getDefense());
		
		// We can't do less than 0 damage
		if (damageAmt<=0)
			damageAmt = 0;
		
		return damageAmt;
	}

	/**
	 * damage()
	 * Figures out damage done to this object
	 * Subtracts the amount of damage from our health
	 * If healthdrops to 0 or less, we're down
	 * 
	 * @param damageAmt How much damage was done
	 */
	public void damage(int damageAmt) {
		this.health -= damageAmt;
		if (this.health <= 0) {
			this.health = 0;
		}
	}

	/**
	 * heal()
	 * Heals up to 20 health points.
	 * We can't heal more than we originally had, and
	 * we can't heal more than once per battle
	 * 
	 * @return How much did we heal?
	 */
	public int heal() {
		// Have we already healed once?  Don't do it again
		if (this.healed)
			return 0;
		
		// How much can we heal?  Figure out where we are against where we started.
		// Pick the lowest of that difference and 20, and add it to our health
		int healedAmt = this.initialHealth - this.health;
		if (healedAmt > 20)
			healedAmt = 20;
		this.health += healedAmt;

		// We've healed, so set that to true
		this.healed = true;
		return healedAmt;
	}

}
