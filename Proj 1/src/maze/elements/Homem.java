package maze.elements;

import java.io.Serializable;

public class Homem implements Serializable {
	private static final long serialVersionUID = 1821692878583846095L;
	private Position pos;
	private boolean armado;
	private int numDardos;
	private boolean invencivel;
	private boolean vivo;

	/**
	 * Construtor de Homem
	 */
	public Homem() {
		pos = new Position(1, 1);
		armado = false;
		numDardos = 0;
		vivo = true;
	}

	/**
	 * Retorna a posição do homem
	 * 
	 * @return pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Altera a posição do homem
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	/**
	 * Retorna a condição de armado do homem
	 * 
	 * @return armado
	 */
	public boolean getArmado() {
		return armado;
	}

	/**
	 * Altera a arma do homem
	 * 
	 * @param arma
	 */
	public void setArmado(boolean arma) {
		armado = arma;
	}

	/**
	 * Retorna o número de dardos que o homem tem no momento
	 * 
	 * @return numDardos
	 */
	public int getDardos() {
		return numDardos;
	}

	/**
	 * Retorna o escudo do homem
	 * 
	 * @return invencivel
	 */
	public boolean getInvencivel() {
		return invencivel;
	}

	/**
	 * Retorna a condição do homem
	 * 
	 * @return vivo
	 */
	public boolean getVivo() {
		return vivo;
	}

	/**
	 * Altera a condição do homem
	 * 
	 * @param vivo
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * Altera o escudo do homem
	 * 
	 * @param invencivel
	 */
	public void setInvencivel(boolean invencivel) {
		this.invencivel = invencivel;
	}

	/**
	 * Adiciona dardos ao homem
	 */
	public void addDardos() {
		numDardos++;
	}

	/**
	 * Retira dardos ao homem
	 */
	public void subDardos() {
		numDardos--;
	}
}
