package it.luca.chessgame.controller;

import it.luca.chessgame.moves.*;
import it.luca.chessgame.view.View;

public class ChessController implements Controller {
	private final View view;
	private final Mover mover;
	
	public ChessController(View view){
		this.view = view;
		mover = new Mover(view.getModel());
		
		view.setController(this);
	}
	
	@Override
	public Mover getMover(){
		return mover;
	}
	
	@Override
	public View getView(){
		return view;
	}
	
	@Override
	public void setNewGame(){
		mover.newGame();
	}
	
	@Override
	public void onClick(int fromX, int fromY, int toX, int toY) {		
		mover.move(fromX, fromY, toX, toY);
	}
}
