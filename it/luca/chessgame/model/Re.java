package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Re extends Pezzo {
	public Re(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/renero.png") : new ImageIcon("img/rebianco.png"));
	}
}
