package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoMattoInTreMosse5 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoMattoInTreMosse5(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(1, 4, new Torre(white));
		c.set(7, 7, new Re(white));
		c.set(4, 2, new Cavallo(white));
		c.set(3, 3, new Cavallo(white));
		c.set(1, 6, new Regina(white));
		
		// aggiungo i pezzi neri
		c.set(0, 3, new Re(black));
		c.set(6, 3, new Regina(black));
		c.set(1, 3, new Cavallo(black));
		c.set(5, 6, new Torre(black));
		
		new Simulation(c, "Scacco matto in tre mosse (5)");
		
		new Simulation(c = c.swap(1, 4, 0, 4), "");
		
		new Simulation(c = c.swap(0, 3, 0, 4), "");
		
		new Simulation(c = c.swap(4, 2, 2, 3), "");
		
		new Simulation(c = c.swap(0, 4, 0, 3), "");
		
		new Simulation(c = c.swap(1, 6, 1, 4), "");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
