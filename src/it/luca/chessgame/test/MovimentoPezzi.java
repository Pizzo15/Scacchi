package it.luca.chessgame.test;

import static org.junit.Assert.*;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class MovimentoPezzi {
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
		
		// mi sposto di una casella in una qualsiasi direzione
		assertTrue(mover.isMoveLegal(4, 4, 4, 5));
		
		// non posso muovermi di due caselle
		assertTrue(!mover.isMoveLegal(4, 4, 4, 6));
		
		// posso mangiare un pezzo in una direzione qualsiasi a una
		// casella di distanza del colore opposto
		assertTrue(mover.isMoveLegal(4, 4, 3, 4));
		
		// non posso mangiare un pezzo del mio schieramento
		assertTrue(!mover.isMoveLegal(4, 4, 3, 3));
		
		c.set(6, 4, new Torre(black));
		
		mover = new Mover(new TilesModel(c));
		
		// se il re è sotto scacco devo necessariamente muoverlo...
		assertTrue(!mover.isMoveLegal(3, 3, 3, 2));
		assertTrue(mover.isMoveLegal(4, 4, 4, 3));
		
		//... a meno che non posso mangiare il pezzo che tiene lo scacco
	}
	
	@Test
	public void movimentoRegina(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(4, 4, new Regina(white));
		c.set(4, 5, new Pedone(white));
		c.set(5, 5, new Pedone(black));
		c.set(2, 0, new Re(white));
		
		mover = new Mover(new TilesModel(c));
		
		// posso spostarmi di quante caselle voglio lungo la 
		// perpendicolare...
		assertTrue(mover.isMoveLegal(4, 4, 4, 1));
		assertTrue(mover.isMoveLegal(4, 4, 0, 4));
		
		// ... e la diagonale
		assertTrue(mover.isMoveLegal(4, 4, 0, 0));
		assertTrue(mover.isMoveLegal(4, 4, 1, 7));
		
		// pur di non saltare pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 4, 7));
		assertTrue(!mover.isMoveLegal(4, 4, 7, 7));
	}
	
	@Test
	public void movimentoAlfiere(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(4, 4, new Alfiere(white));
		c.set(2, 6, new Pedone(white));
		mover = new Mover(new TilesModel(c));
		
		// posso muovermi di quante caselle voglio 
		// lungo le diagonali
		assertTrue(mover.isMoveLegal(4, 4, 2, 2));
		assertTrue(mover.isMoveLegal(4, 4, 7, 7));
		
		// ma non posso saltare pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 1, 7));
	}
	
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
	
		mover = new Mover(new TilesModel(c));
		
		// posso muovermi a "L"
		assertTrue(mover.isMoveLegal(4, 4, 5, 2));
		assertTrue(mover.isMoveLegal(4, 4, 6, 5));
		
		// e anche saltare altri pezzi
		assertTrue(mover.isMoveLegal(4, 4, 2, 3));
	}
	
	@Test
	public void movimentoTorre(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());

		c.set(2, 0, new Re(white));
		c.set(4, 4, new Torre(white));
		c.set(3, 4, new Pedone(white));
		c.set(3, 3, new Torre(black));
		c.set(4, 3, new Alfiere(white));
	
		mover = new Mover(new TilesModel(c));
		
		// posso muovermi di quante caselle voglio lungo
		// la diagonale
		assertTrue(mover.isMoveLegal(4, 4, 4, 7));
		assertTrue(mover.isMoveLegal(4, 4, 7, 4));
		
		// ma non saltare altri pezzi
		assertTrue(!mover.isMoveLegal(4, 4, 0, 4));
		assertTrue(!mover.isMoveLegal(4, 4, 4, 0));
	}
	
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
