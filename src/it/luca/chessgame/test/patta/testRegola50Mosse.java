package it.luca.chessgame.test.patta;

import static org.junit.Assert.assertTrue;
import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import org.junit.Test;

public class testRegola50Mosse {
	private Configuration c = new ArrayConfiguration();
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	private Mover mover;
	
	@Test
	public void testPattaPerRegola50Mosse(){
		// inizializzo la scacchiera
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				c.set(x, y, new CasellaVuota());
			
		// aggiungo i pezzi bianchi
		c.set(2, 4, new Pedone(white));
		c.set(7, 4, new Pedone(white));
		c.set(3, 5, new Pedone(white));
		c.set(7, 2, new Re(white));
		c.set(3, 7, new Regina(white));
		
		// aggiungo i pezzi neri
		c.set(0, 0, new Re(black));
		c.set(2, 3, new Pedone(black));
		
		mover = new Mover(new TilesModel(c), true);
		
		mover.move(3, 7, 4, 7);
		mover.move(0, 0, 0, 1);
		mover.move(4, 7, 3, 7);
		mover.move(0, 1, 0, 0);
		
		// Ho effettuato 4 mosse che non prevedono catture o coinvolgono pedoni
		assertTrue(mover.getMosse() == 5);
		
		mover.move(3, 5, 3, 4);
		
		// Ho mosso un pedone: azzero contatore
		assertTrue(mover.getMosse() == 1);	
		
		mover.move(2, 3, 3, 4);

		// Effettuo una cattura: non incremento il contatore
		assertTrue(mover.getMosse() == 1);		
		
		mover.move(3, 7, 3, 4);
		
		// Effettuo una cattura(non utilizzando un pedone): 
		// non incremento il contatore.
		assertTrue(mover.getMosse() == 1);
	}
}
