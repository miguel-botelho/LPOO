package maze.test;

import static org.junit.Assert.*;
import maze.elements.Maze;
import maze.logic.Logic;

import org.junit.Test;

public class Testing {
	
	/**
	 * Construtor de um labirinto previamente definido, de teste
	 * @return maze
	 */
	Maze labirinto_de_teste(){
		Maze labirinto = new Maze();
		labirinto.setMatrix(new char[10][10]);
		for(int i = 0; i < 10; i++){
			labirinto.getMatrix()[i][0] = 'X';
			labirinto.getMatrix()[0][i] = 'X';
			labirinto.getMatrix()[i][9] = 'X';
			labirinto.getMatrix()[9][i] = 'X';
		}
		for(int i = 2; i < 9; i++){
			labirinto.getMatrix()[2][i] = 'X';
			labirinto.getMatrix()[3][i] = 'X';
			labirinto.getMatrix()[5][i] = 'X';
			labirinto.getMatrix()[7][i] = 'X';
		}
		labirinto.getMatrix()[2][5] = ' ';
		labirinto.getMatrix()[3][5] = ' ';
		labirinto.getMatrix()[5][5] = ' ';
		labirinto.getMatrix()[5][8] = ' ';
		labirinto.getMatrix()[7][8] = ' ';
		labirinto.getMatrix()[9][5] = 'S';
		return labirinto;
	}
	
	@Test
	/**
	 * Testa a movimentação do homem
	 */
	public void testMover() {
		String array_mover[] = {"d","d","d","d","d","s"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
		}
		assertEquals(6,myLogic.getHomemPos().getX());
		assertEquals(2,myLogic.getHomemPos().getY());
	}
	
	@Test
	/**
	 * Testa a movimentação inválida do homem
	 */
	public void testImovel() {
		String array_mover[] = {"a","w","d","w","s","s","w","s","w"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
		}
		assertEquals(2,myLogic.getHomemPos().getX());
		assertEquals(1,myLogic.getHomemPos().getY());
	}
	
	@Test
	/**
	 * Testa se o homem apanha a espada
	 */	
	public void testEspada() {
		String array_mover[] = {"d","d","d","s","s","s","s","a","a","a","s","s","s"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertTrue(myLogic.getHomemArmado());
	}
	
	@Test
	/**
	 * Testa se o homem morre para dragões
	 */
	public void testMorto() {
		String array_mover[] = {"s"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
		}
		assertEquals("morreste",myLogic.check_perto(1));
	}
	
	@Test
	/**
	 * Testa se o homem mata dragões 
	 */
	public void testMatarCima() {
		String array_mover[] = {"d","d","d","s","s","s","s","a","a","a","s","s","s","w","w","w","w"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertEquals("mataste",myLogic.check_perto(1));
	}
	
	@Test
	/**
	 * Testa a possibilidade de ganhar o jogo
	 */
	public void testGanhar() {
		String array_mover[] = {"d","d","d","s","s","s","s","a","a","a","s","s","s","w","w","w","w","s","d","d","d","d","d","s"
				,"s","s","d","d","w","w","w","d"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		myLogic.dragoesDead();
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertTrue(myLogic.ganhou());
	}

	@Test
	/**
	 * Testa a possibilidade de não ganhar o jogo
	 */
	public void testNaoGanhar() {
		String array_mover[] = {"d","d","d","d","d","d","w","d","d","s","s","s","s","d"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertFalse(myLogic.ganhou());
	}
	@Test
	/**
	 * Testa a possibilidade de homem morrer com o fogo aleatoriamente
	 */
	public void testNaoMorrerFogo() {
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,true);
		assertFalse(myLogic.check_fogo());
	}
	
	
	@Test
	/**
	 * Testa a existência de mais do que um dragão
	 */
	public void testMultiplosDragoes() {
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,true);
		assertTrue(myLogic.multiplosDragoes());
	}
	
	@Test
	/**
	 * Testa a movimentação de um dragão
	 */
	public void testmovimentoDragao() {
		
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {2,2,4,4,4,1,2,3,4};// 1 cima, 2 baixo, 3 esquerda, 4 direita
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		assertEquals(4,myLogic.getPosicaoDragao().getX());
		assertEquals(5,myLogic.getPosicaoDragao().getY());
	}
	
	@Test
	/**
	 * Testa se os dragões dormem
	 */
	public void testDormir() {
		
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {6};// 6 dormir
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		assertTrue(myLogic.dragaoADormir());
	}
	
	@Test
	/**
	 * Testa se os dragões cospem chamas
	 */
	public void testFogo() {
		
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {7};// 7 cuspir fogo
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		assertTrue(myLogic.dragaoACuspirFogo());
	}

	@Test
	/**
	 * Testa se o homem apanha o escudo
	 */
	public void testEscudo() {
		String array_mover[] = {"d","d","d","d","d","d","d"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertTrue(myLogic.getHomemProtegido());
	}
	
	@Test
	/**
	 * Testa se o homem apanha dardos
	 */
	public void testDardos() {
		String array_mover[] = {"d","d","d","s","s"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		for(int i = 0; i < array_mover.length;i++){
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		assertTrue(myLogic.getHomemDardos() > 0);
	}
	
	@Test
	/**
	 * Testa se os dragões morrem quando o homem dispara os dardos
	 */
	public void testMatarComDardos() {
		String array_mover[] = {"d","d","d","s","s","s","s","a","a","a"};
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		myLogic.Dispara(4);
		for(int i = 0; i < array_mover.length;i++){
			if(i == 3) myLogic.Dispara(2);
			if(i == 7) myLogic.Dispara(3);
			myLogic.move_homem(array_mover[i]);
			myLogic.check_arma();
		}
		myLogic.Dispara(1);
		assertFalse(myLogic.DragaoVivo());
	}
	
	@Test
	/**
	 * Testa se o dragão acorda
	 */
	public void testDragaoAcordar() {
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {6};// 6 dormir
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		while(myLogic.dragaoADormir()){
			myLogic.mover_dragao();
		}
		assertFalse(myLogic.dragaoADormir());
	}
	
	@Test
	/**
	 * Testa se o homem morre para as chamas
	 */
	public void testMorrerComFogo() {
		
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {7};// 7 cuspir fogo
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		assertTrue(myLogic.Fireball() != 100);
		assertTrue(myLogic.check_fogo());
	}
	
	@Test
	/**
	 * Testa se o dragão fica parado
	 */
	public void testDragaoParado() {
		
		Maze labirinto = labirinto_de_teste();
		Logic myLogic = new Logic(labirinto,false);
		int array_jogada_dragao[] = {5};// 5 para estar parado
		for(int i = 0; i < array_jogada_dragao.length;i++){
			myLogic.jogada_dragao(array_jogada_dragao[i],0);
		}
		assertEquals(1,myLogic.getPosicaoDragao().getX());
		assertEquals(3,myLogic.getPosicaoDragao().getY());
	}
}
