package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoDiBoden {
	private char[][] pezzi = {
			{ ' ', ' ', 'R', 'T', ' ', ' ', ' ', 'T' }, 
			{ ' ', 'P', ' ', 'C', ' ', 'P', 'A', ' ' }, 
			{ 'P', ' ', 'P', ' ', ' ', ' ', 'P', 'P' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'D' }, 
			{ ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', 'p', ' ', ' ', 'd', ' ', 'p' }, 
			{ 'p', ' ', ' ', ' ', 'a', 'p', 'p', 'a' }, 
			{ ' ', ' ', 't', ' ', ' ', ' ', 'r', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiBoden(){	
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(5, 5, 2, 2);

		mover.move(1, 1, 2, 2);

		mover.move(4, 6, 0, 2);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
