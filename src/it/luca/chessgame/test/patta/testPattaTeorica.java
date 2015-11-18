package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class testPattaTeorica {
	private char[][] pezzi = {
			{ 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
	};
	private Configuration c = new ArrayConfiguration(pezzi);
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testPattaPerRegola50Mosse(){			
		// R vs R
		assertTrue(new Mover(new TilesModel(c), true).patta());
		
		c.set(4, 4, new Alfiere(white));
		
		 // R vs R + A
		assertTrue(new Mover(new TilesModel(c), true).patta());
		
		c.set(5, 3, new Alfiere(black));
		
		// R + Acc vs R + Acc
		assertTrue(new Mover(new TilesModel(c), true).patta());

		c.set(1, 1, new Pedone(white));
		
		assertTrue(!new Mover(new TilesModel(c), true).patta());
		
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		c.set(7, 2, new Re(white));
		
		c.set(0, 0, new Re(black));
		
		c.set(4, 3, new Alfiere(white));
		
		c.set(6, 3, new Alfiere(black));
		
		 // R + Acs vs R + Acs	
		assertTrue(new Mover(new TilesModel(c), true).patta());
		
		c.set(6, 3, new CasellaVuota());
		
		c.set(5, 3, new Alfiere(black));
		
		assertTrue(!new Mover(new TilesModel(c), true).patta());
		
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		c.set(7, 2, new Re(white));
		
		c.set(0, 0, new Re(black));
		
		c.set(2, 3, new Cavallo(black));
		
		 // R vs R + C		
		assertTrue(new Mover(new TilesModel(c), true).patta());
	}
}
