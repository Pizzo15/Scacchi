package it.luca.chessgame.view;

import java.awt.BorderLayout;

import it.luca.chessgame.moves.Mover;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class LogPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextArea whiteArea, blackArea, countArea;
	private final String whiteTitle = " Bianco";
	private final String blackTitle = " Nero";
	private static int count = 1;
	
	public LogPanel(JFrame owner, Mover mover){
		countArea = new JTextArea(" #", 35, 2);
		countArea.setEditable(false);
		countArea.setBackground(getBackground());
		
		whiteArea = new JTextArea(whiteTitle, 35, 7);
		whiteArea.setEditable(false);
		whiteArea.setBackground(getBackground());
		
		blackArea = new JTextArea(blackTitle, 35, 7);
		blackArea.setEditable(false);
		blackArea.setBackground(getBackground());
		
		JScrollPane scrollPane = new JScrollPane(countArea);
		
		add(scrollPane, BorderLayout.WEST);
		
		scrollPane = new JScrollPane(whiteArea);
		
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane(blackArea);
		
		add(scrollPane, BorderLayout.EAST);
	}
	
	/**
	 * Inserisce move nell'area di testo di player.
	 */
	public void insert(String move, boolean player){
		if(player) {
			countArea.append("\n " + count);
			whiteArea.append("\n " + move);
		} else {
			blackArea.append("\n " + move);
			count++;
		}
	}
	
	/**
	 * Aggiunge un simbolo che indica una situazione di scacco o
	 * scacco matto nel registro.
	 */
	public void addFinale(String finale, boolean player){
		if(player)
			whiteArea.append(finale);
		else
			blackArea.append(finale);
	}
	
	/**
	 * Rimuove l'ultima mossa inserita nell'area di testo di player.
	 */
	public void undo(boolean player) throws BadLocationException{
		if(player){
			removeLastLine(whiteArea);
			removeLastLine(countArea);
		} else {
			removeLastLine(blackArea);
			count--;
		}
	}

	/**
	 * Rimuove l'ultima riga di testo da area.
	 */
	private void removeLastLine(JTextArea area) throws BadLocationException{
		String content = area.getDocument().getText(0, area.getDocument().getLength());
		int lastLineBreak = content.lastIndexOf('\n');
		area.getDocument().remove(lastLineBreak, area.getDocument().getLength() - lastLineBreak);
	}

	/**
	 * Ripristina la situazione iniziale delle aree di testo.
	 */
	public void clean(){
		countArea.setText(" #");
		whiteArea.setText(whiteTitle);
		blackArea.setText(blackTitle);
		count = 1;
	}
}
