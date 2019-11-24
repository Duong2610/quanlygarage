package quanlygargae;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kpham
 */
public class Front extends JFrame implements ActionListener{
    JButton btnReadSV = new JButton("Đọc Sinh Viên");
    JButton btnAddSV = new JButton("Thêm Sinh Viên");
    JButton btnWriteSV = new JButton("Ghi File");
    ArrayList<SinhVien> listSV = new ArrayList<>();
    
    JButton btnReadBT = new JButton("Đọc Bài Tập");
    JButton btnAddBT = new JButton("Thêm Bài Tập");
    JButton btnWriteBT = new JButton("Ghi File");
    ArrayList<BTL> listBT = new ArrayList<>();
    
    JButton btnReadDS = new JButton("Đọc Danh sách");
    JButton btnAddDS = new JButton("Thêm pair");
    JButton btnWriteDS = new JButton("Ghi File");
    JButton btnSort = new JButton("Sort");
    ArrayList<Pair<SinhVien, BTL> > listDS = new ArrayList<>();
    
    JTable tbSV = new JTable();
    Object[] colsSV = {"MaSV", "TenSV", "Lop"};
    DefaultTableModel dtmSV = new DefaultTableModel();
    
    JTable tbBT = new JTable();
    Object[] colsBT = {"MaBT", "TenBT", "Thoi han"};
    DefaultTableModel dtmBT = new DefaultTableModel();
    
    JTable tbDS = new JTable();
    Object[] colsDS = {"MaSV", "TenSV", "Lop", "MaBT", "TenBT", "Thoi han"};
    DefaultTableModel dtmDS = new DefaultTableModel();
    public Front(){
        JTabbedPane tp = new JTabbedPane();
        
        dtmSV.setColumnIdentifiers(colsSV);
        tbSV.setModel(dtmSV);
        JScrollPane sp1 = new JScrollPane(tbSV);
        
        dtmBT.setColumnIdentifiers(colsBT);
        tbBT.setModel(dtmBT);
        JScrollPane sp2 = new JScrollPane(tbBT);
        
        dtmDS.setColumnIdentifiers(colsDS);
        tbDS.setModel(dtmDS);
        JScrollPane sp3 = new JScrollPane(tbDS);
        
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());
        
        p1.add(sp1, BorderLayout.CENTER);
        JPanel p11 = new JPanel(new GridLayout(1, 3, 1, 1));
        p11.add(btnReadSV);
        p11.add(btnAddSV);
        p11.add(btnWriteSV);
        p1.add(p11, BorderLayout.SOUTH);
        
        p2.add(sp2, BorderLayout.CENTER);
        JPanel p21 = new JPanel(new GridLayout(1, 3, 1, 1));
        p21.add(btnReadBT);
        p21.add(btnAddBT);
        p21.add(btnWriteBT);
        p2.add(p21, BorderLayout.SOUTH);
        
        p3.add(sp3, BorderLayout.CENTER);
        JPanel p31 = new JPanel(new GridLayout(1, 4, 1, 1));
        p31.add(btnReadDS);
        p31.add(btnAddDS);
        p31.add(btnWriteDS);
        p31.add(btnSort);
        p3.add(p31, BorderLayout.SOUTH);
        
        tp.addTab("Sinh Viên", p1);
        tp.addTab("Bài tập lớn", p2);
        tp.addTab("SV-BT", p3);
        this.add(tp);
        
