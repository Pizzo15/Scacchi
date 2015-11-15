package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class testStallo1 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testPattaPerStallo1(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(3, 2, new Pedone(white));
		c.set(1, 2, new Re(white));
		c.set(2, 0, new Torre(white));
		
		// aggiungo i pezzi neri
		c.set(0, 0, new Re(black));
		c.set(1, 0, new Alfiere(black));
			
		new Simulation(c, "Patta per stallo");
		
		assertTrue(new Mover(new TilesModel(c), false).patta());
	}
}
