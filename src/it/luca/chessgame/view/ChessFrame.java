package it.luca.chessgame.view;

import java.awt.*;

import it.luca.chessgame.controller.*;
import it.luca.chessgame.model.*;

import javax.swing.*;

/**
 * Frame contenente il gioco.
 * 
 * @author luca
 */
public class ChessFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final TilesModel model = new TilesModel(new ArrayConfiguration());
	private final Controller controller;
	private Dimension d;
	private LogPanel logPanel;
	
	public ChessFrame(){
		setTitle("ChessGame");
		setResizable(false);
		
		d = Toolkit.getDefaultToolkit().getScreenSize();
		
		View view = addTiles();
		controller = new ChessController(view);

		addBorder();
		addMenu();
		addLog();
		
		pack();

		setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
	}
	
	/**
	 * Aggiunge il pannello con la scacchiera al centro del frame
	 * e lo ritorna.
	 */
	private View addTiles() {
		TilesPanel panel = new TilesPanel(model, this);
		
		add(panel, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * Aggiunge un menù in alto nel frame.
	 */
	private void addMenu(){
		MenuBar menuBar = new MenuBar(this, controller.getMover());
		
		add(menuBar, BorderLayout.NORTH);
	}
	
	/**
	 * Aggiunge due pannelli a sinistra e in basso della scacchiera
	 * che rappresentano le coordinate delle caselle.
	 */
	private void addBorder(){
		JPanel westBorder = new JPanel();
		westBorder.setLayout(new GridLayout(8, 1));
		westBorder.setBackground(Color.GRAY);
		
		westBorder.add(new JLabel("8"));
		westBorder.add(new JLabel("7"));
		westBorder.add(new JLabel("6"));
		westBorder.add(new JLabel("5"));
		westBorder.add(new JLabel("4"));
		westBorder.add(new JLabel("3"));
		westBorder.add(new JLabel("2"));
		westBorder.add(new JLabel("1"));
		
		add(westBorder, BorderLayout.WEST);
		
		JTextArea south = new JTextArea(1, 50);
		String space = "          ";
		String space2 = "      ";
		
		south.setBackground(Color.GRAY);
		
		south.append(space + "A"+ space2);
		south.append(space + "B"+ space2);
		south.append(space + "C"+ space2);
		south.append(space + "D"+ space2);
		south.append(space + "E"+ space2);
		south.append(space + "F"+ space2);
		south.append(space + "G"+ space2);
		south.append(space + "H"+ space2);

		add(south, BorderLayout.SOUTH);
	}	
	
	public LogPanel getLog(){
		return logPanel;
	}
	
	private void addLog(){
		logPanel = new LogPanel(this, controller.getMover());
		
		add(logPanel, BorderLayout.EAST);
	}
}
