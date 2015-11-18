package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoDiArabo {
	private char[][] pezzi = {
			{ ' ', 'R', 'A', 'T', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 't', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'P', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
			{ ' ', 'c', ' ', ' ', ' ', 'A', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'c', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'r', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDiArabo(){	
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(1, 4, 2, 2);

		mover.move(1, 0, 0, 0);

		mover.move(6, 1, 0, 1);

		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.scaccoMatto());
	}
}
