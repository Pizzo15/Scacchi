package it.luca.chessgame.test.movimentopezzi;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class TestTorre {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;

	@Test
	public void movimentoTorre(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(4, 4, new Torre(white));
		c.set(3, 4, new Pedone(white));
		c.set(3, 3, new Torre(black));
		c.set(4, 3, new Alfiere(white));
	
		mover = new Mover(new TilesModel(c), true);
		
		// posso muovermi di quante caselle voglio lungo
		// la perpendicolare
		assertTrue(mover.isMoveLegal(4, 4, 4, 7));
		assertTrue(mover.isMoveLegal(4, 4, 7, 4));
		
		// ma non saltare altri pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 0, 4));
		assertTrue(!mover.isMoveLegal(4, 4, 4, 0));
	}

}
