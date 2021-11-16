package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import sample.QLKS_ClassData.ConnectionDB;
import sample.QLKS_ClassData.NhanVien;

import javax.swing.*;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;


public class Controller {

    void InitializeComponent(){
        ;
    }
    public Controller() throws SQLException, ClassNotFoundException {
        InitializeComponent();
        db = new ConnectionDB();

    }

    private static NhanVien nguoidung;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private GridPane grpLogIn;

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    private static Stage primaryStage;


    private static Stage addDialogStage;

    private static String user;
    private static String password;
    private static int MaNV_User= 0;


    private List<NhanVien> listNV = null;

    private ConnectionDB db;



    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnEsc;

    @FXML
    void PressEsc(ActionEvent event)  {
        int a = JOptionPane.showConfirmDialog(null,"Thoát chương trình","Bạn muốn đóng chương trình ?",JOptionPane.YES_NO_OPTION);
        if ( a == 0 ) primaryStage.close();
    }

    @FXML
    void PressForgot(ActionEvent event) {
        try{
            addDialogStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader quenPass = new FXMLLoader(getClass().getResource("ForgotPassWord.fxml"));

            Parent addsample2 = quenPass.load();

            //((Node)event.getSource()).getScene().getWindow().hide();
            addDialogStage.setTitle("ForgotPassWord");
            //addDialogStage.initModality(Modality.WINDOW_MODAL);
            //addDialogStage.initOwner(primaryStage);
            //String newID = addsample2.getId(); createRandomCode(newID.length(),newID);
            //addsample2.setId(newID);

            //Truyền dữ liệu database qua form ForgotPassWord
            ForgotPassWord controller  = quenPass.getController();
            Scene scene = new Scene(addsample2);
            scene.setRoot(addsample2);


            addDialogStage.setScene(scene);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void LinkToHome(ActionEvent event){
        try {

            addDialogStage = new Stage();
            addDialogStage.initOwner(primaryStage);
            addDialogStage.initModality(Modality.WINDOW_MODAL);



            FXMLLoader home = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent addsample2 = home.load();

            addDialogStage.setTitle("Thêm người dùng");
            Scene scene = new Scene(addsample2);



            //((Node)event.getSource()).getScene().getWindow().hide();
            addDialogStage.setTitle("Home");


            Home controller = home.getController();
            controller.setPrimaryStage(addDialogStage);

            addDialogStage.initStyle(StageStyle.UNDECORATED);

            addDialogStage.setScene(scene);
            addDialogStage.setMaximized(true);

            controller.setPrimaryStage(addDialogStage);
            if (nguoidung != null){
                if (nguoidung.getUser().equals(user) && nguoidung.getPassword().equals(password))
                    controller.setNguoidung(nguoidung);
            }

            controller.setUser(user);controller.setPass(password);

            controller.setMaNV_User(MaNV_User);
            controller.init();
            controller.PressMenu(event);

            addDialogStage.showAndWait();

            if (addDialogStage.isShowing() == false)
            nguoidung = controller.getNV_User_afterchangedIMG();





            //addDialogStage.show();lag


        }catch (Exception ex) {
            ex.printStackTrace();

        }



    }

    @FXML
    void PressOnLogin(ActionEvent event) {

        try {


//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSSQLSERVER:1433;database=QLKS","sa","sa");
//
//            if (conn == null) System.out.println("Fail connect database");
//
//            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
//            // get data from table 'student'
//
//
//            ResultSet rs = null;




            db = new ConnectionDB();
            db.rs = db.stmt.executeQuery("SELECT * FROM NhanVien");


            // show data
            while (db.rs.next()) {
                user = db.rs.getString(11);
                password = db.rs.getString(12);

                if (txtUser.getText().equals(user) && txtPass.getText().equals(password)){

                    JOptionPane.showMessageDialog(null,"Đăng nhập thành công");
                    MaNV_User = db.rs.getInt(1);
                    LinkToHome(event);
                    break;

                }


            }

            if (txtUser.getText().equals(user) == false || txtPass.getText().equals(password) == false)
            {
                txtUser.setText("");txtPass.setText("");
                JOptionPane.showMessageDialog(null,"Đăng nhập thất bại.\nSai tên đăng nhập" +
                        "hoặc mật khẩu");



            }
            txtUser.setText("");txtPass.setText("");


            db.conn.close();
            return;

        } catch (Exception ex) {


        }

    }


}
