	package it.luca.chessgame.test;

	import static org.junit.Assert.*;

	import java.awt.Color;

	import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

	public class ScaccoDelleSpalline{
		private Configuration c = new ArrayConfiguration();
		private Simulate simulate;
		private final Color white = Color.WHITE;
		private final Color black = Color.BLACK;
		private Mover mover;
		
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
			c.set(6, 0, new Re(black));
			c.set(7, 2, new Pedone(black));
			c.set(7, 0, new Torre(black));
			c.set(6, 1, new Pedone(black));
			
			c.set(5, 2, new Torre(white));
			c.set(6, 4, new Regina(white));
			c.set(6, 6, new Pedone(white));
			c.set(6, 7, new Re(white));
			c.set(7, 5, new Pedone(white));
		
			simulate = new Simulate(c, c = c.swap(6, 4, 4, 2), c = c.swap(6, 0, 7, 1),
					c = c.swap(5, 2, 7, 2));
			
			mover = new Mover(new TilesModel(c));
			mover.setTurno(false);
			
			// scacco matto
			assertTrue(mover.scaccoMatto());
		}

}