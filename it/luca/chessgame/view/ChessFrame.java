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
	
	public ChessFrame(){
		setTitle("ChessGame");
		setResizable(false);
		
		d = Toolkit.getDefaultToolkit().getScreenSize();
		
		View view = addTiles();
		controller = new ChessController(view);

		addBorder();
		addMenu();
		
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
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(Color.GRAY);
		
		// 1° voce: Partita
		JMenu gameMenu = new JMenu("Partita");
		gameMenu.setBackground(Color.GRAY);
		
		JMenuItem newItem = new JMenuItem("Nuova Partita");
		gameMenu.add(newItem);
		
		JMenuItem exitItem = new JMenuItem("Esci");
		gameMenu.add(exitItem);
		
		newItem.addActionListener(event -> controller.getMover().newGame());
		
		exitItem.addActionListener(event -> {
			int selection = JOptionPane.showConfirmDialog(ChessFrame.this, "Uscire?", "", JOptionPane.YES_NO_OPTION);
			if(selection == JOptionPane.OK_OPTION)
				System.exit(0);
		});
				
		// 2° voce: Mosse
		JMenu moveMenu = new JMenu("Mosse");
		moveMenu.setBackground(Color.GRAY);
		
		JMenuItem undoItem = new JMenuItem("Annulla mossa");
		moveMenu.add(undoItem);
		
		undoItem.addActionListener(event -> controller.getMover().undo());
				
		JMenuItem logItem = new JMenuItem("Registro");
		moveMenu.add(logItem);
		
		logItem.addActionListener(event -> controller.getView().showLog());
		
		// 3° voce: Scacchi
		JMenu aboutMenu = new JMenu("Scacchi");
		aboutMenu.setBackground(Color.GRAY);
		JMenuItem infoItem = new JMenuItem("Info");
		infoItem.addActionListener(event -> JOptionPane.showMessageDialog(ChessFrame.this, "Scacchi\nVersione 1.0\n@2015 Luca Pizzini", 
						"", 0, new ImageIcon("img/infoicon.png")));
		
		aboutMenu.add(infoItem);
		
		menuBar.add(gameMenu);
		menuBar.add(moveMenu);
		menuBar.add(aboutMenu);
		
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
}
