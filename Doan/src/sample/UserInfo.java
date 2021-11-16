package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import sample.QLKS_ClassData.ConnectionDB;
import sample.QLKS_ClassData.NhanVien;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.swing.*;
import javax.xml.transform.Source;
import javax.xml.transform.SourceLocator;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;

public class UserInfo {
    private ConnectionDB db;

    private static NhanVien nguoidung;

    void setNguoidung(NhanVien user) {
        nguoidung = user;
    }
    public NhanVien getNguoidung() {
        return nguoidung;
    }

    public void init() throws SQLException, ClassNotFoundException {

        db = new ConnectionDB();
        imgProfile.setImage( nguoidung.getHinhAnh());

        txtUser.setText(nguoidung.getUser());
        txtPass.setText(nguoidung.getPassword());
        txtEmail.setText(nguoidung.getEmail());
        txtCLV_User.setText(nguoidung.getTenCaLamViec());
        txtCMND.setText(nguoidung.getCMND());
        txtDayJoin_User.setText(nguoidung.getNgayGiaNhap().toString());
        txtFullName_user.setText(nguoidung.getTenNV());
        txtID_User.setText(String.valueOf(nguoidung.getMaNV()));
        txtRole_User.setText(nguoidung.getChucVu());
        dtpWasBorn.setValue(nguoidung.getNgaysinh().toLocalDate());
        txtSDT.setText(nguoidung.getSDT());
        txtSalary_User.setText(nguoidung.getLuong());

        if (nguoidung.getGioiTinh().equals(rbNam.getText())){

                rbNam.setSelected(true);
                rbNu.setSelected(false);
            }
        else {
                rbNu.setSelected(true);rbNam.setSelected(false);
            }


    }



    public void setDb(ConnectionDB db) {
        this.db = db;
    }

    @FXML
    private AnchorPane anpTab;

    @FXML
    private TextField txtFullName_user;

    @FXML
    private TextField txtSDT;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCMND;


    @FXML
    private TextField txtRole_User;

    @FXML
    private TextField txtSalary_User;

    @FXML
    private TextField txtCLV_User;

    @FXML
    private TextField txtDayJoin_User;

    @FXML
    private TextField txtID_User;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private DatePicker dtpWasBorn;

    @FXML
    private Button btnChangeInfo;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChangePass;

    @FXML
    private Button btnCancelPass;

    @FXML
    private Button btnChangeImage;

    @FXML
    private RadioButton rbNam;

    @FXML
    private RadioButton rbNu;

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
    private ImageView imgProfile;

    private static Stage addDialogStage;

    private static String user;
    private static String password;

    private static Stage primaryStage;

    private static int sukienDoiHinhAnh = 0;

    public void setpimaryStage(Stage pS) {
        primaryStage = pS;
    }

    private int flag = 0;

    public int getFlag() {
        return flag;
    }

    void ConfigTextBox() throws IOException, SQLException {

        if (rbNu.isSelected()== false && rbNam.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null,"Phải chọn giới tính.");
            return;
        }

