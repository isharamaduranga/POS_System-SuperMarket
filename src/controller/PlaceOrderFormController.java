package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class PlaceOrderFormController {

    public Label lblOrderID;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtaddress;
    public JFXTextField txtCity;

    public JFXComboBox<String> cmbItemID;
    public JFXTextField txtDiscription;
    public JFXTextField txtQTYOnHand;
    public JFXTextField txtUnitPrice;

    public TextField txtQTY;
    public TextField txtDiscount;

    public TableView tblCart;
    public TableColumn colItemID;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;

    public Label lblTotal;

    public void initialize(){
        curDateTime();
        autoId();

        try {
            loadCustomerIds();
            loadItemIds();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                setCustomerData(newValue);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                setItemData(newValue);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });



    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item WHERE ItemCode=?", itemCode);
        if (result.next()) {
            txtDiscription.setText(result.getString(2));
            txtQTYOnHand.setText(result.getString(5));
            txtUnitPrice.setText(result.getString(4));

        }
    }

    private void setCustomerData(String customerID) throws SQLException, ClassNotFoundException {

            ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE CusID=?", customerID);
            if (result.next()) {
                txtName.setText(result.getString(3));
                txtaddress.setText(result.getString(4));
                txtCity.setText(result.getString(5));

            }
    }


    private void loadItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ObservableList<String> codes= FXCollections.observableArrayList();

        while (rst.next()) {
            codes.add(rst.getString(1)); ;
        }
        cmbItemID.setItems(codes);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> ids= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        cmbCustomerID.setItems(ids);
    }

    private void curDateTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void autoId() {
        try {
            ResultSet result = CrudUtil.execute("SELECT OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1");

            if (result.next()) {

                String rnno = result.getString("OrderID");
                int co = rnno.length();
                String txt = rnno.substring(0, 2);//mul deka  (CI)
                String num = rnno.substring(2, co);//aga deaka (1000)

                int n = Integer.parseInt(num);
                n++;
                String snum = Integer.toString(n);
                String ftxt = txt + snum;
                lblOrderID.setText(ftxt);
            } else {
                lblOrderID.setText("OI1000");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void fieldClear(){
        cmbCustomerID.getSelectionModel().clearSelection();
        txtName.clear();
        txtaddress.clear();
        txtCity.clear();
        cmbItemID.getSelectionModel().clearSelection();
        txtDiscription.clear();
        txtQTYOnHand.clear();
        txtUnitPrice.clear();
        txtQTY.clear();
        txtDiscount.clear();
    }

    public void clearOnAction(ActionEvent actionEvent) {

    }

    public void addToCartOnAction(ActionEvent actionEvent) {

        String discription = txtDiscription.getText();
        int qtyOnHand = Integer.parseInt(txtQTYOnHand.getText());
        String unitPrice = txtUnitPrice.getText();
        String qtyText = txtQTY.getText();


        //fieldClear();
    }

    public void ComfirmOrderOnAction(ActionEvent actionEvent) {

    }
}
