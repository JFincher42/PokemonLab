package battleGround;

import player.Player;
import pokemon.Pokemon;
import pokemon.PokemonType;

import java.util.Scanner;

/**
 * BattleGround
 * 
 * Manages a Pokemon battle between two players
 * @author jon
 *
 */
public class BattleGround {

	// Inheritable fields
	protected Player[] players;				// Who is playing?
	
	// Private fields
	private int currentPlayer = 0;			// Which is the current player?
	private int opponent = 1;				// Who is the opponent?
	private Scanner console;				// Scanner used for all internal input
	
	private Pokemon[] battlePokemon = new Pokemon[9];	// Pokemon available to be selected for this battle
	
	/**
	 * BattleGround
	 * 
	 * Default constructor:
	 * - creates two new players
	 * - gets player details
	 * - initializes the list of Pokemon available for battle, and
	 * - doles out Pokemon to each player
	 */
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
	
	/**
	 * finalize
	 * 
	 * When we get cleaned up, close the console
	 */
	public void finalize() {
		console.close();
	}
	
	/**
	 * getPlayers
	 * 
	 * Gets details about each player
	 * We assume we have at least one human player, and possibly one computer player
	 * Once we get player details, we can dole out the Pokemon
	 */
	public void getPlayers() {
		
		// We assume there is one human player
		System.out.print("Player 1, what is your name: ");
		String name = console.nextLine();
		if (name.length()==0) name= "Player 1";
		this.players[this.currentPlayer] = new Player(name, true);
		
		// Do we play the PC or another human?
		System.out.print("\nPlayer 2, what is your name (enter for a computer opponent): ");
		name = console.nextLine();
		if (name.length()==0)
			this.players[this.opponent] = new Player();
		else
			this.players[this.currentPlayer] = new Player(name, true);
		
		// Now select the Pokemon
		this.selectPokemon();
		
	}

	/**
	 * setNextPlayer
	 * 
	 * Changes the active player, and their opponent, to the next player
	 */
	public void setNextPlayer() {
		// Since we only have two players, there are only two choices - 0 and 1
		// So we just increment currentPlayer, then mod it by 2 to toggle it.
		// Same with the opponent
		this.currentPlayer = (this.currentPlayer+1)%2;
		this.opponent = (this.opponent+1)%2;
	}
	
