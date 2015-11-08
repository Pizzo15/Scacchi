package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoDiLucena {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testScaccoDiLucena(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 1, new Pedone(black));
		c.set(1, 1, new Pedone(black));
		c.set(4, 0, new Torre(black));
		c.set(6, 0, new Torre(black));
		c.set(1, 0, new Re(black));
		c.set(6, 4, new Regina(black));
		
		c.set(0, 6, new Pedone(white));
		c.set(1, 6, new Pedone(white));
		c.set(2, 5, new Pedone(white));
		c.set(0, 7, new Torre(white));
		c.set(4, 6, new Regina(white));
		c.set(4, 7, new Re(white));
		c.set(4, 2, new Cavallo(white));
		
		new Simulation(c, "Scacco di Lucena");
		
		new Simulation(c = c.swap(4, 6, 4, 3), "La donna muove e minaccia il re");
		
		new Simulation(c = c.swap(1, 0, 0, 0), "Il re para la minaccia muovendosi nella casella sicura");
		
		new Simulation(c = c.swap(4, 2, 2, 1), "Il cavallo muove e minaccia");

		new Simulation(c = c.swap(0, 0, 1, 0), "Il re scappa nella casella sicura");
		
		new Simulation(c = c.swap(2, 1, 4, 0), "Il cavallo mangia la torre esponendo il re alla minaccia della regina");

		new Simulation(c = c.swap(1, 0, 0, 0), "Il re para la minaccia muovendosi nella casella sicura");

		new Simulation(c = c.swap(4, 0, 2, 1), "Il cavallo muove e minaccia");

		new Simulation(c = c.swap(0, 0, 1, 0), "Il re scappa nella casella sicura");

		new Simulation(c = c.swap(2, 1, 0, 2), "Il cavallo muove esponendo il re alla minaccia della regina");

		new Simulation(c = c.swap(1, 0, 0, 0), "Il re para la minaccia muovendosi nella casella sicura");

		new Simulation(c = c.swap(4, 3, 1, 0), "La donna muove e minaccia il re");

		new Simulation(c = c.swap(6, 0, 1, 0), "La torre mangia la regina liberando il re");

		new Simulation(c = c.swap(0, 2, 2, 1), "Il cavallo muove e dà matto al re in trappola");

		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		assertTrue(mover.scaccoMatto());
	}
}
