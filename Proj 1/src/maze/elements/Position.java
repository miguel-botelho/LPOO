package maze.elements;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = 5737263018400795522L;
	private int x;
	private int y;

	/**
	 * Construtor de posi��o
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Retorna a coordenada x de uma posi��o
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retorna a coordenada y de uma posi��o
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Altera a coordenada y de uma posi��o
	 * 
	 * @param inteiro
	 */
	public void setY(int inteiro) {
		y = inteiro;
	}

	/**
	 * Altera a coordenada x de uma posi��o
	 * 
	 * @param inteiro
	 */
	public void setX(int inteiro) {
		x = inteiro;
	}

}
