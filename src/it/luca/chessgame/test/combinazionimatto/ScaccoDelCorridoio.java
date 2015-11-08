package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.*;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto del 
 * corridoio: la donna da scacco matto al re che è 
 * "bloccato" dai pedoni.
 * 
 * @author luca
 */
public class ScaccoDelCorridoio {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoDelCorridoio(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(0, 0, new Torre(white));
		c.set(0, 6, new Pedone(white));
		c.set(1, 5, new Pedone(white));
		c.set(2, 4, new Pedone(white));
		c.set(3, 3, new Regina(white));
		c.set(6, 6, new Pedone(white));
		c.set(6, 7, new Re(white));
		c.set(7, 6, new Pedone(white));
		
		// aggiungo i pezzi neri
		c.set(2, 1, new Torre(black));
		c.set(5, 3, new Pedone(black));
		c.set(4, 1, new Regina(black));
		c.set(6, 2, new Pedone(black));
		c.set(7, 1, new Re(black));
		c.set(7, 2, new Pedone(black));
			
		new Simulation(c, "Scacco del corridoio");
		
		new Simulation(c = c.swap(4, 1, 4, 7), "La donna dà matto al re bloccato dai pedoni");
		
		// scacco matto
		assertTrue(new Mover(new TilesModel(c)).scaccoMatto());
	}
}
