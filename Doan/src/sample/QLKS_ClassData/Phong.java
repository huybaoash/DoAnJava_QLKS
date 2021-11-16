package sample.QLKS_ClassData;

public class Phong {
    private int MaP,SoNguoi,Tang;
    private String TenP,TrangThai,TenLP;

    public Phong(int maP, int soNguoi, int tang, String tenP, String trangThai, String tenLP) {
        MaP = maP;
        SoNguoi = soNguoi;
        Tang = tang;
        TenP = tenP;
        TrangThai = trangThai;
        TenLP = tenLP;
    }

    public int getMaP() {
        return MaP;
    }

    public void setMaP(int maP) {
        MaP = maP;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        SoNguoi = soNguoi;
    }

    public int getTang() {
        return Tang;
    }

    public void setTang(int tang) {
        Tang = tang;
    }

    public String getTenP() {
        return TenP;
    }

    public void setTenP(String tenP) {
        TenP = tenP;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenLP() {
        return TenLP;
    }

    public void setTenLP(String tenLP) {
        TenLP = tenLP;
    }


}
