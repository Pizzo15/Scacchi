package it.luca.chessgame.moves;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.*;

import javax.swing.*;

import it.luca.chessgame.model.*;

public class Mover {
	private final Model model;
	private boolean turno;	// T = bianco, F = nero
	private Stack<Configuration> undos = new Stack<Configuration>();
	private Stack<Move> whiteLog = new Stack<Move>();
	private Stack<Move> blackLog = new Stack<Move>();
	
	public Mover(Model model){
		this.model = model;
		this.turno = true;
	}
	
	/**
	 * Esegue la mossa (fromX, fromY) -> (toX, toY)
	 * se questa è lecita.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void move(int fromX, int fromY, int toX, int toY){
		if(moveLegal(fromX, fromY, toX, toY)){
			// salvo la configurazione corrente
			undos.push(model.getConfiguration());
			// aggiungo la mossa da effettuare al registro
			addMoveToLog(fromX, fromY, toX, toY);
			// aggiorno la configurazione scambiando le caselle
			model.setConfiguration(model.getConfiguration().swap(fromX, fromY, toX, toY));
			// cambio il turno
			turno = !turno;
		}
	}
	
	/**
	 * Ritorna la legalità o meno della mossa effettuata.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	public boolean isMoveLegal(int fromX, int fromY, int toX, int toY){
		return moveLegal(fromX, fromY, toX, toY);
	}

	/**
	 * Aggiunge la mossa al registro di uno dei due giocatori in 
	 * base al turno specificando se si tratta di una cattura.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	private void addMoveToLog(int fromX, int fromY, int toX, int toY){
		boolean eat = model.at(toX, toY) instanceof CasellaVuota ? false : true;
		if(turno)
			whiteLog.push(new Move(fromX, fromY, toX, toY, eat));
		else
			blackLog.push(new Move(fromX, fromY, toX, toY, eat));
	}
	
	public void setTurno(boolean turno){
		this.turno = turno;
	}
	
	public boolean getTurno(){ return turno; }
	
	public Stack<Move> getWhiteLog(){ return whiteLog; }
	
	public Stack<Move> getBlackLog(){ return blackLog; }
	
	/**
	 * Scacco matto: imposto una nuova partita nel caso di si, altrimenti esco.
	 * Non scacco matto: nel caso di selezione negativa resto sulla partita attuale
	 * e non faccio nulla.
	 */
	public void newGame(){
		int selection = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Nuova partita?", "", 
				JOptionPane.YES_NO_OPTION);
		if(!scaccoMatto()){
			if(selection == JOptionPane.YES_OPTION && !undos.isEmpty()) {
				setNewGame();
			}
		} else {
			if(selection == JOptionPane.YES_OPTION)
				setNewGame();
			else 
				System.exit(0);
		}
	}
	
	/**
	 * Imposta la configurazione a quella iniziale e svuota i registri delle mosse
	 * effettuate.
	 */
	private void setNewGame(){
		turno = true;
		model.setConfiguration(new ArrayConfiguration());
		blackLog.removeAllElements();
		whiteLog.removeAllElements();
	}
	
	/**
	 * Annulla l'ultima mossa effettuata e riporta la scacchiera alla configurazione
	 * precedente. Se nessuna mossa è stata effettuata stampa un messaggio di errore.
	 */
	public void undo(){
		if(!undos.isEmpty()){
			int selection = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Annullare l'ultima mossa?", "",
					JOptionPane.YES_NO_OPTION, 0, new ImageIcon("img/logicon.png"));
			if(selection == JOptionPane.YES_OPTION){
				model.setConfiguration(undos.pop());
				removeMoveFromLog();
				simulateMouseClick();
				turno = !turno;
			}
		}
		else
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Nessuna mossa da annullare", "Info", 
					JOptionPane.WARNING_MESSAGE, new ImageIcon("img/logicon.png"));
	}
	
	/**
	 * Rimuove una mossa dal registro nel caso questa venga annullata.
	 */
	private void removeMoveFromLog() {
		if(!turno)
			whiteLog.pop();
		else
			blackLog.pop();
	}
	
	/**
	 * Controlla che la casella di arrivo non sia occupata da un pezzo dello stesso
	 * colore di quello selezionato.
	 * In tal caso blocca la mossa ritornando false.
	 * @return
	 */
	private boolean checkColor(int fromX, int fromY, int toX, int toY){
		return model.at(fromX, fromY).getColor() != model.at(toX, toY).getColor();
	}
	
	/** 
	 * Controlla che l'alternanza dei turni sia rispettata.
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 * @return
	 */
	private boolean checkTurn(int fromX, int fromY, int toX, int toY){
		if(turno){
			if(model.at(fromX, fromY).getColor() == Color.WHITE)
				return true;
		} else {
			if(model.at(fromX, fromY).getColor() == Color.BLACK)
				return true;
		}
		
		return false;
	}
	
	
	private boolean checkMove(int fromX, int fromY, int toX, int toY){
		if(model.at(fromX, fromY) instanceof Pedone) {
			if(model.at(fromX, fromY).getColor() == Color.BLACK && toY - fromY == 1) {
				if(fromX - toX == 0 && model.at(toX, toY) instanceof CasellaVuota)
					return true;
				else if(!(model.at(toX, toY) instanceof CasellaVuota) && Math.abs(toX - fromX) == 1)
					return true;
			}
			else if (model.at(fromX, fromY).getColor() == Color.WHITE && fromY - toY == 1) {
				if(model.at(toX, toY) instanceof CasellaVuota && fromX - toX == 0)
					return true;
				else if(!(model.at(toX, toY) instanceof CasellaVuota) && Math.abs(toX - fromX) == 1)
					return true;
			}
		} else if(model.at(fromX, fromY) instanceof Cavallo) {
			return (Math.abs(toX-fromX) == 2 && Math.abs(toY-fromY) == 1) || 
					(Math.abs(toX-fromX) == 1 && Math.abs(toY-fromY) == 2);
		} else if(model.at(fromX, fromY) instanceof Re) {
			return Math.abs(toX - fromX) < 2 && Math.abs(toY - fromY) < 2;		
		} else if(model.at(fromX, fromY) instanceof Torre) {
			return (Math.abs(toY-fromY) == 0 || Math.abs(toX-fromX) == 0) && checkRookJump(fromX, fromY, toX, toY);		
		} else if(model.at(fromX, fromY) instanceof Alfiere) {
			return Math.abs(fromX-toX) == Math.abs(fromY-toY) && checkBishopJump(fromX, fromY, toX, toY);
		} else if(model.at(fromX, fromY) instanceof Regina) {
			return (checkRookJump(fromX, fromY, toX, toY) && (Math.abs(toY-fromY) == 0 || Math.abs(toX-fromX) == 0)) ||
					(Math.abs(fromX-toX) == Math.abs(fromY-toY) && checkBishopJump(fromX, fromY, toX, toY));
		}
		return false;
	}
	
	/**
	 * Ritorna le coordinate dei pezzi che tengono sotto scacco 
	 * una casella.
	 * @return
	 */
	private ArrayList<Point> scacco(int kingX, int kingY){
		Color oppColor = turno ? Color.BLACK : Color.WHITE;
		ArrayList<Point> res = new ArrayList<Point>();
		
		// Per ogni pezzo avversario salvo le coordinate
		// Se la mossa dal pezzo al re è possibile ritorno true
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				if(!(model.at(x, y) instanceof CasellaVuota) &&
						model.at(x, y).getColor() == oppColor &&
						checkColor(x, y, kingX, kingY) &&
						checkMove(x, y, kingX, kingY))
					res.add(new Point(x, y));				
		
		return res;
	}

	/**
	 * Nel caso il re sia sotto scacco le mosse ammesse sono:
	 * - il re scappa in una casella sicura sottraendosi allo scacco
	 * - mangiare il pezzo che minaccia lo scacco se questo è unico
	 */
	private boolean checkMoveUnderScacco(int fromX, int fromY, int toX, int toY){
		boolean canEscape = false, canBeEaten = false;
		
		if(kingCanEscape())
			canEscape =  model.at(fromX, fromY) instanceof Re && isSafe(toX, toY);

		canBeEaten = canReachCheckHolder(fromX, fromY) && isCheckHolder(toX, toY);
		
		return  canBeEaten || canEscape;
	}
	
	private boolean canReachCheckHolder(int x, int y){
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		Point checkHolder = scacco(kingX, kingY).get(0);
		
		return checkColor(x, y, checkHolder.x, checkHolder.y) && checkTurn(x, y, checkHolder.x, checkHolder.y)
				&& checkMove(x, y, checkHolder.x, checkHolder.y);
	}
	
	private boolean isCheckHolder(int x, int y){
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		
		for(Point p: scacco(kingX, kingY))
			if(p.equals(new Point(x, y)))
				return true;
		
		return false;
	}
	
	private boolean kingCanEscape(){
		ArrayList<Point> free = freeTilesAroundKing();

		// controllo le caselle libere non sotto scacco
		for(Point p: free)
			// se almeno una di queste non è sotto la minaccia diretta di un pezzo avversario
			// posso provare a scappare
			if(scacco(p.x, p.y).isEmpty())
				return true;
		
		return false;
	}
	
	private boolean isSafe(int x, int y){
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		
		// simulo la mossa del re nella casella libera non sotto scacco
		model.setConfiguration(model.getConfiguration().swap(kingX, kingY, x, y));			
		// se non è sotto scacco -> posso scappare
		if(scacco(x, y).isEmpty()){
			model.setConfiguration(model.getConfiguration().swap(x, y, kingX, kingY));		
			return true;	
		}
		model.setConfiguration(model.getConfiguration().swap(x, y, kingX, kingY));	
		
		return false;
	}
	
	/**
	 * Controlla se la mossa (fromX, fromY) -> (toX, toY) è legale:
	 *  - i colori dei pezzi sono diversi
	 *  - il turno è coerente col pezzo da muovere
	 *  - le coordinate sono raggiungibili dal pezzo mosso
	 */
	private boolean moveLegal(int fromX, int fromY, int toX, int toY){
		boolean legal = checkColor(fromX, fromY, toX, toY) && checkTurn(fromX, fromY, toX, toY)
				&& checkMove(fromX, fromY, toX, toY);
		
		if(scacco(getKingPosition().x, getKingPosition().y).isEmpty())
			return legal;
		else
			return legal && checkMoveUnderScacco(fromX, fromY, toX, toY);
	}
	
	/**
	 * Ritorna true se uno dei giocatori ha dato scacco matto all'
	 * altro. In tal caso termina la partita.
	 */
	public boolean scaccoMatto(){
		ArrayList<Point> free = freeTilesAroundKing();
		ArrayList<Point> notOnScacco = new ArrayList<Point>();
		Point check;
		
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		
		// se il re non è sotto scacco: false
		if(scacco(kingX, kingY).isEmpty())
			return false;
		
		// controllo le caselle libere non sotto scacco
		for(Point p: free)
			if(scacco(p.x, p.y).isEmpty())
				notOnScacco.add(p);
		
		// se le caselle libere non sono tutte sotto scacco:
		// controllo se il re è in trappola
		if(!notOnScacco.isEmpty()){
			for(Point p: notOnScacco){
				// simulo la mossa del re nella casella libera non sotto scacco
				model.setConfiguration(model.getConfiguration().swap(getKingPosition().x, getKingPosition().y, p.x, p.y));			
				// se non è sotto scacco -> non è scacco matto
				if(scacco(p.x, p.y).isEmpty()){
					model.setConfiguration(model.getConfiguration().swap(p.x, p.y, kingX, kingY));		
					return false;	
				}
				model.setConfiguration(model.getConfiguration().swap(p.x, p.y, kingX, kingY));
			}
		} else {
			// tutte le caselle libere sono sotto scacco
			// controllo se posso mangiare il pezzo che tiene
			// sotto scacco il re -> se questo è unico non è scacco
				if(scacco(kingX, kingY).size() == 1){
					// salvo coordinate del pezzo che tiene in scacco
					check = scacco(kingX, kingY).get(0);
					turno = !turno;
					// se il pezzo che tiene lo scacco non è catturabile al prossimo turno -> scacco matto
					if(scacco(check.x, check.y).isEmpty()){
						return true;
					} else {
						// altrimenti non è scacco matto: la partita continua
						turno = !turno;
						return false;
					}
				}
		}
		
		return true;
	}
	
	/**
	 * Controlla che la mossa della torre non richieda un salto.
	 * In tal caso la blocca ritornando false.
	 */
	private boolean checkRookJump(int fromX, int fromY, int toX, int toY){
		// considero le 4 direzioni di movimento possibili per una torre
		// verticale...
		if(fromX - toX == 0){
			// ...in alto...
			if(fromY > toY) {
				for(int y = fromY - 1; y > toY; y--)
					if(!(model.at(fromX, y) instanceof CasellaVuota))
						return false;
			} else {
				// ...in basso
				for(int y = fromY + 1; y < toY; y++)
					if(!(model.at(fromX, y) instanceof CasellaVuota))
						return false;
			}
		}
		// orizzontale...
		else {
			// ...in alto...
			if(fromX > toX) {
				for(int x = fromX - 1; x > toX; x--)
					if(!(model.at(x, fromY) instanceof CasellaVuota))
						return false;
			} else {
				// ...in basso
				for(int x = fromX + 1; x < toX; x++)
					if(!(model.at(x, fromY) instanceof CasellaVuota))
						return false;
			}
		}
				
		return true;
	}
	
	/**
	 * Controlla che la mossa dell'alfiere non richieda un salto.
	 * In tal caso la blocca ritornando false.
	 */
	private boolean checkBishopJump(int fromX, int fromY, int toX, int toY){
		// considero le 4 direzioni di movimento possibili per un alfiere
		// alto a destra...
		if(fromX < toX && fromY > toY)
			for(int x = fromX + 1, y = fromY - 1; x < toX; x++, y--)
				if(!(model.at(x, y) instanceof CasellaVuota))
					return false;
		// ...alto a sinistra...
		if(fromX > toX && fromY > toY)
			for(int x = fromX - 1, y = fromY - 1; x > toX; x--, y--)
				if(!(model.at(x, y) instanceof CasellaVuota))
					return false;
		// ...basso a destra...
		if(fromX < toX && fromY < toY)
			for(int x = fromX + 1, y = fromY + 1; x < toX; x++, y++)
				if(!(model.at(x, y) instanceof CasellaVuota))
					return false;
		// ...basso a sinistra
		if(fromX > toX && fromY < toY)
			for(int x = fromX - 1, y = fromY + 1; x > toX; x--, y++)
				if(!(model.at(x, y) instanceof CasellaVuota))
					return false;
		
		return true;
	}
	
	/**
	 * Ritorna le coordinate del re del giocatore che muove.
	 */
	private Point getKingPosition(){
		Color kingColor = turno ? Color.WHITE : Color.BLACK;
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				if(model.at(x, y) instanceof Re && model.at(x, y).getColor() == kingColor)
					return new Point(x, y);
		
		return null;
	}
		
	/**
	 * Ritorna tutte le coordinate raggiungibili dal pezzo in (x, y)
	 */
	public ArrayList<Point> reachableTilesFrom(int x, int y){
		ArrayList<Point> res = new ArrayList<Point>();
		
		for(int toX = 0; toX < 8; toX++)
			for(int toY = 0; toY < 8; toY++)
				if(isMoveLegal(x, y, toX, toY))
					res.add(new Point(toX, toY));
		
		return res;
	}
	
	/**
	 * Ritorna le coordinate delle caselle libere attorno al re
	 * nella configurazione attuale.
	 */
	private ArrayList<Point> freeTilesAroundKing(){
		ArrayList<Point> freeTiles = new ArrayList<Point>();	
		int x = getKingPosition().x;
		int y = getKingPosition().y;
		
		if(y > 0){
			if(x > 0 && model.at(x - 1, y - 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x - 1, y - 1));
			if(model.at(x, y - 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x, y - 1));
			if(x < 7 && model.at(x + 1, y - 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x + 1, y - 1));
		}
		if(y < 7){
			if(x > 0 && model.at(x - 1, y + 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x - 1, y + 1));
			if(model.at(x, y + 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x, y + 1));
			if(x < 7 && model.at(x + 1, y + 1) instanceof CasellaVuota)
				freeTiles.add(new Point(x + 1, y + 1));			
		}
		if(x > 0 && model.at(x - 1, y) instanceof CasellaVuota)
			freeTiles.add(new Point(x - 1, y));
		if(x < 7 && model.at(x + 1, y) instanceof CasellaVuota)
			freeTiles.add(new Point(x + 1, y));
		
		return freeTiles;
	}

	/**
	 * Simula un evento mouseClicked.
	 */
	private void simulateMouseClick(){
		try {
			Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
