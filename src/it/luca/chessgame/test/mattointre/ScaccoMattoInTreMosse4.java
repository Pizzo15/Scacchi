package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoMattoInTreMosse4 {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 't', ' ' }, 
			{ ' ', 'P', 'C', 'R', 'P', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', 'p', ' ', 'p', 'D', ' ', ' ', ' ' }, 
			{ 'r', 'p', 'd', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoMattoInTreMosse4(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(2, 5, 2, 3);

		mover.move(1, 2, 2, 3);

		mover.move(1, 4, 2, 3);

		mover.move(3, 2, 3, 3);

		mover.move(6, 1, 3, 1);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