        if (btnChangeInfo.getText().equals("Lưu")) {
            txtEmail.setDisable(true);
            txtCMND.setDisable(true);
            txtFullName_user.setDisable(true);
            dtpWasBorn.setDisable(true);
            txtSDT.setDisable(true);


           nguoidung.setNgaysinh(java.sql.Date.valueOf(dtpWasBorn.getValue()));
           nguoidung.setSDT(txtSDT.getText());
           nguoidung.setTenNV(txtFullName_user.getText());
           nguoidung.setEmail(txtEmail.getText());
           nguoidung.setCMND(txtCMND.getText());

            String gioitinh ="";
            if (rbNu.isSelected()) gioitinh = rbNu.getText();
            else gioitinh = rbNam.getText();
            nguoidung.setGioiTinh(gioitinh);

            db.updateNVDB(nguoidung.getMaNV(),nguoidung.getTenNV(),
                    nguoidung.getNgaysinh(),nguoidung.getCMND(),nguoidung.getEmail(),nguoidung.getGioiTinh());

            rbNam.setDisable(true);
            rbNu.setDisable(true);


            btnChangeInfo.setText("Sửa thông tin");
            btnCancel.setVisible(false);
            return;
        }
    }

    public void pressChange(ActionEvent event) throws IOException, SQLException {

        if (btnChangeInfo.getText().equals("Sửa thông tin")) {
            rbNam.setDisable(false);
            rbNu.setDisable(false);
            txtEmail.setDisable(false);
            txtCMND.setDisable(false);
            txtFullName_user.setDisable(false);
            dtpWasBorn.setDisable(false);
            txtSDT.setDisable(false);
            rbNam.setDisable(false);
            rbNu.setDisable(false);

            btnChangeInfo.setText("Lưu");
            btnCancel.setVisible(true);
            return;
        }

        ConfigTextBox();


    }


    @FXML
    void KeyNumber(KeyEvent event) {
        txtSDT.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtSDT.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txtCMND.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtCMND.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



    }

    public void pressCancel(ActionEvent event) throws IOException, SQLException {
        ConfigTextBox();
    }


    @FXML
    void pressChangePass(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        XacNhanDoiPass(nguoidung.getUser(), nguoidung.getPassword());
        nguoidung.setPassword(txtPass.getText());
        System.out.println(nguoidung.getMaNV()+nguoidung.getPassword());
        db.updateNVDB(nguoidung,nguoidung.getPassword());

    }

    void XacNhanDoiPass(String user, String password) throws IOException, SQLException, ClassNotFoundException {
        addDialogStage = new Stage();
        addDialogStage.initOwner(primaryStage);
        addDialogStage.initModality(Modality.WINDOW_MODAL);


        FXMLLoader addUser = new FXMLLoader(getClass().getResource("AddUser.fxml"));
        Parent addsample2 = addUser.load();

        addDialogStage.setTitle("Thêm người dùng");
        Scene scene = new Scene(addsample2);

        AddUser controller = addUser.getController();
        controller.setPrimaryStage(addDialogStage);
        controller.setFlag(2);
        controller.init(user, password);
        //scene.setRoot(addsample2);

        addDialogStage.initStyle(StageStyle.UNDECORATED);

        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();

        if (addDialogStage.isShowing() == false) {
            int luachon = 0;
            if (controller.getFlag(luachon) == 0) return;

            password = controller.getPass();
            txtPass.setText(password);
            nguoidung.setPassword(password);

        } else System.out.println("Add User form doesn't show up");

    }



    void ImageIO_write() {
        try
        {
            Image image = new Image(file.toURL().toString());

            BufferedImage img = SwingFXUtils.fromFXImage(image, null);
            File f = new File("C:\\Users\\84948\\Desktop\\Do_an_QLKS\\Doan\\src\\sample\\PictureManage\\" + "NV" + nguoidung.getMaNV() + ".jpg");

            nguoidung.setHinhAnh(image);
            flag = 1;
            db.updateImageNVDB(nguoidung);



            ImageIO.write(img, "jpg", f);

        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }






    private File file = null;

    @FXML
    void pressChangeImage(ActionEvent event) throws IOException {

        final FileChooser fileChooser = new FileChooser();
        configuringFileChooser(fileChooser);
        file = fileChooser.showOpenDialog(primaryStage).getAbsoluteFile();
        if (file == null) return;
        else {
            URL url = file.toURL();
            Image imageChange = new Image(url.toString());
            imgProfile.setImage(imageChange);
            ImageIO_write();
        }

        flag = 5; // Su kien doi anh


    }

    private void configuringFileChooser(FileChooser fileChooser) {

        // Set tiêu đề cho FileChooser
        fileChooser.setTitle("Select Pictures");


        // Sét thư mục bắt đầu nhìn thấy khi mở FileChooser
        fileChooser.setInitialDirectory(null);



        // Thêm các bộ lọc file vào
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"));

    }
}
