package it.luca.chessgame.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import it.luca.chessgame.controller.Controller;
import it.luca.chessgame.model.*;

import javax.swing.*;

public class TilesPanel extends JPanel implements View, MouseListener {
	private final JFrame frame;
	private final Model model;
	private final JPanel[][] panels = new JPanel[8][8];
	private Controller controller;
	private static int count = 1;
	private static int pieceX, pieceY;
	
	public TilesPanel(Model model, JFrame frame){
		this.model = model;
		this.frame = frame;
	
		createPieces();
		
		model.setView(this);

		addMouseListener(this);
	}
	
	public Model getModel(){
		return model;
	}
	
	private void createPieces(){
		setLayout(new GridLayout(8, 8));
		setSize(600, 600);
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				add(panels[x][y] = mkPiece(x, y, model.at(x, y), (x + y) % 2 == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY));
	}
	
	private JPanel mkPiece(int x, int y, Pezzo p, Color c){
		JPanel panel = new JPanel();
		panel.setBackground(c);
		panel.add(new JLabel(p.getImage()));
		
		return panel;
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void onConfigurationChange() {			
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				if(!(model.at(x, y) instanceof CasellaVuota)) {
					panels[x][y].removeAll();
					panels[x][y].add(new JLabel(model.at(x, y).getImage()));
				}
				else
					panels[x][y].removeAll();	
	}
	
	/**
	 * Marca con un'icona le caselle raggiungibili dal pezzo alle coordinate 
	 * (x, y) se questo può muovere.
	 * @param x
	 * @param y
	 */
	public void highlightReachableTiles(int x, int y){
		ArrayList<Point> reachable = controller.getMover().reachableTilesFrom(x, y);
		
		for(Point p: reachable)
			panels[p.x][p.y].add(new JLabel(new ImageIcon("img/pointicon.png")));
	}
	
	/**
	 * Elimina le icone aggiunte dal metodo sopra.
	 */
	private void cleanReachableTiles(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				if(model.at(x, y) instanceof CasellaVuota)
					panels[x][y].removeAll();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// lato di una casella della scacchiera
		int dim = this.getSize().width / 8;
		
		// ottengo le coordinate della casella cliccata
		int x = e.getX() / dim;
		int y = e.getY() / dim;
	
		// 1° click: salvo le coordinate del pezzo da muovere se la casella non è vuota
		// e non premo un pezzo dell'altro schieramento
		// e mostro le caselle raggiungibili
		if(count % 2 != 0){
			if(!(model.at(x, y) instanceof CasellaVuota) && 
					(controller.getMover().getTurno() ? model.at(x, y).getColor() == Color.WHITE : model.at(x, y).getColor() == Color.BLACK)){
				pieceX = x;
				pieceY = y;
				highlightReachableTiles(x, y);
				count++;
			}
		} else {
			// 2° click: se la casella cliccata è tra le raggiungibili muovo
			cleanReachableTiles();
			count++;
			controller.onClick(pieceX, pieceY, x, y);
			// controllo se ho dato scacco matto
			if(controller.getMover().scaccoMatto()){
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Scacco Matto!", 
						(controller.getMover().getTurno() ? "Bianco" : "Nero") + " vince!", 0, new ImageIcon("img/winicon.png"));
				controller.getMover().newGame();
			}
		}

		// aggiorno la view
		validate();
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void showLog(){
		new LogDialog(frame, controller).setVisible(true);
	}
}