        btnReadSV.addActionListener(this);
        btnWriteSV.addActionListener(this);
        btnAddSV.addActionListener(this);
        btnReadBT.addActionListener(this);
        btnWriteBT.addActionListener(this);
        btnAddBT.addActionListener(this);
        btnReadDS.addActionListener(this);
        btnWriteDS.addActionListener(this);
        btnAddDS.addActionListener(this);
        btnSort.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReadSV){
            try {
                listSV = readFileSV();
                for(SinhVien sv: listSV){
                    dtmSV.addRow(new Object[]{sv.getMaSV(), sv.getTenSV(), sv.getLop()});
                }
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "File not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Error occurs", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnWriteSV){
            try {
                ghiFileSV();
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnAddSV){
            try{
                String temp = String.valueOf(listSV.size());
                StringBuilder sb = new StringBuilder("");
                for(int i = 0; i < 4 - temp.length(); i++){
                    sb.append("0");
                }
                sb.append(temp);
                
                String name = JOptionPane.showInputDialog(new JTextField(20), "Tên học sinh");
                char x = name.charAt(0);
                String cols[] = {"CN11", "CN12", "CN1"};
                String lop = (String) JOptionPane.showInputDialog(null, "Chọn lớp", "Lớp", JOptionPane.QUESTION_MESSAGE, null, cols, "CN11");
                listSV.add(new SinhVien(sb.toString(), name, lop));
                dtmSV.addRow(new Object[]{sb.toString(), name, lop});
            }catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Input Error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnReadBT){
            try {
                listBT = readFileBT();
                for(BTL bt: listBT){
                    dtmBT.addRow(new Object[]{bt.getMaBT(), bt.getTenBT(), bt.getTime()});
                }
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "File not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Error occurs", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnWriteBT){
            try {
                ghiFileBT();
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnAddBT){
            try{
                String temp = String.valueOf(listBT.size());
                StringBuilder sb = new StringBuilder("");
                for(int i = 0; i < 4 - temp.length(); i++){
                    sb.append("0");
                }
                sb.append(temp);
                
                String cols[] = {"OOP", "MySQL", "OS"};
                String name = (String) JOptionPane.showInputDialog(null, "Chọn đê tài", "Đề tài", JOptionPane.QUESTION_MESSAGE, null, cols, "OOP");
                int time = Integer.parseInt(JOptionPane.showInputDialog(new JTextField(20), "Thời gian hoàn thành"));
                listBT.add(new BTL(sb.toString(), name, time));
                dtmBT.addRow(new Object[]{sb.toString(), name, time});
            }catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Input Error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnReadDS){
            try {
                listDS = readFileDS();
                for(Pair<SinhVien, BTL> ds: listDS){
                    dtmDS.addRow(new Object[]{ds.getFirst().getMaSV(), ds.getFirst().getTenSV(), ds.getFirst().getLop(),
                    ds.getSecond().getMaBT(), ds.getSecond().getTenBT(), ds.getSecond().getTime()});
                }
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "File not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch(Exception ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(new JFrame(), "Error occurs", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnWriteDS){
            try {
                ghiFileDS();
                JOptionPane.showMessageDialog(new JFrame(), "Done", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "IO ex", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnAddDS){
            try{
                String code = JOptionPane.showInputDialog(new JTextField(20), "Nhập mã sinh viên");
                boolean checkSV = true;
                for(Pair<SinhVien, BTL> ds: listDS){
                    if(ds.getFirst().equals(new SinhVien(code))){
                        checkSV = false;
                        break;
                    }
                }
                if(checkSV){
                    checkSV = false;
                    SinhVien sv = new SinhVien(code);
                    for(SinhVien temp: listSV){
                        if(temp.equals(sv)){
                            sv = temp;
                            checkSV = true;
                            break;
                        }
                    }
                    if(checkSV){
                        code = JOptionPane.showInputDialog(new JTextField(20), "Nhập mã BT");
                        int count = 0;
                        for(Pair<SinhVien, BTL> ds: listDS){
                            if(ds.getSecond().equals(new BTL(code))){
                                count++;
                            }
                        }
                        if(count < 3){
                            boolean checkBT = false;
                            BTL bt = new BTL(code);
                            for(BTL temp: listBT){
                                if(temp.equals(bt)){
                                    bt = temp;
                                    checkBT = true;
                                    break;
                                }
                            }
                            if(checkBT){
                                listDS.add(new Pair<SinhVien, BTL>(sv, bt));
                                dtmDS.addRow(new Object[]{sv.getMaSV(), sv.getTenSV(), sv.getLop(),
                                bt.getMaBT(), bt.getTenBT(), bt.getTime()});

                            }else{
                                JOptionPane.showMessageDialog(new JFrame(), "Không tìm thấy đề tài", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        }else{
                            JOptionPane.showMessageDialog(new JFrame(), "Đề tài đã vượt quá số lượng đăng kí", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Không tìm thấy sinh viên", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Sinh Viên đã đăng kí", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(new JFrame(), "Input Error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == btnSort){
            Collections.sort(listDS, new Comparator<Pair<SinhVien, BTL>>(){
                @Override
                public int compare(Pair<SinhVien, BTL> a, Pair<SinhVien, BTL> b){
                    if(a.getSecond().getTime() > b.getSecond().getTime()) return 1;
                    else {
                        if(a.getSecond().getTime() < b.getSecond().getTime()) return - 1;
                        return 0;
                    }
                }
            });
            dtmDS.setRowCount(0);
            for(Pair<SinhVien, BTL> ds: listDS){
                    dtmDS.addRow(new Object[]{ds.getFirst().getMaSV(), ds.getFirst().getTenSV(), ds.getFirst().getLop(),
                    ds.getSecond().getMaBT(), ds.getSecond().getTenBT(), ds.getSecond().getTime()});
            }
            JOptionPane.showMessageDialog(new JFrame(), "Sorted", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public ArrayList<SinhVien> readFileSV() throws FileNotFoundException, IOException{
        ArrayList<SinhVien> rs = new ArrayList<>();
        FileReader fr = new FileReader(new File("sinhvien.txt"));
        BufferedReader bf = new BufferedReader(fr);
        String temp;
        while((temp = bf.readLine()) != null){
            String code = temp;
            String name = bf.readLine();
            String lop = bf.readLine();
            rs.add(new SinhVien(code, name, lop));
        }
        bf.close();
        fr.close();
        return rs;
    }
    
    public void ghiFileSV() throws IOException{
        FileWriter fw = new FileWriter(new File("sinhvien.txt"));
        PrintWriter pw = new PrintWriter(fw);
        for(SinhVien sv: listSV){
            pw.println(sv.getMaSV());
            pw.println(sv.getTenSV());
            pw.println(sv.getLop());
        }
        fw.close();
        pw.close();
    }
    public ArrayList<BTL> readFileBT() throws FileNotFoundException, IOException{
        ArrayList<BTL> rs = new ArrayList<>();
        FileReader fr = new FileReader(new File("btl.txt"));
        BufferedReader bf = new BufferedReader(fr);
        String temp;
        while((temp = bf.readLine()) != null){
            String code = temp;
            String name = bf.readLine();
            int lop = Integer.parseInt(bf.readLine());
            rs.add(new BTL(code, name, lop));
        }
        bf.close();
        fr.close();
        return rs;
    }
    
    public void ghiFileBT() throws IOException{
        FileWriter fw = new FileWriter(new File("btl.txt"));
        PrintWriter pw = new PrintWriter(fw);
        for(BTL bt: listBT){
            pw.println(bt.getMaBT());
            pw.println(bt.getTenBT());
            pw.println(bt.getTime());
        }
        fw.close();
        pw.close();
    }
    public ArrayList<Pair<SinhVien, BTL> > readFileDS() throws FileNotFoundException, IOException{
        ArrayList<Pair<SinhVien, BTL> > rs = new ArrayList<>();
        FileReader fr = new FileReader(new File("ds.txt"));
        BufferedReader bf = new BufferedReader(fr);
        String temp;
        while((temp = bf.readLine()) != null){
            String sv = temp;
            String nameSV = bf.readLine();
            String lop = bf.readLine();
            String bt = bf.readLine();
            String nameBT = bf.readLine();
            int time = Integer.parseInt(bf.readLine());
            
            rs.add(new Pair<SinhVien, BTL>(new SinhVien(sv, nameSV, lop), new BTL(bt, nameBT, time)));
        }
        bf.close();
        fr.close();
        return rs;
    }
    
    public void ghiFileDS() throws IOException{
        FileWriter fw = new FileWriter(new File("ds.txt"));
        PrintWriter pw = new PrintWriter(fw);
        for(Pair<SinhVien, BTL>  ds: listDS){
            pw.println(ds.getFirst().getMaSV());
            pw.println(ds.getFirst().getTenSV());
            pw.println(ds.getFirst().getLop());
            pw.println(ds.getSecond().getMaBT());
            pw.println(ds.getSecond().getTenBT());
            pw.println(ds.getSecond().getTime());
        }
        fw.close();
        pw.close();
    }
}

