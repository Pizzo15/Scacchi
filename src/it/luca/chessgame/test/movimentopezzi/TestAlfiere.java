package it.luca.chessgame.test.movimentopezzi;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class TestAlfiere {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private Mover mover;
		
	@Test
	public void movimentoAlfiere(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(4, 4, new Alfiere(white));
		c.set(2, 6, new Pedone(white));
		
		mover = new Mover(new TilesModel(c), true);
		
		// posso muovermi di quante caselle voglio 
		// lungo le diagonali
		assertTrue(mover.isMoveLegal(4, 4, 2, 2));
		assertTrue(mover.isMoveLegal(4, 4, 7, 7));
		
		// ma non posso saltare pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 1, 7));
	}
	
}
