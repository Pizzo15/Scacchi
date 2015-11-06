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

public class TestPedone {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void movimentoPedone(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(2, 7, new Re(black));
		c.set(4, 6, new Pedone(white));
		c.set(5, 1, new Pedone(black));
		c.set(3, 3, new Torre(black));
		c.set(4, 3, new Alfiere(white));
	
		mover = new Mover(new TilesModel(c));
		
		// posso muovermi solo in alto di una casella se bianco
		assertTrue(mover.isMoveLegal(4, 6, 4, 5));
		assertTrue(!mover.isMoveLegal(4, 6, 4, 4));
		
		mover.setTurno(false);
		
		// posso muovermi solo in basso di una casella se nero
		assertTrue(mover.isMoveLegal(5, 1, 5, 2));
		assertTrue(!mover.isMoveLegal(5, 1, 5, 0));
		
		c.set(5, 2, new Torre(white));
		c.set(5, 5, new Pedone(black));
		c.set(6, 2, new Torre(white));
		
		mover = new Mover(new TilesModel(c));

		// se un pezzo occupa la casella di fronte non posso mangiarlo
		assertTrue(!mover.isMoveLegal(5, 1, 5, 2));
		
		mover.setTurno(true);
		
		// ma posso mangiare i pezzi sulle caselle diagonali adiacenti
		assertTrue(mover.isMoveLegal(4, 6, 5, 5));
		
		mover.setTurno(false);
		
		assertTrue(mover.isMoveLegal(5, 1, 6, 2));
	}
}
