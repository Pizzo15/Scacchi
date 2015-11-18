package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoDiLucena {
	private char[][] pezzi = {
			{ ' ', 'R', ' ', ' ', 'T', ' ', 'T', ' ' }, 
			{ 'P', 'P', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', 'c', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'D', ' ' }, 
			{ ' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', 'p', ' ', ' ', 'd', ' ', ' ', ' ' }, 
			{ 't', ' ', ' ', ' ', 'r', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiLucena(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(4, 6, 4, 3);

		mover.move(1, 0, 0, 0);
		
		mover.move(4, 2, 2, 1);

		mover.move(0, 0, 1, 0);

		mover.move(2, 1, 4, 0);

		mover.move(1, 0, 0, 0);

		mover.move(4, 0, 2, 1);

		mover.move(0, 0, 1, 0);

		mover.move(2, 1, 0, 2);

		mover.move(1, 0, 0, 0);

		mover.move(4, 3, 1, 0);

		mover.move(6, 0, 1, 0);

		mover.move(0, 2, 2, 1);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
