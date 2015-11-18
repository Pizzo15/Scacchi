package it.luca.chessgame.view;

import java.awt.BorderLayout;
import java.awt.Color;

import it.luca.chessgame.moves.Mover;

import javax.swing.*;

public class EndGameDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public EndGameDialog(JFrame owner, Mover mover, String msg){
		setSize(250, 100);
		setResizable(false);
		
		JPanel textPanel = new JPanel();
		JLabel text = new JLabel(msg);
		textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		textPanel.add(text);
		
		JPanel buttonPanel = new JPanel();
		JButton newGame = new JButton("New Game...");
		
		newGame.addActionListener(event -> 
			{
				dispose();
				mover.newGame();
				owner.validate();
				owner.repaint();
			});
		
		JButton exit = new JButton("Exit");
		
		exit.addActionListener(event -> 
			{
				owner.dispose();
				dispose();
			});
		
		buttonPanel.add(newGame);
		buttonPanel.add(exit);
		
		add(textPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(owner);
	}
}
