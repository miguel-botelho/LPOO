package maze.elements;

import java.io.Serializable;

public class Escudo implements Serializable {
	private static final long serialVersionUID = 3629014087029564928L;
	private Position pos;
	private boolean visibilidade;

	/**
	 * Construtor de escudo
	 */
	public Escudo() {
		pos = new Position(-1, -1);
		visibilidade = true;
	}

	/**
	 * Retorna a posição do escudo
	 * 
	 * @return pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Retorna a visibilidade do escudo
	 * 
	 * @return visibilidade
	 */
	public boolean getVisibilidade() {
		return visibilidade;
	}

	/**
	 * Altera a visibilidade do escudo
	 * 
	 * @param vis
	 */
	public void setVisibilidade(boolean vis) {
		visibilidade = vis;
	}

	/**
	 * Altera a posição do escudo
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}
}
