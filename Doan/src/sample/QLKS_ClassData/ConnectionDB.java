package sample.QLKS_ClassData;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

public class ConnectionDB {
    private String url;
    private String username;
    private String password;
    private String driverName;
    private int MaNV_User = 0;

    public Connection conn;
    public Statement stmt;
    public ResultSet rs;


    public ConnectionDB() throws ClassNotFoundException, SQLException {
        driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        url = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433;database=QLKS";
        username = "linh123";
        password = "linh123";


        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url,username,password);
        if (conn == null) System.out.println("Fail connect database");
        stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
        rs = null;
    }

    public void AddNVDB(String hoten, Date ngaysinh, String cmnd,String sdt, BigDecimal luongCB, String trangthai,
                        int maCLV, String maCV, String gioitinh, String taikhoan, String matkhau, String email, Date ngayGN) throws SQLException {
    //([MaNV], [TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap])
        String sql = "INSERT [dbo].[NhanVien] ([TenNV], [Ngaysinh], [CMND], [SDT], [Luongcoban], [TrangThai], [MaCLV], [MaCV], [GioiTinh], [user], [Password], [Email], [NgayGiaNhap]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, hoten);
        stmt.setDate(2, ngaysinh);
        stmt.setString(3, cmnd);
        stmt.setString(4, sdt);
        stmt.setBigDecimal(5, luongCB);
        stmt.setString(6, trangthai);
        stmt.setInt(7, maCLV);
        stmt.setString(8, maCV);
        stmt.setString(9, gioitinh);
        stmt.setString(10, taikhoan);
        stmt.setString(11, matkhau);
        stmt.setString(12, email);
        stmt.setDate(13, ngayGN);

        stmt.execute();
        System.out.println("Thêm thành công");
    }

    public void deleteNVDB(int maNV) throws SQLException {
        String sql = "DELETE FROM [dbo].[NhanVien] WHERE MaNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, maNV);
        stmt.execute();
    }

    public void updateNVDB(int maNV, String hoten, Date ngaysinh, String cmnd,String sdt, BigDecimal luongCB, String trangthai,
                           int maCLV, String maCV, String gioitinh, String email, Date ngayGN) throws SQLException{
        String sql = "UPDATE [dbo].[NhanVien] SET TenNV = ? , Ngaysinh = ? , CMND = ? , SDT = ?, Luongcoban = ? , TrangThai = ? ,"
                + "MaCLV = ? , MaCV = ? , GioiTinh = ? , Email = ? , NgayGiaNhap = ? "
                + "WHERE MaNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, hoten);
        stmt.setDate(2, ngaysinh);
        stmt.setString(3, cmnd);
        stmt.setString(4, sdt);
        stmt.setBigDecimal(5, luongCB);
        stmt.setString(6, trangthai);
        stmt.setInt(7, maCLV);
        stmt.setString(8, maCV);
        stmt.setString(9, gioitinh);
        stmt.setString(10, email);
        stmt.setDate(11, ngayGN);
        stmt.setInt(12,maNV);
        stmt.execute();

        System.out.println("Sửa thành công");
    }

    public void updateNVDB(int maNV,String hoten,Date ngaysinh,String cmnd,String email,String gioitinh) throws SQLException {
        String sql = "UPDATE [dbo].[NhanVien] SET TenNV = ?, Ngaysinh = ?, CMND = ?, Email = ?, GioiTinh =?"
                + " WHERE MaNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1,hoten);
        stmt.setDate(2,ngaysinh);
        stmt.setString(3,cmnd);
        stmt.setString(4,email);
        stmt.setString(5,gioitinh);
        stmt.setInt(6,maNV);
        stmt.execute();
        System.out.println("Sửa thành công");
    }
    public void updateNVDB(NhanVien nv,String pass) throws SQLException {
        String sql = "UPDATE [dbo].[NhanVien] SET Password = ?"
                + " WHERE MaNV = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1,pass);
        stmt.setInt(2,nv.getMaNV());
        stmt.execute();
        System.out.println("Sửa thành công");
    }

    public void updateImageNVDB(NhanVien nv) throws SQLException {
        String sql = "UPDATE [dbo].[NhanVien] SET HinhAnh = ?"
                + " WHERE MaNV = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1,String.valueOf("NV"+nv.getMaNV()));
        stmt.setInt(2,nv.getMaNV());
        stmt.execute();
        System.out.println("Sửa ảnh thành công");
    }



    public void updateKHDB(int maKH,String hoten,Date ngaysinh,String cmnd,String email,String gioitinh,String quoctich,String sdt) throws SQLException {
        String sql = "UPDATE [dbo].[KHACH] SET TenKHACH = ?, NgaySinh = ?, CMND = ?, Email = ?, GioiTinh = ?, QuocTich = ?, SDT = ? "
                + " WHERE MaKHACH = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1,hoten);
        stmt.setDate(2,ngaysinh);
        stmt.setString(3,cmnd);
        stmt.setString(4,email);
        stmt.setString(5,gioitinh);
        stmt.setString(6,quoctich);
        stmt.setString(7,sdt);

        stmt.setInt(8,maKH);
        stmt.execute();
        System.out.println("Sửa thành công");
    }

    public void setdsCLV(List<CaLamViec> listCLV) throws SQLException {
        rs = stmt.executeQuery("SELECT  * FROM CaLamViec");

        while (rs.next()) {
            int maCLV = rs.getInt(1);
            String tenCLV = rs.getString(2);
            Time GioBatDau = rs.getTime(3);
            Time GioKetThuc = rs.getTime(4);
            CaLamViec clv = new CaLamViec(maCLV,tenCLV,GioBatDau,GioKetThuc);

            listCLV.add(clv);
        }
    }

    public void setdsCV(List<ChucVu> listCV) throws SQLException{
        rs = stmt.executeQuery("SELECT  * FROM ChucVu");

        while (rs.next()) {
            String maCV = rs.getString(1);
            String tenCLV = rs.getString(2);
            float hsl = rs.getFloat(3);

            ChucVu cv = new ChucVu(maCV,tenCLV,hsl);

            listCV.add(cv);
        }
    }

    public void setdstenCLV(List<String> listtenCLV) throws SQLException {
        rs = stmt.executeQuery("SELECT  * FROM CaLamViec");

        while (rs.next()) {
            String tenCLV = rs.getString(2);
            Time GioBatDau = rs.getTime(3);
            Time GioKetThuc = rs.getTime(4);

            listtenCLV.add(tenCLV + " (" + GioBatDau.toString() + " -"  + GioKetThuc + ")");
        }
    }

    public void setdstenCV(List<String> listtenCV) throws SQLException {
        rs = stmt.executeQuery("SELECT  * FROM ChucVu");

        while (rs.next()) {
            String tenCV = rs.getString(2);
            listtenCV.add(tenCV);
        }
    }

    public void addKhachDB(String hoten,Date NgaySinh,String SDT,String Email,String CMND,String QuocTich,String GioiTinh) throws SQLException {
        String sql = "INSERT [dbo].[KHACH] ([TenKHACH], [Ngaysinh], [CMND], [SDT], [GioiTinh],[Email], [QuocTich]) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1,hoten);
        stmt.setDate(2,NgaySinh);
        stmt.setString(3,CMND);
        stmt.setString(4,SDT);
        stmt.setString(5,GioiTinh);
        stmt.setString(6,Email);
        stmt.setString(7,QuocTich);

        stmt.execute();
        System.out.println("Đăng ký khách hàng thành công");
    }

    public void addPDPDB(int maPDP,int maP,int maKHACH,int maNV,Date NgayDat,Date NgayTra,BigDecimal TienTra,BigDecimal GiaPhong) throws SQLException {
        String sql = "INSERT [dbo].[PhieuDatPhong] ([MaPhieuDP], [MaPhong], [MaKHACH], [MaNV], [NgayDatPhong],[Traphong], [DonGiaThue],[DonGiaPhong]) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1,maPDP);
        stmt.setInt(2,maP);
        stmt.setInt(3,maKHACH);
        stmt.setInt(4,maNV);
        stmt.setDate(5,NgayDat);
        stmt.setDate(6,NgayTra);
        stmt.setBigDecimal(7,TienTra);
        stmt.setBigDecimal(8,GiaPhong);


        stmt.execute();
        System.out.println("Đặt phòng thành công");
    }

    public void addPDV  (int MaPhieuDV,int MaP,String MaDV,BigDecimal TienDV,int Soluong,Date NgaySD,BigDecimal giaDV) throws SQLException{
        String sql = "INSERT [QLKS].[dbo].[PhieuDV] ([MaPhieuDV], [MaPhong], [MaDV], [TienDV], [Soluong], [NgaySD],[GiaDV]) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1,MaPhieuDV);
        stmt.setInt(2,MaP);
        stmt.setString(3,MaDV);
        stmt.setBigDecimal(4,TienDV);
        stmt.setInt(5,Soluong);
        stmt.setDate(6,NgaySD);
        stmt.setBigDecimal(7,giaDV);
        stmt.execute();
        System.out.println("Đặt dịch vụ thành công");
    }

    public void addHD(int MaPhong,BigDecimal TongTien,Date NgayInHD,int MaNV) throws SQLException {
        String sql = "INSERT [QLKS].[dbo].[HoaDon] ( [MaPhong], [TongTien], [NgayInHD], [MaNV]) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);


        stmt.setInt(1,MaPhong);
        stmt.setBigDecimal(2,TongTien);
        stmt.setDate(3,NgayInHD);
        stmt.setInt(4,MaNV);
        stmt.execute();
        System.out.println("Lưu hóa đơn thành công");
    }

    public void updateHD(int MaPhong,BigDecimal TongTien,Date NgayInHD) throws  SQLException{
        String sql = "UPDATE [dbo].[HoaDon] SET TongTien = ? WHERE MaPhong = ? and NgayInHD =?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setBigDecimal(1,TongTien);
        stmt.setInt(2,MaPhong);
        stmt.setDate(3,NgayInHD);

        stmt.execute();
        System.out.println("Cập nhật hóa đơn thành công");
    }

}
