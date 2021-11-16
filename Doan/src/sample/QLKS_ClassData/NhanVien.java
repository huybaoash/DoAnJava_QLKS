package sample.QLKS_ClassData;

import javafx.scene.image.Image;


import java.sql.Date;

public class NhanVien {
    private int STT;
    private int MaNV;
    private String TenNV;
    private Date Ngaysinh;
    private String GioiTinh;
    private String CMND;
    private String SDT;
    private String Email;

    private String ChucVu;
    private String Luong;
    private Date NgayGiaNhap;
    private String TenCaLamViec;
    private String TrangThai;
    private Image HinhAnh;

    private String User;
    private String Password;

    public NhanVien(int sTT,int maNV, String tenNV, Date ngaysinh, String gioiTinh, String email, String CMND, String SDT,  String luong,String chucVu, Date ngayGiaNhap, String tenCaLamViec, String trangThai,Image hinhAnh) {
        STT = sTT;
        MaNV = maNV;
        TenNV = tenNV;
        Ngaysinh = ngaysinh;
        GioiTinh = gioiTinh;
        Email = email;
        this.CMND = CMND;
        this.SDT = SDT;

        ChucVu = chucVu;
        Luong = luong;
        NgayGiaNhap = ngayGiaNhap;
        TenCaLamViec = tenCaLamViec;
        TrangThai = trangThai;
        HinhAnh = hinhAnh;
    }

    public NhanVien(NhanVien a, String user, String pass){
        STT = a.STT;
        MaNV = a.MaNV;
        TenNV = a.TenNV;
        Ngaysinh = a.Ngaysinh;
        GioiTinh = a.GioiTinh;
        Email = a.Email;
        CMND = a.CMND;
        SDT = a.SDT;

        ChucVu = a.ChucVu;
        Luong = a.Luong;
        NgayGiaNhap = a.NgayGiaNhap;
        TenCaLamViec = a.TenCaLamViec;
        TrangThai = a.TrangThai;
        HinhAnh = a.HinhAnh;
        User = user;
        Password = pass;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        this.User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Image getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(Image hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getSTT() {
        return STT;
    }

    public int getMaNV() {
        return MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public String getEmail() {
        return Email;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public String getLuong() {
        return Luong;
    }

    public Date getNgayGiaNhap() {
        return NgayGiaNhap;
    }

    public String getTenCaLamViec() {
        return TenCaLamViec;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public void setNgaysinh(Date ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public void setLuong(String luong) {
        Luong = luong;
    }

    public void setNgayGiaNhap(Date ngayGiaNhap) {
        NgayGiaNhap = ngayGiaNhap;
    }

    public void setTenCaLamViec(String tenCaLamViec) {
        TenCaLamViec = tenCaLamViec;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }




}
