package quanlygargae;

import java.io.Serializable;

public class chitietlinhkien implements Serializable {
	int ma;
	String tenlinhkien;
	int soluong;	
	String thoigiangiaohang;
	int trangthai;
	
	public chitietlinhkien() {
		
	}
	public chitietlinhkien(int ma, String tenlinhkien, int soluong, String thoigiangiaohang, int trangthai) {
		super();
		this.ma = ma;
		this.tenlinhkien = tenlinhkien;
		this.soluong = soluong;
		this.thoigiangiaohang = thoigiangiaohang;
		this.trangthai = trangthai;
	}
	
	public int getMa() {
		return ma;
	}
	public void setMa(int ma) {
		this.ma = ma;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public chitietlinhkien(int ma, int soluong) {
		super();
		this.ma = ma;
		this.soluong = soluong;
	}
	public String getTenlinhkien() {
		return tenlinhkien;
	}
	public void setTenlinhkien(String tenlinhkien) {
		this.tenlinhkien = tenlinhkien;
	}
	public String getThoigiangiaohang() {
		return thoigiangiaohang;
	}
	public void setThoigiangiaohang(String thoigiangiaohang) {
		this.thoigiangiaohang = thoigiangiaohang;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
