package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.QLKS_ClassData.*;
import sample.QLKS_ClassData.Khach;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QL_KH extends Application {

    @FXML
    private TextField txtID_KH;

    @FXML
    private TextField txtName_KH;

    @FXML
    private TextField txtCMND_KH;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtCountry_KH;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rbNam;

    @FXML
    private RadioButton rbNu;

    @FXML
    private DatePicker dtpWasBorn_KH;

    @FXML
    private TableView<Khach> tblKH;

    @FXML
    private TableColumn<Khach, Integer> Index_table;

    @FXML
    private TableColumn<Khach, Integer> IDKHtable;

    @FXML
    private TableColumn<Khach, String> NameKHtable;

    @FXML
    private TableColumn<Khach, Date> WasBorn_table;

    @FXML
    private TableColumn<Khach, String> GioiTinh_table;

    @FXML
    private TableColumn<Khach, String> CMND_table;

    @FXML
    private TableColumn<Khach, String> SDT_table;

    @FXML
    private TableColumn<Khach, String> Email_table;

    @FXML
    private TableColumn<Khach,String> QuocTich_table;

    @FXML
    private TextField txtSearch;

    private ObservableList<PhieuDatPhong> phieuDatPhongsList = null;

    @FXML
    void PressAdd(ActionEvent event) {

    }

    @FXML
    void PressClear(ActionEvent event) {
        ClearKH();
    }

    void ClearKH(){

        txtPhoneNumber.setText("");
        txtCMND_KH.setText("");
        txtName_KH.setText("");
        txtCountry_KH.setText("");
        txtEmail.setText("");
    }


    @FXML
    void PressEdit(ActionEvent event) throws SQLException {
        if (tblKH.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,"H??y ch???n kh??ch h??ng c???n ch???nh s???a !");return;
        }

        if (rbNu.isSelected()== false && rbNam.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null,"Ph???i ch???n gi???i t??nh.");
            return;
        }

        int maKH = tblKH.getSelectionModel().getSelectedItem().getMaKHACH();
        String hoten = txtName_KH.getText();
        Date ngaysinh = java.sql.Date.valueOf(dtpWasBorn_KH.getValue());
        String cmnd = txtCMND_KH.getText();
        String sdt = txtPhoneNumber.getText();
        String quoctich = txtCountry_KH.getText();
        String email = txtEmail.getText();

        String gioitinh ="";
        if (rbNu.isSelected()) gioitinh = rbNu.getText();
        else gioitinh = rbNam.getText();

        db.updateKHDB(maKH,hoten,ngaysinh,cmnd,email,gioitinh,quoctich,sdt);
        JOptionPane.showMessageDialog(null,"S???a th??nh c??ng !");
        ShowQuest();

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

    //tu???n 10 ch??a ch??a bk n???a l??n wen

    @FXML
    void SearchKH(KeyEvent event) throws ClassNotFoundException {
        if (event.getSource() == txtSearch)
        {
            String tukhoa = txtSearch.getText().toLowerCase();
            tblKH.setItems(null);
            List<Khach> KH_temp = new ArrayList<>();


            if (tukhoa.equals("")) ShowQuest();
            else{
                for (int i=0;i<khachesList.size();i++){
                    if (String.valueOf(khachesList.get(i).getMaKHACH()).toLowerCase().contains(tukhoa) ||
                            khachesList.get(i).getTenKHACH().toLowerCase().contains(tukhoa)
                    ) KH_temp.add(khachesList.get(i));
                }

                ObservableList<Khach> khachesList_Search = FXCollections.observableArrayList(KH_temp);
                ConfigTable();
                tblKH.setItems(khachesList_Search);
            }
        }

    }

    @FXML
    void ShowInfoBacktoText(MouseEvent event) {
        int maKH = tblKH.getSelectionModel().getSelectedItem().getMaKHACH();
        String ID_KH = String.valueOf(maKH);

        txtID_KH.setText(ID_KH);
        txtName_KH.setText(tblKH.getSelectionModel().getSelectedItem().getTenKHACH());
        txtEmail.setText(tblKH.getSelectionModel().getSelectedItem().getEmail());
        txtCMND_KH.setText(tblKH.getSelectionModel().getSelectedItem().getCMND());

        txtPhoneNumber.setText(tblKH.getSelectionModel().getSelectedItem().getSDT());



        if (tblKH.getSelectionModel().getSelectedItem().getGioiTinh().equals("Nam")) {
            rbNam.setSelected(true);rbNu.setSelected(false);
        }
        else {
            rbNu.setSelected(true);rbNam.setSelected(false);
        }



        dtpWasBorn_KH.setValue(tblKH.getSelectionModel().getSelectedItem().getNgaySinh().toLocalDate());
        txtCountry_KH.setText(tblKH.getSelectionModel().getSelectedItem().getQuocTich());


    }
    void InitializeComponent(){

    }


    @Override
    public void start(Stage stage) throws Exception {
        InitializeComponent();
        ShowQuest();

    }

    private ObservableList<Khach> khachesList = null;

    private ConnectionDB db;

    private static Stage  addDialogStage;

    private static String user;
    private static String password;

    private static Stage primaryStage;
    public void setpimaryStage(Stage pS){
        primaryStage = pS;
    }

    private static int flag = 0;

    void ShowQuest(){
        try {
            dtpWasBorn_KH.setValue(LocalDate.now().minusYears(21));
            db = new ConnectionDB();

            if (db == null) System.out.println("Unconnected");
            else System.out.println("Connected");

            String sql = String.format("SELECT * FROM KHACH");

            PreparedStatement statement = db.conn.prepareStatement(sql);
            db.rs = statement.executeQuery();

            List<Khach> KHList = new ArrayList<Khach>();



            int STT = 1;
            while (db.rs.next()) {

                int MaKH = db.rs.getInt("MaKHACH");

                String TenKH = db.rs.getString("TenKHACH");

                Date Ngaysinh =  db.rs.getDate("NgaySinh") ;

                String SDT = db.rs.getString("SDT");

                String Email = db.rs.getString("Email");

                String CMND = db.rs.getString("CMND");

                String QuocTich = db.rs.getString("QuocTich");

                String GioiTinh = db.rs.getString("GioiTinh");



                Khach khach = new Khach(STT,MaKH,TenKH,SDT,Email,CMND,QuocTich,GioiTinh,Ngaysinh);
                KHList.add(khach);
                STT ++;

            }

            khachesList = FXCollections.observableArrayList(KHList);
            ConfigTable();
            tblKH.setItems(khachesList);

            //

        } catch (SQLException | ClassNotFoundException throwables) {

        }
    }

    void ConfigTable(){
        Index_table.setCellValueFactory(new PropertyValueFactory<Khach,Integer>("STT"));
        IDKHtable.setCellValueFactory(new PropertyValueFactory<Khach,Integer>("MaKHACH"));
        NameKHtable.setCellValueFactory(new PropertyValueFactory<Khach,String>("TenKHACH"));
        WasBorn_table.setCellValueFactory(new PropertyValueFactory<Khach,Date>("NgaySinh"));
        GioiTinh_table.setCellValueFactory(new PropertyValueFactory<Khach,String>("GioiTinh"));
        Email_table.setCellValueFactory(new PropertyValueFactory<Khach,String>("Email"));
        CMND_table.setCellValueFactory(new PropertyValueFactory<Khach,String>("CMND"));
        SDT_table.setCellValueFactory(new PropertyValueFactory<Khach,String>("SDT"));
        QuocTich_table.setCellValueFactory(new PropertyValueFactory<Khach,String>("QuocTich"));
    }

    void ShowHistory() throws IOException {
        addDialogStage = new Stage();
        addDialogStage.initOwner(primaryStage);
        addDialogStage.initModality(Modality.WINDOW_MODAL);



        FXMLLoader historyRoom = new FXMLLoader(getClass().getResource("HistoryRoom.fxml"));
        Parent addsample2 = historyRoom.load();

        addDialogStage.setTitle("L???ch s??? ?????t ph??ng");
        Scene scene = new Scene(addsample2);




        addDialogStage.initStyle(StageStyle.UNDECORATED);
        HistoryRoom controller = historyRoom.getController();
        controller.setPrimaryStage(addDialogStage);
        controller.setPhieuDPList(phieuDatPhongsList);controller.Init();
        addDialogStage.setScene(scene);
        addDialogStage.showAndWait();


    }

    @FXML
    void PressHistory(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        db = new ConnectionDB();

        if (tblKH.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null,"H??y ch???n kh??ch h??ng c???n xem !");return;
        }

        String sql = String.format("SELECT * FROM [QLKS].[dbo].[View_PDP]");

        PreparedStatement statement = db.conn.prepareStatement(sql);
        db.rs = statement.executeQuery();

        List <PhieuDatPhong> PDPList = new ArrayList<PhieuDatPhong>();

        while (db.rs.next()) {
               if (tblKH.getSelectionModel().getSelectedItem().getMaKHACH() == db.rs.getInt("M?? Kh??ch"))
               {
                   PhieuDatPhong pdp = new PhieuDatPhong(db.rs.getInt("M?? PDP"),db.rs.getInt("M?? Ph??ng"),
                           db.rs.getString("T??n Ph??ng"),db.rs.getString("T??n LP"),
                           db.rs.getString("Kh??ch"),db.rs.getDate("Ng??y ?????t"),
                           db.rs.getDate("Ng??y tr???"),db.rs.getBigDecimal("Gi?? Ph??ng").toPlainString(),
                           db.rs.getBigDecimal("Ti???n tr???").toPlainString());
                   PDPList.add(pdp);
                   System.out.println(pdp.getTenKHACH()+pdp.getTenP() + pdp.getTienTra());
               }


        }
        phieuDatPhongsList = FXCollections.observableArrayList(PDPList);
        ShowHistory();
    }

    @FXML
    void KeyNumber(KeyEvent event) {
        txtPhoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtPhoneNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txtCMND_KH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtCMND_KH.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



    }

}
