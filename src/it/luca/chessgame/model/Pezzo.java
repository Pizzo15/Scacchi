package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public abstract class Pezzo {
	private final Color color;
	private final ImageIcon image;
	
	/**
	 * Un pezzo è identificato dal colore e dall'icona che lo rappresenta.
	 */
	public Pezzo(Color color, ImageIcon image){
		this.color = color;
		this.image = image;
	}
	
	public Color getColor(){ return this.color; }
	public ImageIcon getImage(){ return this.image; }
	
	@Override
	public boolean equals(Object other){
		return other instanceof Pezzo && color == ((Pezzo) other).color &&
				image.toString().equals(((Pezzo) other).getImage().toString());
	}
}
