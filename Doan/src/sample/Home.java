package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.QLKS_ClassData.*;

import javax.swing.*;
import javax.xml.stream.XMLReporter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Home {



    public void init() throws SQLException, ClassNotFoundException,IOException {

        db = new ConnectionDB();

        menu = new FXMLLoader(getClass().getResource("MenuTheme.fxml"));
        QLNV = new FXMLLoader(getClass().getResource("QL_NV.fxml"));
        userInfo = new FXMLLoader(getClass().getResource("UserInfo.fxml"));
        room = new FXMLLoader(getClass().getResource("DatPhong.fxml"));
        thongke = new FXMLLoader(getClass().getResource("ThongKe.fxml"));

        QLKH = new FXMLLoader(getClass().getResource("QL_KH.fxml"));

        menuDesign = menu.load();
        QLNV_Design = QLNV.load();
        userInfo_Design = userInfo.load();
        room_Design = room.load();
        thongke_Design = thongke.load();

        QLKH_Design = QLKH.load();

        if (nguoidung == null) getNhanVien_user();
        {
            if (nguoidung.getMaNV() != MaNV_User){
                nguoidung = null;
                nguoidung = getNhanVien_user();
            }
        }

        if (nguoidung.getChucVu().equals("Nhân viên")){
            btnEmployee.setVisible(false);
            btnQuest.setVisible(false);
            btnRP.setVisible(false);

            btnInfo.setLayoutX(btnEmployee.getLayoutX());
            btnInfo.setLayoutY(btnEmployee.getLayoutY());

            btnSignOut.setLayoutX(btnQuest.getLayoutX());
            btnSignOut.setLayoutY(btnQuest.getLayoutY());
            return;
        }

        if (nguoidung.getChucVu().equals("Bảo vệ")){
            btnRoom.setVisible(false);
            btnEmployee.setVisible(false);
            btnQuest.setVisible(false);
            btnRP.setVisible(false);

            btnInfo.setLayoutX(btnRoom.getLayoutX());
            btnInfo.setLayoutY(btnRoom.getLayoutY());

            btnSignOut.setLayoutX(btnEmployee.getLayoutX());
            btnSignOut.setLayoutY(btnEmployee.getLayoutY());

            return;

        }

        if (nguoidung.getChucVu().equals("Quản lý")){
            btnRoom.setVisible(false);
            btnRP.setVisible(false);

            btnSignOut.setLayoutX(btnRP.getLayoutX());
            btnSignOut.setLayoutY(btnRP.getLayoutY());

            btnInfo.setLayoutX(btnQuest.getLayoutX());
            btnInfo.setLayoutY(btnQuest.getLayoutY());

            btnQuest.setLayoutX(btnEmployee.getLayoutX());
            btnQuest.setLayoutY(btnEmployee.getLayoutY());

            btnEmployee.setLayoutX(btnRoom.getLayoutX());
            btnEmployee.setLayoutY(btnRoom.getLayoutY());

            return;
        }

        if (nguoidung.getChucVu().equals("Kế toán")){
            btnEmployee.setVisible(false);
            btnQuest.setVisible(false);
            btnRoom.setVisible(false);

            btnRP.setLayoutX(btnRoom.getLayoutX());
            btnRP.setLayoutY(btnRoom.getLayoutY());

            btnInfo.setLayoutX(btnEmployee.getLayoutX());
            btnInfo.setLayoutY(btnEmployee.getLayoutY());

            btnSignOut.setLayoutX(btnQuest.getLayoutX());
            btnSignOut.setLayoutY(btnQuest.getLayoutY());
            return;
        }

//admin là thằng sa là nv

    }

    public NhanVien getNV_User_afterchangedIMG(){
        return nguoidung;
    }

    public NhanVien getNhanVien_user()throws SQLException {
        String sql = "SELECT * FROM [QLKS].[dbo].[Info_NV]";

        PreparedStatement statement = db.conn.prepareStatement(sql);
        db.rs = statement.executeQuery();

        NhanVien nv = null;

        while (db.rs.next()){

            if (MaNV_User == db.rs.getInt(1)){
                int MaNV = db.rs.getInt(1);

                String TenNV = db.rs.getString(2);

                Date Ngaysinh =  db.rs.getDate(3) ;

                String GioiTinh = db.rs.getString(4);

                String Email = db.rs.getString(5);

                String CMND = db.rs.getString(6);

                String SDT = db.rs.getString(7);

                BigDecimal luongSQL = db.rs.getBigDecimal(8);
                String Luong = luongSQL.toPlainString();


                String ChucVu = db.rs.getString(9);

                Date NgayGiaNhap = db.rs.getDate(10);

                String TenCaLamViec = db.rs.getString(11);

                String TrangThai = db.rs.getString(12);

                String idHinhAnh = db.rs.getString(13);

                Image HinhAnh = null;
                if (idHinhAnh == null) {
                    HinhAnh = null;
                }
                else HinhAnh = new Image("sample/PictureManage/" + idHinhAnh + ".jpg");

                System.out.println("sample/PictureManage/" + idHinhAnh + ".jpg");

                nv = new NhanVien(5,MaNV,TenNV,Ngaysinh,GioiTinh,Email,CMND,SDT,Luong,ChucVu,NgayGiaNhap,TenCaLamViec,TrangThai,HinhAnh);
                nguoidung = new NhanVien(nv,user,pass);

            }
        }

        return nguoidung;
    }

    FXMLLoader QLNV,userInfo,menu,room,thongke,dv,QLKH;
    private static Stage addDialogStage;
    Parent menuDesign,QLNV_Design,userInfo_Design,room_Design,thongke_Design,dv_Design,QLKH_Design;
    private static Stage primaryStage;
    private static NhanVien nguoidung;


    private String user ="";
    private String pass ="";private int MaNV_User=0;



    public void setMaNV_User(int MaNV_user) {
        this.MaNV_User = MaNV_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    void setNguoidung(NhanVien nv){
        nguoidung = nv;
    }

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }


    private ConnectionDB db;

    @FXML
    private AnchorPane anpTab;


    @FXML
    private Label btnManage;

    @FXML
    private Button btnSignOut;

    @FXML
    private AnchorPane anpHome;


    @FXML
    private Button btnInfo;

    @FXML
    private Button btnRP;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnRoom;

    @FXML
    private Button btnQuest;

    @FXML
    void PressManage(ActionEvent event) throws Exception {
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        anpTab.getChildren().setAll(QLNV_Design);

        AnchorPane.setBottomAnchor(QLNV.getRoot(),0.0);
        AnchorPane.setLeftAnchor(QLNV.getRoot(), 0.0);
        AnchorPane.setRightAnchor(QLNV.getRoot(), 0.0);
        AnchorPane.setTopAnchor(QLNV.getRoot(), 0.0);



        QL_NV controller = QLNV.getController();
        controller.ShowEmployee();controller.ShowCombobox();
        controller.setpimaryStage(addDialogStage);

        UserInfo controllerUSIF = userInfo.getController();
        if (controllerUSIF.getFlag() == 5) {
            db = new ConnectionDB();
            controller.ShowEmployee();controller.ShowCombobox();
            controller.setpimaryStage(addDialogStage);
        }
    }


    @FXML
    public void PressSignOut(javafx.event.ActionEvent event) throws Exception {
        int result = JOptionPane.showConfirmDialog(null,"Bạn muốn đăng xuất ?","Đăng xuất",JOptionPane.YES_NO_OPTION);

        if (result == 0) {

            UserInfo controller = userInfo.getController();

            if (controller.getFlag() == 1 ) nguoidung = controller.getNguoidung();

            primaryStage.close();
        }

        else return;
    }

    @FXML
    void PressInfo(ActionEvent event) throws SQLException, ClassNotFoundException {

        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        anpTab.getChildren().setAll(userInfo_Design);

        AnchorPane.setBottomAnchor(userInfo.getRoot(),0.0);
        AnchorPane.setLeftAnchor(userInfo.getRoot(), 0.0);
        AnchorPane.setRightAnchor(userInfo.getRoot(), 0.0);
        AnchorPane.setTopAnchor(userInfo.getRoot(), 0.0);

        UserInfo controller = userInfo.getController();
        controller.setNguoidung(nguoidung);
        controller.setpimaryStage(primaryStage);
        controller.init();



    }

    @FXML
    void PressMenu(ActionEvent event){
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        anpTab.getChildren().setAll(menuDesign);


        AnchorPane.setBottomAnchor(menu.getRoot(),0.0);
        AnchorPane.setLeftAnchor(menu.getRoot(), 0.0);
        AnchorPane.setRightAnchor(menu.getRoot(), 0.0);
        AnchorPane.setTopAnchor(menu.getRoot(), 0.0);
    }
    //

    private ObservableList<PhieuDatPhong> phieuDatPhongsList = null;

    void setDSPDP_list() throws SQLException {
        String sql = String.format("SELECT * FROM [QLKS].[dbo].[View_PDP]");

        PreparedStatement statement = db.conn.prepareStatement(sql);
        db.rs = statement.executeQuery();

        List <PhieuDatPhong> PDPList = new ArrayList<PhieuDatPhong>();

        while (db.rs.next()) {
                PhieuDatPhong pdp = new PhieuDatPhong(db.rs.getInt("Mã PDP"),db.rs.getInt("Mã Phòng"),
                        db.rs.getString("Tên Phòng"),db.rs.getString("Tên LP"),
                        db.rs.getString("Khách"),db.rs.getDate("Ngày đặt"),
                        db.rs.getDate("Ngày trả"),db.rs.getBigDecimal("Giá Phòng").toPlainString(),
                        db.rs.getBigDecimal("Tiền trả").toPlainString());
                PDPList.add(pdp);
        }
        phieuDatPhongsList = FXCollections.observableArrayList(PDPList);

    }

    @FXML
    void PressRoom(ActionEvent event) throws SQLException {
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        setDSPDP_list();

        anpTab.getChildren().setAll(room_Design);
        AnchorPane.setBottomAnchor(room.getRoot(),0.0);
        AnchorPane.setLeftAnchor(room.getRoot(), 0.0);
        AnchorPane.setRightAnchor(room.getRoot(), 0.0);
        AnchorPane.setTopAnchor(room.getRoot(), 0.0);

        DatPhong controller = room.getController();
        controller.setAnpTabHome(anpTab);
        controller.setpimaryStage(primaryStage);controller.setNvphutrach(nguoidung);
        controller.setPhieuDatPhongsList(phieuDatPhongsList);
        controller.Init();
    }



    @FXML
    void PressServices(ActionEvent event) {
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        anpTab.getChildren().setAll(dv_Design);


        AnchorPane.setBottomAnchor(dv.getRoot(),0.0);
        AnchorPane.setLeftAnchor(dv.getRoot(), 0.0);
        AnchorPane.setRightAnchor(dv.getRoot(), 0.0);
        AnchorPane.setTopAnchor(dv.getRoot(), 0.0);

    }

    @FXML
    void PressThongKe(ActionEvent event) {
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();

        anpTab.getChildren().setAll(thongke_Design);

        AnchorPane.setBottomAnchor(thongke.getRoot(),0.0);
        AnchorPane.setLeftAnchor(thongke.getRoot(), 0.0);
        AnchorPane.setRightAnchor(thongke.getRoot(), 0.0);
        AnchorPane.setTopAnchor(thongke.getRoot(), 0.0);

        ThongKe controller = thongke.getController();
        controller.setpimaryStage(addDialogStage);
        controller.Init();
    }

    @FXML
    void PressKhachHang(ActionEvent event) {
        addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        anpTab.getChildren().setAll(QLKH_Design);

        AnchorPane.setBottomAnchor(QLKH.getRoot(),0.0);
        AnchorPane.setLeftAnchor(QLKH.getRoot(), 0.0);
        AnchorPane.setRightAnchor(QLKH.getRoot(), 0.0);
        AnchorPane.setTopAnchor(QLKH.getRoot(), 0.0);


        QL_KH controller = QLKH.getController();
        controller.ShowQuest();
        controller.setpimaryStage(addDialogStage);
    }

}
