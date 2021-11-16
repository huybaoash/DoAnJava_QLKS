package sample.QLKS_ClassData;

import  java.sql.Time;
import java.sql.Time;

public class CaLamViec {
    private int maCLV;
    private String tenCLV;
    private Time gioBatDau;
    private Time gioKetThuc;

    public CaLamViec(int maCLV, String tenCLV, Time gioBatDau, Time gioKetThuc) {
        this.maCLV = maCLV;
        this.tenCLV = tenCLV;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public int getMaCLV() {
        return maCLV;
    }

    public void setMaCLV(int maCLV) {
        this.maCLV = maCLV;
    }

    public String getTenCLV() {
        return tenCLV;
    }

    public void setTenCLV(String tenCLV) {
        this.tenCLV = tenCLV;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
