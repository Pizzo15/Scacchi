package it.luca.chessgame.test.mattointre;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoMattoInTreMosse2 {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoMattoInTreMosse2(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(0, 2, new Pedone(white));
		c.set(1, 3, new Pedone(white));
		c.set(4, 4, new Regina(white));
		c.set(0, 4, new Re(white));
		c.set(4, 3, new Alfiere(white));
		
		// aggiungo i pezzi neri
		c.set(0, 1, new Re(black));
		c.set(0, 0, new Regina(black));
		c.set(4, 2, new Cavallo(black));
		c.set(3, 4, new Pedone(black));
		
		new Simulation(c, "Scacco matto in tre mosse (2)");
		
		new Simulation(c = c.swap(4, 4, 7, 1), "La donna minaccia il re");
		
		new Simulation(c = c.swap(0, 1, 1, 2), "Il re para la minaccia fuggendo");
		
		new Simulation(c = c.swap(7, 1, 2, 1), "La donna muove e minaccia il re");
		
		new Simulation(c = c.swap(4, 2, 2, 1), "Il cavallo mangia la donna che minaccia il re");
		
		new Simulation(c = c.swap(4, 3, 3, 4), "L'alfiere dà matto al re");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
