package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maze.elements.MazeBuilder;

public class Config extends JDialog {
	private static final long serialVersionUID = 5070709421348126451L;
	private int codigo_teclas[] = InGameGUI.getInstance().getTeclas();
	private int escolha_int = InGameGUI.getInstance().getEscolha();

	private JButton cima_tecla;
	private JButton baixo_tecla;
	private JButton esquerda_tecla;
	private JButton direita_tecla;

	private JButton disparar_cima_tecla;
	private JButton disparar_baixo_tecla;
	private JButton disparar_esquerda_tecla;
	private JButton disparar_direita_tecla;

	/**
	 * O menu de configuração do jogo
	 */
	Config() {
		this.setLayout(null);
		JLabel tamanho = new JLabel("Tamanho: ");
		JTextField caixa = new JTextField(3);
		JLabel seleciona = new JLabel("O Dragão vai estar:");
		JLabel escolha = new JLabel();
		setEscolha(escolha);
		caixa.setText("" + InGameGUI.getInstance().getTamanho());
		tamanho.setBounds(50, 0, 100, 50);
		caixa.setBounds(150, 13, 50, 25);
		seleciona.setBounds(30, 50, 150, 50);
		escolha.setBounds(145, 50, 150, 50);
		JLabel aplicado = new JLabel();
		aplicado.setBounds(30, 480, 250, 25);

		JComboBox<String> lista = new JComboBox<String>();
		lista.addItem("Dormir");
		lista.addItem("Dormir/Acordado");
		lista.addItem("Acordado");
		switch (escolha_int) {
		case 1:
			lista.setSelectedItem("Dormir");
			break;
		case 2:
			lista.setSelectedItem("Dormir/Acordado");
			break;
		case 3:
			lista.setSelectedItem("Acordado");
			break;
		}
		lista.setBounds(145, 60, 100, 25);

		JLabel n_dragoes = new JLabel("Max nº dragoes: ");
		JTextField caixa_2 = new JTextField(3);
		n_dragoes.setBounds(40, 110, 100, 25);
		caixa_2.setBounds(150, 110, 50, 25);
		caixa_2.setText("" + MazeBuilder.getNumDragons());
		JLabel teclas = new JLabel("Carrega para alterar os comandos: ");
		teclas.setBounds(30, 150, 230, 25);

		// Teclas

		JLabel cima = new JLabel("Cima: ");
		cima.setBounds(40, 180, 180, 20);
		cima_tecla = new JButton();
		cima_tecla.setText(KeyEvent.getKeyText(codigo_teclas[0]));
		cima_tecla.setBounds(190, 180, 70, 25);
		cima_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "up");
			}
		});

		JLabel baixo = new JLabel("Baixo: ");
		baixo.setBounds(40, 210, 150, 20);
		baixo_tecla = new JButton();
		baixo_tecla.setText(KeyEvent.getKeyText(codigo_teclas[1]));
		baixo_tecla.setBounds(190, 210, 70, 25);
		baixo_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "down");
			}
		});

		JLabel esquerda = new JLabel("Esquerda: ");
		esquerda.setBounds(40, 240, 150, 20);
		esquerda_tecla = new JButton();
		esquerda_tecla.setText(KeyEvent.getKeyText(codigo_teclas[2]));
		esquerda_tecla.setBounds(190, 240, 70, 25);
		esquerda_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "left");
			}
		});

		JLabel direita = new JLabel("Direita: ");
		direita.setBounds(40, 270, 150, 20);
		direita_tecla = new JButton();
		direita_tecla.setText(KeyEvent.getKeyText(codigo_teclas[3]));
		direita_tecla.setBounds(190, 270, 70, 25);
		direita_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "right");
			}
		});

		JLabel disparar_cima = new JLabel("Disparar para cima: ");
		disparar_cima.setBounds(40, 300, 150, 20);
		disparar_cima_tecla = new JButton();
		disparar_cima_tecla.setText(KeyEvent.getKeyText(codigo_teclas[4]));
		disparar_cima_tecla.setBounds(190, 300, 70, 25);
		disparar_cima_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "shoot_up");
			}
		});

		JLabel disparar_baixo = new JLabel("Disparar para baixo: ");
		disparar_baixo.setBounds(40, 330, 150, 20);
		disparar_baixo_tecla = new JButton();
		disparar_baixo_tecla.setText(KeyEvent.getKeyText(codigo_teclas[5]));
		disparar_baixo_tecla.setBounds(190, 330, 70, 25);
		disparar_baixo_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "shoot_down");
			}
		});

		JLabel disparar_esquerda = new JLabel("Disparar para esquerda: ");
		disparar_esquerda.setBounds(40, 360, 150, 20);
		disparar_esquerda_tecla = new JButton();
		disparar_esquerda_tecla.setText(KeyEvent.getKeyText(codigo_teclas[6]));
		disparar_esquerda_tecla.setBounds(190, 360, 70, 25);
		disparar_esquerda_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "shoot_left");
			}
		});

		JLabel disparar_direita = new JLabel("Disparar para direita: ");
		disparar_direita.setBounds(40, 390, 150, 20);
		disparar_direita_tecla = new JButton();
		disparar_direita_tecla.setText(KeyEvent.getKeyText(codigo_teclas[7]));
		disparar_direita_tecla.setBounds(190, 390, 70, 25);
		disparar_direita_tecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PressAnyKey(Config.this, "shoot_right");
			}
		});

		JButton aplicar = new JButton("Aplicar");
		aplicar.setBounds(50, 430, 90, 25);
		aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lista.getSelectedIndex() + 1 != InGameGUI.getInstance()
						.getEscolha()
						|| Integer.parseInt(caixa.getText()) != InGameGUI
								.getInstance().getTamanho()
					|| teclasDiferentes() || Integer.parseInt(caixa_2.getText()) != MazeBuilder.getNumDragons()) {
					InGameGUI.getInstance().setEscolha(
							lista.getSelectedIndex() + 1);

					if (Integer.parseInt(caixa.getText()) < 5
							|| Integer.parseInt(caixa.getText()) > 51) {
						Message m = new Message("O tamanho tem que estar entre 5 e 51");
						m.setVisible(true);
						return;
					} else if (Integer.parseInt(caixa.getText()) % 2 == 0)
						InGameGUI.getInstance().setTamanho(
								Integer.parseInt(caixa.getText()) + 1);
					else if (Integer.parseInt(caixa.getText()) % 2 != 0)
						InGameGUI.getInstance().setTamanho(
						Integer.parseInt(caixa.getText()));
					
					if(teclas_repetidas()){
						Message m = new Message("Tens teclas repetidas, nao podes!");
						m.setVisible(true);
						return;
					}
					
					
					if(Integer.parseInt(caixa_2.getText()) != MazeBuilder.getNumDragons()){
						if(Integer.parseInt(caixa_2.getText()) > (int)(0.4 * Integer.parseInt(caixa.getText()))){
							Message m = new Message("Os teus dragoes nao podem ser mais que " + (int)(0.4 * Integer.parseInt(caixa.getText())));
							m.setVisible(true);
							return;
						}else
						MazeBuilder.setNumDragons(Integer.parseInt(caixa_2.getText()));
					}
					
					InGameGUI.getInstance().setTeclas(codigo_teclas);
					aplicado.setText("As suas alterações foram aplicadas");
					
				}
			}

			private boolean teclasDiferentes() {
				for (int i = 0; i < codigo_teclas.length; i++) {
					if (codigo_teclas[i] != InGameGUI.getInstance().getTeclas()[i])
						return true;
				}
				return false;
			}
		});

		JButton voltar = new JButton("Voltar");
		voltar.setBounds(140, 430, 90, 25);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel myPanel = InGameGUI.getInstance().getPanel();
				Config.this.setVisible(false);
				myPanel.setFocusable(true);
				myPanel.requestFocus();
				Config.this.dispose();
			}
		});
		this.add(tamanho);
		this.add(caixa);
		this.add(seleciona);
		this.add(lista);
		this.add(aplicado);
		this.add(aplicar);
		this.add(voltar);
		this.add(caixa_2);
		this.add(n_dragoes);
		this.add(teclas);
		this.add(cima);
		this.add(baixo);
		this.add(esquerda);
		this.add(direita);
		this.add(disparar_cima);
		this.add(disparar_baixo);
		this.add(disparar_esquerda);
		this.add(disparar_direita);
		this.add(cima_tecla);
		this.add(baixo_tecla);
		this.add(esquerda_tecla);
		this.add(direita_tecla);
		this.add(disparar_cima_tecla);
		this.add(disparar_baixo_tecla);
		this.add(disparar_esquerda_tecla);
		this.add(disparar_direita_tecla);
		this.setSize(300, 560);
		this.setResizable(false);
		this.setModal(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Para não
																	// poder
																	// voltar ao
																	// jogo
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected boolean teclas_repetidas() {
		for(int i = 0; i < codigo_teclas.length; i++){
			for(int a = 0; a < codigo_teclas.length; a++){
				if(a != i){
					if(codigo_teclas[a] == codigo_teclas[i])
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * Altera a escolha do utilizador relativamente à atitude dos dragões
	 * 
	 * @param escolha
	 */
	void setEscolha(JLabel escolha) {
		switch (escolha_int) {
		case 1:
			escolha.setText("Parado");
			break;
		case 2:
			escolha.setText("Dormir/Acordado");
			break;
		case 3:
			escolha.setText("Acordado");
			break;
		default:
			escolha.setText("N/a");
			break;
		}
	}

	/**
	 * Altera a tecla para andar para cima
	 * 
	 * @param code
	 */
	public void setCima(int code) {
		codigo_teclas[0] = code;
		cima_tecla.setText(KeyEvent.getKeyText(codigo_teclas[0]));
	}

	/**
	 * Altera a tecla para andar para baixo
	 * 
	 * @param code
	 */
	public void setBaixo(int code) {
		codigo_teclas[1] = code;
		baixo_tecla.setText(KeyEvent.getKeyText(codigo_teclas[1]));
	}

	/**
	 * Altera a tecla para andar para a esquerda
	 * 
	 * @param code
	 */
	public void setEsquerda(int code) {
		codigo_teclas[2] = code;
		esquerda_tecla.setText(KeyEvent.getKeyText(codigo_teclas[2]));
	}

	/**
	 * Altera a tecla para andar para a direita
	 * 
	 * @param code
	 */
	public void setDireita(int code) {
		codigo_teclas[3] = code;
		direita_tecla.setText(KeyEvent.getKeyText(codigo_teclas[3]));
	}

	/**
	 * Altera a tecla para andar para disparar para cima
	 * 
	 * @param code
	 */
	public void setDispararCima(int code) {
		codigo_teclas[4] = code;
		disparar_cima_tecla.setText(KeyEvent.getKeyText(codigo_teclas[4]));
	}

	/**
	 * Altera a tecla para andar para disparar para baixo
	 * 
	 * @param code
	 */
	public void setDispararBaixo(int code) {
		codigo_teclas[5] = code;
		disparar_baixo_tecla.setText(KeyEvent.getKeyText(codigo_teclas[5]));
	}

	/**
	 * Altera a tecla para andar para disparar para a esquerda
	 * 
	 * @param code
	 */
	public void setDispararEsquerda(int code) {
		codigo_teclas[6] = code;
		disparar_esquerda_tecla.setText(KeyEvent.getKeyText(codigo_teclas[6]));
	}

	/**
	 * Altera a tecla para andar para disparar para a direita
	 * 
	 * @param code
	 */
	public void setDispararDireita(int code) {
		codigo_teclas[7] = code;
		disparar_direita_tecla.setText(KeyEvent.getKeyText(codigo_teclas[7]));
	}

}
