package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.elements.Dardo;
import maze.elements.Dragao;
import maze.elements.Escudo;
import maze.elements.Espada;
import maze.elements.Homem;

public class CreateMaze extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private BufferedImage floor;
	private BufferedImage brick;
	private BufferedImage shadow_floor;
	private BufferedImage exit;
	private BufferedImage dragon;
	private BufferedImage knight;
	private BufferedImage shield;
	private BufferedImage fireball;
	private BufferedImage dart;
	private BufferedImage dragon_dead;
	private BufferedImage dragon_sleeping;
	private BufferedImage sword;
	private BufferedImage brick_3d;

	private static Dragao dragoes[] = {};
	private static Homem homem = new Homem();
	private static Dardo dardos[] = {};
	private static Espada espada = new Espada();
	private static Escudo escudo = new Escudo();
	private static int tamanho;

	private static JPanel panel;

	private static JFrame f;

	private static char[][] labirinto;

	/**
	 * Cria um novo labirinto
	 * 
	 * @param tamanho
	 */

	CreateMaze(int tamanho) {

		homem.setPos(-1, -1);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		CreateMaze.tamanho = tamanho;
		CreateMaze.labirinto = new char[tamanho][tamanho];

		for (int linha = 0; linha < tamanho; linha++) {
			for (int coluna = 0; coluna < tamanho; coluna++) {
				labirinto[linha][coluna] = ' ';
			}
		}

		for (int i = 0; i < tamanho; i++) {
			labirinto[0][i] = 'X';
			labirinto[i][0] = 'X';
			labirinto[tamanho - 1][i] = 'X';
			labirinto[i][tamanho - 1] = 'X';
		}

		getElements();
	}

	/**
	 * Atualiza os objetos do labirinto
	 */
	void getElements() {
		floor = InGameGUI.getInstance().getFloor();
		shadow_floor = InGameGUI.getInstance().getShadowFloor();
		exit = InGameGUI.getInstance().getExit();
		dragon = InGameGUI.getInstance().getDragon();
		knight = InGameGUI.getInstance().getKnight();
		shield = InGameGUI.getInstance().getShield();
		fireball = InGameGUI.getInstance().getFireball();
		dart = InGameGUI.getInstance().getDart();
		sword = InGameGUI.getInstance().getSword();
		brick = InGameGUI.getInstance().getBrick();
		brick_3d = InGameGUI.getInstance().getBrick3d();
	}

	/**
	 * Overload da função de paintComponent Pinta todo o jogo em modo gráfico
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		int x1 = 0, y1 = 0;
		int size = tamanho;
		int sizePerTileH = panel.getWidth() / size;
		int sizePerTileV = panel.getHeight() / size;
		int sizeOfBrick = (int) (sizePerTileV * (1 + 0.46));
		for (int linha = 0; linha < size; linha++) {
			for (int coluna = 0; coluna < size; coluna++) {
				if (labirinto[coluna][linha] == 'b') {
					if (labirinto[coluna - 1][linha] == 'X') {
						g.drawImage(shadow_floor, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					} else {
						g.drawImage(floor, x1,
								y1 + (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					}
					g.drawImage(fireball, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				}

				if (labirinto[coluna][linha] == 'S') {
					if (linha == 0)
						g.drawImage(brick_3d, x1, y1, sizePerTileH,
								(int) (sizePerTileH * 0.3), null);

					g.drawImage(floor, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
					g.drawImage(exit, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				}
				if (labirinto[coluna][linha] == ' ')
					if (labirinto[coluna - 1][linha] == 'X') {
						g.drawImage(shadow_floor, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					} else {
						g.drawImage(floor, x1,
								y1 + (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					}
				for (int i = 0; i < dragoes.length; i++) {
					if (dragoes[i].getPos().getX() == coluna
							&& dragoes[i].getPos().getY() == linha) {
						if (dragoes[i].getVivo()) {
							if (dragoes[i].getDormir() > 0)
								g.drawImage(dragon_sleeping, x1, y1
										+ (int) (sizePerTileV * 0.33),
										sizePerTileH, sizePerTileV, null);
							else
								g.drawImage(dragon, x1, y1
										+ (int) (sizePerTileV * 0.2),
										sizePerTileH, sizePerTileV, null);
						} else {
							g.drawImage(dragon_dead, x1, y1
									+ (int) (sizePerTileV * 0.2), sizePerTileH,
									sizePerTileV, null);
						}
					}
				}

				for (int i = 0; i < dardos.length; i++) {
					if (dardos[i].getPos().getX() == coluna
							&& dardos[i].getPos().getY() == linha
							&& dardos[i].getVisibilidade())
						g.drawImage(dart, x1, y1 + (int) (sizePerTileV * 0.33),
								sizePerTileH, sizePerTileV, null);
				}
				if (homem.getPos().getX() == coluna
						&& homem.getPos().getY() == linha && homem.getVivo())
					g.drawImage(knight, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				if (espada.getPos().getX() == coluna
						&& espada.getPos().getY() == linha
						&& !homem.getArmado())
					g.drawImage(sword, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				if (escudo.getPos().getX() == coluna
						&& escudo.getPos().getY() == linha
						&& !homem.getInvencivel())
					g.drawImage(shield, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				if (homem.getArmado() && (coluna == 0) && (linha == 0))
					g.drawImage(sword, x1, y1, sizePerTileH, sizePerTileV, null);
				if (homem.getInvencivel() && (coluna == 1) && (linha == 0))
					g.drawImage(shield, x1, y1, sizePerTileH, sizePerTileV,
							null);
				if (homem.getDardos() > 0 && (coluna == 2) && (linha == 0))
					g.drawImage(dart, x1, y1, sizePerTileH, sizePerTileV, null);
				if (labirinto[coluna][linha] == 'X')
					g.drawImage(brick, x1, y1, sizePerTileH, sizeOfBrick, null);
				x1 += sizePerTileH;
			}

			x1 = 0;
			y1 += sizePerTileV;
		}
	}

	/**
	 * Construir um labirinto pelo utilizador
	 * 
	 * @param size
	 */
	public static void construct(int size) {
		f = new JFrame("Constroi o teu labirinto");
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setPreferredSize(new Dimension(620, 670));
		f.getContentPane().setLayout(new BorderLayout());
		panel = new CreateMaze(size);
		JPanel panel2 = new CreateOptions();
		f.getContentPane().add(panel, BorderLayout.CENTER);
		f.getContentPane().add(panel2, BorderLayout.SOUTH);
		f.pack();
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setFocusable(true);
		f.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Cria novos objetos sempre que o rato é premido
	 */
	public void mousePressed(MouseEvent arg0) {
		float x = arg0.getX();
		float y = arg0.getY();
		double coordenada_x = ((float) (x / panel.getWidth()) * tamanho);
		double coordenada_y = ((float) (y / panel.getHeight()) * tamanho);
		if (CreateOptions.getSelecionado() == "Parede") {
			if (labirinto[(int) coordenada_x][(int) coordenada_y] != 'X')
				labirinto[(int) coordenada_x][(int) coordenada_y] = 'X';
			else if ((int) coordenada_x != 0
					&& (int) coordenada_x != tamanho - 1
					&& (int) coordenada_y != 0
					&& (int) coordenada_y != tamanho - 1)
				labirinto[(int) coordenada_x][(int) coordenada_y] = ' ';
		}

		if (CreateOptions.getSelecionado() == "Saida") {
			if (!isCorner((int) coordenada_x, (int) coordenada_y))
				if ((int) coordenada_x == 0
						|| (int) coordenada_x == tamanho - 1
						|| (int) coordenada_y == 0
						|| (int) coordenada_y == tamanho - 1) {
					if (labirinto[(int) coordenada_x][(int) coordenada_y] == 'S') {
						labirinto[(int) coordenada_x][(int) coordenada_y] = 'X';
					} else {
						for (int linha = 0; linha < tamanho; linha++) {
							for (int coluna = 0; coluna < tamanho; coluna++) {
								if (labirinto[coluna][linha] == 'S')
									labirinto[coluna][linha] = 'X';
							}
						}
						labirinto[(int) coordenada_x][(int) coordenada_y] = 'S';
					}
				}
		}

		if (CreateOptions.getSelecionado() == "Homem") {
			if (!((int) coordenada_x == 0 || (int) coordenada_x == tamanho - 1
					|| (int) coordenada_y == 0 || (int) coordenada_y == tamanho - 1)) {
				if (homem.getPos().getX() == (int) coordenada_x
						&& homem.getPos().getY() == (int) coordenada_y) {
					homem.setPos(-1, -1);
				} else {
					removeAlgumaCoisa((int) coordenada_x, (int) coordenada_y);
					homem.setPos((int) coordenada_x, (int) coordenada_y);
				}
			}
		}

		if (CreateOptions.getSelecionado() == "Escudo") {
			if (!((int) coordenada_x == 0 || (int) coordenada_x == tamanho - 1
					|| (int) coordenada_y == 0 || (int) coordenada_y == tamanho - 1)) {
				if (escudo.getPos().getX() == (int) coordenada_x
						&& escudo.getPos().getY() == (int) coordenada_y) {
					escudo.setPos(-1, -1);
				} else {
					removeAlgumaCoisa((int) coordenada_x, (int) coordenada_y);
					escudo.setPos((int) coordenada_x, (int) coordenada_y);
				}
			}
		}

		if (CreateOptions.getSelecionado() == "Espada") {
			if (!((int) coordenada_x == 0 || (int) coordenada_x == tamanho - 1
					|| (int) coordenada_y == 0 || (int) coordenada_y == tamanho - 1)) {
				if (espada.getPos().getX() == (int) coordenada_x
						&& espada.getPos().getY() == (int) coordenada_y) {
					espada.setPos(-1, -1);
				} else {
					removeAlgumaCoisa((int) coordenada_x, (int) coordenada_y);
					espada.setPos((int) coordenada_x, (int) coordenada_y);
				}
			}
		}

		if (CreateOptions.getSelecionado() == "Dragao") {
			if (!((int) coordenada_x == 0 || (int) coordenada_x == tamanho - 1
					|| (int) coordenada_y == 0 || (int) coordenada_y == tamanho - 1)) {
				if (!isDragon((int) coordenada_x, (int) coordenada_y)) {
					Dragao dragao = new Dragao();
					dragao.setPos((int) coordenada_x, (int) coordenada_y);
					Dragao dragoes_temp[] = new Dragao[dragoes.length + 1];
					for (int i = 0; i < dragoes.length; i++) {
						dragoes_temp[i] = dragoes[i];
					}
					dragoes_temp[dragoes_temp.length - 1] = dragao;
					dragoes = dragoes_temp.clone();
				} else {
					Dragao dragoes_temp[] = new Dragao[dragoes.length - 1];
					int temp = 0;
					for (int i = 0; i < dragoes.length; i++) {
						if (!((dragoes[i].getPos().getX() == (int) coordenada_x) && (dragoes[i]
								.getPos().getY() == (int) coordenada_y))) {
							dragoes_temp[temp] = dragoes[i];
							temp++;
						}
					}
					dragoes = dragoes_temp.clone();
				}
			}
		}

		if (CreateOptions.getSelecionado() == "Dardo") {
			if (!((int) coordenada_x == 0 || (int) coordenada_x == tamanho - 1
					|| (int) coordenada_y == 0 || (int) coordenada_y == tamanho - 1)) {
				if (!isDardo((int) coordenada_x, (int) coordenada_y)) {
					Dardo dardo = new Dardo();
					dardo.setPos((int) coordenada_x, (int) coordenada_y);
					Dardo dardos_temp[] = new Dardo[dardos.length + 1];
					for (int i = 0; i < dardos.length; i++) {
						dardos_temp[i] = dardos[i];
					}
					dardos_temp[dardos_temp.length - 1] = dardo;
					dardos = dardos_temp;
				} else {
					Dardo dardos_temp[] = new Dardo[dardos.length - 1];
					int temp = 0;
					for (int i = 0; i < dardos.length; i++) {
						if (!(dardos[i].getPos().getX() == (int) coordenada_x && dardos[i]
								.getPos().getY() == (int) coordenada_y)) {
							dardos_temp[temp] = dardos[i];
							temp++;
						}
					}
					dardos = dardos_temp.clone();
				}
			}
		}

		repaint();
	}

	/**
	 * Verifica se é um canto do board do labirinto
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	private boolean isCorner(int x, int y) {
		if (x == 0 && y == 0)
			return true;
		if (x == 0 && y == tamanho - 1)
			return true;
		if (x == tamanho - 1 && y == 0)
			return true;
		if (x == tamanho - 1 && y == tamanho - 1)
			return true;
		return false;
	}

	/**
	 * Verifica se a posição {x, y} é um dardo
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	private boolean isDardo(int x, int y) {
		for (int i = 0; i < dardos.length; i++) {
			if (dardos[i].getPos().getX() == x
					&& dardos[i].getPos().getY() == y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	/**
	 * Retorna o painel principal
	 * 
	 * @return panel
	 */
	public static JPanel getPanel() {
		return panel;
	}

	/**
	 * Verfica se a posição {x, y} é um dragão
	 * 
	 * @param x
	 * @param y
	 * @return true, se sim
	 */
	boolean isDragon(int x, int y) {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getPos().getX() == x
					&& dragoes[i].getPos().getY() == y) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Remove tudo em que o utilizador carrega em {x, y} que não seja o
	 * selecionado
	 * 
	 * @param x
	 * @param y
	 */
	void removeAlgumaCoisa(int x, int y) {
		labirinto[x][y] = ' '; // remove paredes

		// remove dardos
		if (isDardo(x, y)) {
			Dardo dardos_temp[] = new Dardo[dardos.length - 1];
			int temp = 0;
			for (int i = 0; i < dardos.length; i++) {
				if (!(dardos[i].getPos().getX() == x && dardos[i].getPos()
						.getY() == y)) {
					dardos_temp[temp] = dardos[i];
					temp++;
				}
			}
			dardos = dardos_temp.clone();
		}

		// remove dragoes
		if (isDragon(x, y)) {
			Dragao dragoes_temp[] = new Dragao[dragoes.length - 1];
			int temp = 0;
			for (int i = 0; i < dragoes.length; i++) {
				if (!(dragoes[i].getPos().getX() == x && dragoes[i].getPos()
						.getY() == y)) {
					dragoes_temp[temp] = dragoes[i];
					temp++;
				}
			}
			dragoes = dragoes_temp.clone();
		}

		// remove escudo
		if (escudo.getPos().getX() == x && escudo.getPos().getY() == y)
			escudo.setPos(-1, -1);

		// remove espada

		if (espada.getPos().getX() == x && espada.getPos().getY() == y)
			espada.setPos(-1, -1);

		// remove homem

		if (homem.getPos().getX() == x && homem.getPos().getY() == y)
			homem.setPos(-1, -1);
	}

	/**
	 * Retorna os dragões
	 * 
	 * @return dragoes
	 */
	public static Dragao[] getDragoes() {
		return dragoes;
	}

	/**
	 * Retorna o homem
	 * 
	 * @return homem
	 */
	public static Homem getHomem() {
		return homem;
	}

	/**
	 * Retorna os dardos
	 * 
	 * @return dardos
	 */
	public static Dardo[] getDardos() {
		return dardos;
	}

	/**
	 * Retorna a espada
	 * 
	 * @return espada
	 */
	public static Espada getEspada() {
		return espada;
	}

	/**
	 * Retorna o escudo
	 * 
	 * @return escudo
	 */
	public static Escudo getEscudo() {
		return escudo;
	}

	/**
	 * Retorna o board do labirinto
	 * 
	 * @return labirinto
	 */
	public static char[][] getBoard() {
		return labirinto;
	}

	/**
	 * Altera o tamanho do board
	 * 
	 * @param value
	 */
	public static void setSize(int value) {
		tamanho = value;
	}

	/**
	 * Retorna o painel do maze
	 * 
	 * @return
	 */
	static CreateMaze getMaze() {
		return (CreateMaze) panel;
	}

	/**
	 * Retorna a frame usada
	 * 
	 * @return f
	 */
	static JFrame getFrame() {
		return f;
	}

}
