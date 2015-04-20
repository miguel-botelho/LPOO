package maze.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogFinal extends JDialog { //Construtor de diálogos finais
	private static final long serialVersionUID = -8298848403287593550L;

	DialogFinal(String text){
	this.setEnabled(true);
	this.setModal(true);
	JPanel pan=new JPanel();
	pan.setLayout(new FlowLayout());
	pan.setSize(200, 200);
	//Texto
	JLabel myLabel = new JLabel(text);
	myLabel.setBounds(0,0,100,100);
	//Botão
	JButton sair = new JButton("Sair");
	JButton novo_jogo = new JButton("Novo Jogo");
	sair.setBounds(100,100,100,100);
	
	novo_jogo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
    	 InGameGUI.getInstance().new_game();
    	 JPanel myPanel = InGameGUI.getInstance().getPanel();
    	 DialogFinal.this.setVisible(false); 
    	 myPanel.setFocusable(true);
    	 myPanel.requestFocus();
      }
    });
	
	sair.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });
	pan.add(myLabel);
	pan.add(novo_jogo);
	pan.add(sair);
	this.getContentPane().add(pan);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Para não poder voltar ao jogo
	this.pack();
	this.setLocationRelativeTo(null);
	}
}
