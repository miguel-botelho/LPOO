package maze.logic;

import java.util.Random;
import java.util.Scanner;

import maze.cli.CLI;
import maze.elements.Dragao;
import maze.elements.Espada;
import maze.elements.Homem;
import maze.elements.Maze;
import maze.elements.Data;
import maze.elements.*;

public class Logic {
	Homem homem;
	Dragao[] dragoes;
	int size;
	Espada espada;
	Escudo escudo;
	Maze myMaze;
	Dardo[] dardos;
	Scanner myScanner;
	CLI cli = new CLI();
	int morreu_com_fogo = 0;
	boolean cuspiu_fogo = false;
	int escolha;

	/**
	 * Cria uma nova lógica de jogo, com um Maze e a existência de mais de um
	 * dragão
	 * 
	 * @param labirinto
	 * @param multiplosDragoes
	 */
	public Logic(Maze labirinto, boolean multiplosDragoes) {
		Dragao[] thisdragoes = {};
		if (!multiplosDragoes) {
			thisdragoes = new Dragao[1];
			thisdragoes[0] = new Dragao();
			thisdragoes[0].setPos(1, 3);
		} else {
			thisdragoes = new Dragao[4];
			thisdragoes[0] = new Dragao();
			thisdragoes[0].setPos(1, 3);
			thisdragoes[1] = new Dragao();
			thisdragoes[1].setPos(4, 1);
			thisdragoes[2] = new Dragao();
			thisdragoes[2].setPos(4, 5);
			thisdragoes[3] = new Dragao();
			thisdragoes[3].setPos(1, 5);
		}
		Dardo thisdardo = new Dardo();
		thisdardo.setPos(4, 3);
		Dardo[] thisdardos = { thisdardo };
		Homem thishomem = new Homem();
		thishomem.setPos(1, 1);
		Escudo thisescudo = new Escudo();
		thisescudo.setPos(8, 1);
		Espada thisespada = new Espada();
		thisespada.setPos(1, 8);
		Data.setMain();
		Data.jogo_de_teste(labirinto, thishomem, thisdragoes, thisdardos,
				thisespada, thisescudo);
		homem = Data.getInstance().getHomem();
		dragoes = Data.getInstance().getDragoes();
		size = Data.getInstance().getSize();
		espada = Data.getInstance().getEspada();
		myMaze = Data.getInstance().getMaze();
		myScanner = Data.getInstance().getScanner();
		escolha = Data.getInstance().getEscolha();
		escudo = Data.getInstance().getEscudo();
		dardos = Data.getInstance().getDardos();
	}

	/**
	 * Construtor vazio de Logic
	 */
	public Logic() {
	}

	/**
	 * Move os dragões
	 */
	public void mover_dragao() {
		homem = Data.getInstance().getHomem();
		dragoes = Data.getInstance().getDragoes();
		size = Data.getInstance().getSize();
		espada = Data.getInstance().getEspada();
		myMaze = Data.getInstance().getMaze();
		escolha = Data.getInstance().getEscolha();
		dragoes();
	}

	/**
	 * Atualiza os objetos do labirinto
	 */
	public void getElements() {
		homem = Data.getInstance().getHomem();
		dragoes = Data.getInstance().getDragoes();
		size = Data.getInstance().getSize();
		espada = Data.getInstance().getEspada();
		myMaze = Data.getInstance().getMaze();
		escolha = Data.getInstance().getEscolha();
		escudo = Data.getInstance().getEscudo();
		dardos = Data.getInstance().getDardos();
	}

