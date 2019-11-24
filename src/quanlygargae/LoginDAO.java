package quanlygargae;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class LoginDAO {
    private account acc;
    private LoginFrm frm;
    
 
    public LoginDAO(LoginFrm frm){
        this.frm = frm;
 
        frm.addLoginListener(new LoginListener());
    }
 
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                acc = frm.getUser();
                if(checkUser(acc)){
                    frm.showMessage("Login succesfully!");
                    MenuFrm f2 = new MenuFrm();
                    f2.setVisible(true);
                    frm.dispose();
                }else{
                    frm.showMessage("Invalid username and/or password!");
                }                
            } catch (Exception ex) {
                frm.showMessage(ex.getStackTrace().toString());
            }
        }
    }
 
    public boolean checkUser(account acc) throws Exception {
 
        String dbUrl = "jdbc:mysql://localhost/quanlygarage";
        String dbClass = "com.mysql.cj.jdbc.Driver";
        String uname="root";
        String pw="1234";
        String query = "Select * FROM account WHERE taikhoan ='" + acc.getTaikhoan() 
                + "' AND matkhau ='" + acc.getMatkhau() + "'";
        try {
            Class.forName(dbClass);
            Connection con = DriverManager.getConnection (dbUrl,uname,pw);
            if(con!=null) {
            	System.out.println("Ket noi thanh cong");	
            }
            System.out.println(acc.getTaikhoan());
            System.out.println(acc.getMatkhau());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
              return true;
            }
            con.close();
        }catch(Exception e) {
            throw e;
        } 
        return false;
      }
} 
