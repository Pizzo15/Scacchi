package it.luca.chessgame.model;

public interface Configuration {
	
	// produce il pezzo sulla tessera alle coordinate date
	Pezzo at(int x, int y);
	
	// imposta il pezzo p alle coordinate (x, y)
	void set(int x, int y, Pezzo p);
	
	// produce una nuova configurazione dove due tessere sono state scambiate
	Configuration swap(int fromX, int fromY, int toX, int toY);
	
	boolean compare(Object other);
}
