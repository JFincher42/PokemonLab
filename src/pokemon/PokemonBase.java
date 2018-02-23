package pokemon;

public class PokemonBase {
	protected String name;			// The name of the Pokemon
	protected int health;			// Current health
	protected int attack;
	protected int defense;
	protected int speed;			// OPTIONAL: used for figuring out who goes first
	
	public PokemonBase() {
	}
	
	public PokemonBase(String name, int health, int attack, int defense, int speed) {
	}
	
	public String getName() {
		// Add implementation here
	}
	
	public int getHealth() {
		// Add implementation here
	}
	
	public boolean isDown() {
		// Add implementation here
	}
	
	public boolean hasHealed() {
		// Add implementation here
	}
	
	public int getAttack() {
		// Add implementation here
	}
	
	public int getDefense() {
		// Add implementation here
	}
	
	public int getSpeed() {
		// Add implementation here
	}
	
	public void setName(String name) {
		// Add implementation here
	}
	
	public void setHealth(int health) {
		// Add implementation here
	}
	
	public void setAttack(int attack) {
		// Add implementation here
	}
	
	public void setDefense(int defense) {
		// Add implementation here
	}
	
	public void setSpeed(int speed) {
		// Add implementation here
	}
	
	public String toString() {
		// Add implementation here
	}
	
	public int attack(PokemonBase enemy) {
		// Add implementation here
	}
	
	public void damage(int damageAmt) {
		// Add implementation here
	}
	
	public int heal() {
		// Add implementation here
		// Think about how to track the initial Pokemon health
		// and how to track whether we've healed this Pokemon or not
	}

}
