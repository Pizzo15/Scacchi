package it.luca.chessgame.test.combinazionimatto;

import static org.junit.Assert.*;

import it.luca.chessgame.model.*;
import it.luca.chessgame.moves.Mover;

import org.junit.Test;

/**
 * Classe di test per la combinazione di matto del 
 * corridoio: la donna da scacco matto al re che è 
 * "bloccato" dai pedoni.
 */
public class ScaccoDelCorridoio {
	private char[][] pezzi = {
			{ 't', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', 'T', ' ', 'D', ' ', ' ', 'R' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'P', 'P' }, 
			{ ' ', ' ', ' ', 'd', ' ', 'P', ' ', ' ' }, 
			{ ' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', ' ', ' ', ' ', ' ', ' ', 'p', 'p' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'r', ' ' }, 
	};
	private Mover mover;
	
	@Test
	public void testScaccoDelCorridoio(){	
		mover = new Mover(new TilesModel(new ArrayConfiguration(pezzi)), false);

		mover.move(4, 1, 4, 7);
		
		mover.showSimulation(mover.getModel().getConfiguration());
		
		// scacco matto
		assertTrue(mover.scaccoMatto());
	}
}
