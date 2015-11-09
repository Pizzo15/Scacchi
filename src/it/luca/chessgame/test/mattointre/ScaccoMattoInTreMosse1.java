package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoMattoInTreMosse1 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoMattoInTreMosse1(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(1, 7, new Torre(white));
		c.set(5, 6, new Re(white));
		c.set(5, 7, new Cavallo(white));
		
		// aggiungo i pezzi neri
		c.set(0, 0, new Torre(black));
		c.set(7, 5, new Torre(black));
		c.set(7, 7, new Re(black));
		c.set(5, 5, new Alfiere(black));
			
		new Simulation(c, "Scacco matto in tre mosse (1)");
		
		new Simulation(c = c.swap(5, 7, 6, 5), "Il cavallo minaccia il re");
		
		new Simulation(c = c.swap(7, 7, 7, 6), "Il re para la minaccia fuggendo");
		
		new Simulation(c = c.swap(1, 7, 7, 7), "La torre muove e minaccia il re");
		
		new Simulation(c = c.swap(5, 5, 7, 7), "L'alfiere mangia la torre");
		
		new Simulation(c = c.swap(6, 5, 5, 7), "Il cavallo dà matto al re in trappola");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
