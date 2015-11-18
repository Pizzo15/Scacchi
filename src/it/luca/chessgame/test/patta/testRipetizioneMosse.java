package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class testRipetizioneMosse {
	private char[][] pezzi = {
			{ 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
			{ ' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', 'p', ' ', ' ', ' ', ' ', 'p' }, 
			{ ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', 'd', ' ', ' ', ' ', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testPattaPerRipetizioneMosse(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		for(int i = 0; i < 2; i++){
			mover.move(3, 7, 4, 7);
			mover.move(0, 0, 0, 1);
			mover.move(4, 7, 3, 7);
			mover.move(0, 1, 0, 0);
		}
		
		assertTrue(mover.patta());
	}
}
