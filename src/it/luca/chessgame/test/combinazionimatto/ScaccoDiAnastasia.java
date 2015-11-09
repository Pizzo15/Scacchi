package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import org.junit.Test;

/**
 * Classe di Test per la simulazione della combinazione di matto 
 * di Anastasia: un cavallo controlla le due case di fuga del re 
 * attaccato con uno scacco di torre.
 */
public class ScaccoDiAnastasia {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoDiAnastasia(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 1, new Pedone(black));
		c.set(1, 6, new Torre(black));
		c.set(3, 6, new Regina(black));
		c.set(4, 2, new Pedone(black));
		c.set(5, 0, new Re(black));
		c.set(5, 1, new Pedone(black));
		c.set(6, 1, new Pedone(black));
		c.set(7, 2, new Pedone(black));
		c.set(7, 5, new Alfiere(black));

		c.set(0, 4, new Pedone(white));
		c.set(2, 7, new Torre(white));
		c.set(4, 1, new Cavallo(white));
		c.set(4, 4, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(6, 5, new Pedone(white));
		c.set(2, 3, new Regina(white));
		c.set(6, 7, new Re(white));
		c.set(7, 6, new Pedone(white));
	
		new Simulation(c, "Scacco di Anastasia");
		
		new Simulation(c = c.swap(4, 1, 5, 3), "Il cavallo si sposta e libera il re alla minaccia della regina");
		
		// il re può scappare: non è scacco matto
		assertTrue(!new Mover(new TilesModel(c), false).scaccoMatto());
		
		new Simulation(c = c.swap(5, 0, 6, 0), "Il re scappa nella casella sicura");
		
		new Simulation(c = c.swap(2, 3, 5, 0), "La donna insegue il re");

		// il pezzo che tiene lo scacco può essere mangiato: non è scacco matto
		assertTrue(!new Mover(new TilesModel(c), false).scaccoMatto());
		
		new Simulation(c = c.swap(6, 0, 5, 0), "Il re para la minaccia mangiando la regina");
		
		new Simulation(c = c.swap(2, 7, 2, 0), "La torre dà scacco matto");
		
		// la regina può mettersi tra la torre e il re: non è scacco matto
		assertTrue(!new Mover(new TilesModel(c), false).scaccoMatto());
		
		new Simulation(c = c.swap(3, 6, 3, 0), "La donna salva il re");
		
		new Simulation(c = c.swap(2, 0, 3, 0), "La torre mangia la regina dando scacco matto");
	
		// la regina può mettersi tra la torre e il re: non è scacco matto
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
