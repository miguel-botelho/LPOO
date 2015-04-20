package maze.cli;

import java.util.Scanner;

import maze.elements.*;

public class CLI {

	Scanner myScanner = new Scanner(System.in);;
	
	public static void main(String[] args) {
		Data.setMain(new Data());
		Data.preencher_labirinto();
		while (Data.getMyChar().charAt(0) != 'q') {
			Data.getLogic().atualizar_labirinto();
		}
	}
	
	/**
	 * Imprime o labirinto em modo texto 
	 */
	public void imprimir_labirinto(){
		Homem homem = Data.getInstance().getHomem();
		Dragao  [] dragoes = Data.getInstance().getDragoes();
		Dardo [] dardos = Data.getInstance().getDardos();
		int size = Data.getInstance().getSize();
		Espada espada = Data.getInstance().getEspada();
		int escolha = Data.getInstance().getEscolha();
		Escudo escudo = Data.getInstance().getEscudo();
		char [][] Copia =  Data.getInstance().getBoard();
		char [][] Board = new char[size][size];
		for (int linha = 0 ; linha < size; linha++){
			for(int coluna = 0; coluna < size; coluna++){
				Board[coluna][linha] = Copia[coluna][linha];
			}

		}

		for (int linha = 0 ; linha < size; linha++){
			for(int coluna = 0; coluna < size; coluna++){
				if(coluna == homem.getPos().getX() && linha == homem.getPos().getY()){
					if(!homem.getArmado()){
						if (homem.getInvencivel())
						{
							Board[coluna][linha] = 'P';
						}
						else{
							Board[coluna][linha] = 'H';
						}
					}else if (homem.getArmado()){
						Board[coluna][linha] = 'A';
					}
					else{
					}
				}else if(coluna == espada.getPos().getX() && linha == espada.getPos().getY()){
					if(!homem.getArmado()){
						if(FStatement()){
							Board[coluna][linha] = 'F';
						}else{
							Board[coluna][linha] = 'E';
						}
					}else{
						Board[coluna][linha] = ' ';
					}
				}else if (coluna == escudo.getPos().getX() && linha == escudo.getPos().getY()){
					if (escudo.getVisibilidade())
					{
						Board[coluna][linha] = 'P';
					}
					else
					{
						Board[coluna][linha] = ' ';
					}
				}
				else{
				}

				for(int i = 0; i < dragoes.length; i++){
					if(coluna == dragoes[i].getPos().getX() && linha == dragoes[i].getPos().getY()){
						if(!((dragoes[i].getPos().getX() == homem.getPos().getX() && dragoes[i].getPos().getY() == homem.getPos().getY()) || (dragoes[i].getPos().getX() == espada.getPos().getX() && dragoes[i].getPos().getY() == espada.getPos().getY())))
							if(dragoes[i].getVivo()){
								if((!(Board[coluna][linha] == 'D') && !(Board[coluna][linha] == 'd')))
									if(escolha == 3){
										Board[coluna][linha] = 'D';
									}else if(escolha == 2){
										if(dragoes[i].getDormir() > 0){
											Board[coluna][linha] = 'd';
										}else{
											Board[coluna][linha] = 'D';
										}
									}else if(escolha == 1){
										Board[coluna][linha] = 'D';
									}
							}else{
								Board[coluna][linha] = ' ';
							}
					}
				}

				for(int i = 0; i < dardos.length; i++){
					if(coluna == dardos[i].getPos().getX() && linha == dardos[i].getPos().getY()){
						if(!((dardos[i].getPos().getX() == homem.getPos().getX() && dardos[i].getPos().getY() == homem.getPos().getY()) || (dardos[i].getPos().getX() == espada.getPos().getX() && dardos[i].getPos().getY() == espada.getPos().getY())))
							if(dardos[i].getVisibilidade()){
								Board[coluna][linha] = '>';
							}else{
								Board[coluna][linha] = ' ';
							}
					}
				}
			}

		}
		for (int linha = 0 ; linha < size; linha++){
			for(int coluna = 0; coluna < size; coluna++){
				System.out.print(Board[coluna][linha]);
			}
			System.out.println();
		}

		System.out.println("Numero de dardos: " + homem.getDardos());

	}

	/**
	 * Verifica a existencia de um dragao e uma espada na mesma posiçao
	 * @return true se a espada se encontra na mesma posicao que um dragao, se nao false
	 */
	boolean FStatement(){
		Dragao[] dragoes = Data.getInstance().getDragoes();
		Espada espada =  Data.getInstance().getEspada();
		for(int i = 0; i < dragoes.length ; i++){
			if(dragoes[i].getPos().getX() == espada.getPos().getX() && dragoes[i].getPos().getY() == espada.getPos().getY()){
				return true;
			}
		}
		return false;
	}
	/**
	 * Verifica a existencia de um dragao em coluna e linha
	 * @param coluna
	 * @param linha
	 * @return true se sim
	 */
	boolean haUmDragaoEm(int coluna, int linha){
		Dragao[] dragoes = Data.getInstance().getDragoes();
		for(int i = 0; i < dragoes.length ; i++){
			if(dragoes[i].getPos().getX() == coluna && dragoes[i].getPos().getY() == linha)
				return true;
		}
		return false;
	}

	/**
	 * Tamanho do labirinto
	 * @return size
	 */
	public int tamanho(){
		int size = -1;
		while ( size <= 5 || size % 2 == 0){
			System.out.print("Tamanho do labirinto? ");
			size  = myScanner.nextInt();
		}
		return size;
	}

	/**
	 * Pergunta ao utilizador a escolha do modo dos dragões
	 * @return escolha
	 */
	public int escolha(){
		int escolha = -1;
		while ( escolha != 1 && escolha != 2 && escolha != 3){
			System.out.print("1 - Parado \n2 - Dormir intercalado \n3 - Acordado\n\n");
			escolha  = myScanner.nextInt();
		}
		return escolha;
	}

	/**
	 * Pergunta ao utilizador em que direcao quer disparar o dardo.
	 * @return direcao
	 */
	public int dardos(){
		int num = 0;
		while (num < 1 || num > 4)
		{
			System.out.println("Em que direcao queres disparar o dardo?");
			System.out.println("1: Cima");
			System.out.println("2: Baixo");
			System.out.println("3: Esquerda");
			System.out.println("4: Direita");
			num = myScanner.nextInt();
		}
		return num;
	}

	/**
	 * print Dormir
	 */
	public void printDormir() {
		System.out.println("A dormir");
	}

	/**
	 * print Jogada Inválida
	 */
	public void jogada_invalida() {
		System.out.println("JOGADA INVALIDA");
	}
}