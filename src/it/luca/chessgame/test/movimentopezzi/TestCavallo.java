package it.luca.chessgame.test.movimentopezzi;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.Alfiere;
import it.luca.chessgame.model.ArrayConfiguration;
import it.luca.chessgame.model.CasellaVuota;
import it.luca.chessgame.model.Cavallo;
import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.Pedone;
import it.luca.chessgame.model.Re;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.model.Torre;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class TestCavallo {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;

	@Test
	public void movimentoCavallo(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(4, 4, new Cavallo(white));
		c.set(3, 4, new Pedone(white));
		c.set(3, 3, new Torre(black));
		c.set(4, 3, new Alfiere(white));
	
		mover = new Mover(new TilesModel(c), true);
		
		// posso muovermi a "L"
		assertTrue(mover.isMoveLegal(4, 4, 5, 2));
		assertTrue(mover.isMoveLegal(4, 4, 6, 5));
		
		// e anche saltare altri pezzi
		assertTrue(mover.isMoveLegal(4, 4, 2, 3));
	}
}
