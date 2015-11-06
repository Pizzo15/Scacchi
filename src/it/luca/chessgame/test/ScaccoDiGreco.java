package it.luca.chessgame.test;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto di Greco:
 * la torre si unisce all'alfiere che inibisce una casa di fuga.
 */
public class ScaccoDiGreco {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
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
		c.set(7, 0, new Re(black));
		c.set(6, 1, new Pedone(black));
		c.set(6, 2, new Pedone(black));
		c.set(5, 3, new Pedone(black));
	
		c.set(5, 0, new Pedone(white));
		c.set(6, 1, new Pedone(white));
		c.set(2, 4, new Alfiere(white));
		c.set(2, 7, new Torre(white));
		c.set(3, 7, new Regina(white));
		c.set(4, 7, new Re(white));
		c.set(4, 5, new Pedone(white));
		c.set(5, 5, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(6, 5, new Pedone(white));
		c.set(7, 4, new Torre(white));
	
		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		new Simulation(c, "La torre si unisce all'alfiere che inibisce una casa di fuga");

		assertTrue(mover.scaccoMatto());
	}
}
