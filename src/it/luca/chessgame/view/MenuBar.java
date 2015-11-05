package it.luca.chessgame.view;

import it.luca.chessgame.moves.Mover;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar(ChessFrame owner, Mover mover){
		setBorder(null);
		
		// 1° voce: Partita
		JMenu gameMenu = new JMenu("Partita");
		gameMenu.setBackground(Color.GRAY);
		
		JMenuItem newItem = new JMenuItem("Nuova Partita");
		gameMenu.add(newItem);
		
		newItem.addActionListener(event -> {
			mover.newGame();
			
			owner.getLog().clean();
			
			owner.validate();
			owner.repaint();
		});
		
		JMenuItem exitItem = new JMenuItem("Esci");
		gameMenu.add(exitItem);
		
		exitItem.addActionListener(event -> {
			int selection = JOptionPane.showConfirmDialog(owner, "Uscire?", "", JOptionPane.YES_NO_OPTION);
			if(selection == JOptionPane.OK_OPTION)
				System.exit(0);
		});
				
		// 2° voce: Mosse
		JMenu moveMenu = new JMenu("Mosse");
		moveMenu.setBackground(Color.GRAY);
		
		JMenuItem undoItem = new JMenuItem("Annulla mossa");
		moveMenu.add(undoItem);
		
		undoItem.addActionListener(event -> {
			mover.undo();
		
			try {
				owner.getLog().undo(mover.getTurno());
			} catch (Exception e) { }
			
			owner.validate();
			owner.repaint();
		});
		
		add(gameMenu);
		add(moveMenu);
	}
}
