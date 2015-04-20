package maze.elements;

import java.io.Serializable;

public class Dragao implements Serializable {
	private static final long serialVersionUID = 6997169652752399016L;
	private Position pos;
	private boolean vivo;
	private int dormir;
	private boolean cuspiu_fogo = false;

	/**
	 * Construtor de um dragão
	 */
	public Dragao() {
		pos = new Position(-1, -1);
		vivo = true;
		dormir = 0;
	}

	/**
	 * Retorna a posição do dragão
	 * 
	 * @return pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Retorna se o dragão está vivo ou não
	 * 
	 * @return vivo
	 */
	public boolean getVivo() {
		return vivo;
	}

	/**
	 * Muda o estado do dragão
	 * 
	 * @param vivo
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * Muda a condição de dormir, ou não, do dragão
	 * 
	 * @param dormir
	 */
	public void setDormir(int dormir) {
		this.dormir = dormir;
	}

	/**
	 * Retorna quanto tempo o dragão vai dormir
	 * 
	 * @return dormir
	 */
	public int getDormir() {
		return dormir;
	}

	/**
	 * Muda a posição de um dragão
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	/**
	 * Retorna a existência de fogo
	 * 
	 * @return cuspiu_fogo
	 */
	public boolean getFogo() {
		return cuspiu_fogo;
	}

	/**
	 * Ativa o fogo
	 * 
	 * @param fogo
	 */
	public void setFogo(boolean fogo) {
		cuspiu_fogo = fogo;
	}

}
