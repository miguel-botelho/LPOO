package maze.elements;

import java.io.Serializable;
import java.util.Scanner;

import maze.cli.CLI;
import maze.elements.Homem;
import maze.elements.Maze;
import maze.logic.*;

public class Data implements Serializable {
	private static final long serialVersionUID = 4180121475487778521L;
	private static Homem homem;
	private static Espada espada;
	private static Escudo escudo;
	private static Dragao[] dragoes;
	private static Dardo[] dardos;
	private static Scanner myScanner = new Scanner(System.in);
	private static String myChar = "null";
	private static int size;
	private static Maze myMaze = new Maze();
	private static int escolha; // 1 para dormir, 2 para dormir intercalado, 3
								// para estar sempre acordado
	private static CLI cli = new CLI();
	private static Logic logic = new Logic();

	/**
	 * Retorna a instância deste objeto
	 * 
	 * @return main
	 */
	public static Data getInstance() {
		return main;
	}

	/**
	 * Construtor
	 */
	public Data() {
	}

	private static Data main;

	/**
	 * Muda o tamanho do labirinto
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		Data.size = size;
	}

	/**
	 * Retorna os dragões
	 * 
	 * @return dragoes
	 */
	public Dragao[] getDragoes() {
		return dragoes;
	}

	/**
	 * Retorna os dardos
	 * 
	 * @return dardos
	 */
	public Dardo[] getDardos() {
		return dardos;
	}

	/**
	 * Retorna o homem
	 * 
	 * @return homem
	 */
	public Homem getHomem() {
		return homem;
	}

	/**
	 * Retorna a espada
	 * 
	 * @return espada
	 */
	public Espada getEspada() {
		return espada;
	}

	/**
	 * Retorna o escudo
	 * 
	 * @return escudo
	 */
	public Escudo getEscudo() {
		return escudo;
	}

	/**
	 * Retorna o tamanho
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Retorna o labirinto
	 * 
	 * @return myMaze
	 */
	public Maze getMaze() {
		return myMaze;
	}

	/**
	 * Retorna a interface
	 * 
	 * @return cli
	 */
	public CLI getCLI() {
		return cli;
	}

	/**
	 * Retorna o scanner usado em modo de texto
	 * 
	 * @return myScanner
	 */
	public Scanner getScanner() {
		return myScanner;
	}

	/**
	 * Retorna a tecla que o utilizador premiu
	 * 
	 * @return myChar
	 */
	public String getChar() {
		return getMyChar();
	}

	/**
	 * Muda a tecla do utilizador
	 * 
	 * @param myString
	 */
	public void setChar(String myString) {
		setMyChar(myString);
	}

	/**
	 * Retorna a escolha de dragões do utilizador
	 * 
	 * @return escolha
	 */
	public int getEscolha() {
		return escolha;
	}

	/**
	 * Retorna o board do labirinto
	 * 
	 * @return board
	 */
	public char[][] getBoard() {
		return myMaze.getMatrix();
	}

	/**
	 * Altera os dragoes
	 * 
	 * @param dragoes
	 */
	public void setDragoes(Dragao[] dragoes) {
		Data.dragoes = dragoes;
	}

	/**
	 * Altera os dardos
	 * 
	 * @param dardos
	 */
	public void setDardos(Dardo[] dardos) {
		Data.dardos = dardos;
	}

	/**
	 * Altera o escudo
	 * 
	 * @param escudo
	 */
	public void setEscudo(Escudo escudo) {
		Data.escudo = escudo;
	}

	/**
	 * Altera a espada
	 * 
	 * @param espada
	 */
	public void setEspada(Espada espada) {
		Data.espada = espada;
	}

	/**
	 * Altera o homem
	 * 
	 * @param homem
	 */
	public void setHomem(Homem homem) {
		Data.homem = homem;
	}

	/**
	 * Altera o board do labirinto
	 * 
	 * @param board
	 */
	public void setBoard(char[][] board) {
		Data.myMaze.setMatrix(board);
	}

	/**
	 * Cria um novo labirinto
	 * 
	 * @param size
	 */
	public static void criar_labirinto(int size) {
		MazeBuilder inBuildMaze = new MazeBuilder(size);
		myMaze.setMatrix(new char[size][size]);
		myMaze.setMatrix(inBuildMaze.getBoard());
		dragoes = inBuildMaze.getDragoes();
		dardos = inBuildMaze.getDardos();
		espada = inBuildMaze.getEspada();
		homem = inBuildMaze.getHomem();
		escudo = inBuildMaze.getEscudo();
	}

	/**
	 * Cria um novo jogo designado para testes
	 * 
	 * @param labirinto
	 * @param thishomem
	 * @param dragao
	 * @param dardo
	 * @param thisespada
	 * @param thisescudo
	 */
	public static void jogo_de_teste(Maze labirinto, Homem thishomem,
			Dragao[] dragao, Dardo[] dardo, Espada thisespada, Escudo thisescudo) {
		myMaze = labirinto;
		dragoes = dragao;
		dardos = dardo;
		espada = thisespada;
		escudo = thisescudo;
		homem = thishomem;
		escolha = 1;
	}

	/**
	 * Preenche um labirinto novo em modo de texto
	 */
	public static void preencher_labirinto() {
		size = cli.tamanho();

		escolha = cli.escolha();

		criar_labirinto(size);

	}

	/**
	 * Main da versão de texto
	 * 
	 * @param args
	 */

	/**
	 * Cria um novo objeto
	 */
	public static void setMain() {
		main = new Data();
	}

	/**
	 * Altera a escolha do utilizador
	 * 
	 * @param escolha2
	 */
	public void setEscolha(int escolha2) {
		escolha = escolha2;
	}

	public static void setMain(Data data) {
		main = data;	
	}

	public static String getMyChar() {
		return myChar;
	}

	public static void setMyChar(String myChar) {
		Data.myChar = myChar;
	}

	public static Logic getLogic() {
		return logic;
	}

	public static void setLogic(Logic logic) {
		Data.logic = logic;
	}

}