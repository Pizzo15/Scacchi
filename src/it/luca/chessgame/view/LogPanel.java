package it.luca.chessgame.view;

import java.awt.BorderLayout;

import it.luca.chessgame.moves.Mover;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class LogPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextArea whiteArea, blackArea;
	private final String whiteTitle = "Bianco";
	private final String blackTitle = "Nero";
	private static int count = 1;
	
	public LogPanel(JFrame owner, Mover mover){
		whiteArea = new JTextArea(whiteTitle, 35, 10);
		whiteArea.setEditable(false);
		whiteArea.setBackground(getBackground());
		
		blackArea = new JTextArea(blackTitle, 35, 10);
		blackArea.setEditable(false);
		blackArea.setBackground(getBackground());
		
		JScrollPane scrollPane = new JScrollPane(whiteArea);
		
		add(scrollPane, BorderLayout.WEST);
		
		scrollPane = new JScrollPane(blackArea);
		
		add(scrollPane, BorderLayout.EAST);
	}
	
	public void insert(String move, boolean player){
		if(player)
			whiteArea.append("\n" + count + ": " + move);
		else {
			blackArea.append("\n" + count + ": " + move);
			count++;
		}
	}
	
	public void undo(boolean player) throws BadLocationException{
		if(player)
			removeLastLine(whiteArea);
		else {
			removeLastLine(blackArea);
			count--;
		}
	}

	private void removeLastLine(JTextArea area) throws BadLocationException{
		String content = area.getDocument().getText(0, area.getDocument().getLength());
		int lastLineBreak = content.lastIndexOf('\n');
		area.getDocument().remove(lastLineBreak, area.getDocument().getLength() - lastLineBreak);
	}
	
	public void clean(){
		whiteArea.setText(whiteTitle);
		blackArea.setText(blackTitle);
		count = 1;
	}
}
