package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto di Greco:
 * la torre si unisce all'alfiere che inibisce una casa di fuga.
 */
public class ScaccoDiGreco {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoDiGreco(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 0, new Torre(black));
		c.set(0, 1, new Pedone(black));
		c.set(1, 1, new Alfiere(black));
		c.set(1, 2, new Pedone(black));
		c.set(2, 1, new Pedone(black));
		c.set(3, 2, new Alfiere(black));
		c.set(5, 0, new Torre(black));
		c.set(3, 0, new Regina(black));
		c.set(6, 0, new Re(black));
		c.set(6, 5, new Cavallo(black));
		c.set(6, 1, new Pedone(black));
		c.set(7, 1, new Pedone(black));
		c.set(5, 3, new Pedone(black));
	
		c.set(0, 5, new Pedone(white));
		c.set(2, 4, new Alfiere(white));
		c.set(2, 7, new Torre(white));
		c.set(3, 3, new Cavallo(white));
		c.set(4, 7, new Regina(white));
		c.set(4, 7, new Re(white));
		c.set(1, 6, new Pedone(white));
		c.set(4, 5, new Pedone(white));
		c.set(5, 5, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(7, 6, new Pedone(white));
		c.set(7, 7, new Torre(white));
	
		new Simulation(c, "Scacco di Greco");
		
		new Simulation(c = c.swap(3, 3, 4, 1), "Il cavallo muove e minaccia il re");
		
		new Simulation(c = c.swap(6, 0, 7, 0), "Il re fugge nella casella sicura");
		
		new Simulation(c = c.swap(4, 1, 6, 2), "Il cavallo insegue il re");
		
		new Simulation(c = c.swap(7, 1, 6, 2), "Il pedone mangia il cavallo e libera il re dalla minaccia");
		
		new Simulation(c = c.swap(7, 6, 6, 5), "Il pedone mangia e pone il re sotto la minaccia della torre");
		
		new Simulation(c = c.swap(3, 0, 7, 4), "La donna para la minaccia");
		
		new Simulation(c = c.swap(7, 7, 7, 4), "La torre mangia la donna e dà scacco matto");

		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}
