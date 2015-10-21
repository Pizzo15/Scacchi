package it.luca.chessgame.test;

import it.luca.chessgame.model.*;
import it.luca.chessgame.view.*;

/**
 * Mostra a video una scacchiera in varie configurazioni a intervalli 
 * regolari al fine di simulare una combinazione di mosse.
 * 
 * @author luca
 */
public class Simulate {
	ChessFrame frame = new ChessFrame();
	
	public Simulate(Configuration... c){
		for(Configuration conf: c){
			TilesPanel panel = new TilesPanel(new TilesModel(conf), frame);
			frame = new ChessFrame(panel);

			frame.setTitle("Simulation");

			frame.setVisible(true);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
