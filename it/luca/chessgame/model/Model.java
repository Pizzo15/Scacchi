package it.luca.chessgame.model;

import it.luca.chessgame.view.View;

public interface Model {
	// informazioni sullo stato attuale
	/**
	 * Ritorna il pezzo contenuto nella scacchiera alle coordinate (x,y)
	 * @param x
	 * @param y
	 * @return
	 */
	public Pezzo at(int x, int y);
	public Configuration getConfiguration();
	
	// Cambia lo stato attuale
	public void setConfiguration(Configuration configuration);
	public void setView(View view);
}
