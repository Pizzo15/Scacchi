package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Regina extends Pezzo {
	public Regina(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/reginanera.png") : new ImageIcon("img/reginabianca.png"));
	}
}
