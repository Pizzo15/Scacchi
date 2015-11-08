package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoDiDamiano {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testScaccoDiDamiano(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(1, 0, new Torre(black));
		c.set(7, 0, new Torre(black));
		c.set(0, 1, new Pedone(black));
		c.set(4, 4, new Regina(black));
		c.set(0, 0, new Re(black));
	
		c.set(1, 6, new Torre(white));
		c.set(1, 7, new Torre(white));
		c.set(5, 6, new Regina(white));
		c.set(0, 7, new Re(white));
	
		new Simulation(c, "Scacco di Damiano");
		
		new Simulation(c = c.swap(5, 6, 0, 1), "La donna mangia il pedone e minaccia il re");
		
		new Simulation(c = c.swap(0, 0, 0, 1), "Il re para la minaccia mangiando la regina");
		
		new Simulation(c = c.swap(1, 6, 0, 6), "La torre muove e minaccia il re");
		
		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		// un pezzo si può intromettere tra il re e chi minaccia: non è scacco matto
		assertTrue(!mover.scaccoMatto());
		
		new Simulation(c = c.swap(4, 4, 0, 4), "La regina para la minaccia mettendosi tra la torre e il re");
		
		new Simulation(c = c.swap(0, 6, 0, 4), "La torre mangia la regina e dà scacco matto");

		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		assertTrue(mover.scaccoMatto());
	}
}
