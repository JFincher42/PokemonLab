package pokemon;

public class Pokemon extends PokemonType {

	protected double critChance;
	public static final String POKEMON[][] = { {"Bulbasaur",  "Bellsprout", "Oddish"},		// Grass types
											   {"Charmander", "Ninetails",  "Ponyta"},		// Fire types
											   {"Squirtle",   "Psyduck",    "Polywag"}};	// Water types


	public Pokemon() {
		super();
		this.critChance = 0.0;
	}

	public Pokemon(String name, String type) {
		super(name, type, 0, 0, 0, 0);
		this.critChance = (Math.random()*5)/100;		// Reasonably small, < 5% chance
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