	/**
	 * getCurrentPlayer
	 * 
	 * Who is the current player?
	 * @return Player object representing the current player
	 */
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayer];
	}
	
	/**
	 * getOpponent
	 * 
	 * Who is the current opponent?
	 * @return Player object representing the opponent player
	 */
	public Player getOpponent() {
		return this.players[this.opponent];
	}
	
	/**
	 * fight
	 * 
	 * The main fight method.  Implements a game loop, which:
	 * - presents the current state of the game
	 * - handles a move by the current player
	 * - if the move ends the turn, end the players turn
	 * - check to see if the current player has won
	 * 
	 * Human player moves include:
	 * - attack: attacks the opponent's Pokemon
	 * 			 If this downs that Pokemon, ask the opponent to swap
	 * - heal:   heals up to 20 points for the current Pokemon
	 *           no effect if the current Pokemon has healed already
	 * - swap:   change to another Pokemon
	 * - stats:  show all the Pokemon the player has
	 *           doesn't end the turn
	 * 
	 * Computer player moves are handled randomly:
	 * - attack: 70% of the time
	 * 			 If this downs the human Pokemon, ask them to swap
	 * - swap:   15% of the time
	 * - heal:   15% of the time
	 * 
	 * End conditions are whether one player has no active Pokemon left
	 * Since the current player will never be in that situation, always check the opponent
	 */
	public void fight() {
		
		// Game loop
		boolean ended = false;
		while (!ended) {
			
			// Turn processing
			boolean endOfTurn = false;
			// If the current player is human, get their input
			if (this.getCurrentPlayer().isHuman()) {

				System.out.println(this.getCurrentPlayer().getName() + ", what do you want to do?");
				
				while (!endOfTurn) {
					System.out.println("You can 'attack', 'heal', 'swap', or print 'stats': ");
					String command = console.next().toLowerCase();
					
					// ATTACK
					if (command.equals("attack")) {
						int attackAmt = this.getCurrentPlayer().getPokemon().attack(this.getOpponent().getPokemon());
						this.getOpponent().getPokemon().damage(attackAmt);
						printAttack(attackAmt);
						endOfTurn = true;
					
					// HEAL
					} else if (command.equals("heal")) {
						int healAmt = this.getCurrentPlayer().getPokemon().heal();
						endOfTurn = (healAmt!=0);
						printHeal(healAmt);
					
					// SWAP
					} else if (command.equals("swap")) {
						System.out.println("Select from the following list: ");
						this.getCurrentPlayer().showSwapPokemon();
						System.out.println("Which one? ");
						this.getCurrentPlayer().selectPokemon(console.nextInt());
						System.out.println("Current Pokemon is now: " + this.getCurrentPlayer().getPokemon());
						endOfTurn = true;
					
					// STATS
					} else if (command.equals("stats")) {
						System.out.println(this.getCurrentPlayer());
						
					// INVALID COMMAND
					} else {
						System.out.println("Invalid command: " + command);
					}
				}
				
			// COMPUTER PLAYER
			} else {
				// Keep going until it's the end of our turn
				while (!endOfTurn) {
					// Generate a random number and use it to figure out what to do
					double randomAction = Math.random();
					
					// ATTACK - 70% chance
					if (randomAction < 0.7) {
						int attackAmt = this.getCurrentPlayer().getPokemon().attack(this.getOpponent().getPokemon());
						this.getOpponent().getPokemon().damage(attackAmt);
						printAttack(attackAmt);
						endOfTurn = true;
						
					// HEAL - 15% chance
					} else if (randomAction < 0.85) {
						int healAmt = this.getCurrentPlayer().getPokemon().heal();
						endOfTurn = (healAmt != 0);
						printHeal(healAmt);
						
					// SWAP - 15% chance
					} else { 
						// TODO: Implement computer swap
					}
				}
			}
			
			
			// Check for an ending
			// Since there's no way to down a Pokemon by the current player
			// we just need to check the opponent's active Pokemon
			ended = (!this.getOpponent().hasActivePokemon());
			if (!ended)	this.setNextPlayer();
		}
		
		// If we're here, it's because the opponent has no more active Pokemon, so we can print victory for the current player
		System.out.println(this.getCurrentPlayer().getName() + " has won!");
	}
	
	// PRIVATE METHODS - PRINTING TURN INFO
	/**
	 * printAttack
	 * 
	 * Prints out details of the current attack
	 * @param attackAmt How much damage did the current player do to the opponent? 
	 */
	private void printAttack(int attackAmt) {
		Pokemon attacker = this.getCurrentPlayer().getPokemon();
		Pokemon defender = this.getOpponent().getPokemon();
		System.out.println(this.getCurrentPlayer().getName() + "'s " + attacker.getName() + " attacks " + this.getOpponent().getName() + "'s " + defender.getName() + ", doing " + attackAmt + " damage.");
		if (defender.isDown())
			System.out.println(defender.getName() + " is down!");
	}
	
	/**
	 * printHeal
	 * 
	 * Prints details of a player heal
	 * @param healed How many health points did the current player heal? 
	 */
	private void printHeal(int healed) {
		Pokemon attacker = this.getCurrentPlayer().getPokemon();
		if (healed!=0)
			System.out.println(attacker.getName() + " has healed " + healed + " health points.");
		else
			System.out.println(attacker.getName() + " didn''t heal anything.");

	}

	/**
	 * selectPokemon
	 * 
	 * Implements the Pokemon selection process prior to the battle
	 * There are three rounds:
	 * - starting with Player 1, show the available Pokemon
	 * - Ask them to pick
	 * - add their selected Pokemon to their list
	 * - repeat for player 2
	 * 
	 * If player 2 is the computer, make a random selection
	 */
	private void selectPokemon() {
		int round = 0;					// Which round is it?
		int remaining = 9;				// How many Pokemon are remaining?
		
		// We're selecting six Pokemon
		while (round < 6) {
			
			// Is the current player human?
			Player current = getCurrentPlayer();
			
			// HUMAN
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
				
			// COMPUTER
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
	}

	/**
	 * removePokemon
	 * 
	 * Removes a Pokemon from the list of battle ready Pokemon
	 * Consolidate the list as well, moving all active Pokemon to left in the list
	 * @param selection Index of the selected Pokemon in the list 
	 */
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
	
	/**
	 * showRemainingPokemon
	 * 
	 * Shows the current list of remaining Pokemon
	 */
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
