package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto di Greco:
 * la torre si unisce all'alfiere che inibisce una casa di fuga.
 */
public class ScaccoDiGreco {
	private char[][] pezzi = {
			{ 'T', ' ', ' ', 'D', ' ', 'T', 'R', ' ' }, 
			{ 'P', 'A', 'P', ' ', ' ', ' ', 'P', 'P' }, 
			{ ' ', 'P', ' ', 'A', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', 'c', ' ', 'P', ' ', ' ' }, 
			{ ' ', ' ', 'a', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', ' ', ' ', ' ', 'p', 'p', 'C', ' ' }, 
			{ ' ', 'p', ' ', ' ', ' ', 'p', ' ', 'p' }, 
			{ ' ', ' ', 't', ' ', 'r', ' ', ' ', 't' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiGreco(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);

		mover.move(3, 3, 4, 1);

		mover.move(6, 0, 7, 0);

		mover.move(4, 1, 6, 2);

		mover.move(7, 1, 6, 2);

		mover.move(7, 6, 6, 5);

		mover.move(3, 0, 7, 4);

		mover.move(7, 7, 7, 4);

		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.scaccoMatto());
	}
}
