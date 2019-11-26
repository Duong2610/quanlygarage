package quanlygargae;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginFrm f= new LoginFrm();
		LoginDAO dao= new LoginDAO(f);
		f.setVisible(true);
		f.setTitle("Quan ly garage");
		f.setSize(300,150);
		f.setLocation(200, 10);
	}

}