	/**
	 * Verifica se os dragões estão a dormir e, se não, ativa uma jogada para
	 * cada um
	 */
	private void dragoes() {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getDormir() > 0) {
				dragoes[i].setDormir(dragoes[i].getDormir() - 1);
				System.out.println("a dormir");
			} else {
				if (escolha != 1 && dragoes[i].getVivo()) {
					jogada_dragao(0, i);
				}
			}
		}
	}

	/**
	 * Dispara um dardo numa direcao, num
	 * 
	 * @param num
	 */
	public void Dispara(int num) {
		Maze myMaze = Data.getInstance().getMaze();
		Homem homem = Data.getInstance().getHomem();
		Dragao[] dragoes = Data.getInstance().getDragoes();
		int size = Data.getInstance().getSize();

		switch (num) {
		case 1: // CIMA
			homem.subDardos();
			for (int i = homem.getPos().getY(); i > 0; i--) {
				if (myMaze.getMatrix()[homem.getPos().getX()][i] == 'X')
					break;
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].getPos().getX() == homem.getPos().getX()) {
						if (dragoes[j].getPos().getY() == i) {
							dragoes[j].setVivo(false);
						}
					}
				}
			}
			break;
		case 2: // BAIXO
			homem.subDardos();
			for (int i = homem.getPos().getY(); i < size - 1; i++) {
				if (myMaze.getMatrix()[homem.getPos().getX()][i] == 'X')
					break;
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].getPos().getX() == homem.getPos().getX()) {
						if (dragoes[j].getPos().getY() == i) {
							dragoes[j].setVivo(false);
							return;
						}
					}
				}
			}
			break;
		case 3: // ESQUERDA
			homem.subDardos();
			for (int i = homem.getPos().getX(); i > 0; i--) {
				if (myMaze.getMatrix()[i][homem.getPos().getY()] == 'X')
					break;
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].getPos().getY() == homem.getPos().getY()) {
						if (dragoes[j].getPos().getX() == i) {
							dragoes[j].setVivo(false);
						}
					}
				}
			}
			break;
		case 4: // DIREITA
			homem.subDardos();
			for (int i = homem.getPos().getX(); i < size - 1; i++) {
				if (myMaze.getMatrix()[i][homem.getPos().getY()] == 'X')
					break;
				for (int j = 0; j < dragoes.length; j++) {
					if (dragoes[j].getPos().getY() == homem.getPos().getY()) {
						if (dragoes[j].getPos().getX() == i) {
							dragoes[j].setVivo(false);
						}
					}
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Verifica a existencia de dragões perto do homem
	 * 
	 * @return i, o indíce do dragão que estiver perto
	 */
	public int perto() {
		Homem homem = Data.getInstance().getHomem();
		Dragao[] dragoes = Data.getInstance().getDragoes();
		for (int i = 0; i < dragoes.length; i++) {
			if (((homem.getPos().getX() == dragoes[i].getPos().getX() + 1 && homem
					.getPos().getY() == dragoes[i].getPos().getY())
					|| (homem.getPos().getX() == dragoes[i].getPos().getX() - 1 && homem
							.getPos().getY() == dragoes[i].getPos().getY())
					|| (homem.getPos().getY() == dragoes[i].getPos().getY() + 1 && homem
							.getPos().getX() == dragoes[i].getPos().getX())
					|| (homem.getPos().getY() == dragoes[i].getPos().getY() - 1 && homem
							.getPos().getX() == dragoes[i].getPos().getX()) || (homem
					.getPos().getX() == dragoes[i].getPos().getX() && homem
					.getPos().getY() == dragoes[i].getPos().getY()))) {
				return i;
			}
		}
		return 100;
	}

	/**
	 * Verifica a possibilidade de um dragão lançar fogo ao homem
	 * 
	 * @return 1 se sim, 0 se não
	 */
	public int Fireball() {
		Homem homem = Data.getInstance().getHomem();
		Dragao[] dragoes = Data.getInstance().getDragoes();
		char[][] board = Data.getInstance().getBoard();
		cuspiu_fogo = true;
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getVivo()) {
				if (homem.getPos().getX() == dragoes[i].getPos().getX()) {
					for (int j = 1; j <= 3; j++) {
						if (board[homem.getPos().getX()][homem.getPos().getY()
								+ j] == 'X') {
							j = 3;
							break;
						}
						if (homem.getPos().getY() + j == dragoes[i].getPos()
								.getY()) {
							return 1;
						}
					}
					for (int j = 1; j <= 3; j++) {
						if (board[homem.getPos().getX()][homem.getPos().getY()
								- j] == 'X') {
							j = 3;
							break;
						}
						if (homem.getPos().getY() - j == dragoes[i].getPos()
								.getY()) {
							return 1;
						}
					}

				} else if (homem.getPos().getY() == dragoes[i].getPos().getY()) {
					for (int j = 1; j <= 3; j++) {
						if (board[homem.getPos().getX() + j][homem.getPos()
								.getY()] == 'X') {
							j = 3;
							break;
						}
						if (homem.getPos().getX() + j == dragoes[i].getPos()
								.getX()) {
							return 1;
						}
					}

					for (int j = 1; j <= 3; j++) {
						if (board[homem.getPos().getX() - j][homem.getPos()
								.getY()] == 'X') {
							j = 3;
							break;
						}
						if (homem.getPos().getX() - j == dragoes[i].getPos()
								.getX()) {
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Atualiza todo o labirinto, usado na cli
	 */
	public void atualizar_labirinto() {
		homem = Data.getInstance().getHomem();
		dragoes = Data.getInstance().getDragoes();
		dardos = Data.getInstance().getDardos();
		escudo = Data.getInstance().getEscudo();
		espada = Data.getInstance().getEspada();
		myMaze = Data.getInstance().getMaze();
		CLI cli = Data.getInstance().getCLI();
		myScanner = Data.getInstance().getScanner();
		String myChar = Data.getInstance().getChar();
		escolha = Data.getInstance().getEscolha();
		check_arma();

		check_perto(escolha);

		if (escolha != 1)
			if (check_fogo())
				return;

		check_arma();

		if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos().getY()] == 'S') {
			if (ganhou()) {
				myChar = "q";
				return;
			}
		} else {
			cli.imprimir_labirinto();

			myChar = myScanner.next();
			move_homem(myChar);

		}
	}

	/**
	 * Verifica se o herói morre com o fogo
	 * 
	 * @return true se sim
	 */
	public boolean check_fogo() {

		if (morreu_com_fogo == 1) {
			if (!(homem.getInvencivel())) {
				Data.getInstance().setChar("q");
				System.out.println("MORRESTE COM O FOGO");
				homem.setVivo(false);
				myMaze.getMatrix()[homem.getPos().getX()][homem.getPos().getY()] = 'B'; // morto
				// por
				// bola
				morreu_com_fogo = 0;
				return true;
			}
		}
		morreu_com_fogo = 0;
		return false;
	}

	/**
	 * Move o homem numa determinada direcao, myChar
	 * 
	 * @param myChar
	 * @return dragões vivos
	 */
	public boolean move_homem(String myChar) {
		switch (myChar.charAt(0)) {
		case 'd':
			if (myMaze.getMatrix()[homem.getPos().getX() + 1][homem.getPos()
					.getY()] == 'X'
					|| myMaze.getMatrix()[homem.getPos().getX() + 1][homem
							.getPos().getY()] == 'T'
					|| (myMaze.getMatrix()[homem.getPos().getX() + 1][homem
							.getPos().getY()] == 'S' && !(homem.getArmado()))) {
				cli.jogada_invalida();
			} else {
				homem.setPos(homem.getPos().getX() + 1, homem.getPos().getY());
				mover_dragao();
			}
			break;
		case 'a':
			if (myMaze.getMatrix()[homem.getPos().getX() - 1][homem.getPos()
					.getY()] == 'X'
					|| myMaze.getMatrix()[homem.getPos().getX() - 1][homem
							.getPos().getY()] == 'T'
					|| (myMaze.getMatrix()[homem.getPos().getX() - 1][homem
							.getPos().getY()] == 'S' && !(homem.getArmado()))) {
				cli.jogada_invalida();
			} else {
				homem.setPos(homem.getPos().getX() - 1, homem.getPos().getY());
				mover_dragao();
			}
			break;
		case 'w':
			if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos().getY() - 1] == 'X'
					|| myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
							.getY() - 1] == 'T'
					|| (myMaze.getMatrix()[homem.getPos().getX()][homem
							.getPos().getY() - 1] == 'S' && !(homem.getArmado()))) {
				cli.jogada_invalida();
			} else {
				homem.setPos(homem.getPos().getX(), homem.getPos().getY() - 1);
				mover_dragao();
			}
			break;
		case 's':
			if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos().getY() + 1] == 'X'
					|| myMaze.getMatrix()[homem.getPos().getX()][homem.getPos()
							.getY() + 1] == 'T'
					|| (myMaze.getMatrix()[homem.getPos().getX() + 1][homem
							.getPos().getY() + 1] == 'S' && !(homem.getArmado()))) {
				cli.jogada_invalida();
			} else {
				homem.setPos(homem.getPos().getX(), homem.getPos().getY() + 1);
				mover_dragao();
			}
			break;
		case 'q':
			Data.getInstance().setChar("q");
			break;
		case 'r':
			if (homem.getDardos() == 0) {
				cli.jogada_invalida();
			} else {
				int num = cli.dardos();
				Dispara(num);
			}
			break;
		default:
			break;
		}
		return DragaoVivo();
	}

	/**
	 * Retorna a posição do homem
	 * 
	 * @return pos
	 */
	public Position getHomemPos() {
		return homem.getPos();
	}

	/**
	 * Retorna a arma do homem
	 * 
	 * @return armado
	 */
	public boolean getHomemArmado() {
		return homem.getArmado();
	}

	/**
	 * Retorna o labirinto
	 * 
	 * @return
	 */
	public Maze getMaze() {
		return myMaze;
	}

	/**
	 * Verifica a arma do homem
	 */
	public void check_arma() {
		if (homem.getPos().getX() == espada.getPos().getX()
				&& homem.getPos().getY() == espada.getPos().getY()) {
			homem.setArmado(true);
		}
		if (homem.getPos().getX() == escudo.getPos().getX()
				&& homem.getPos().getY() == escudo.getPos().getY()) {
			escudo.setVisibilidade(false);
			homem.setInvencivel(true);
		}

		for (int i = 0; i < dardos.length; i++) {
			if (dardos[i].getVisibilidade()
					&& dardos[i].getPos().getX() == homem.getPos().getX()
					&& dardos[i].getPos().getY() == homem.getPos().getY()) {
				homem.addDardos();
				dardos[i].setVisibilidade(false);
			}
		}

	}

	/**
	 * Verifica se o homem está perto de um dragão, e se o mesmo está a dormir
	 * ou não
	 * 
	 * @param escolha
	 * @return "morreste", se estiver, se não, null
	 */
	public String check_perto(int escolha) {
		if (perto() != 100) {
			int retorno = perto();
			if (dragoes[retorno].getVivo()) {
				if (homem.getArmado()) {
					dragoes[retorno].setVivo(false);
					return "mataste";
				} else if (escolha == 2 && dragoes[retorno].getDormir() > 1) {

				} else {
					if (dragoes[retorno].getDormir() == 0) {
						Data.getInstance().setChar("q");
						homem.setVivo(false);
						myMaze.getMatrix()[homem.getPos().getX()][homem
								.getPos().getY()] = 'M'; // morto
						return "morreste";
					}
				}
			}
		}
		return "";
	}

	/**
	 * Verifica a condição de vitória
	 * 
	 * @return true se o homem ganhou
	 */
	public boolean ganhou() {
		if (myMaze.getMatrix()[homem.getPos().getX()][homem.getPos().getY()] == 'S')
			if (homem.getArmado() && !DragaoVivo()) {
				System.out.println("GANHOU");
				Data.getInstance().setChar("q");
				return true;
			} else {
				if (homem.getPos().getX() == 0)
					homem.setPos(homem.getPos().getX() + 1, homem.getPos()
							.getY());
				if (homem.getPos().getX() == size - 1)
					homem.setPos(homem.getPos().getX() - 1, homem.getPos()
							.getY());
				if (homem.getPos().getY() == 0)
					homem.setPos(homem.getPos().getX(),
							homem.getPos().getY() + 1);
				if (homem.getPos().getY() == size - 1)
					homem.setPos(homem.getPos().getX(),
							homem.getPos().getY() - 1);

				System.out.println("NAO ESTA ARMADO OU NAO MATOU TODOS OS DRAGOES");
			}
		return false;
	}

	/**
	 * Verifica a existencia de mais do que um dragão
	 * 
	 * @return true se existir mais do que um dragão
	 */
	public boolean multiplosDragoes() {
		if (dragoes.length >= 1)
			return true;
		else
			return false;
	}

	/**
	 * Faz uma jogada para um dragão, i
	 * 
	 * @param number
	 * @param i
	 */
	public void jogada_dragao(int number /*
										 * A zero se aleatorio, acima de zero
										 * para controlar o dragao
										 */, int i) {
		boolean jogada_valida = false;
		while (!jogada_valida) {

			Random random = new Random();
			int random2 = random.nextInt(6) + 1;
			if (number > 0)
				random2 = number;
			switch (random2) {
			case 1: // CIMA
				if (myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
						.getPos().getY() - 1] != 'X'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() - 1] != 'S'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() - 1] != 'T') {
					jogada_valida = true;
					dragoes[i].getPos().setY(dragoes[i].getPos().getY() - 1);
					morreu_com_fogo = Fireball();
					if (morreu_com_fogo == 1)
					{
						dragoes[i].setFogo(true);
						System.out.println("FOGO");
					}
						
				}
				break;
			case 2: // BAIXO
				if (myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
						.getPos().getY() + 1] != 'X'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() + 1] != 'S'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() - 1] != 'T') {
					jogada_valida = true;
					dragoes[i].getPos().setY(dragoes[i].getPos().getY() + 1);
					morreu_com_fogo = Fireball();
					if (morreu_com_fogo == 1)
					{
						dragoes[i].setFogo(true);
						System.out.println("FOGO");
					}
				}
				break;
			case 3: // ESQUERDA
				if (myMaze.getMatrix()[dragoes[i].getPos().getX() - 1][dragoes[i]
						.getPos().getY()] != 'X'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX() - 1][dragoes[i]
								.getPos().getY()] != 'S'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() - 1] != 'T') {
					jogada_valida = true;
					dragoes[i].getPos().setX(dragoes[i].getPos().getX() - 1);
					morreu_com_fogo = Fireball();
					if (morreu_com_fogo == 1)
					{
						dragoes[i].setFogo(true);
						System.out.println("FOGO");
					}
				}
				break;
			case 4: // DIREITA
				if (myMaze.getMatrix()[dragoes[i].getPos().getX() + 1][dragoes[i]
						.getPos().getY()] != 'X'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX() + 1][dragoes[i]
								.getPos().getY()] != 'S'
						&& myMaze.getMatrix()[dragoes[i].getPos().getX()][dragoes[i]
								.getPos().getY() - 1] != 'T') {
					jogada_valida = true;
					dragoes[i].getPos().setX(dragoes[i].getPos().getX() + 1);
					morreu_com_fogo = Fireball();
					if (morreu_com_fogo == 1)
					{
						dragoes[i].setFogo(true);
						System.out.println("FOGO");
					}
				}
				break;
			case 5: // PARADO
				System.out.println("parado");
				jogada_valida = true;
				morreu_com_fogo = Fireball();
				if (morreu_com_fogo == 1)
				{
					dragoes[i].setFogo(true);
					System.out.println("FOGO");
				}
				break;
			case 6: // DORMIR
				if (escolha == 2 || number == 6) { // number no caso de ser
													// controlado
					int dormir = random.nextInt(6) + 1;
					dragoes[i].setDormir(dormir);
					jogada_valida = true;
				} else {
					jogada_valida = false;
				}
				break;
			case 7:// cuspir fogo
				System.out.println("CUSPIU FOGO");
				dragoes[i].setFogo(true);// usado para mostrar dragao a cuspir
											// fogo na interface grafica
				morreu_com_fogo = Fireball();
				jogada_valida = true;
			}
		}
	}

	/**
	 * Retorna a posição do primeiro dragão
	 * 
	 * @return pos
	 */
	public Position getPosicaoDragao() {
		return dragoes[0].getPos();
	}

	/**
	 * Retorna o estado de dormida do dragão
	 * 
	 * @return true, se estiver a dormir
	 */
	public boolean dragaoADormir() {
		if (dragoes[0].getDormir() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Retorna o facto de o dragão ter cuspido fogo
	 * 
	 * @return cuspiu_fogo
	 */
	public boolean dragaoACuspirFogo() {
		return cuspiu_fogo;
	}

	/**
	 * Retorna a possibilidade de haver um escudo no homem
	 * 
	 * @return invencivel
	 */
	public boolean getHomemProtegido() {
		return homem.getInvencivel();
	}

	/**
	 * Retorna o número de dardos do homem
	 * 
	 * @return numDardos
	 */
	public int getHomemDardos() {
		return homem.getDardos();
	}

	/**
	 * Verifica a existência de dragões vivos
	 * 
	 * @return true, se houver
	 */
	public boolean DragaoVivo() {
		for (int i = 0; i < dragoes.length; i++) {
			if (dragoes[i].getVivo())
				return true;
		}
		return false;
	}

	/**
	 * Retorna uma instância desta classe
	 * 
	 * @return instance
	 */
	public Data getMain() {
		return Data.getInstance();
	}

	public void dragoesDead() {
		for(int i = 0; i < dragoes.length;i++){
			dragoes[i].setVivo(false);
		}
	}
}
