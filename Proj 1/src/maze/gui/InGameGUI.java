package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.elements.Dardo;
import maze.elements.Dragao;
import maze.elements.Escudo;
import maze.elements.Espada;
import maze.elements.Homem;
import maze.elements.Maze;
import maze.elements.Data;
import maze.logic.Logic;

public class InGameGUI extends JPanel implements KeyListener {

	private static final long serialVersionUID = 3732091348719216614L;
	private BufferedImage sword;
	private BufferedImage brick;
	private BufferedImage brick_3d;
	private BufferedImage destroyed_brick;
	private BufferedImage exit;
	private BufferedImage floor;
	private BufferedImage shadow_floor;
	private BufferedImage dragon;
	private BufferedImage knight;
	private BufferedImage dead_knight;
	private BufferedImage shield;
	private BufferedImage fireball;
	private BufferedImage dart;
	private BufferedImage dragon_dead;
	private BufferedImage dragon_sleeping;
	private BufferedImage background;
	private BufferedImage sword_knight;
	private BufferedImage shield_knight;
	private BufferedImage sword_shield_knight;

	//
	private int up = KeyEvent.VK_UP;
	private int down = KeyEvent.VK_DOWN;
	private int right = KeyEvent.VK_RIGHT;
	private int left = KeyEvent.VK_LEFT;
	private int shoot_up = KeyEvent.VK_I;
	private int shoot_down = KeyEvent.VK_K;
	private int shoot_left = KeyEvent.VK_J;
	private int shoot_right = KeyEvent.VK_L;
	//

	private static JFrame f;

	//
	private boolean first_run = true;
	private Data main;
	private Maze myMaze;
	private Dardo[] dardos;
	private Dragao[] dragoes;
	private Homem homem;
	private Espada espada;
	private Escudo escudo;
	private int escolha = 2;
	private static JPanel panel;
	private int tamanho = 11;
	private static Logic logic = new Logic();
	private static InGameGUI gui;

	/**
	 * Retorna uma instância da interface gráfica
	 * @return gui
	 */
	public static InGameGUI getInstance() {
		return gui;
	}

	/**
	 * Retorna a lógica de jogo
	 * @return
	 */
	public static Logic getLogic() {
		return logic;
	}

	/**
	 * Cria um novo jogo
	 */
	public void new_game() {
		main.setEscolha(escolha);
		System.out.println(tamanho);
		main.setSize(tamanho);
		Data.criar_labirinto(tamanho); // cria labirinto
		getElements();
		logic.getElements();
		repaint();
	}

