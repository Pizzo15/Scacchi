package it.luca.chessgame.moves;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import it.luca.chessgame.model.*;

public class Mover {
	private final Model model;
	private boolean turno;	// T = bianco, F = nero
	private Stack<Configuration> undos = new Stack<Configuration>();
	
	public Mover(Model model, boolean turno){
		this.model = model;
		this.turno = turno;
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

	public boolean getTurno(){
		return turno;
	}
	
	public void setTurno(boolean turno){
		this.turno = turno;
	}

	/**
	 * Scacco matto: imposto una nuova partita nel caso di si, altrimenti esco.
	 * Non scacco matto: nel caso di selezione negativa resto sulla partita attuale
	 * e non faccio nulla.
	 */
	public void newGame(){
		int selection = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Nuova partita?", "", 
				JOptionPane.YES_NO_OPTION);
		if(selection == JOptionPane.YES_OPTION) {
				setNewGame();
		} else {
			if(scaccoMatto())
				System.exit(0);
		}
	}
	
	/**
	 * Imposta la configurazione a quella iniziale e svuota i registri delle mosse
	 * effettuate.
	 */
	private void setNewGame(){
		turno = true;
		undos.removeAllElements();
		model.setConfiguration(new ArrayConfiguration());
	}
	
	/**
	 * Annulla l'ultima mossa effettuata e riporta la scacchiera alla configurazione
	 * precedente.
	 */
	public void undo(){
		if(!undos.isEmpty()){
			model.setConfiguration(undos.pop());
			turno = !turno;
		}
	}
	
	/**
	 * Controlla che la casella di arrivo non sia occupata da un pezzo dello stesso
	 * colore di quello selezionato.
	 * In tal caso blocca la mossa ritornando false.
	 */
	private boolean checkColor(int fromX, int fromY, int toX, int toY){
		return model.at(fromX, fromY).getColor() != model.at(toX, toY).getColor();
	}
	
	/** 
	 * Controlla che l'alternanza dei turni sia rispettata.
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
	
	/**
	 * Controlla che la mossa sia valida in base alle regole di movimento
	 * del gioco.
	 */
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
	 * - porre tra il re e il pezzo che minaccia un pezzo
	 */
	private boolean checkMoveUnderScacco(int fromX, int fromY, int toX, int toY){
		boolean canEscape = false, canBeEaten = false, canBeSaved = false;
		
		if(kingCanEscape())
			canEscape =  model.at(fromX, fromY) instanceof Re && isSafe(fromX, fromY, toX, toY);

		canBeEaten = canReachCheckHolder(fromX, fromY) && isCheckHolder(toX, toY) && isSafe(fromX, fromY, toX, toY);
		
		canBeSaved = model.at(toX, toY) instanceof CasellaVuota && isSafe(fromX, fromY, toX, toY);
		
		return  canBeEaten || canEscape || canBeSaved;
	}

	/**
	 * Controlla se il pezzo di coordinate (x, y) può mangiare il pezzo
	 * che tiene sotto scacco il re e questa mossa non lascia il re sotto scacco.
	 */
	private boolean canReachCheckHolder(int x, int y){
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		Point checkHolder = scacco(kingX, kingY).get(0);
		
		return checkColor(x, y, checkHolder.x, checkHolder.y) && checkTurn(x, y, checkHolder.x, checkHolder.y)
				&& checkMove(x, y, checkHolder.x, checkHolder.y);
	}

	/**
	 * Ritorna true se il pezzo (x, y) è quello che tiene sotto scacco
	 * il re.
	 */
	private boolean isCheckHolder(int x, int y){
		int kingX = getKingPosition().x;
		int kingY = getKingPosition().y;
		
		for(Point p: scacco(kingX, kingY))
			if(p.equals(new Point(x, y)))
				return true;
		
		return false;
	}

	/**
	 * Il re è sotto scacco: ritorna true se c'è almeno una casella libera in 
	 * cui tentare di sottrarsi allo scacco.
	 */
	private boolean kingCanEscape(){
		ArrayList<Point> free = freeTilesAroundKing();

		// controllo le caselle libere attorno al re
		for(Point p: free)
			// se almeno una di queste non è sotto la minaccia diretta di un pezzo avversario
			// posso provare a scappare
			if(scacco(p.x, p.y).isEmpty())
				return true;
		
		return false;
	}
	
	/**
	 * Simula la mossa e verifica se questa mette il re in una situazione di scacco.
	 */
	private boolean isSafe(int fromX, int fromY, int toX, int toY){
		Configuration save = model.getConfiguration();
			
		// simulo la mossa nella casella (toX, toY)
		model.setConfiguration(model.getConfiguration().swap(fromX, fromY, toX, toY));			
		// se non mette il re sotto scacco la mossa è valida
		if(scacco(getKingPosition().x, getKingPosition().y).isEmpty()){
			model.setConfiguration(save);	
			return true;	
		}
		model.setConfiguration(save);	
		
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
			return legal && isSafe(fromX, fromY, toX, toY);
		else
			return legal && checkMoveUnderScacco(fromX, fromY, toX, toY);
	}
	
	public boolean kingUnderScacco(){
		return !scacco(getKingPosition().x, getKingPosition().y).isEmpty();
	}

	/**
	 * Controlla su tutta la scacchiera se almeno una mossa è possibile.
	 */
	private boolean possibleMoves(){
		for(int x1 = 0; x1 < 8; x1++)
			for(int y1 = 0; y1 < 8; y1++)
				for(int x2 = 0; x2 < 8; x2++)
					for(int y2 = 0; y2 < 8; y2++)
				if(moveLegal(x1, y1, x2, y2))
					return true;
		
		return false;
	}
	
	/**
	 * Ritorna true se uno dei giocatori ha dato scacco matto all'
	 * altro. In tal caso termina la partita.
	 */
	public boolean scaccoMatto(){
		// se il re non è sotto scacco: false
		if(!kingUnderScacco())
			return false;
		
		// se il re è sotto scacco ma sono possibili delle mosse: false
		if(possibleMoves())
			return false;
		
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
}
