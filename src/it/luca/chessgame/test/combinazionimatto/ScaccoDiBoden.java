package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.Alfiere;
import it.luca.chessgame.model.ArrayConfiguration;
import it.luca.chessgame.model.CasellaVuota;
import it.luca.chessgame.model.Cavallo;
import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.Pedone;
import it.luca.chessgame.model.Re;
import it.luca.chessgame.model.Regina;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.model.Torre;
import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.test.Simulation;

import java.awt.Color;

import org.junit.Test;

public class ScaccoDiBoden {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testScaccoDiBoden(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(0, 2, new Pedone(black));
		c.set(1, 1, new Pedone(black));
		c.set(2, 2, new Pedone(black));
		c.set(5, 1, new Pedone(black));
		c.set(6, 2, new Pedone(black));
		c.set(7, 2, new Pedone(black));
		c.set(3, 0, new Torre(black));
		c.set(7, 0, new Torre(black));
		c.set(6, 1, new Alfiere(black));
		c.set(7, 3, new Regina(black));
		c.set(2, 0, new Re(black));
		c.set(3, 1, new Cavallo(black));
	
		c.set(0, 6, new Pedone(white));
		c.set(2, 5, new Pedone(white));
		c.set(3, 4, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(6, 6, new Pedone(white));
		c.set(7, 5, new Pedone(white));
		c.set(4, 6, new Alfiere(white));
		c.set(7, 6, new Alfiere(white));
		c.set(2, 7, new Torre(white));
		c.set(5, 5, new Regina(white));
		c.set(6, 7, new Re(white));
	
		new Simulation(c, "Scacco di Boden");
		
		new Simulation(c = c.swap(5, 5, 2, 2), "La donna mangia e minaccia il re");
		
		new Simulation(c = c.swap(1, 1, 2, 2), "Il pedone para la minaccia mangiando la regina");
		
		new Simulation(c = c.swap(4, 6, 0, 2), "L'alfiere dà matto");

		mover = new Mover(new TilesModel(c));
		mover.setTurno(false);
		
		assertTrue(mover.scaccoMatto());
	}
}
