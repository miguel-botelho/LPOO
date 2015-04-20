package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


import maze.elements.Maze;
import maze.test.TestMaze;

public class CreateOptions extends JPanel{
	private static final long serialVersionUID = 6930814056136819156L;
	static JComboBox<String> lista;
	
	/**
	 * Cria as opções do menu de criação de um labirinto
	 */
	CreateOptions(){
		JButton salvar = new JButton("Salvar");
		salvar.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {  
	        Maze labirinto = new Maze();
	  		labirinto.setMatrix(CreateMaze.getBoard());
	  		if(!TestMaze.checkBoundaries(labirinto)){
	  			JDialog mensagem = new Message("Não tens uma saída!");
	  			mensagem.setVisible(true);
	  			return;
	  		}else if(!TestMaze.checkExitReachable(labirinto)){
	  			JDialog mensagem = new Message("A tua saída tem que ser alcançável por todos os espaços em branco.");
	  			mensagem.setVisible(true);
	  			return;
	  		}else if(CreateMaze.getHomem().getPos().getX() == -1){
	  			JDialog mensagem = new Message("Não tens um jogador.");
	  			mensagem.setVisible(true);
	  			return;
	  		}else if(CreateMaze.getEspada().getPos().getX() == -1){
	  			JDialog mensagem = new Message("Precisas de uma espada para ganhar.");
	  			mensagem.setVisible(true);
	  			return;
	  		} else
				try {
					if(!TestMaze.testValidMaze(labirinto)){
						JDialog mensagem = new Message("O teu labirinto nao obedece às regras de construção.");
			  			mensagem.setVisible(true);
			  			return;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	  		
	    	 JFileChooser fileChooser = new JFileChooser();
	    	 fileChooser.setCurrentDirectory(new java.io.File("tmp/"));
	    	 if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	 File file = fileChooser.getSelectedFile();
	         Saves save = new Saves();
	    	 save.SaveCreatedMaze(file.getAbsolutePath());
	    	 JPanel myPanel = CreateMaze.getPanel();
	    	 myPanel.setFocusable(true);
	    	 myPanel.requestFocus();
	    	 }
	      } 
	    });
		
		
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 JPanel myPanel = InGameGUI.getInstance().getPanel();
	     	 CreateOptions.this.setVisible(false);
	     	 myPanel.setFocusable(true);
	     	 myPanel.requestFocus();
	     	 CreateMaze.getFrame().dispose();
	      }
	    });
		
		lista = new JComboBox<String>();
		lista.addItem("Saida");
		lista.addItem("Parede");
		lista.addItem("Homem");
		lista.addItem("Escudo");
		lista.addItem("Espada");
		lista.addItem("Dragao");
		lista.addItem("Dardo");
		this.add(salvar);
		this.add(voltar);
		this.add(lista);
	}
	
	/**
	 * Retorna o que o utilizador selecionou da lista
	 * @return item selecionado
	 */
	public static String getSelecionado(){
		return (String)lista.getSelectedItem();
	}
}
