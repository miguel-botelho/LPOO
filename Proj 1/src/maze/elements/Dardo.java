package maze.elements;

import java.io.Serializable;

public class Dardo implements Serializable{
	
	private static final long serialVersionUID = 3318508084338306058L;
	
	private Position pos;
	private boolean visibilidade;
	
	/**
	 * Construtor de Dardo
	 */
	public Dardo(){ 
		pos = new Position(-1, -1);
		visibilidade = true;
	}
	
	/**
	 * Retorna a posicao de um dardo
	 * @return pos
	 */
	public Position getPos(){
		return pos;
	}
	
	/**
	 * Retorna a visibilidade de um dardo (foi ou não apanhado)
	 * @return visibilidade
	 */
	
	public boolean getVisibilidade(){
		return visibilidade;
	}
	
	/**
	 * Torna um dardo visível ou não
	 * @param vis
	 */
	public void setVisibilidade(boolean vis){
		visibilidade = vis;
	}
	
	/**
	 * Muda a posição de um dardo
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y){
		pos.setX(x);
		pos.setY(y);
	}
	
	
}
