package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.*;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto delle spalline:
 * se il re è autobloccato la donna può dare scacco da sola.
 * Devono essere occupate le due casa di fuga a salto del cavallo
 * dalla posizione della donna.
 */
public class ScaccoDelleSpalline{
	private char[][] pezzi = {
			{ 'T', ' ', ' ', 'D', ' ', ' ', 'R', 'T' }, 
			{ 'P', 'P', ' ', ' ', ' ', ' ', 'P', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', 't', ' ', 'P' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'd', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'p' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'r', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'p', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDelleSpalline(){
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), true);

		mover.move(6, 4, 4, 2);
		
		mover.move(6, 0, 7, 1);
		
		mover.move(5, 2, 7, 2);
		
		// non è scacco matto: il pezzo che tiene sotto scacco il re può essere mangiato
		assertTrue(!mover.scaccoMatto());	
		
		mover.move(6, 1, 7, 2);
		
		mover.move(4, 2, 5, 1);

		mover.showSimulation(mover.getModel().getConfiguration());
		
		assertTrue(mover.scaccoMatto());
	}
}