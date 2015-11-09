package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoMattoInTreMosse3 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoMattoInTreMosse3(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(3, 5, new Cavallo(white));
		c.set(5, 6, new Re(white));
		c.set(3, 3, new Alfiere(white));
		
		// aggiungo i pezzi neri
		c.set(7, 7, new Re(black));
		c.set(4, 4, new Alfiere(black));
		c.set(5, 5, new Pedone(black));
		c.set(7, 4, new Pedone(black));
		c.set(7, 5, new Pedone(black));
		c.set(7, 6, new Pedone(black));
		
		new Simulation(c, "Scacco matto in tre mosse (3)");
		
		new Simulation(c = c.swap(3, 3, 0, 0), "L'alfiere muove");
		
		new Simulation(c = c.swap(4, 4, 0, 0), "L'alfiere cattura l'avversario");
		
		new Simulation(c = c.swap(5, 6, 5, 7), "Il re muove");
		
		new Simulation(c = c.swap(0, 0, 4, 4), "L'alfiere muove");
		
		new Simulation(c = c.swap(3, 5, 5, 6), "Il cavallo dà matto");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
