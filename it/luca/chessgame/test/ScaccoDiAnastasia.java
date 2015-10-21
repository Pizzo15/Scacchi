package it.luca.chessgame.test;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di Test per la simulazione della combinazione di matto 
 * di Anastasia: un cavallo controlla le due case di fuga del re 
 * attaccato con uno scacco di torre.
 * 
 * @author luca
 */
public class ScaccoDiAnastasia {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	private Simulate simulate;
	
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
		c.set(2, 3, new Regina(white));
		c.set(2, 7, new Torre(white));
		c.set(4, 1, new Cavallo(white));
		c.set(4, 4, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(6, 5, new Pedone(white));
		c.set(7, 6, new Re(white));
		c.set(6, 7, new Pedone(white));

		simulate = new Simulate(c, c = c.swap(4, 1, 5, 3), c = c.swap(5, 0, 6, 0),
				c = c.swap(2, 3, 5, 0), c = c.swap(6, 0, 5, 0), c = c.swap(2, 7, 2, 0));
		
		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		assertTrue(mover.scaccoMatto());
	}
}
