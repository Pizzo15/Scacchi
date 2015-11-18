package it.luca.chessgame.model;

import java.awt.Color;

public class ArrayConfiguration implements Configuration {
	private final char[][] init = {
			{ 'T', 'C', 'A', 'D', 'R', 'A', 'C', 'T' }, 
			{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
			{ 't', 'c', 'a', 'd', 'r', 'a', 'c', 't' }, 
	};
	private final Pezzo[][] tiles;
	private final Color white = Color.WHITE;
	private final Color black = Color.BLACK;

	public ArrayConfiguration(){		
		tiles = new Pezzo[8][8];
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				set(x, y, decode(init[y][x]));
	}

	public ArrayConfiguration(Pezzo[][] tiles){
		this.tiles = new Pezzo[8][];
		for(int y = 0; y < 8; y++)
			this.tiles[y] = tiles[y].clone();
	}
	
	public ArrayConfiguration(char[][] tiles){
		this.tiles = new Pezzo[8][8];
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				set(x, y, decode(tiles[y][x]));
	}

	private Pezzo decode(char tile){
		// Maiuscolo = nero, minuscolo = bianco

		switch(tile){
		case 'p':
			return new Pedone(white);
		case 'P':
			return new Pedone(black);
		case 't':
			return new Torre(white);
		case 'T':
			return new Torre(black);
		case 'a':
			return new Alfiere(white);
		case 'A':
			return new Alfiere(black);
		case 'c':
			return new Cavallo(white);
		case 'C':
			return new Cavallo(black);
		case 'r':
			return new Re(white);
		case 'R':
			return new Re(black);
		case 'd':
			return new Regina(white);
		case 'D':
			return new Regina(black);
		default:
			return new CasellaVuota();
		}
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
	
	@Override
	public boolean compare(Object other){
		if(other instanceof Configuration){
			Configuration otherAsConfiguration = (Configuration) other;
			for(int y = 0; y < 8; y++)
				for(int x = 0; x < 8; x++)
					if(!at(x,y).equals(otherAsConfiguration.at(x, y)))
						return false;
			
			return true;
		}

		return false;
	}
}
