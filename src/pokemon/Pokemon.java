package pokemon;

public class Pokemon extends PokemonType {

	protected double critChance;
	
	public Pokemon() {
		super();
		this.critChance = 0.0;
	}

	public Pokemon(String name, String type, double critChance, int health, int attack, int defense, int speed) {
		super(name, type, health, attack, defense, speed);
		this.critChance = critChance;
	}
	
	public double getCritChance() {
		return this.critChance;
	}
	
	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}
	
	public String toString() {
		return super.toString();
	}
	
	public boolean criticalHit() {
		return (Math.random() <= this.critChance);
	}

}
