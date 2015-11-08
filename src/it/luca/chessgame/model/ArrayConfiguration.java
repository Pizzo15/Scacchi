package it.luca.chessgame.model;

import java.awt.Color;

public class ArrayConfiguration extends AbstractConfiguration {
	private final Pezzo[][] tiles;
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;
	
	public ArrayConfiguration(Pezzo[][] tiles){
		this.tiles = new Pezzo[8][];
		for(int y = 0; y < 8; y++)
			this.tiles[y] = tiles[y].clone();
	}
	
	public ArrayConfiguration(){
		tiles = new Pezzo[8][8];
	
		configurazioneIniziale();
	}

	private void configurazioneIniziale(){		
		// inizializzo il modello della scacchiera...
		// inizializzo tutte le caselle come vuote
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				set(x, y, new CasellaVuota());

		// poi aggiungo i pezzi...
		// la prima riga contiene i pezzi neri
		set(0, 0, new Torre(black));
		set(1, 0, new Cavallo(black));
		set(2, 0, new Alfiere(black));
		set(3, 0, new Regina(black));
		set(4, 0, new Re(black));
		set(5, 0, new Alfiere(black));
		set(6, 0, new Cavallo(black));
		set(7, 0, new Torre(black));
		
		// la seconda riga contiene i pedoni neri
		for(int x = 0; x < 8; x++)
			set(x, 1, new Pedone(black));
		
		// la penultima riga contiene i pedoni bianchi
		for(int x = 0; x < 8; x++)
			set(x, 6, new Pedone(white));
		
		// l'ultima riga contiene i pezzi bianchi
		set(0, 7, new Torre(white));
		set(1, 7, new Cavallo(white));
		set(2, 7, new Alfiere(white));
		set(3, 7, new Regina(white));
		set(4, 7, new Re(white));
		set(5, 7, new Alfiere(white));
		set(6, 7, new Cavallo(white));
		set(7, 7, new Torre(white));
	}
	
	@Override
	public Pezzo at(int x, int y){
		return tiles[x][y];
	}
	
	@Override
	public void set(int x, int y, Pezzo p){
		tiles[x][y] = p;
	}
	
	@Override
	public Configuration swap(int fromX, int fromY, int toX, int toY) {
		ArrayConfiguration res = new ArrayConfiguration(tiles);
		
		res.set(toX, toY, res.at(fromX, fromY));
		res.set(fromX, fromY, new CasellaVuota());
		
		return res;
	}
}
