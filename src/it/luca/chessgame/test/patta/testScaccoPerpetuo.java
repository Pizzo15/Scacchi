package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

/**
 * Lo scacco perpetuo è un tema tattico difensivo con cui si impone la patta per ripetizioni di mosse, 
 * dando scacco al Re senza che questo possa avere la possibilità di sottrarsi: non c’è matto, ma il re
 * in fuga non può evitare la ripetizione delle mosse.
 */
public class testScaccoPerpetuo {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testPerpetuo(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(1, 6, new Pedone(white));
		c.set(2, 6, new Pedone(white));
		c.set(5, 6, new Pedone(white));
		c.set(6, 5, new Pedone(white));
		c.set(6, 7, new Re(white));
		c.set(1, 5, new Alfiere(white));
		c.set(0, 5, new Cavallo(white));
		c.set(2, 5, new Cavallo(white));
		
		// aggiungo i pezzi neri
		c.set(3, 4, new Re(black));
		c.set(0, 2, new Pedone(black));
		c.set(2, 3, new Pedone(black));
		c.set(4, 3, new Pedone(black));
		c.set(6, 2, new Pedone(black));
		c.set(7, 1, new Torre(black));
		c.set(5, 5, new Alfiere(black));
		
		mover = new Mover(new TilesModel(c), true);

		mover.move(0, 5, 1, 3);
		mover.move(0, 2, 1, 3);
		
		mover.move(2, 5, 1, 3);
		mover.move(3, 4, 4, 4);
		
		mover.move(1, 3, 3, 2);
		mover.move(4, 4, 3, 4);
		
		mover.move(3, 2, 1, 3);
		mover.move(3, 4, 4, 4);
		
		mover.move(1, 3, 3, 2);
		mover.move(4, 4, 3, 4);
		
		mover.move(3, 2, 1, 3);
		
		assertTrue(mover.patta());
	}
}
