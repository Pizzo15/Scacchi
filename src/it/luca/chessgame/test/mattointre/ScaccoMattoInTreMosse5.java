package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoMattoInTreMosse5 {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', 'c', ' ', ' ', ' ' }, 
			{ 'R', 'C', ' ', 'c', ' ', ' ', 'D', ' ' }, 
			{ ' ', 't', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', 'd', ' ', ' ', ' ', 'T', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoMattoInTreMosse5(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(1, 4, 0, 4);
		
		mover.move(0, 3, 0, 4);
		
		mover.move(4, 2, 2, 3);
		
		mover.move(0, 4, 0, 3);

		mover.move(1, 6, 1, 4);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
