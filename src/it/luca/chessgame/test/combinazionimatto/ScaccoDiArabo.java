package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoDiArabo {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testScaccoDiArabo(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 3, new Pedone(black));
		c.set(6, 3, new Pedone(black));
		c.set(3, 0, new Torre(black));
		c.set(2, 0, new Alfiere(black));
		c.set(5, 4, new Alfiere(black));
		c.set(1, 0, new Re(black));
	
		c.set(5, 6, new Pedone(white));
		c.set(6, 6, new Pedone(white));
		c.set(7, 6, new Pedone(white));
		c.set(1, 4, new Cavallo(white));
		c.set(6, 5, new Cavallo(white));
		c.set(6, 1, new Torre(white));
		c.set(6, 7, new Re(white));
	
		new Simulation(c, "Scacco di arabo");
		
		new Simulation(c = c.swap(1, 4, 2, 2), "Il cavallo muove e minaccia il re");
		
		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		// non è scacco matto: il re può scappare
		assertTrue(!mover.scaccoMatto());
		
		new Simulation(c = c.swap(1, 0, 0, 0), "Il re para la minaccia spostandosi nella casella sicura");
		
		new Simulation(c = c.swap(6, 1, 0, 1), "La torre dà matto");

		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		assertTrue(mover.scaccoMatto());
	}
}
