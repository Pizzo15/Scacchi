package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class ScaccoMattoInTreMosse2 {
	private char[][] pezzi = {
			{ 'D', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', ' ', ' ', ' ', 'C', ' ', ' ', ' ' }, 
			{ ' ', 'p', ' ', ' ', 'a', ' ', ' ', ' ' }, 
			{ 'r', ' ', ' ', 'P', 'd', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoMattoInTreMosse2(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(4, 4, 7, 1);

		mover.move(0, 1, 1, 2);

		mover.move(7, 1, 2, 1);

		mover.move(4, 2, 2, 1);

		mover.move(4, 3, 3, 4);

		mover.showSimulation(mover.getModel().getConfiguration());

		assertTrue(mover.scaccoMatto());
	}
}