	/**
	 * Retorna o painel
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Retorna o tamanho do labirinto
	 * @return tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Cria uma nova interface de jogo
	 */
	InGameGUI() {
		this.addKeyListener(this);
		gui = this;
		Data.setMain();
		main = Data.getInstance();
		Data.criar_labirinto(tamanho);
		main.setEscolha(escolha);
		logic.getElements();
		getElements();
		try {
			sword = ImageIO.read(new File("images/sword.png"));
			dragon_dead = ImageIO.read(new File("images/dead_dragon.png"));
			destroyed_brick = ImageIO.read(new File(
					"images/destroyed_brick.png"));
			dart = ImageIO.read(new File("images/dart.png"));
			brick = ImageIO.read(new File("images/brick.png"));
			brick_3d = ImageIO.read(new File("images/brick_3d.png"));
			exit = ImageIO.read(new File("images/exit2.png"));
			floor = ImageIO.read(new File("images/floor.png"));
			shadow_floor = ImageIO.read(new File("images/shadow_floor.png"));
			dragon = ImageIO.read(new File("images/dragon.png"));
			dragon_sleeping = ImageIO.read(new File(
					"images/dragon_sleeping.png"));
			knight = ImageIO.read(new File("images/knight.png"));
			sword_knight = ImageIO.read(new File("images/sword_knight.png"));
			shield_knight = ImageIO.read(new File("images/shield_knight.png"));
			sword_shield_knight = ImageIO.read(new File(
					"images/sword_shield_knight.png"));
			dead_knight = ImageIO.read(new File("images/dead_knight.png"));
			shield = ImageIO.read(new File("images/shield.png"));
			fireball = ImageIO.read(new File("images/fireball2.png"));
			background = ImageIO.read(new File("images/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza os objetos do labirinto
	 */
	public void getElements() {
		myMaze = main.getMaze();
		dragoes = main.getDragoes();
		homem = main.getHomem();
		espada = main.getEspada();
		escudo = main.getEscudo();
		dardos = main.getDardos();
	}


	/**
	 * Pinta o jogo em modo gráfico
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		if (first_run) {
			g.drawImage(background, 0, 0, panel.getWidth(), panel.getHeight(),
					null);
			return;
		}
		panel.getWidth();
		dragao_cospe_fogo();
		int x1 = 0, y1 = 0;
		int size = myMaze.getMatrix().length;
		int sizePerTileH = panel.getWidth() / size;
		int sizePerTileV = panel.getHeight() / size;
		int sizeOfBrick = (int) (sizePerTileV * (1 + 0.46));
		for (int linha = 0; linha < size; linha++) {
			for (int coluna = 0; coluna < size; coluna++) {
				if (myMaze.getMatrix()[coluna][linha] == 'b') {
					if (myMaze.getMatrix()[coluna - 1][linha] == 'X') {
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

				if (myMaze.getMatrix()[coluna][linha] == 'S') {
					if (linha == 0)
						g.drawImage(brick_3d, x1, y1, sizePerTileH,
								(int) (sizePerTileH * 0.3), null);
					g.drawImage(floor, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
					g.drawImage(exit, x1, y1 + (int) (sizePerTileV * 0.33),
							sizePerTileH, sizePerTileV, null);
				}
				if (myMaze.getMatrix()[coluna][linha] == 'M') {
					if (myMaze.getMatrix()[coluna - 1][linha] == 'X') {
						g.drawImage(shadow_floor, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					} else {
						g.drawImage(floor, x1,
								y1 + (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					}
					g.drawImage(dead_knight, x1, y1
							+ (int) (sizePerTileV * 0.33), sizePerTileH,
							sizePerTileV, null);
				}
				if (myMaze.getMatrix()[coluna][linha] == 'B') {
					if (myMaze.getMatrix()[coluna - 1][linha] == 'X') {
						g.drawImage(shadow_floor, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					} else {
						g.drawImage(floor, x1,
								y1 + (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					}
					g.drawImage(dead_knight, x1, y1
							+ (int) (sizePerTileV * 0.33), sizePerTileH,
							sizePerTileV, null);
				}
				if (myMaze.getMatrix()[coluna][linha] == 'T')
					g.drawImage(brick, x1, y1, sizePerTileH, sizeOfBrick, null);
				if (myMaze.getMatrix()[coluna][linha] == ' ')
					if (myMaze.getMatrix()[coluna - 1][linha] == 'X') {
						g.drawImage(shadow_floor, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					} else {
						g.drawImage(floor, x1,
								y1 + (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
					}

				for (int i = 0; i < dardos.length; i++) {
					if (dardos[i].getPos().getX() == coluna
							&& dardos[i].getPos().getY() == linha
							&& dardos[i].getVisibilidade())
						g.drawImage(dart, x1, y1 + (int) (sizePerTileV * 0.33),
								sizePerTileH, sizePerTileV, null);
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
									+ (int) (sizePerTileV * 0.33),
									sizePerTileH, sizePerTileV, null);
						}
					}
				}

				if (homem.getPos().getX() == coluna
						&& homem.getPos().getY() == linha && homem.getVivo()) {
					if (!homem.getArmado() && !homem.getInvencivel())
						g.drawImage(knight, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);

					if (homem.getArmado() && !homem.getInvencivel())
						g.drawImage(sword_knight, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);

					if (!homem.getArmado() && homem.getInvencivel())
						g.drawImage(shield_knight, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);

					if (homem.getArmado() && homem.getInvencivel())
						g.drawImage(sword_shield_knight, x1, y1
								+ (int) (sizePerTileV * 0.33), sizePerTileH,
								sizePerTileV, null);
				}

				if (myMaze.getMatrix()[coluna][linha] == 'X')
					g.drawImage(brick, x1, y1, sizePerTileH, sizeOfBrick, null);
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


				if(coluna == 0 && linha == 0){
					if(homem.getDardos() > 0){
						g.drawImage(dart, x1, y1,
								sizePerTileH, sizePerTileV, null);
					}
				}



				x1 += sizePerTileH;

			}

			x1 = 0;
			y1 += sizePerTileV;
		}
		if(homem.getDardos() > 0){
			g.drawImage(dart, x1, y1,
					sizePerTileH, sizePerTileV, null);
		}
		limpa_fogo();
	}

	/**
	 * Limpa o fogo desenhado no board
	 */
	private void limpa_fogo() {
		for (int i = 0; i < dragoes.length; i++) {
			dragoes[i].setFogo(false);
		}
		int size = myMaze.getMatrix().length;
		for (int linha = 0; linha < size; linha++) {
			for (int coluna = 0; coluna < size; coluna++) {
				if (myMaze.getMatrix()[coluna][linha] == 'b')
					myMaze.getMatrix()[coluna][linha] = ' ';
			}
		}
	}

	/**
	 * Altera o board para desenhar fogo
	 */
	private void dragao_cospe_fogo() {

		for (int i = 0; i < dragoes.length; i++) {

			int direcao_h = 0; //0 = esquerda, 1 = direita
			for (int existe = 1; existe < tamanho; existe++){
				if (homem.getPos().getX() == dragoes[i].getPos().getX() + existe){
					direcao_h = 1;
				}
			}
			for (int existe = 1; existe < tamanho; existe++){
				if (homem.getPos().getX() == dragoes[i].getPos().getX() - existe){
					direcao_h = 0;
				}
			}

			int direcao_v = 0; //0 = cima, 1 = baixo
			for (int existe = 1; existe < tamanho; existe++){
				if (homem.getPos().getY() == dragoes[i].getPos().getY() + existe){
					direcao_v = 1;
				}
			}
			for (int existe = 1; existe < tamanho; existe++){
				if (homem.getPos().getY() == dragoes[i].getPos().getY() - existe){
					direcao_v = 0;
				}
			}


			if (dragoes[i].getFogo() && dragoes[i].getVivo()) {
				if (homem.getPos().getX() == dragoes[i].getPos().getX()) {
					if (direcao_v == 1){
						for (int x = 1; x <= 3; x++) {
							if (myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i].getPos().getY() + x] == 'X' || myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i].getPos().getY() + x] == 'S')
							{
								for (int temp = 1; temp < x; temp++)
								{
									myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i].getPos().getY() + temp] = ' ';
								}
								x = 4;
							}
							else
								myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
										.getPos().getY() + x] = 'b';
						}
					}
					else{
						for (int x = 1; x <= 3; x++) {
							if (myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
									.getPos().getY() - x] == 'X'
									|| myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
											.getPos().getY() - x] == 'S'){
								for (int temp = 1; temp < x; temp++)
								{
									myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i].getPos().getY() - temp] = ' ';
								}
								x = 4;
							}

							else
								myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
										.getPos().getY() - x] = 'b';
						}
					}
				}

				if (homem.getPos().getY() == dragoes[i].getPos().getY()) {
					if (direcao_h == 1){
						for (int x = 1; x <= 3; x++) {
							if (myMaze.getMatrix()[dragoes[i].getPos().getX() + x][dragoes[i]
									.getPos().getY()] == 'X'
									|| myMaze.getMatrix()[dragoes[i].getPos().getX() + x][dragoes[i]
											.getPos().getY()] == 'S'){
								for (int temp = 1; temp < x; temp++)
								{
									myMaze.getMatrix()[dragoes[i].getPos().getX() + temp][dragoes[i].getPos().getY()] = ' ';
								}
								x = 4;
							}
							else
								myMaze.getMatrix()[dragoes[i].getPos().getX() + x][dragoes[i]
										.getPos().getY()] = 'b';
						}
					}
					else{
						for (int x = 1; x <= 3; x++) {
							if (myMaze.getMatrix()[dragoes[i].getPos().getX() - x][dragoes[i]
									.getPos().getY()] == 'X'
									|| myMaze.getMatrix()[dragoes[i].getPos().getX() - x][dragoes[i]
											.getPos().getY()] == 'S'){
								for (int temp = 1; temp < x; temp++)
								{
									myMaze.getMatrix()[dragoes[i].getPos().getX() - temp][dragoes[i].getPos().getY()] = ' ';
								}
								x = 4;
							}
							else
								myMaze.getMatrix()[dragoes[i].getPos().getX() - x][dragoes[i]
										.getPos().getY()] = 'b';
						}
					}
				}
			}
		}
	}

	
	/**
	 * Dispara um dardo numa direcao, alterando o gráfico
	 * @param direcao
	 */
	private void disparar_dardo(int direcao) {
		if (homem.getDardos() > 0) {
			switch (direcao) {
			case 1: // CIMA
				for (int i = 1; i < tamanho; i++) {
					if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
					                                        .getY() - i] == 'X'
					                                        || myMaze.getMatrix()[homem.getPos().getX()][homem
					                                                                               .getPos().getY() - i] == 'S'
					                                                                               || myMaze.getMatrix()[homem.getPos().getX()][homem
					                                                                                                                      .getPos().getY() - i] == 'T') {
						myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
						                                    .getY() - i] = 'T';
						i = tamanho;
					} else {
						for (int j = 0; j < dragoes.length; j++) {
							if (homem.getPos().getX() == dragoes[j].getPos()
									.getX()
									&& homem.getPos().getY() - i == dragoes[j]
											.getPos().getY())
								i = tamanho;
						}
					}
				}
				break;
			case 2: // BAIXO
				for (int i = 1; i < tamanho; i++) {
					if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
					                                        .getY() + i] == 'X'
					                                        || myMaze.getMatrix()[homem.getPos().getX()][homem
					                                                                               .getPos().getY() + i] == 'S'
					                                                                               || myMaze.getMatrix()[homem.getPos().getX()][homem
					                                                                                                                      .getPos().getY() + i] == 'T') {
						myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
						                                    .getY() + i] = 'T';
						i = tamanho;
					} else {
						for (int j = 0; j < dragoes.length; j++) {
							if (homem.getPos().getX() == dragoes[j].getPos()
									.getX()
									&& homem.getPos().getY() + i == dragoes[j]
											.getPos().getY())
								i = tamanho;
						}
					}
				}
				break;
			case 3: // ESQUERDA
				for (int i = 1; i < tamanho; i++) {
					if (myMaze.getMatrix()[homem.getPos().getX() - i][homem.getPos()
					                                            .getY()] == 'X'
					                                            || myMaze.getMatrix()[homem.getPos().getX() - i][homem
					                                                                                       .getPos().getY()] == 'S'
					                                                                                       || myMaze.getMatrix()[homem.getPos().getX() - i][homem
					                                                                                                                                  .getPos().getY()] == 'T') {
						myMaze.getMatrix()[homem.getPos().getX() - i][homem.getPos()
						                                        .getY()] = 'T';
						i = tamanho;
					} else {
						for (int j = 0; j < dragoes.length; j++) {
							if (homem.getPos().getX() - i == dragoes[j]
									.getPos().getX()
									&& homem.getPos().getY() == dragoes[j]
											.getPos().getY())
								i = tamanho;
						}
					}
				}
				break;
			case 4: // DIREITA
				for (int i = 1; i < tamanho; i++) {
					if (myMaze.getMatrix()[homem.getPos().getX() + i][homem.getPos()
					                                            .getY()] == 'X'
					                                            || myMaze.getMatrix()[homem.getPos().getX() + i][homem
					                                                                                       .getPos().getY()] == 'S'
					                                                                                       || myMaze.getMatrix()[homem.getPos().getX() + i][homem
					                                                                                                                                  .getPos().getY()] == 'T') {
						myMaze.getMatrix()[homem.getPos().getX() + i][homem.getPos()
						                                        .getY()] = 'T';
						i = tamanho;
					} else {
						for (int j = 0; j < dragoes.length; j++) {
							if (homem.getPos().getX() + i == dragoes[j]
									.getPos().getX()
									&& homem.getPos().getY() == dragoes[j]
											.getPos().getY())
								i = tamanho;
						}
					}
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	/**
	 * Faz uma jogada
	 * @param e, a tecla pressionada
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == left) {
			logic.move_homem("a");
		} else if (e.getKeyCode() == right) {
			logic.move_homem("d");
		} else if (e.getKeyCode() == up) {
			logic.move_homem("w");
		} else if (e.getKeyCode() == down) {
			logic.move_homem("s");
		} else if (e.getKeyCode() == shoot_up && homem.getDardos() > 0) {
			disparar_dardo(1);
			logic.Dispara(1);
		} else if (e.getKeyCode() == shoot_down && homem.getDardos() > 0) {
			disparar_dardo(2);
			logic.Dispara(2);
		} else if (e.getKeyCode() == shoot_left && homem.getDardos() > 0) {
			disparar_dardo(3);
			logic.Dispara(3);
		} else if (e.getKeyCode() == shoot_right && homem.getDardos() > 0) {
			disparar_dardo(4);
			logic.Dispara(4);
		}
		logic.check_arma();
		repaint();
		
		if (logic.check_perto(3) == "morreste") {
			JDialog dialog = new DialogFinal("Foste morto pelo dragão.");
			dialog.setVisible(true);
		} 
		else if (logic.ganhou()) {
			JDialog dialog = new DialogFinal("Ganhaste!");
			dialog.setVisible(true);	
		}
		else if (logic.check_fogo()) {
			JDialog dialog = new DialogFinal("Foste morto pelo fogo.");
			dialog.setVisible(true);
		}
		else {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Main da interface gráfica
	 * @param args
	 */
	public static void main(String[] args) {
		f = new JFrame("Get Out of the Maze");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(800, 670));
		f.getContentPane().setLayout(new BorderLayout());
		panel = new InGameGUI();
		JPanel panel2 = new Buttons();
		f.getContentPane().add(panel, BorderLayout.CENTER);
		f.getContentPane().add(panel2, BorderLayout.SOUTH);
		f.pack();
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		panel.setFocusable(true);
		panel.requestFocus();
	}

	/**
	 * Retorna a escolha feita pelo utilizador
	 * @return escolha
	 */
	public int getEscolha() {
		return Data.getInstance().getEscolha();
	}

	/**
	 * Altera a escolha do utilizador
	 * @param escolha_int
	 */
	public void setEscolha(int escolha_int) {
		escolha = escolha_int;
	}

	/**
	 * Altera o tamanho do labirinto
	 * @param parseInt
	 */
	public void setTamanho(int parseInt) {
		tamanho = parseInt;
	}

	/**
	 * Retorna a main
	 * @return main
	 */
	public Data getMain() {
		return main;
	}

	/**
	 * Retorna o array com as teclas necessárias para o funcionamento do jogo
	 * @return teclas
	 */
	public int[] getTeclas() {
		int[] teclas = { up, down, left, right, shoot_up, shoot_down,
				shoot_left, shoot_right };
		return teclas;
	}

	/**
	 * Altera as teclas do jogo
	 * @param codigo_teclas
	 */
	public void setTeclas(int[] codigo_teclas) {
		up = codigo_teclas[0];
		down = codigo_teclas[1];
		left = codigo_teclas[2];
		right = codigo_teclas[3];
		shoot_up = codigo_teclas[4];
		shoot_down = codigo_teclas[5];
		shoot_left = codigo_teclas[6];
		shoot_right = codigo_teclas[7];
	}

	/**
	 * Retorna a imagem de uma espada
	 * @return sword
	 */
	public BufferedImage getSword() {
		return sword;
	}

	/**
	 * Retorna a imagem do chão
	 * @return floor
	 */
	public BufferedImage getFloor() {
		return floor;
	}

	/**
	 * Retorna a imagem do chão sombreado
	 * @return shadow_floor
	 */
	public BufferedImage getShadowFloor() {
		return shadow_floor;
	}

	/**
	 * Retorna a imagem duma parede destruída
	 * @return destroyed_brick
	 */
	public BufferedImage getDestroyed_brick() {
		return destroyed_brick;
	}

	/**
	 * Retorna a imagem da saída
	 * @return exit
	 */
	public BufferedImage getExit() {
		return exit;
	}

	/**
	 * Retorna a imagem duma erva
	 * @return floor
	 */
	public BufferedImage getGrass() {
		return floor;
	}

	/**
	 * Retorna a imagem de um dragão
	 * @return dragon
	 */
	public BufferedImage getDragon() {
		return dragon;
	}
	
	/**
	 * Retorna a imagem do homem
	 * @return knight
	 */
	public BufferedImage getKnight() {
		return knight;
	}

	/**
	 * Retorna a imagem de um escudo
	 * @return shield
	 */
	public BufferedImage getShield() {
		return shield;
	}
	
	/**
	 * Retorna a imagem de uma bola de fogo 
	 * @return fireball
	 */
	public BufferedImage getFireball() {
		return fireball;
	}

	/**
	 * Retorna a imagem de um dardo
	 * @return dart
	 */
	public BufferedImage getDart() {
		return dart;
	}

	/**
	 * Retorna a imagem de um dragão morto
	 * @return dragon_dead
	 */
	public BufferedImage getDragon_dead() {
		return dragon_dead;
	}

	/**
	 * Retorna a imagem de um dragão a dormir
	 * @return dragon_sleeping
	 */
	public BufferedImage getDragon_sleeping() {
		return dragon_sleeping;
	}

	/**
	 * Altera a primeira vez do utilizador
	 */
	public void setSecondRun() {
		first_run = false;
	}

	/**
	 * Retorna o primeiro jogo
	 * @return first_run
	 */
	public boolean getFirstRun() {
		return first_run;
	}

	/**
	 * Retorna a imagem de uma parede
	 * @return brick
	 */
	public BufferedImage getBrick() {
		return brick;
	}

	/**
	 * Retorna a imagem de uma parede em 3D
	 * @return brick_3d
	 */
	public BufferedImage getBrick3d() {
		return brick_3d;
	}
}
