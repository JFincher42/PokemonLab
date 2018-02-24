package pokemon;

public class PokemonBase {
	protected String name;			// The name of the Pokemon
	protected int health;			// Current health
	private int initialHealth;		// Where did the Pokemon start life?
	private boolean healed;			// Have we healed this battle yet?
	protected int attack;
	protected int defense;
	protected int speed;			// OPTIONAL: used for figuring out who goes first
	
	public PokemonBase() {
		this("",0,0,0,0);
	}
	
	public PokemonBase(String name, int health, int attack, int defense, int speed) {
		this.name = name;
		this.health = health;
		this.initialHealth = health;
		this.healed = false;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}
	
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
	
	public String toString() {
		return this.name + " (" + this.attack + "/" + this.defense + "), H:" + this.health;
	}
	
	public int attack(PokemonBase enemy) {
		int damageAmt = (int)(Math.random()*this.attack) + 1;
		damageAmt -= (int)(Math.random()*enemy.getDefense());
		if (damageAmt<=0) damageAmt = 0;
		return damageAmt;
		//System.out.println(this.name + " attacks " + enemy.getName() + " for " + damageAmt + " damage!");
		//enemy.damage(damageAmt);
	}
	
	public void damage(int damageAmt) {
		this.health -= damageAmt;
		if (this.health <= 0) {
			this.health = 0;
			//System.out.println(this.name + " is down!");
		}
	}
	
	public int heal() {
		if (this.healed) {
			return 0;
		}
		int healedAmt = this.initialHealth - this.health;
		if (healedAmt > 20)
			this.health += 20;
		else
			this.health = this.initialHealth;
		//System.out.println(this.name + " has healed " + healed + " points.");
		//System.out.println(this.name + " now has " + this.health + " health.");
		this.healed = true;
		return healedAmt;
	}

}
