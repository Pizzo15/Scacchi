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

	public ChessFrame(TilesPanel panel){
		setTitle("ChessGame");
		setResizable(false);
		
		d = Toolkit.getDefaultToolkit().getScreenSize();
		
		View view = panel;
		controller = new ChessController(view);

		add(panel, BorderLayout.CENTER);
		addBorder();
		addMenu();
		
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
		
		JPanel southBorder = new JPanel();
		southBorder.setBackground(Color.GRAY);
		southBorder.setLayout(new GridLayout(1, 8));
		
		southBorder.add(new JLabel("A", SwingConstants.CENTER));
		southBorder.add(new JLabel("B", SwingConstants.CENTER));
		southBorder.add(new JLabel("C", SwingConstants.CENTER));
		southBorder.add(new JLabel("D", SwingConstants.CENTER));
		southBorder.add(new JLabel("E", SwingConstants.CENTER));
		southBorder.add(new JLabel("F", SwingConstants.CENTER));
		southBorder.add(new JLabel("G", SwingConstants.CENTER));
		southBorder.add(new JLabel("H", SwingConstants.CENTER));

		add(southBorder, BorderLayout.SOUTH);
	}	
	
	public LogPanel getLog(){
		return logPanel;
	}
	
	private void addLog(){
		logPanel = new LogPanel(this, controller.getMover());
		
		add(logPanel, BorderLayout.EAST);
	}
}
