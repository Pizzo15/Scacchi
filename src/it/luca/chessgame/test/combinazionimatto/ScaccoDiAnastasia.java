package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di Test per la simulazione della combinazione di matto 
 * di Anastasia: un cavallo controlla le due case di fuga del re 
 * attaccato con uno scacco di torre.
 */
public class ScaccoDiAnastasia {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', 'R', ' ', ' ' }, 
			{ 'P', ' ', ' ', ' ', 'c', 'P', 'P', ' ' }, 
			{ ' ', ' ', ' ', ' ', 'P', ' ', ' ', 'P' }, 
			{ ' ', ' ', 'd', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', ' ', ' ', ' ', 'p', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'p', 'A' }, 
			{ ' ', 'T', ' ', 'D', ' ', 'p', ' ', 'p' }, 
			{ ' ', ' ', 't', ' ', ' ', ' ', 'r', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiAnastasia(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);

		mover.move(4, 1, 5, 3);

		// il re può scappare: non è scacco matto
		assertTrue(!mover.scaccoMatto());
		
		mover.move(5, 0, 6, 0);

		mover.move(2, 3, 5, 0);

		// il pezzo che tiene lo scacco può essere mangiato: non è scacco matto
		assertTrue(!mover.scaccoMatto());
		
		mover.move(6, 0, 5, 0);
		
		mover.move(2, 7, 2, 0);
		
		// la regina può mettersi tra la torre e il re: non è scacco matto
		assertTrue(!mover.scaccoMatto());
		
		mover.move(3, 6, 3, 0);

		mover.move(2, 0, 3, 0);

		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.scaccoMatto());
	}
}
