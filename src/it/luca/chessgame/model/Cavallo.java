package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Cavallo extends Pezzo {
	public Cavallo(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/cavallonero.png") : new ImageIcon("img/cavallobianco.png"));
	}
}
