package it.luca.chessgame.view;

import it.luca.chessgame.controller.Controller;
import it.luca.chessgame.model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);
	
	void onConfigurationChange();
	
	void showEndGameDialog(String msg);
}
