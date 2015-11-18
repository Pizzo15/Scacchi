package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Lo scacco perpetuo è un tema tattico difensivo con cui si impone la patta per ripetizioni di mosse, 
 * dando scacco al Re senza che questo possa avere la possibilità di sottrarsi: non c’è matto, ma il re
 * in fuga non può evitare la ripetizione delle mosse.
 */
public class testScaccoPerpetuo {
	private char[][] pezzi = {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'T' }, 
			{ 'P', ' ', ' ', ' ', ' ', ' ', 'P', ' ' }, 
			{ ' ', ' ', 'P', ' ', 'P', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', 'R', ' ', ' ', ' ', ' ' }, 
			{ 'c', 'a', 'c', ' ', ' ', 'A', 'p', ' ' }, 
			{ ' ', 'p', 'p', ' ', ' ', 'p', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'r', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testPerpetuo(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);
	
		mover.move(0, 5, 1, 3);
		mover.move(0, 2, 1, 3);
		
		mover.move(2, 5, 1, 3);
		mover.move(3, 4, 4, 4);
		
		mover.move(1, 3, 3, 2);
		mover.move(4, 4, 3, 4);
		
		mover.move(3, 2, 1, 3);
		mover.move(3, 4, 4, 4);
		
		mover.move(1, 3, 3, 2);
		mover.move(4, 4, 3, 4);
		
		mover.move(3, 2, 1, 3);
		
		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.patta());
	}
}
