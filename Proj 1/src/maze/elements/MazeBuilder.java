package maze.elements;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MazeBuilder {
	private char Board[][];
	private char helpingBoard[][];
	private int size;
	private Deque<Position> path;
	private Position pos;
	private Dardo[] dardos;
	private Dragao[] dragoes;
	private Escudo escudo;
	private Homem homem = new Homem();
	private Espada espada = new Espada();
	private static int numDragons = 4;

	/**
	 * Retorna o num de dragoes
	 * 
	 * @return numDragons
	 */

	public static int getNumDragons() {
		return numDragons;
	}
	
	/**
	 * Dá um valor ao numDragons
	 * 
	 */
	public static void setNumDragons(int num) {
		MazeBuilder.numDragons = num;
	}

	/**
	 * Construtor vazio de Maze Builder
	 */
	public MazeBuilder() {
	}

	/**
	 * Retorna a board
	 * 
	 * @return board
	 */
	char[][] getBoard() {
		return Board;
	}

	/**
	 * Retorna o homem
	 * 
	 * @return homem
	 */
	Homem getHomem() {
		return homem;
	}

	/**
	 * Retorna a espada
	 * 
	 * @return espada
	 */
	Espada getEspada() {
		return espada;
	}

	/**
	 * Retorna os dragoes
	 * 
	 * @return dragoes
	 */
	Dragao[] getDragoes() {
		return dragoes;
	}

	/**
	 * Retorna os dardos
	 * 
	 * @return dardos
	 */
	Dardo[] getDardos() {
		return dardos;
	}

	/**
	 * Retorna o escudo
	 * 
	 * @return escudo
	 */
	Escudo getEscudo() {
		return escudo;
	}

	/**
	 * Constroi um labirinto novo, com todos os seus extras
	 * 
	 * @param size
	 */
	MazeBuilder(int size) {

		Random MyRandom = new Random();
		int nrDragoes = MyRandom.nextInt(numDragons) + 1;
		dragoes = new Dragao[nrDragoes];
		dardos = new Dardo[nrDragoes];
		escudo = new Escudo();
		for (int i = 0; i < nrDragoes; i++) {
			dragoes[i] = new Dragao();
			dardos[i] = new Dardo();
		}

		this.size = size;
		Board = new char[size][size];
		helpingBoard = new char[(size - 1) / 2][(size - 1) / 2];
		path = new ArrayDeque<Position>();
		// Filling helpingBoard
		for (int xi = 0; xi < (size - 1) / 2; xi++)
			for (int yi = 0; yi < (size - 1) / 2; yi++)
				helpingBoard[xi][yi] = '.';

		// Filling board
		for (int xi = 0; xi < size; xi++)
			for (int yi = 0; yi < size; yi++)
				if (isOnHelpingBoard(xi, yi))
					Board[xi][yi] = ' ';
				else
					Board[xi][yi] = 'X';

		// set exit
		int startX = 0, startY = 0;
		switch ((int) (Math.random() * 4)) {
		case 0: // Left wall
			Random random = new Random();
			startX = 0;
			startY = random.nextInt(((size - 1) / 2) - 1) + 1;
			Board[0][startY * 2 + 1] = 'S';
			break;
		case 1: // Right wall
			Random random2 = new Random();
			startX = (size - 1) / 2 - 1;
			startY = random2.nextInt(((size - 1) / 2) - 1) + 1;
			Board[size - 1][startY * 2 + 1] = 'S';
			break;
		case 2: // Up wall
			Random random3 = new Random();
			startX = random3.nextInt(((size - 1) / 2) - 1) + 1;
			startY = 0;
			Board[startX * 2 + 1][0] = 'S';
			break;
		case 3: // Down wall
			Random random4 = new Random();
			startX = random4.nextInt(((size - 1) / 2) - 1) + 1;
			startY = (size - 1) / 2 - 1;
			Board[startX * 2 + 1][size - 1] = 'S';
			break;
		}
		pos = new Position(startX * 2 + 1, startY * 2 + 1);
		path.push(new Position(startX, startY));
		helpingBoard[startX][startY] = '+';
		int first_time = 0;
		while (!path.isEmpty()) {
			pos.setX(path.peek().getX() * 2 + 1);
			pos.setY(path.peek().getY() * 2 + 1);
			if (!hasNeighbors(pos.getX(), pos.getY())) {
				if (first_time == 0) {
					homem.setPos(pos.getX(), pos.getY());
				}

				path.pop();
				first_time++;
			}

			switch ((int) (Math.random() * 4)) {
			// GOING UP
			case 0:
				if (!isBorder(pos.getX(), pos.getY() - 1)
						&& !wasVisited(pos.getX(), pos.getY() - 2)) {
					Board[pos.getX()][pos.getY() - 1] = ' ';
					Board[pos.getX()][pos.getY() - 2] = ' ';
					pos.setY(pos.getY() - 2);
					path.push(new Position((pos.getX() - 1) / 2,
							(pos.getY() - 1) / 2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1) / 2] = '+';
				}
				break;
			// GOING DOWN
			case 1:
				if (!isBorder(pos.getX(), pos.getY() + 1)
						&& !wasVisited(pos.getX(), pos.getY() + 2)) {
					Board[pos.getX()][pos.getY() + 1] = ' ';
					Board[pos.getX()][pos.getY() + 2] = ' ';
					pos.setY(pos.getY() + 2);
					path.push(new Position((pos.getX() - 1) / 2,
							(pos.getY() - 1) / 2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1) / 2] = '+';
				}
				break;
			// GOING LEFT
			case 2:
				if (!isBorder(pos.getX() - 1, pos.getY())
						&& !wasVisited(pos.getX() - 2, pos.getY())) {
					Board[pos.getX() - 1][pos.getY()] = ' ';
					Board[pos.getX() - 2][pos.getY()] = ' ';
					pos.setX(pos.getX() - 2);
					path.push(new Position((pos.getX() - 1) / 2,
							(pos.getY() - 1) / 2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1) / 2] = '+';
				}
				break;
			// GOING RIGHT
			case 3:
				if (!isBorder(pos.getX() + 1, pos.getY())
						&& !wasVisited(pos.getX() + 2, pos.getY())) {
					Board[pos.getX() + 1][pos.getY()] = ' ';
					Board[pos.getX() + 2][pos.getY()] = ' ';
					pos.setX(pos.getX() + 2);
					path.push(new Position((pos.getX() - 1) / 2,
							(pos.getY() - 1) / 2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1) / 2] = '+';
				}
				break;
			}
			// Gera posicao aleatória do dragao e da espada

			while (samePos(nrDragoes)) {
				int x = (int) (Math.random() * ((size - 1) / 2));
				int y = (int) (Math.random() * ((size - 1) / 2));
				espada.setPos(x * 2 + 1, y * 2 + 1);

				x = (int) (Math.random() * ((size - 1) / 2));
				y = (int) (Math.random() * ((size - 1) / 2));
				escudo.setPos(x * 2 + 1, y * 2 + 1);

				for (int i = 0; i < nrDragoes; i++) {
					x = (int) (Math.random() * ((size - 1) / 2));
					y = (int) (Math.random() * ((size - 1) / 2));
					dragoes[i].setPos(x * 2 + 1, y * 2 + 1);
				}

				for (int i = 0; i < nrDragoes; i++) {
					x = (int) (Math.random() * ((size - 1) / 2));
					y = (int) (Math.random() * ((size - 1) / 2));
					dardos[i].setPos(x * 2 + 1, y * 2 + 1);
				}
			}

		}
		System.out.println("Número de dragões: " + dragoes.length + "\n");

	}

	/**
	 * Verifica a existencia de um determinado número na matriz
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	private boolean isOnHelpingBoard(int x, int y) {
		for (int xi = 1; xi < size; xi = xi + 2) {
			if (xi == x) {
				for (int yi = 1; yi < size; yi = yi + 2) {
					if (yi == y) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se a coordenada passada é um limite do labirinto
	 * 
	 * @param Horizontal
	 * @param Vertical
	 * @return true, se sim
	 */
	public boolean isBorder(int Horizontal, int Vertical) {
		if (Horizontal == 0) {
			return true;
		} else if (Horizontal == size - 1) {
			return true;
		} else if (Vertical == 0) {
			return true;
		} else if (Vertical == size - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se a posição enviada já foi visitada pelo algoritmo de criação
	 * de um algoritmo
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	public boolean wasVisited(int x, int y) {
		if (x <= 0 || y <= 0 || y > size || x > size) {
			return true;
		}
		x = (x - 1) / 2;
		y = (y - 1) / 2;

		if (helpingBoard[x][y] == '+')
			return true;
		else
			return false;
	}

	/**
	 * Verifica a existência de células adjacentes
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	public boolean hasNeighbors(int x, int y) {
		x = (x - 1) / 2;
		y = (y - 1) / 2;
		if (x != 0)
			if (helpingBoard[x - 1][y] == '.')
				return true;

		if (y != 0)
			if (helpingBoard[x][y - 1] == '.')
				return true;

		if (x != ((size - 1) / 2) - 1)
			if (helpingBoard[x + 1][y] == '.')
				return true;

		if (y != ((size - 1) / 2) - 1)
			if (helpingBoard[x][y + 1] == '.')
				return true;

		return false;
	}

	/**
	 * Verifica se há objetos com a mesma posição
	 * 
	 * @param nrDragoes
	 * @return true, se houver
	 */
	boolean samePos(int nrDragoes) {

		for (int i = 0; i < nrDragoes; i++) {
			for (int j = 0; j < nrDragoes; j++) {
				if (i != j) {
					if (dardos[i].getPos().getX() == dardos[j].getPos().getX()
							&& dardos[i].getPos().getY() == dardos[j].getPos()
									.getY()) {
						return true;
					}
				}

			}
		}
		for (int i = 0; i < nrDragoes; i++) {
			if (dardos[i].getPos().getX() == espada.getPos().getX()
					&& dardos[i].getPos().getY() == espada.getPos().getY()) {
				return true;
			}
		}
		for (int i = 0; i < nrDragoes; i++) {
			if (dardos[i].getPos().getX() == homem.getPos().getX()
					&& dardos[i].getPos().getY() == homem.getPos().getY()) {
				return true;
			}
		}
		for (int i = 0; i < nrDragoes; i++) {
			for (int j = 0; j < nrDragoes; j++) {
				if (dardos[i].getPos().getX() == dragoes[j].getPos().getX()
						&& dardos[i].getPos().getY() == dragoes[j].getPos()
								.getY()) {
					return true;
				}
			}
		}

		for (int i = 0; i < nrDragoes; i++) {
			for (int j = 0; j < nrDragoes; j++) {
				if (i != j) {
					if (dragoes[i].getPos().getX() == dragoes[j].getPos()
							.getX()
							&& dragoes[i].getPos().getY() == dragoes[j]
									.getPos().getY()) {
						return true;
					}
				}

			}
		}
		for (int i = 0; i < nrDragoes; i++) {
			if (dragoes[i].getPos().getX() == espada.getPos().getX()
					&& dragoes[i].getPos().getY() == espada.getPos().getY()) {
				return true;
			}
		}
		for (int i = 0; i < nrDragoes; i++) {
			if (dragoes[i].getPos().getX() == homem.getPos().getX()
					&& dragoes[i].getPos().getY() == homem.getPos().getY()) {
				return true;
			}
		}

		if (espada.getPos().getX() == homem.getPos().getX()
				&& espada.getPos().getY() == homem.getPos().getY())
			return true;

		if (escudo.getPos().getX() == homem.getPos().getX()
				&& escudo.getPos().getY() == homem.getPos().getY())
			return true;

		if (espada.getPos().getX() == escudo.getPos().getX()
				&& espada.getPos().getY() == escudo.getPos().getY())
			return true;

		for (int i = 0; i < nrDragoes; i++) {
			if (dragoes[i].getPos().getX() == escudo.getPos().getX()
					&& dragoes[i].getPos().getY() == escudo.getPos().getY()) {
				return true;
			}
		}

		for (int i = 0; i < nrDragoes; i++) {
			if (dardos[i].getPos().getX() == escudo.getPos().getX()
					&& dardos[i].getPos().getY() == escudo.getPos().getY()) {
				return true;
			}
		}

		return false;
	}
}
