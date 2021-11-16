package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.QLKS_ClassData.ConnectionDB;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddUser {



    //flag = 0 la huy stage nay, flag = 1 la da thay doi du lieu stage nay
    //flag = 2 la su kien doi mat khau
    private ConnectionDB db;
    public void init(String user,String password) throws SQLException, ClassNotFoundException {

        if (flag == 2){
            txtTK.setText(user);txtTK.setEditable(false);
            txtPass.setText("");txtConfPass.setText("");
            txtTK.setDisable(true);
        }

    }

    public Stage primaryStage;
    private int flag=0;

    public int getFlag(int sukien){
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @FXML
    private AnchorPane anpAddUser;

    @FXML
    private TextField txtTK;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtConfPass;

    public String getUser(){
        return txtTK.getText();
    }

    public String getPass(){
        return txtPass.getText();
    }

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    @FXML
    void PressCancel(ActionEvent event) {
        primaryStage.close();
        flag = 0;
    }

    @FXML
    void PressOK(ActionEvent event) throws SQLException, ClassNotFoundException {
        db = new ConnectionDB();
        if (txtPass.getText().equals("") || txtTK.getText().equals("") || txtConfPass.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Cần nhập đầy đủ thông tin");
            return;
        }

        db.rs = db.stmt.executeQuery("SELECT  * FROM NhanVien");

        while (db.rs.next()){
            if (txtTK.getText().equals(db.rs.getString("user")) && flag != 2) {
                JOptionPane.showMessageDialog(null,"Tên tài khoản này đã được sử dụng");
                return;
            }
        }

        if (txtConfPass.getText().equals(txtPass.getText()) == false) {
            JOptionPane.showMessageDialog(null,"Mật khẩu xác nhận phải trùng với mật khẩu !");
            return;
        }
        else {
            flag = 1;
            primaryStage.hide();
        }

    }

}
