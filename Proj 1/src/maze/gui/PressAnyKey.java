package maze.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PressAnyKey extends JDialog implements KeyListener{
	private static final long serialVersionUID = -5523838490427653429L;
	private int code = -1;
	Config main;
	String type;
	/**
	 * Cria um menu de onde só sai quando carrega numa tecla
	 * @param main
	 * @param type
	 */
	PressAnyKey(Config main, String type){
		this.main = main;
		this.type = type;
		this.addKeyListener(this);
		this.setLayout(null);
		JLabel carrega = new JLabel("Pressiona qualquer tecla...");
		this.setSize(300, 100);
		carrega.setBounds(40, 20 , 200, 25);
		this.setResizable(false);
		this.add(carrega);
		this.setModal(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		code = e.getKeyCode();
		this.setVisible(false);
		switch(type){
		case "up":
			main.setCima(code);
			break;
		case "down":
			main.setBaixo(code);
			break;
		case "left":
			main.setEsquerda(code);
			break;
		case "right":
			main.setDireita(code);
			break;
		case "shoot_up":
			main.setDispararCima(code);
			break;
		case "shoot_down":
			main.setDispararBaixo(code);
			break;
		case "shoot_left":
			main.setDispararEsquerda(code);
			break;
		case "shoot_right":
			main.setDispararDireita(code);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
