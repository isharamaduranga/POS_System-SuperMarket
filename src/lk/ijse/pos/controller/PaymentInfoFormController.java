/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/28/2022
 * Time        : 8:01 PM
 * Year        : 2022
 */

package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.util.CrudUtil;
import lk.ijse.pos.util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentInfoFormController {

    public JFXButton btnCash;
    public JFXButton btnCredit;
    public Label lblTotalBill;
    public TextField cashOfCustomer;
    public TextField saveBalance;
    public AnchorPane cardPaymentContext;
    public ComboBox cmbMonth;
    public ComboBox cmbYear;
    public JFXButton btnCancel;
    public AnchorPane paymentContext;
    public TextField txtCrdNumber;
    public TextField txtCVN;
    public JFXButton printInvoice;

    String lastOid = "";

    public void initialize() {
        try {

            printInvoice.setDisable(true);
            getLastId();
            cmbMonth.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
            cmbYear.getItems().addAll("2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getLastId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT orderid from `order details` ORDER BY orderid desc Limit 1");
        if (result.next()) {
            lastOid = result.getString("OrderID");
            ResultSet result1 = CrudUtil.execute("SELECT SUM(Price) from `order details` where orderid=?", lastOid);
            result1.next();
            lblTotalBill.setText(String.valueOf(result1.getDouble(1)));


        }
    }


    public void cashOnAction(ActionEvent actionEvent) {
        cardPaymentContext.setVisible(false);

    }

    public void creditOnAction(ActionEvent actionEvent) {
        cardPaymentContext.setVisible(true);
    }

    public void payOnAction(ActionEvent actionEvent) throws IOException {

        if(cmbMonth.getSelectionModel().isEmpty() && cmbYear.getSelectionModel().isEmpty() &&
                txtCrdNumber.getText().equals("")&& txtCVN.getText().equals("") ){
            new Alert(Alert.AlertType.WARNING, "Something went wrong..Please check your details..").show();
        }else {
            Utilities.setUiChildren(paymentContext, "DonePayment");
        }


    }

    public void btnCancelOnAction(ActionEvent actionEvent) {

        cardPaymentContext.setVisible(false);
    }

    public void printInvoiceOnAction(ActionEvent actionEvent) {
    }

    public void cashKeyReleased(KeyEvent keyEvent) {
        double price= Double.parseDouble(lblTotalBill.getText());
        double customerMoney= Double.parseDouble(cashOfCustomer.getText());
        saveBalance.setText(String.valueOf(price-customerMoney)+"/=");
        printInvoice.setDisable(false);
    }
}
