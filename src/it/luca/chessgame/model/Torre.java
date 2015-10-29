package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Torre extends Pezzo {
	public Torre(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/torrenera.png") : new ImageIcon("img/torrebianca.png"));
	}
}
