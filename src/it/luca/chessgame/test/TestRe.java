package it.luca.chessgame.test;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.Alfiere;
import it.luca.chessgame.model.ArrayConfiguration;
import it.luca.chessgame.model.CasellaVuota;
import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.Pedone;
import it.luca.chessgame.model.Re;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.model.Torre;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class TestRe {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void movimentoRe(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
		
		c.set(4, 4, new Re(white));
		c.set(3, 4, new Pedone(black));
		c.set(3, 3, new Pedone(white));
		
		mover = new Mover(new TilesModel(c));

		// mi posso spostare di una casella in una qualsiasi direzione
		assertTrue(mover.isMoveLegal(4, 4, 5, 4));
		assertTrue(mover.isMoveLegal(4, 4, 5, 5));
		assertTrue(mover.isMoveLegal(4, 4, 3, 4));
		
		// non posso muovermi di due caselle
		assertTrue(!mover.isMoveLegal(4, 4, 4, 6));
		assertTrue(!mover.isMoveLegal(4, 4, 6, 4));
		
		// posso mangiare un pezzo in una direzione qualsiasi a una
		// casella di distanza del colore opposto
		assertTrue(mover.isMoveLegal(4, 4, 3, 4));
		
		// non posso mangiare un pezzo del mio schieramento
		assertTrue(!mover.isMoveLegal(4, 4, 3, 3));
		
		c.set(6, 4, new Torre(black));
		
		mover = new Mover(new TilesModel(c));
		
		// se il re è sotto scacco
		// non posso muovere altri pezzi del mio schieramento...
		assertTrue(!mover.isMoveLegal(3, 3, 3, 2));
		assertTrue(mover.isMoveLegal(4, 4, 4, 3));
		
		//... a meno che non posso mangiare il pezzo che tiene lo scacco
		c.set(5, 3, new Alfiere(white));
		
		mover = new Mover(new TilesModel(c));
		
		// mangio il pezzo che tiene sotto scacco il re
		assertTrue(mover.isMoveLegal(5, 3, 6, 4));
	}
}
