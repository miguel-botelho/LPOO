package maze.elements;

import java.io.Serializable;

public class Espada implements Serializable {
	private static final long serialVersionUID = 4359466006326409194L;

	private Position pos;

	/**
	 * Construtor de espada
	 */
	public Espada() {
		pos = new Position(-1, -1);
	}

	/**
	 * Retorna a posi��o da espada
	 * 
	 * @return pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Altera a posi��o da espada
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}
}
