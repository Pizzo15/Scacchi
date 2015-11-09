package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.*;

import java.awt.Color;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto delle spalline:
 * se il re è autobloccato la donna può dare scacco da sola.
 * Devono essere occupate le due casa di fuga a salto del cavallo
 * dalla posizione della donna.
 */
public class ScaccoDelleSpalline{
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	@Test
	public void testScaccoDelleSpalline(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 0, new Torre(black));
		c.set(0, 1, new Pedone(black));
		c.set(1, 1, new Pedone(black));
		c.set(3, 0, new Regina(black));
		c.set(6, 1, new Pedone(black));
		c.set(7, 2, new Pedone(black));
		c.set(6, 0, new Re(black));
		c.set(7, 0, new Torre(black));
			
		c.set(5, 2, new Torre(white));
		c.set(6, 4, new Regina(white));
		c.set(7, 5, new Pedone(white));
		c.set(6, 6, new Re(white));
		c.set(6, 7, new Pedone(white));

		new Simulation(c, "Scacco delle spalline");

		new Simulation(c = c.swap(6, 4, 4, 2), "La donna muove e minaccia il re");
		
		new Simulation(c = c.swap(6, 0, 7, 1), "Il re scappa in una casella sicura");
		
		new Simulation(c = c.swap(5, 2, 7, 2), "La torre mangia il pedone e minaccia il re");
		
		// non è scacco matto: il pezzo che tiene sotto scacco il re può essere mangiato
		assertTrue(!new Mover(new TilesModel(c), false).scaccoMatto());	
		
		new Simulation(c = c.swap(6, 1, 7, 2), "Il pedone para la minaccia mangiando la torre");
		
		new Simulation(c = c.swap(4, 2, 5, 1), "La regina dà matto al re bloccato");
		
		assertTrue(new Mover(new TilesModel(c), false).scaccoMatto());
	}
}