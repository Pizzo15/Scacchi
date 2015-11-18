package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoMattoInTreMosse3 {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', 'a', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', 'A', ' ', ' ', 'P' }, 
			{ ' ', ' ', ' ', 'c', ' ', 'P', ' ', 'P' }, 
			{ ' ', ' ', ' ', ' ', ' ', 'r', ' ', 'P' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoMattoInTreMosse3(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(3, 3, 0, 0);

		mover.move(4, 4, 0, 0);

		mover.move(5, 6, 5, 7);

		mover.move(0, 0, 4, 4);

		mover.move(3, 5, 5, 6);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
