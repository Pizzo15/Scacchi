package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class testRegola50Mosse {
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
	public void testPattaPerRegola50Mosse(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
		
		mover.move(3, 7, 4, 7);
		mover.move(0, 0, 0, 1);
		mover.move(4, 7, 3, 7);		
		mover.move(0, 1, 0, 0);
		
		// Ho effettuato 4 mosse che non prevedono catture o coinvolgono pedoni
		assertTrue(mover.getMosse() == 5);
		
		mover.move(3, 5, 3, 4);
		
		// Ho mosso un pedone: azzero contatore
		assertTrue(mover.getMosse() == 1);	
		
		mover.move(2, 3, 3, 4);

		// Effettuo una cattura: non incremento il contatore
		assertTrue(mover.getMosse() == 1);		
		
		mover.move(3, 7, 3, 4);
		
		// Effettuo una cattura(non utilizzando un pedone): 
		// non incremento il contatore.
		assertTrue(mover.getMosse() == 1);
	}
}
