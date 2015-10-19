package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Pedone extends Pezzo {	
	public Pedone(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/pedonenero.png") : new ImageIcon("img/pedonebianco.png"));
	}
}
