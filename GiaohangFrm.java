package quanlygargae;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiaohangFrm extends JFrame implements ActionListener{
    private LinhkienFrm parent;
    private chitietlinhkien chitiet;
    private int index;
    private JTextField txtTen, txtSoluong;
    private JButton btnXacnhan;
    private JTextField txtTime;

    public GiaohangFrm(LinhkienFrm parent, chitietlinhkien chitiet, int index){
        super("Xac nhan");
        this.parent = parent;
        this.chitiet = chitiet;
        this.index = index;
        
        txtTen = new JTextField(20);
        txtTen.setEditable(false);
        txtSoluong = new JTextField(20);
        txtSoluong.setEditable(false);
        txtTime = new JTextField(20);
        txtTime.setEditable(false);
        btnXacnhan = new JButton("Xac nhan");

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(5,2));
        content.add(new JLabel("Ten linh kien:")); content.add(txtTen);
        content.add(new JLabel("So luong:")); content.add(txtSoluong);
        content.add(new JLabel("Thoi gian:")); 
        content.add(txtTime);
        content.add(btnXacnhan);     

        btnXacnhan.addActionListener(this);

        initForm();

        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public String clock() {
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(cal.YEAR);
    	int month = cal.get(cal.MONTH);
    	int day = cal.get(cal.DATE);
    	int hr = cal.get(cal.HOUR);
    	int min =cal.get(cal.MINUTE);
    	return (hr+":"+min+" - "+day+"/"+month+"/"+year);
    }

    private void initForm(){
    	
        if(chitiet != null){
            txtTen.setText(chitiet.getTenlinhkien());
            txtSoluong.setText(chitiet.getSoluong()+"");
            String time = clock();
            txtTime.setText(time);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton)e.getSource();

        if(btnClicked.equals(btnXacnhan)){
            btnUpdateClick();
        }
    }

    private void btnUpdateClick(){
    	chitiet.setThoigiangiaohang(txtTime.getText());
        LinhkienDAO lkDAO = new LinhkienDAO();
        lkDAO.giaolinhkien(chitiet);
        parent.refreshResultAfterUpdate(index, chitiet);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
} 