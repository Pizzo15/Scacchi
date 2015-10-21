package it.luca.chessgame.test;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto del Cantone:
 * con una casa di fuga bloccata, la torre supportata dall'alfiere
 * scende in un vertice della scacchiera e dà matto.
 * 
 * @author luca
 */
public class ScaccoDelCantone {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	private Simulate simulate;
	
	@Test
	public void testScaccoDelCantone(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
		
		// aggiungo i pezzi bianchi
		c.set(0, 6, new Pedone(white));
		c.set(1, 4, new Alfiere(white));
		c.set(1, 6, new Pedone(white));
		c.set(2, 7, new Regina(white));
		c.set(3, 4, new Pedone(white));
		c.set(5, 2, new Alfiere(white));
		c.set(5, 5, new Pedone(white));
		c.set(6, 6, new Pedone(white));
		c.set(5, 6, new Re(white));
		c.set(7, 4, new Torre(white));
		c.set(7, 7, new Torre(white));
		
		// aggiungo i pezzi neri
		c.set(0, 1, new Pedone(black));
		c.set(1, 3, new Pedone(black));
		c.set(2, 1, new Regina(black));
		c.set(2, 2, new Pedone(black));
		c.set(3, 1, new Torre(black));
		c.set(3, 2, new Alfiere(black));
		c.set(4, 0, new Torre(black));
		c.set(5, 1, new Pedone(black));
		c.set(5, 4, new Cavallo(black));
		c.set(6, 0, new Re(black));
		c.set(6, 2, new Pedone(black));
		c.set(7, 3, new Pedone(black));

		simulate = new Simulate(c, c = c.swap(2, 7, 5, 4), c = c.swap(3, 2, 5, 4), c = c.swap(7, 4, 7, 3),
				c = c.swap(6, 2, 7, 3), c = c.swap(7, 7, 7, 3));
		
		mover = new Mover(new TilesModel(c));
		
		assertTrue(mover.scaccoMatto());
	}
}
