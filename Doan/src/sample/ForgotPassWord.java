package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.QLKS_ClassData.ConnectionDB;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ForgotPassWord {

    @FXML
    private Main main;

    private static Stage primaryStage;
    private static GridPane mainLayout;

    private static Stage addDialogStage;

    private String user;
    private String CMND;
    private String password;

    private ConnectionDB db;

    public void setDb(ConnectionDB db) {
        this.db = db;
    }

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtConfirmID;

    void BackToLogin(ActionEvent event) throws IOException {
        addDialogStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader login = new FXMLLoader();
        login.setLocation(Main.class.getResource("login.fxml"));
        Parent addsample2 = login.load();

        //((Node)event.getSource()).getScene().getWindow().hide();
        //super.CloseFrame();

//        addDialogStage.setTitle("Hello World");
//        addDialogStage.initModality(Modality.WINDOW_MODAL);
//        addDialogStage.initOwner(primaryStage);
        Scene scene = new Scene(addsample2);
        addDialogStage.setScene(scene);
        addDialogStage.show();
    }

    @FXML
    void PressCancel(ActionEvent event) throws IOException {

        BackToLogin(event);
    }

    @FXML
    void PressYes(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        db = new ConnectionDB();

        db.rs = db.stmt.executeQuery("SELECT * FROM NhanVien");
        int flag=0;
        while (db.rs.next()) {
            user = db.rs.getString("user");
            CMND = db.rs.getString("CMND");

            if (txtUser.getText().equals(user) && txtID.getText().equals(CMND) && txtConfirmID.getText().equals(CMND))
            {
                password = db.rs.getString("Password");
                JOptionPane.showMessageDialog(null,"Mật khẩu của bạn là:" + password);
                flag = 1;
                break;

            }
        }

        if (flag == 1) BackToLogin(event);

        if (txtUser.getText().equals(user) == false || txtID.getText().equals(CMND) == false ||
                txtConfirmID.getText().equals(CMND) == false)
        {
            JOptionPane.showMessageDialog(null,"Bạn điền sai thông tin cần nhập !");return;
        }


    }
}
