package quanlygargae;

import java.util.*;

public class SinhVien {
    private String maSV;
    private String tenSV;
    private String lop;

    public SinhVien(String maSV) {
        this.maSV = maSV;
    }

    public SinhVien(String maSV, String tenSV, String lop) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.lop = lop;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public String getLop() {
        return lop;
    }
    
    @Override
    public String toString() {
        return maSV + "\t" + tenSV + "\t" + lop;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinhVien other = (SinhVien) obj;
        if (!Objects.equals(this.maSV, other.maSV)) {
            return false;
        }
        return true;
    }
    
    
}

