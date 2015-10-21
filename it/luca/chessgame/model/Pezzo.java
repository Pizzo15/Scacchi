package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public abstract class Pezzo {
	private Color color;
	private ImageIcon image;
	
	/**
	 * Un pezzo è identificato dal colore e dall'icona che lo rappresenta
	 * @param color
	 * @param image
	 */
	public Pezzo(Color color, ImageIcon image){
		this.color = color;
		this.image = image;
	}
	
	public Color getColor(){ return this.color; }
	public ImageIcon getImage(){ return this.image; }
}
