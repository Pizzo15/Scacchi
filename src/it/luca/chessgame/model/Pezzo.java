package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public abstract class Pezzo {
	private final Color color;
	private final ImageIcon image;
	
	/**
	 * Un pezzo è identificato dal colore, dall'icona che lo rappresenta
	 * e dalle sue coordinate
	 */
	public Pezzo(Color color, ImageIcon image){
		this.color = color;
		this.image = image;
	}
	
	public Color getColor(){ return this.color; }
	public ImageIcon getImage(){ return this.image; }
}
