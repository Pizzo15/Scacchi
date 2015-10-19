package it.luca.chessgame.controller;

import it.luca.chessgame.moves.Mover;
import it.luca.chessgame.view.View;

public interface Controller {
	void onClick(int fromX, int fromY, int toX, int toY);

	View getView();
	
	Mover getMover();
}
