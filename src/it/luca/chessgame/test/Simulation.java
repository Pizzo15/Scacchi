package it.luca.chessgame.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.view.ChessFrame;
import it.luca.chessgame.view.TilesPanel;

/**
 * Classe che mostra a video una simulazione della configurazione attuale, mostra
 * un messaggio di descrizione e attende finchè l'utente preme il pulsante.
 */
public class Simulation {
	private TilesPanel panel;
	private JFrame frame = new JFrame("Simulazione");
	private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean buttonPressed = false;
	
	public Simulation(Configuration c, String description){
		panel = new TilesPanel(new TilesModel(c), new ChessFrame());
			
		frame.add(panel, BorderLayout.CENTER);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(event -> buttonPressed = true);
		
		frame.add(okButton, BorderLayout.EAST);
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.GRAY);
		
		JLabel label = new JLabel(description);
		textPanel.add(label);
		
		frame.add(textPanel, BorderLayout.SOUTH);
		
		frame.pack();
		
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		
		frame.setVisible(true);
			
		while(buttonPressed == false)
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) { }
		}
}
