package it.luca.chessgame.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.luca.chessgame.controller.Controller;
import it.luca.chessgame.model.ArrayConfiguration;
import it.luca.chessgame.model.Model;
import it.luca.chessgame.moves.Move;

import javax.swing.*;

/**
 * Dialogo che mostra il registro delle mosse effettuate dai due giocatori
 * fino a quel momento.
 * 
 * @author luca
 */
public class LogDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextArea whiteArea;
	private JTextArea blackArea;
	
	public LogDialog(JFrame owner, Model model){
		setTitle("Registro mosse");
		setResizable(false);

		JPanel logPanel = new JPanel();
		whiteArea = new JTextArea(5, 10);
		whiteArea.setEditable(false);
		whiteArea.append(" Bianco\n");
		whiteArea.setBackground(getBackground());
		
	
		blackArea = new JTextArea(5, 10);
		blackArea.setEditable(false);
		blackArea.append(" Nero\n");
		blackArea.setBackground(getBackground());
		
		JScrollPane scrollPane = new JScrollPane(whiteArea);
		
		logPanel.add(scrollPane, BorderLayout.WEST);
		
		scrollPane = new JScrollPane(blackArea);
		
		logPanel.add(scrollPane, BorderLayout.EAST);
		
		add(logPanel, BorderLayout.CENTER);

		pack();
		
		setLocation(50, owner.getLocation().y);
	}
	
	public void insert(String msg, boolean player){
		if(player)
			whiteArea.append(msg);
		else
			blackArea.append(msg);
		
		validate();
		repaint();
		pack();
	}
	
	public void delete(boolean player){
		if(player)
			whiteArea.remove(whiteArea.getLineCount());
		else
			blackArea.remove(blackArea.getLineCount());
		
		validate();
		repaint();
		pack();
	}
	
	private void cleanLogs(){
		whiteArea.removeAll();
		blackArea.removeAll();
		
		validate();
		repaint();
		pack();
	}
}
