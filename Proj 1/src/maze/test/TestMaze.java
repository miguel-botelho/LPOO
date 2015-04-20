package maze.test;

import maze.elements.Position;
import maze.elements.Maze;
import maze.elements.MazeBuilder;

public class TestMaze {

	// a) the maze boundaries must have exactly one exit and everything else
	// walls
	// b) the exist cannot be a corner
	/**
	 * Verifica os limites do labirinto
	 * 
	 * @param m
	 * @return true, se passar
	 */
	public static boolean checkBoundaries(Maze m) {
		int countExit = 0;
		int n = m.getSize();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m.getChar(i, j) == 'S')
						if ((i == 0 || i == n - 1) && (j == 0 || j == n - 1))
							return false;
						else
							countExit++;
					else if (m.getChar(i, j) != 'X')
						return false;
		return countExit == 1;
	}

	// d) there cannot exist 2x2 (or greater) squares with blanks only
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal
	// and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only

	/**
	 * Verifica se o labirinto tem espaços vazios a mais
	 * 
	 * @param maze
	 * @param square
	 * @return true, se passar
	 */
	public static boolean hasSquare(Maze maze, char[][] square) {
		char[][] m = maze.getMatrix();
		for (int i = 0; i < m.length - square.length; i++)
			for (int j = 0; j < m.length - square.length; j++) {
				boolean match = true;
				for (int x = 0; x < square.length; x++)
					for (int y = 0; y < square.length; y++) {
						if (m[i + x][j + y] != square[x][y])
							match = false;
					}
				if (match)
					return true;
			}
		return false;
	}

	// c) there must exist a path between any blank cell and the maze exit

	/**
	 * Verifica a existência de uma saída viável de atingir
	 * 
	 * @param maze
	 * @return true, se passar
	 */
	public static boolean checkExitReachable(Maze maze) {
		Position p = maze.getExitPosition();
		char[][] m = deepClone(maze.getMatrix());
		visit(m, p.getX(), p.getY());

		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m.length; j++)
				if (m[i][j] != 'X' && m[i][j] != 'V')
					return false;

		return true;
	}

	// auxiliary method used by checkExitReachable
	// marks a cell as visited (V) and proceeds recursively to its neighbors

	/**
	 * Método auxiliar que ajuda a função de atingir a saída
	 * 
	 * @param m
	 * @param i
	 * @param j
	 */
	private static void visit(char[][] m, int i, int j) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || m[i][j] == 'V')
			return;
		m[i][j] = 'V';
		visit(m, i - 1, j);
		visit(m, i + 1, j);
		visit(m, i, j - 1);
		visit(m, i, j + 1);
	}

	// Auxiliary method used by checkExitReachable.
	// Gets a deep clone of a char matrix.

	/**
	 * Cria um clone do labirinto
	 * 
	 * @param m
	 * @return clone
	 */
	private static char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	// Checks if all the arguments (in the variable arguments list) are not null
	// and distinct



	/**
	 * Verifica se o labirinto é válido de jogar
	 * 
	 * @param m
	 * @return true, se passar
	 * @throws Exception
	 */
	public static boolean testValidMaze(Maze m) throws Exception {

		new MazeBuilder();
		char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' },
				{ 'X', 'X', 'X' } };
		char[][] badSpaces = { { ' ', ' ' }, { ' ', ' ' } };
		char[][] badDiag1 = { { 'X', ' ' }, { ' ', 'X' } };
		char[][] badDiag2 = { { ' ', 'X' }, { 'X', ' ' } };
		if (!checkBoundaries(m))
			return false;
		if (!checkExitReachable(m))
			return false;
		if (hasSquare(m, badWalls))
			return false;
		if (hasSquare(m, badSpaces))
			return false;
		if (hasSquare(m, badDiag1))
			return false;
		if (hasSquare(m, badDiag2))
			return false;
		return true;
	}

}