package maze.elements;

import java.io.Serializable;

public class Dragao implements Serializable {
	private static final long serialVersionUID = 6997169652752399016L;
	private Position pos;
	private boolean vivo;
	private int dormir;
	private boolean cuspiu_fogo = false;

	/**
	 * Construtor de um drag�o
	 */
	public Dragao() {
		pos = new Position(-1, -1);
		vivo = true;
		dormir = 0;
	}

	/**
	 * Retorna a posi��o do drag�o
	 * 
	 * @return pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Retorna se o drag�o est� vivo ou n�o
	 * 
	 * @return vivo
	 */
	public boolean getVivo() {
		return vivo;
	}

	/**
	 * Muda o estado do drag�o
	 * 
	 * @param vivo
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * Muda a condi��o de dormir, ou n�o, do drag�o
	 * 
	 * @param dormir
	 */
	public void setDormir(int dormir) {
		this.dormir = dormir;
	}

	/**
	 * Retorna quanto tempo o drag�o vai dormir
	 * 
	 * @return dormir
	 */
	public int getDormir() {
		return dormir;
	}

	/**
	 * Muda a posi��o de um drag�o
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.setX(x);
		pos.setY(y);
	}

	/**
	 * Retorna a exist�ncia de fogo
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
