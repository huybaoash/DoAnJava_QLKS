package sample.QLKS_ClassData;

import javafx.scene.control.Hyperlink;

import java.sql.Date;

public class Khach {
    private int STT;
   private int MaKHACH;
   private String TenKHACH,SDT,Email,CMND,QuocTich,GioiTinh;
   private Date NgaySinh;


    public Khach(int sTT,int maKHACH, String tenKHACH, String SDT, String email, String CMND, String quocTich, String gioiTinh, Date ngaySinh) {
        STT = sTT;
        MaKHACH = maKHACH;
        TenKHACH = tenKHACH;
        this.SDT = SDT;
        Email = email;
        this.CMND = CMND;
        QuocTich = quocTich;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;

    }

    public Khach(String tenKHACH, String SDT, String email, String CMND, String quocTich, String gioiTinh, Date ngaySinh) {
        TenKHACH = tenKHACH;
        this.SDT = SDT;
        Email = email;
        this.CMND = CMND;
        QuocTich = quocTich;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getMaKHACH() {
        return MaKHACH;
    }

    public void setMaKHACH(int maKHACH) {
        MaKHACH = maKHACH;
    }

    public String getTenKHACH() {
        return TenKHACH;
    }

    public void setTenKHACH(String tenKHACH) {
        TenKHACH = tenKHACH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String quocTich) {
        QuocTich = quocTich;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }
}
