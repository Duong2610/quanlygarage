package quanlygargae;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
 
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class LinhkienFrm extends JFrame implements ActionListener {
	private ArrayList<chitietlinhkien> listLinhkien;
    private ArrayList<JButton> listEdit;
    private JTextField txtKey;
    private JButton btnSearch;
    private JTable tblResult;
    private JButton btnReturn;
    public LinhkienFrm(){
        super("Giao linh kien");
        listLinhkien = new ArrayList<chitietlinhkien>();
        listEdit = new ArrayList<JButton>();

 
        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);        
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
 
        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
        pn1.setSize(this.getSize().width-5, 20);
        pn1.add(new JLabel("Ma hoa don: "));
        txtKey = new JTextField();
        pn1.add(txtKey);
        btnSearch = new JButton("Tim kiem");
        btnSearch.addActionListener(this);
        pn1.add(btnSearch);
        btnReturn=new JButton("Quay lai");
        btnReturn.addActionListener(this);
        pn1.add(btnReturn);
        pnMain.add(pn1);
 
        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));
        tblResult = new JTable(new linhkienTableModel());
        JScrollPane scrollPane= new  JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false); 
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));
 
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblResult.getColumn("Giao hang").setCellRenderer(buttonRenderer);
        tblResult.addMouseListener(new JTableButtonMouseListener(tblResult));
        pn2.add(scrollPane);        
        pnMain.add(pn2);    
        this.add(pnMain);
        pnMain.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnSearch)){
            btnSearchClick();
            return;
        }
        for(int i=0; i<listEdit.size(); i++)
            if(btnClicked.equals(listEdit.get(i))){
                btnEditClick(i);
                return;
            }
        if(btnClicked.equals(btnReturn)) {
        	btnReturnClick();
        	return;
        }
    }
    //Action clicking button Quaylai
    private void btnReturnClick() {
    	MenuFrm frm1= new MenuFrm();
    	frm1.setSize(600, 300);
    	frm1.setVisible(true);
    	frm1.setLocation(200, 10);
    	this.dispose();
    }
   //Action clicking button Timkiem
    private void btnSearchClick(){
        if((txtKey.getText() == null)||(txtKey.getText().length() == 0))
            return;
        LinhkienDAO lkDAO = new LinhkienDAO();
        listLinhkien = lkDAO.searchLinhkien(txtKey.getText().trim());
        listEdit.clear();
        for(int i=0; i<listLinhkien.size(); i++){
            JButton btn = new JButton("Giao hang");
            btn.addActionListener(this);
            listEdit.add(btn);
 
        }
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();
    }
 
    //Open confirm form
    private void btnEditClick(int index){
    	 
        (new GiaohangFrm(this, listLinhkien.get(index), index)).setVisible(true);
    }
 
   //Update data on table
    public void refreshResultAfterUpdate(int index, chitietlinhkien chitiet ){
        listLinhkien.remove(index);
//        listLinhkien.add(index, chitiet);
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();
    }
 
 
    class linhkienTableModel extends DefaultTableModel {
        private String[] columnNames = {"Ma","Ten linh kien","So luong","Giao hang"};
        private final Class<?>[] columnTypes = new  Class<?>[] {Integer.class, String.class, Integer.class, JButton.class};
 
        @Override public int getColumnCount() {
            return columnNames.length;
        }
 
        @Override public int getRowCount() {
            return listLinhkien.size();
        }
 
        @Override public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }
 
        @Override public Class<?> getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }
 
        @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
                //Adding components
            switch (columnIndex) {
                case 0: 
                    return listLinhkien.get(rowIndex).getMa();
                case 1: 
                    return listLinhkien.get(rowIndex).getTenlinhkien();
                case 2:
                	return listLinhkien.get(rowIndex).getSoluong();
                case 3: 
                    return listEdit.get(rowIndex);                   
                default: return "Error";
            }
        } 
    }
 
    class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;
 
        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }
 
        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
            int row    = e.getY()/table.getRowHeight(); //get the row of the button
 
                    //*Checking the row or column is valid or not
            if (row < table.getRowCount() && row >= 0  && column < table.getColumnCount() && column >= 0)  {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    //perform a click event
                    ((JButton)value).doClick();
                }
            }
        }
    }
 
    class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }
 
  
}
