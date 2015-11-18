package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class testStallo {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R' }, 
			{ ' ', ' ', ' ', ' ', ' ', 'r', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
			{ ' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', 'p', ' ', ' ', ' ', ' ', 'p' }, 
			{ ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testPattaPerStallo(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), false);
	
		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.patta());
	}
}
