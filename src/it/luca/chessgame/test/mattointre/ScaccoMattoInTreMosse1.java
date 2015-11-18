package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoMattoInTreMosse1 {
	private char[][] pezzi = {
			{ 'T', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', 'A', ' ', 'T' }, 
			{ ' ', ' ', ' ', ' ', ' ', 'r', ' ', ' ' }, 
			{ ' ', 't', ' ', ' ', ' ', 'c', ' ', 'R' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoMattoInTreMosse1(){	
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(5, 7, 6, 5);

		mover.move(7, 7, 7, 6);

		mover.move(1, 7, 7, 7);

		mover.move(5, 5, 7, 7);

		mover.move(6, 5, 5, 7);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
