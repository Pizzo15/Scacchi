package it.luca.chessgame.test;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import it.luca.chessgame.model.ArrayConfiguration;
import it.luca.chessgame.model.CasellaVuota;
import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.Pedone;
import it.luca.chessgame.model.Re;
import it.luca.chessgame.model.Regina;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

public class TestRegina {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void movimentoRegina(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(4, 4, new Regina(white));
		c.set(4, 5, new Pedone(white));
		c.set(5, 5, new Pedone(black));
		c.set(2, 0, new Re(white));
		
		mover = new Mover(new TilesModel(c));
		
		// posso spostarmi di quante caselle voglio lungo la 
		// perpendicolare...
		assertTrue(mover.isMoveLegal(4, 4, 4, 1));
		assertTrue(mover.isMoveLegal(4, 4, 0, 4));
		
		// ... e la diagonale
		assertTrue(mover.isMoveLegal(4, 4, 0, 0));
		assertTrue(mover.isMoveLegal(4, 4, 1, 7));
		
		// pur di non saltare pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 4, 7));
		assertTrue(!mover.isMoveLegal(4, 4, 7, 7));
	}	
}
