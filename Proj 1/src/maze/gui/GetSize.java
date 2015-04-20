package maze.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GetSize extends JDialog{
	private static final long serialVersionUID = -2949582686394141615L;

	GetSize(){
		this.setEnabled(true);
		this.setModal(true);
		JPanel pan=new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setSize(200, 200);
		//Texto
		JLabel myLabel = new JLabel("Tamanho do labirinto: ");
		myLabel.setBounds(0,0,100,100);
		JTextField caixa = new JTextField(3);
		caixa.setText("" + 11);
		//Botão
		JButton ok = new JButton("OK");
		pan.add(myLabel);
		pan.add(caixa);
		pan.add(ok);
		ok.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e) 
	      {
	    	  if (Integer.parseInt(caixa.getText()) < 5 || Integer.parseInt(caixa.getText()) > 51)
		    	{
		    		JDialog mensagem = new Message("O tamanho tem que estar entre 5 e 51");
		    		mensagem.setVisible(true);
		    	}
		    	else if (Integer.parseInt(caixa.getText()) % 2 == 0)
		    		CreateMaze.construct(Integer.parseInt(caixa.getText()) + 1);
		    	else if (Integer.parseInt(caixa.getText()) % 2 != 0)
		    		CreateMaze.construct(Integer.parseInt(caixa.getText()));
	    	 GetSize.this.setVisible(false);
	    	 CreateMaze.getMaze().setFocusable(true);
	    	 CreateMaze.getMaze().requestFocus();
	      }
	    });
		this.getContentPane().add(pan);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Para não poder voltar ao jogo
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
