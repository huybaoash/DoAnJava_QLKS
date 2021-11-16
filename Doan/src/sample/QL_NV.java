package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import sample.QLKS_ClassData.ConnectionDB;
import sample.QLKS_ClassData.NhanVien;
import javafx.stage.Modality;
import java.io.IOException;

import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.event.*;

import java.sql.*;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class QL_NV extends Application {


    void InitializeComponent(){

    }


    @Override
    public void start(Stage stage) throws Exception {
        InitializeComponent();

        ShowEmployee();
        ShowCombobox();
    }

    @FXML
    void KeyNumber(KeyEvent event) {
        txtCMND_NV.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtCMND_NV.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txtSalary_NV.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtSalary_NV.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txtPhoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtPhoneNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }




    @FXML
    private TextField txtID_NV;

    @FXML
    private TextField txtName_NV;

    @FXML
    private DatePicker dtpWasBorn_NV;

    @FXML
    private TextField txtCMND_NV;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSalary_NV;

    @FXML
    private RadioButton rbWorking;

    @FXML
    private RadioButton rbStopped;

    @FXML
    private TableView<NhanVien> tblNV;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<String> cbxCV = new ComboBox<>();

    @FXML
    private ComboBox<String> cbxCLV = new ComboBox<>();



    @FXML
    private TableColumn<NhanVien, Integer> Index_table;

    @FXML
    private TableColumn<NhanVien, Integer> IDtable;

    @FXML
    private TableColumn<NhanVien, String> NameNVtable;

    @FXML
    private TableColumn<NhanVien, Date> WasBorn_table;

    @FXML
    private TableColumn<NhanVien, String> GioiTinh_table;

    @FXML
    private TableColumn<NhanVien, String> CMND_table;

    @FXML
    private TableColumn<NhanVien, String> SDT_table;

    @FXML
    private TableColumn<NhanVien, String> Email_table;

    @FXML
    private TableColumn<NhanVien, String> Role_table;

    @FXML
    private TableColumn<NhanVien, String> Salary_table;

    @FXML
    private TableColumn<NhanVien, Date> Join_table;

    @FXML
    private TableColumn<NhanVien, String> TenCLV_table;

    @FXML
    private TableColumn<NhanVien, String> Status_table;

    @FXML
    private RadioButton rbNam;

    @FXML
    private RadioButton rbNu;

    @FXML
    private ImageView imgNV;



    private ObservableList<NhanVien> nhanViensList = null;

    private ConnectionDB db;

    private static Stage  addDialogStage;

    private static String user;
    private static String password;

    private static Stage primaryStage;
    public void setpimaryStage(Stage pS){
        primaryStage = pS;
    }

    private static int flag = 0;


    void XacNhanThemUser() throws IOException, SQLException, ClassNotFoundException {
        addDialogStage = new Stage();
        addDialogStage.initOwner(primaryStage);
        addDialogStage.initModality(Modality.WINDOW_MODAL);



        FXMLLoader addUser = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        Parent addsample2 = addUser.load();

        addDialogStage.setTitle("Thêm người dùng");
        Scene scene = new Scene(addsample2);

        AddUser controller = addUser.getController();
        controller.setPrimaryStage(addDialogStage);
        if (flag == 1) controller.init(user,password);
        //scene.setRoot(addsample2);

        addDialogStage.initStyle(StageStyle.UNDECORATED);

        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

        if (addDialogStage.isShowing()== false){
            int luachon = 0;
            if (controller.getFlag(luachon) == 0 ) return;
            user = controller.getUser();
            password = controller.getPass();
            System.out.println(user + "\n" + password);

        }
        else {
            System.out.println("Add User form doesn't show up");
            return;
        }

    }


    @FXML
    void PressAdd(ActionEvent event) throws ClassNotFoundException, IOException,SQLException {

        if (txtCMND_NV.getText().equals("") || txtName_NV.getText().equals("") || txtSalary_NV.getText().equals("")
        || txtPhoneNumber.getText().equals("") || txtEmail.getText().equals("") || dtpWasBorn_NV.getEditor().getText().equals("")
        || (rbNam.isSelected() == false && rbNu.isSelected() == false)
        || (rbWorking.isSelected() == false && rbStopped.isSelected() == false )
            )
        {
            JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin !");return;
        }

        if (rbNu.isSelected()== false && rbNam.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null,"Phải chọn giới tính.");
            return;
        }


        for (int i=0;i<nhanViensList.size();i++){
            if (txtCMND_NV.getText().equals(nhanViensList.get(i).getCMND())) {
                JOptionPane.showMessageDialog(null,"Không thể thêm cùng một người.");
                return;
            }
        }

        XacNhanThemUser();



        String hoten = txtName_NV.getText();
        Date ngaysinh = java.sql.Date.valueOf(dtpWasBorn_NV.getValue());
        String cmnd = txtCMND_NV.getText();
        String sdt = txtPhoneNumber.getText();

        String maCV = "";
        float hsl = 1;
        db.rs = db.stmt.executeQuery("SELECT  * FROM ChucVu");
        while (db.rs.next()){
            if (cbxCV.getValue().equals(db.rs.getString(2))) {
                maCV = db.rs.getString(1);
                hsl = db.rs.getFloat(3);
                break;
            }
        }
        BigDecimal luongCB = new BigDecimal(txtSalary_NV.getText());

        String trangthai = "";
        if (rbStopped.isSelected()) trangthai = rbStopped.getText();
        else trangthai = rbWorking.getText();

        String gioitinh ="";
        if (rbNu.isSelected()) gioitinh = rbNu.getText();
        else gioitinh = rbNam.getText();

        int maCLV = 0;
        db.rs = db.stmt.executeQuery("SELECT  * FROM CaLamViec");
        while (db.rs.next()){
            String thongtinCLV = db.rs.getString(2) + " (" + db.rs.getTime(3).toString() + " -"  + db.rs.getTime(4).toString() + ")";
            if (cbxCLV.getValue().equals(thongtinCLV)) {
                maCLV = db.rs.getInt(1);
                break;
            }
        }

        String taikhoan = user;
        String matkhau = password;
        String email = txtEmail.getText();


        Date ngayGiaNhap = new java.sql.Date(System.currentTimeMillis());

        System.out.println(hoten+ngaysinh.toString()+cmnd+sdt+luongCB.toPlainString()+trangthai+maCLV+maCV+gioitinh+taikhoan+matkhau+email+ngayGiaNhap.toString());

        db.AddNVDB(hoten,ngaysinh,cmnd,sdt,luongCB,trangthai,maCLV,maCV,gioitinh,taikhoan,matkhau,email,ngayGiaNhap);
        JOptionPane.showMessageDialog(null,"Thêm thành công !");
        ShowEmployee();
    }

    @FXML
    void PressDelete(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (tblNV.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,"Hãy chọn nhân viên cần xóa !");return;
        }
        int maNV = tblNV.getSelectionModel().getSelectedItem().getMaNV();

        int result = JOptionPane.showConfirmDialog(null,"Bạn muốn xóa nhân viên này  ?","Xóa nhân viên",JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            db.deleteNVDB(maNV);
        }
        ShowEmployee();
    }

    @FXML
    void PressEdit(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        flag = 1;
        if (tblNV.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,"Hãy chọn nhân viên cần chỉnh sửa !");return;
        }

        if (rbNu.isSelected()== false && rbNam.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null,"Phải chọn giới tính.");
            return;
        }

        int maNV = tblNV.getSelectionModel().getSelectedItem().getMaNV();
        String hoten = txtName_NV.getText();
        Date ngaysinh = java.sql.Date.valueOf(dtpWasBorn_NV.getValue());
        String cmnd = txtCMND_NV.getText();
        String sdt = txtPhoneNumber.getText();

        String maCV = "";
        float hsl = 1;
        db.rs = db.stmt.executeQuery("SELECT  * FROM ChucVu");
        while (db.rs.next()){
            if (cbxCV.getValue().equals(db.rs.getString(2))) {
                maCV = db.rs.getString(1);
                hsl = db.rs.getFloat(3);
                break;
            }
        }
        BigDecimal luongCB = new BigDecimal(txtSalary_NV.getText());

        String trangthai = "";
        if (rbStopped.isSelected()) trangthai = rbStopped.getText();
        else trangthai = rbWorking.getText();

        String gioitinh ="";
        if (rbNu.isSelected()) gioitinh = rbNu.getText();
        else gioitinh = rbNam.getText();

        int maCLV = 0;
        db.rs = db.stmt.executeQuery("SELECT  * FROM CaLamViec");
        while (db.rs.next()){
            String thongtinCLV = db.rs.getString(2) + " (" + db.rs.getTime(3).toString() + " -"  + db.rs.getTime(4).toString() + ")";
            if (cbxCLV.getValue().equals(thongtinCLV)) {
                maCLV = db.rs.getInt(1);
                break;
            }
        }

        String email = txtEmail.getText();

        Date ngayGiaNhap = tblNV.getSelectionModel().getSelectedItem().getNgayGiaNhap();

        db.updateNVDB(maNV,hoten,ngaysinh,cmnd,sdt,luongCB,trangthai,maCLV,maCV,gioitinh,email,ngayGiaNhap);
        JOptionPane.showMessageDialog(null,"Sửa thành công !");
        ShowEmployee();

    }



    @FXML
    void SearchNV(KeyEvent event) throws ClassNotFoundException {

        if (event.getSource() == txtSearch)
        {
            String tukhoa = txtSearch.getText().toLowerCase();
            tblNV.setItems(null);
            List<NhanVien> NV_temp = new ArrayList<>();


            if (tukhoa.equals("")) ShowEmployee();
            else{
                for (int i=0;i<nhanViensList.size();i++){
                    if (String.valueOf(nhanViensList.get(i).getMaNV()).toLowerCase().contains(tukhoa) ||
                            nhanViensList.get(i).getTenNV().toLowerCase().contains(tukhoa)
                    ) NV_temp.add(nhanViensList.get(i));
                }

                ObservableList<NhanVien> nhanViensList_Search = FXCollections.observableArrayList(NV_temp);
                ConfigTable();
                tblNV.setItems(nhanViensList_Search);
            }
        }


    }

    @FXML
    void PressClear(ActionEvent event) {
        ClearNV();
    }

    void ClearNV(){

        txtPhoneNumber.setText("");
        txtSalary_NV.setText("");
        txtName_NV.setText("");
        txtCMND_NV.setText("");
        txtEmail.setText("");
        txtID_NV.setText("");
        imgNV.setImage(null);
    }

    public void ShowEmployee() throws ClassNotFoundException {

        dtpWasBorn_NV.setValue(LocalDate.now().minusYears(21));

        try {
            db = new ConnectionDB();

            if (db == null) System.out.println("Unconnected");
            else System.out.println("Connected");

            String sql = String.format("SELECT * FROM [QLKS].[dbo].[Info_NV]");

            PreparedStatement statement = db.conn.prepareStatement(sql);
            db.rs = statement.executeQuery();

            List <NhanVien> NVList = new ArrayList<NhanVien>();


            int STT = 1;
            while (db.rs.next()) {

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




                NhanVien nv_temp = new NhanVien(STT,MaNV,TenNV,Ngaysinh,GioiTinh,Email,CMND,SDT,Luong,ChucVu,NgayGiaNhap,TenCaLamViec,TrangThai,HinhAnh);
                NhanVien nv = new NhanVien(nv_temp,user,password);

                NVList.add(nv);


                STT ++;

            }

            nhanViensList = FXCollections.observableArrayList(NVList);
            ConfigTable();
            tblNV.setItems(nhanViensList);

            //

        } catch (SQLException throwables) {

        }
    }

    void ConfigTable(){
        Index_table.setCellValueFactory(new PropertyValueFactory<NhanVien,Integer>("STT"));
        IDtable.setCellValueFactory(new PropertyValueFactory<NhanVien,Integer>("MaNV"));
        NameNVtable.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("TenNV"));
        WasBorn_table.setCellValueFactory(new PropertyValueFactory<NhanVien,Date>("Ngaysinh"));
        GioiTinh_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("GioiTinh"));
        Email_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("Email"));
        CMND_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("CMND"));
        SDT_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("SDT"));
        Salary_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("Luong"));
        Role_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("ChucVu"));
        Join_table.setCellValueFactory(new PropertyValueFactory<NhanVien,Date>("NgayGiaNhap"));
        TenCLV_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("TenCaLamViec"));
        Status_table.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("TrangThai"));
    }

    public void ShowCombobox() throws SQLException {
        List<String> listtenCLV = new ArrayList<>(); db.setdstenCLV(listtenCLV);
        ObservableList<String> CLVListten = FXCollections.observableArrayList(listtenCLV);
        cbxCLV.setItems(CLVListten);
        cbxCLV.getSelectionModel().select(0);

        List<String> listtenCV = new ArrayList<>(); db.setdstenCV(listtenCV);
        ObservableList<String> CVListten = FXCollections.observableArrayList(listtenCV);
        cbxCV.setItems(CVListten);
        cbxCV.getSelectionModel().select(0);

    }

    @FXML
    void PressNam(ActionEvent event) {
        if (rbNam.isSelected()) rbNam.setSelected(true);
        rbNu.setSelected(false);
    }

    @FXML
    void PressNu(ActionEvent event) {
        if(rbNu.isSelected()) rbNu.setSelected(true);
        rbNam.setSelected(false);
    }

    @FXML
    void PressStoped(ActionEvent event) {
        if (rbStopped.isSelected()) rbStopped.setSelected(true);
        rbWorking.setSelected(false);
    }

    @FXML
    void PressWorking(ActionEvent event) {
        if (rbWorking.isSelected()) rbWorking.setSelected(true);
        rbStopped.setSelected(false);
    }

    @FXML
    void ShowInfoBacktoText(MouseEvent event) throws SQLException {
        int maNV = tblNV.getSelectionModel().getSelectedItem().getMaNV();
        String ID_NV = String.valueOf(maNV);

        txtID_NV.setText(ID_NV);
        txtName_NV.setText(tblNV.getSelectionModel().getSelectedItem().getTenNV());
        txtEmail.setText(tblNV.getSelectionModel().getSelectedItem().getEmail());
        txtCMND_NV.setText(tblNV.getSelectionModel().getSelectedItem().getCMND());

        txtPhoneNumber.setText(tblNV.getSelectionModel().getSelectedItem().getSDT());
        cbxCV.setValue(tblNV.getSelectionModel().getSelectedItem().getChucVu());


        Float luong = new Float(tblNV.getSelectionModel().getSelectedItem().getLuong());

        String detailNameCLV ="";
        db.rs = db.stmt.executeQuery("SELECT * FROM CaLamViec");
        while (db.rs.next()){
            if (tblNV.getSelectionModel().getSelectedItem().getTenCaLamViec().equals(db.rs.getString(2)))
            {
                detailNameCLV = tblNV.getSelectionModel().getSelectedItem().getTenCaLamViec()
                +" (" + db.rs.getTime(3).toString() + " -" + db.rs.getTime(4).toString() + ")";
                break;
            }
        }
        cbxCLV.setValue(detailNameCLV);


        db.rs = db.stmt.executeQuery("SELECT * FROM ChucVu");
        float hsl = 1;

        while (db.rs.next()){
            if (tblNV.getSelectionModel().getSelectedItem().getChucVu().equals(db.rs.getString(2)))
                hsl = db.rs.getFloat(3);
        }
        txtSalary_NV.setText(String.valueOf(luong/hsl));

        if (tblNV.getSelectionModel().getSelectedItem().getGioiTinh().equals("Nam")) {
            rbNam.setSelected(true);rbNu.setSelected(false);
        }
        else {
            rbNu.setSelected(true);rbNam.setSelected(false);
        }

        if (tblNV.getSelectionModel().getSelectedItem().getTrangThai().equals("Đang hoạt động")){
            rbWorking.setSelected(true);rbStopped.setSelected(false);
        }
        else {
            rbStopped.setSelected(true);rbWorking.setSelected(false);
        }

        dtpWasBorn_NV.setValue(tblNV.getSelectionModel().getSelectedItem().getNgaysinh().toLocalDate());

        imgNV.setImage(tblNV.getSelectionModel().getSelectedItem().getHinhAnh());

        if (tblNV.getSelectionModel().getSelectedItem().getHinhAnh().getUrl() == null)
            System.out.println("Khoang load duoc duong dan cua hinh");
        else System.out.println(tblNV.getSelectionModel().getSelectedItem().getHinhAnh().getUrl());


    }

}
