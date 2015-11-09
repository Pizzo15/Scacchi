package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoMattoInTreMosse4 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoMattoInTreMosse4(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(6, 1, new Torre(white));
		c.set(0, 5, new Re(white));
		c.set(2, 5, new Regina(white));
		c.set(1, 4, new Pedone(white));
		c.set(1, 5, new Pedone(white));
		c.set(3, 4, new Pedone(white));
		
		// aggiungo i pezzi neri
		c.set(3, 2, new Re(black));
		c.set(4, 4, new Regina(black));
		c.set(2, 2, new Cavallo(black));
		c.set(1, 2, new Pedone(black));
		c.set(4, 2, new Pedone(black));
		
		new Simulation(c, "Scacco matto in tre mosse (4)");
		
		new Simulation(c = c.swap(2, 5, 2, 3), "");
		
		new Simulation(c = c.swap(1, 2, 2, 3), "");
		
		new Simulation(c = c.swap(1, 4, 2, 3), "");
		
		new Simulation(c = c.swap(3, 2, 3, 3), "");
		
		new Simulation(c = c.swap(6, 1, 3, 1), "");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
