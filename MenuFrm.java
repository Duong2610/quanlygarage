package quanlygargae;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrm extends JFrame implements ActionListener {
	private JButton btnMenu1;
	private JButton btnMenu2;
	
	public MenuFrm(){
		super("Menu");
		btnMenu1= new JButton("Tim hoa don linh kien");
		btnMenu1.addActionListener(this);
		btnMenu2= new JButton("Kiem tra hoa don");
		btnMenu2.addActionListener(this);
		JPanel f2= new JPanel();
		f2.setLayout(new FlowLayout());
		f2.add(btnMenu1);
		f2.add(btnMenu2);
		this.setContentPane(f2);
		this.pack();
	}
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		if (btnClicked.equals(btnMenu1)) {
			LinhkienFrm frm1= new LinhkienFrm();
			frm1.setSize(600, 300);
			frm1.setVisible(true);			
			frm1.setLocation(200, 10);
			this.dispose();
		}
		if (btnClicked.equals(btnMenu2)) {
			KiemtraFrm frm2= new KiemtraFrm();
			frm2.setSize(600,300);
			frm2.setVisible(true);
			frm2.setLocation(200,10);
			this.dispose();
		}
	}
}
