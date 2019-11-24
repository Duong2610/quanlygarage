package quanlygargae;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrm extends JFrame {
	private JButton btnMenu1;

	public MenuFrm() throws HeadlessException {
		btnMenu1= new JButton("Luachon1");
		JPanel f2= new JPanel();
		f2.setLayout(new FlowLayout());
		f2.add(btnMenu1);
		this.setContentPane(f2);
		this.pack();
	}
	
}
