package battleGround;

import player.Player;
import pokemon.Pokemon;
import pokemon.PokemonType;

import java.util.Scanner;

public class BattleGround {

	protected Player[] players;
	private int currentPlayer = 0;
	private int opponent = 1;
	private Scanner console;
	
	private Pokemon[] battlePokemon = new Pokemon[9];
	
	public BattleGround() {
		// We use a console everywhere, so just intialize it here
		console = new Scanner(System.in);
		
		// Initialize the players
		this.players = new Player[2];
		
		// Setup all the available Pokemon
		int counter = 0;
		for (int type=0; type<3; type++){
			for (int poke=0; poke<3; poke++) {
				battlePokemon[counter++] = new Pokemon(Pokemon.POKEMON[type][poke], PokemonType.POKEMONTYPES[type]);
			}
		}
		
		// Now figure out who is playing who
		this.getPlayers();
	}

	public void getPlayers() {
		// We assume there is one human player
		System.out.print("Player 1, what is your name: ");
		String name = console.nextLine();
		this.players[this.currentPlayer] = new Player(name, true);
		
		// Do we play the PC or another human?
		System.out.print("\nPlayer 2, what is your name (enter for PC): ");
		name = console.nextLine();
		if (name.length()==0)
			this.players[this.opponent] = new Player();
		else
			this.players[this.currentPlayer] = new Player(name, true);
		
		// Close the console
		//console.close();
		
		// And select the Pokemon
		this.selectPokemon();
		
	}

	// Cue up the next player, and change the opponent as well 
	public void setNextPlayer() {
		this.currentPlayer = (this.currentPlayer+1)%2;
		this.opponent = (this.opponent+1)%2;
	}
	
	// Who is the current player?
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayer];
	}
	
	// Who is the opponent?
	public Player getOpponent() {
		return this.players[this.opponent];
	}
	
	public void fight() {
		// The algorithm here is simple
		// Each player starts with their first Pokemon
		// We ask the current player for their command.
		// The following commands are acceptable:
		// - attack: attacks the opponent's Pokemon
		// - heal:   heals up to 20 points for the current Pokemone
		//           no effect if the current Pokemon has healed already
		// - swap:   change to another Pokemon
		// - stats:  show all the Pokemon the player has
		//           doesn't end the turn
		//
		// If the player is the computer, they should do the following:
		// - attack: 70% of the time
		// - swap:   15% of the time
		// - heal:   15% of the time
		//
		// Keep doing this until one player has no active Pokemon 
		
		boolean ended = false;
		while (!ended) {
			boolean endOfTurn = false;
			// If the current player is human, get their input
			if (this.getCurrentPlayer().isHuman()) {
				System.out.println(this.getCurrentPlayer().getName() + ", what do you want to do?");
				while (!endOfTurn) {
					System.out.println("You can 'attack', 'heal', 'swap', or print 'stats': ");
					String command = console.next().toLowerCase();
					if (command.equals("attack")) {
					} else if (command.equals("heal")) {
						
					} else if (command.equals("swap")) {
						
					} else if (command.equals("stats")) {
						System.out.println(this.getCurrentPlayer());
						
					} else {
						System.out.println("Invalid command: " + command);
					}
				}				
			} else {
				// Keep going until it's the end of our turn
				while (!endOfTurn) {
					// Generate a random number and use it to figure out what to do
					double randomAction = Math.random();
					if (randomAction < 0.7) {			// 70% chance to attack
						int attackAmt = this.getCurrentPlayer().getPokemon().attack(this.getOpponent().getPokemon());
						this.getOpponent().getPokemon().damage(attackAmt);
						endOfTurn = true;
					} else if (randomAction < 0.85) {	// 15% chance to heal
						int healAmt = this.getCurrentPlayer().getPokemon().heal();
						if (healAmt != 0) {
							endOfTurn = true;
						}
					} else { 							// Final 15% chance to swap
					}
				}
			}
			
			
			// Check for an ending
			ended = (!this.getCurrentPlayer().hasActivePokemon() || !this.getOpponent().hasActivePokemon());
			if (!ended)	this.setNextPlayer();
		}
		
		if (this.getCurrentPlayer().hasActivePokemon())
			System.out.println(this.getCurrentPlayer().getName() + " has won!");
		else
			System.out.println(this.getOpponent().getName() + " has won!");
		
	}
	
	// Pokemon selection process 
	private void selectPokemon() {
		// Three rounds of selections
		// Start with Player 1, then alternate until each has three Pokemon
		int round = 0;
		int remaining = 9;
		
		// We're selecting six Pokemon
		while (round < 6) {
			// Is the current player human?
			Player current = getCurrentPlayer();
			if (current.isHuman()) {
				// Get their selection
				int selection = 0;
				while (selection < 1 || selection > remaining) {
					System.out.println("\n" + current.getName() + ", please select a Pokemon [1-" + remaining + "]: ");
					showRemainingPokemon();
					selection = console.nextInt();
				}
				current.addPokemon(battlePokemon[selection-1]);
				System.out.println("You selected: " + battlePokemon[selection-1] + "\n");
				
				// Remove that Pokemon from the list and consolidate the remaining 
				removePokemon(selection-1);
			} else {
				// It's the computer, just pick a random one
				int selection = (int)(Math.random()*remaining);
				current.addPokemon(battlePokemon[selection]);
				System.out.println("The computer selected: " + battlePokemon[selection] + "\n");
				removePokemon(selection);
			}
			// Reduce the number remaining, increment the count, and change the current player
			remaining -= 1;
			round += 1;
			this.setNextPlayer();
		}
		//console.close();
	}
	
	private void removePokemon(int selection) {
		battlePokemon[selection] = null;
		// Consolidate the rest
		int current = selection, next = selection + 1;
		while (next < 9) {
			if (battlePokemon[current] == null) {
				while (next < 9 && battlePokemon[next]==null) next++;
				if (next<9) {
					battlePokemon[current] = battlePokemon[next];
					battlePokemon[next] = null;
				}
			}
			current += 1;
			next += 1;
		}
	}
	
	private void showRemainingPokemon() {
		int count = 0;
		System.out.println("REMAINING POKEMON");
		System.out.println("=================");
		while (count<9 && battlePokemon[count]!=null) {
			System.out.println((count+1) + ": " + battlePokemon[count]);
			count+=1;
		}
	}
	
}
