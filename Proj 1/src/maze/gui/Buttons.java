package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Buttons extends JPanel {
	private static final long serialVersionUID = 8191986985111010035L;

	/**
	 * Construtor de Buttons Cria os botões iniciais
	 */
	Buttons() {
		JButton novo_jogo = new JButton("Novo Jogo");
		JButton configurar = new JButton("Configurações");
		JButton salvar = new JButton("Salvar");
		JButton carregar = new JButton("Carregar");
		JButton criar_labirinto = new JButton("Criar Labirinto");
		JButton sair = new JButton("Sair");
		novo_jogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InGameGUI.getInstance().setSecondRun();
				InGameGUI.getInstance().new_game();
				JPanel myPanel = InGameGUI.getInstance().getPanel();
				myPanel.setFocusable(true);
				myPanel.requestFocus();
			}
		});
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (InGameGUI.getInstance().getFirstRun()) {
					JDialog message = new Message("Nao estás em nenhum jogo.");
					message.setVisible(true);
					return;
				}
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("tmp/"));
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Saves save = new Saves();
					save.Save(file.getAbsolutePath());
					JPanel myPanel = InGameGUI.getInstance().getPanel();
					myPanel.setFocusable(true);
					myPanel.requestFocus();
				}
			}
		});

		carregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InGameGUI.getInstance().setSecondRun();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("tmp/"));
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Saves save = new Saves();
					save.Load(file.getAbsolutePath());
					InGameGUI.getInstance().getElements();
					InGameGUI.getLogic().getElements();
					JPanel myPanel = InGameGUI.getInstance().getPanel();
					myPanel.setFocusable(true);
					myPanel.requestFocus();
					InGameGUI.getInstance().repaint();
				}
			}
		});

		configurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Config();
			}
		});

		criar_labirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new GetSize();
				dialog.setVisible(true);
				return;
			}
		});

		this.add(novo_jogo);
		this.add(configurar);
		this.add(salvar);
		this.add(carregar);
		this.add(criar_labirinto);
		this.add(sair);
	}
}
