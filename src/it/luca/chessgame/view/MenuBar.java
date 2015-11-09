package it.luca.chessgame.view;

import it.luca.chessgame.moves.Mover;

import javax.swing.*;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private static boolean help = false;
	
	public MenuBar(ChessFrame owner, Mover mover){
		setBorder(null);
		
		// 1° voce: Partita
		JMenu gameMenu = new JMenu("Partita");

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
		
		moveMenu.addSeparator();
		
		JMenu helpMenu = new JMenu("Aiuto");
		moveMenu.add(helpMenu);
		
		// Se attivati evidenziano le caselle raggiungibili dal pezzo selezionato e i pezzi
		// movibili
		JCheckBoxMenuItem showReachable = new JCheckBoxMenuItem("Suggerimenti");
		helpMenu.add(showReachable);
		
		showReachable.addActionListener(event ->
		{
			help = showReachable.isSelected();
		});
		
		add(gameMenu);
		add(moveMenu);
	}
	
	public boolean getHelp(){
		return help;
	}
}
