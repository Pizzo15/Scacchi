package it.luca.chessgame.model;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Alfiere extends Pezzo {
	public Alfiere(Color color){
		super(color, color == Color.BLACK ? new ImageIcon("img/alfierenero.png") : new ImageIcon("img/alfierebianco.png"));		
	}
}
