package maze.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import maze.elements.*;

public class Saves {
	
	/**
	 * Guarda o jogo no absolutePath
	 * @param absolutePath
	 */
	void Save(String absolutePath) {
		Dragao[] dragoes = InGameGUI.getInstance().getMain().getDragoes();
		Escudo escudo = InGameGUI.getInstance().getMain().getEscudo();
		Espada espada = InGameGUI.getInstance().getMain().getEspada();
		Homem homem = InGameGUI.getInstance().getMain().getHomem();
		char[][] board = InGameGUI.getInstance().getMain().getBoard();
		Dardo[] dardos = InGameGUI.getInstance().getMain().getDardos();
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(absolutePath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(dragoes);
	         out.writeObject(escudo);
	         out.writeObject(espada);
	         out.writeObject(homem);
	         out.writeObject(board);
	         out.writeObject(dardos);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + absolutePath);
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          return;
	      }
		return;
	}
	
	/**
	 * Carrega o jogo do absolute path
	 * @param absolutePath
	 */
	void Load(String absolutePath) {
		Dragao[] dragoes = null;
		Escudo escudo = null;
		Espada espada = null;
		Homem homem = null;
		char[][] board = null;
		Dardo[] dardos = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(absolutePath);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         dragoes = (Dragao[]) in.readObject();
	         escudo = (Escudo) in.readObject();
	         espada = (Espada) in.readObject();
	         homem = (Homem) in.readObject();
	         board = (char[][]) in.readObject();
	         dardos = (Dardo[]) in.readObject();
	         InGameGUI.getInstance().getMain().setDragoes(dragoes);
	         InGameGUI.getInstance().getMain().setEscudo(escudo);
	         InGameGUI.getInstance().getMain().setEspada(espada);
	         InGameGUI.getInstance().getMain().setHomem(homem);
	         InGameGUI.getInstance().getMain().setBoard(board);
	         InGameGUI.getInstance().getMain().setDardos(dardos);
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Maze class not found");
	         c.printStackTrace();
	         return;
	      }
	      return;
	}
	
	/**
	 * Guarda o jogo criado pelo utilizador em absolutePath
	 * @param absolutePath
	 */
	void SaveCreatedMaze(String absolutePath) {
		
		
		Dragao[] dragoes = CreateMaze.getDragoes();
		Escudo escudo = CreateMaze.getEscudo();
		Espada espada = CreateMaze.getEspada();
		Homem homem = CreateMaze.getHomem();
		char[][] board = CreateMaze.getBoard();
		Dardo[] dardos = CreateMaze.getDardos();
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(absolutePath);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(dragoes);
	         out.writeObject(escudo);
	         out.writeObject(espada);
	         out.writeObject(homem);
	         out.writeObject(board);
	         out.writeObject(dardos);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + absolutePath);
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          return;
	      }
		return;
	}
}
