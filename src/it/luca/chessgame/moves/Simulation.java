package it.luca.chessgame.moves;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.luca.chessgame.model.Configuration;
import it.luca.chessgame.model.TilesModel;
import it.luca.chessgame.view.ChessFrame;
import it.luca.chessgame.view.TilesPanel;

/**
 * Classe che mostra a video una simulazione del registro delle mosse effettuate.
 * L'ultima configurazione è passata come parametro.
 */
public class Simulation {
	private TilesPanel panel;
	private JFrame frame = new JFrame("Simulazione");
	private final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean buttonPressed = false;
	private static int count = 0;
	
	public Simulation(Stack<Configuration> history, Configuration last){		
		boolean singleConfig = history.isEmpty();

		if(!singleConfig)
			panel = new TilesPanel(new TilesModel(history.get(count)), new ChessFrame());
		else
			panel = new TilesPanel(new TilesModel(last), new ChessFrame());
			
		frame.add(panel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(event -> buttonPressed = true);
		
		if(!singleConfig){
			JButton nextButton = new JButton("Next");
			nextButton.addActionListener(event -> 
			{
				if(count < history.size() - 1){
					panel.getModel().setConfiguration(history.get(++count));
					frame.validate();
					frame.repaint();
				} else {
					panel.getModel().setConfiguration(last);
					frame.validate();
					frame.repaint();				
				}
			});
			
			JButton prevButton = new JButton("Prev");
			prevButton.addActionListener(event -> 
			{
				if(count >= 0){
					panel.getModel().setConfiguration(history.get(count--));
					frame.validate();
					frame.repaint();
				}
			});
			
			buttonPanel.add(prevButton);
			buttonPanel.add(nextButton);
		}
		
		buttonPanel.add(okButton);
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.pack();
		
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		
		frame.setVisible(true);
			
		while(buttonPressed == false)
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) { }
		
		frame.dispose();
	}
}
