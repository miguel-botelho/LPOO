package maze.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JDialog{
	private static final long serialVersionUID = -5891887546715598122L;

	Message(String text){
		this.setEnabled(true);
		this.setModal(true);
		JPanel pan=new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setSize(200, 200);
		
		
		//Texto
		JLabel myLabel = new JLabel(text);
		myLabel.setBounds(0,0,100,100);
		//Botão
		JButton ok = new JButton("OK");
		
		ok.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 Message.this.setVisible(false);
	      }
	    });
		pan.add(myLabel);
		pan.add(ok);
		this.getContentPane().add(pan);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Para não poder voltar ao jogo
		this.pack();
		this.setLocationRelativeTo(null);
		}
}
