package it.luca.chessgame.moves;

public class Move {
	private int fromX, toX;
	private int fromY, toY;
	private boolean eat;
	
	public Move(int fromX, int fromY, int toX, int toY, boolean eat){
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.eat = eat;
	}

	/**
	 * Ritorna una stringa che descrive la mossa effettuata
	 */
	public String toString(){
		return (char) (fromX + 'a') + "" + invertiOrdinate(fromY) + (eat ? " x " : " - ") + (char) (toX + 'a') + 
				invertiOrdinate(toY) + "\n";
	}
	
	/**
	 * L'array ha orientamento crescente. Devo invertire le ordinate
	 * in modo che l'ordinamento stampato risulti decrescente.
	 * @param y
	 * @return
	 */
	private int invertiOrdinate(int y){
		return Math.abs(y - 7) + 1;
	}
}
