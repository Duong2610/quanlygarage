package quanlygargae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LinhkienDAO {
    private static Connection con;

    public LinhkienDAO(){
        if(con == null){
            String dbUrl = "jdbc:mysql://localhost:3306/quanlygarage";
            String dbClass = "com.mysql.cj.jdbc.Driver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection (dbUrl, "root", "1234");
                if(con!=null) {
                	System.out.println("Ket noi thanh cong");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<chitietlinhkien> searchLinhkien(String key){
        ArrayList<chitietlinhkien> result = new ArrayList<chitietlinhkien>();
        String sql = "select  chitietlinhkien.ma, chitietlinhkien.soluong, linhkien.ten\r\n" + 
        		"from chitietlinhkien\r\n" + 
        		"inner join linhkien on chitietlinhkien.malinhkien = linhkien.ma \r\n" + 
        		"and chitietlinhkien.trangthai=1 \r\n" + 
        		"and chitietlinhkien.mahoadon LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               chitietlinhkien chitiet = new chitietlinhkien();
               chitiet.setMa(rs.getInt("ma"));
               chitiet.setTenlinhkien(rs.getString("ten"));
               chitiet.setSoluong(rs.getInt("soluong"));               
               result.add(chitiet);
            }
            }catch(Exception e) {
            	e.printStackTrace();
            }
        for(int i=0;i<result.size();i++) {
        	System.out.println(result.get(i));
        }
        return result;
    }
    public ArrayList<chitietlinhkien> searchLinhkiendagiao(String key){
        ArrayList<chitietlinhkien> result = new ArrayList<chitietlinhkien>();
        String sql = "select  chitietlinhkien.ma, chitietlinhkien.soluong, linhkien.ten, chitietlinhkien.thoigiangiaohang\r\n" + 
        		"from chitietlinhkien\r\n" + 
        		"inner join linhkien on chitietlinhkien.malinhkien = linhkien.ma \r\n" + 
        		"and chitietlinhkien.trangthai=0 \r\n" + 
        		"and chitietlinhkien.mahoadon LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               chitietlinhkien chitiet = new chitietlinhkien();
               chitiet.setMa(rs.getInt("ma"));
               chitiet.setTenlinhkien(rs.getString("ten"));
               chitiet.setSoluong(rs.getInt("soluong"));  
               chitiet.setThoigiangiaohang(rs.getString("thoigiangiaohang"));
               result.add(chitiet);
            }
            }catch(Exception e) {
            	e.printStackTrace();
            }
        for(int i=0;i<result.size();i++) {
        	System.out.println(result.get(i));
        }
        return result;
    }

	public void giaolinhkien(chitietlinhkien chitiet) {
		System.out.println(chitiet.getThoigiangiaohang());
		String sql = "UPDATE chitietlinhkien SET trangthai=0, thoigiangiaohang=? WHERE ma=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setString(1,chitiet.getThoigiangiaohang());
            ps.setInt(2, chitiet.getMa());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}
}
