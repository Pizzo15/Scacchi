package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoDiDamiano {
	private char[][] pezzi = {
			{ 'R', 'T', ' ', ' ', ' ', ' ', ' ', 'T' }, 
			{ 'P', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', 'D', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', 't', ' ', ' ', ' ', 'd', ' ', ' ' }, 
			{ 'r', 't', ' ', ' ', ' ', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiDamiano(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);

		mover.move(5, 6, 0, 1);

		mover.move(0, 0, 0, 1);

		mover.move(1, 6, 0, 6);

		mover.move(4, 4, 0, 4);

		mover.move(0, 6, 0, 4);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
