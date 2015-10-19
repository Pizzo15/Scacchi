package it.luca.chessgame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.luca.chessgame.controller.Controller;
import it.luca.chessgame.moves.Move;

import javax.swing.*;

public class LogDialog extends JDialog {
	public LogDialog(JFrame owner, Controller controller){
		setTitle("Registro mosse");
		setResizable(false);

		JPanel logPanel = new JPanel();
		JTextArea whiteArea = new JTextArea(5, 15);
		whiteArea.setEditable(false);
		whiteArea.append("Bianco\n");
		whiteArea.setBackground(getBackground());
		
		int count = 1;
		
		for(Move m: controller.getMover().getWhiteLog()){
			whiteArea.append(count + ": " + m.toString());
			count++;
		}
		
		JTextArea blackArea = new JTextArea(5, 15);
		blackArea.setEditable(false);
		blackArea.append("Nero\n");
		blackArea.setBackground(getBackground());
		
		count = 1;
		
		for(Move m: controller.getMover().getBlackLog()){
			blackArea.append(count + ": " + m.toString());
			count++;
		}
			
		JButton okButton = new JButton("Ok");
		
		okButton.addActionListener(event-> dispose());
		
		JScrollPane scrollPane = new JScrollPane(whiteArea);
		
		logPanel.add(scrollPane, BorderLayout.WEST);
		
		scrollPane = new JScrollPane(blackArea);
		
		logPanel.add(scrollPane, BorderLayout.EAST);
		
		add(logPanel, BorderLayout.CENTER);
		add(okButton, BorderLayout.SOUTH);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		pack();
		
		setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
	}
}
