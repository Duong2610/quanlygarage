package quanlygargae;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginFrm extends JFrame implements ActionListener{
    private JTextField txttaikhoan;
    private JPasswordField txtmatkhau;
    private JButton btnLogin;
    private account acc;
 
    public LoginFrm(){
       
        txttaikhoan = new JTextField(15);
        txtmatkhau = new JPasswordField(15);
        txtmatkhau.setEchoChar('*');
        btnLogin = new JButton("Login");
 
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Username:"));
        content.add(txttaikhoan);
        content.add(new JLabel("Password:"));
        content.add(txtmatkhau);
        content.add(btnLogin);
 
        btnLogin.addActionListener(this);
 
        this.setContentPane(content);
        this.pack();
 
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
    }
 
    public account getUser(){
        acc = new account(txttaikhoan.getText(), txtmatkhau.getText());
        return acc;       
    }
 
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
 
    public void addLoginListener(ActionListener log) {
          btnLogin.addActionListener(log);
        }
}