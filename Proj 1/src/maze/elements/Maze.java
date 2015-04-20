package maze.elements;

import java.io.Serializable;

public class Maze implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4945279856492066645L;

	private static char[][] board;

	/**
	 * Construtor de Maze
	 */
	public Maze() {
	}

	/**
	 * Retorna o tamanho do board
	 * 
	 * @return tamanho
	 */
	public int getSize() {
		return board.length;
	}

	/**
	 * Retorna o carater existente na coordenada {i, j} do board
	 * 
	 * @param i
	 * @param j
	 * @return char
	 */
	public char getChar(int i, int j) {
		return board[i][j];
	}

	/**
	 * Retorna a board
	 * 
	 * @return board
	 */
	public char[][] getMatrix() {
		return board;
	}

	public void setMatrix(char[][] matrix) {
		board = matrix;
	}

	/**
	 * Retorna a saída do labirinto
	 * 
	 * @return pos
	 */
	public Position getExitPosition() {
		for (int linha = 0; linha < getSize(); linha++) {
			for (int coluna = 0; coluna < getSize(); coluna++) {
				if (board[coluna][linha] == 'S')
					return new Position(coluna, linha);
			}
		}
		return null;
	};
}
