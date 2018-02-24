package pokemon;

public class PokemonType extends PokemonBase {

	protected String type;			// What type of Pokemon?
									// Valid types are "Grass", "Fire", and "Water"
	public static final String POKEMONTYPES[] = {"Grass", "Fire", "Water"};

	public PokemonType() {
		this("", "Grass", 0,0,0,0);
	}

	public PokemonType(String name, String type, int health, int attack, int defense, int speed) {
		super(name, health, attack, defense, speed);
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return this.name + ", " + this.type + " type, (" + this.attack + "/" + this.defense + "), H:" + this.health;
	}

	public double advantage(PokemonType defender) {
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
		return 1.0;
	}

	public int attack(PokemonBase defender) {
		return (int)(super.attack(defender) * this.advantage((PokemonType)defender));
	}
}