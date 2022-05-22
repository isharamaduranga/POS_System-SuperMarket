package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrderFormController {
    public AnchorPane removeOrderContext;
    public JFXTextField txtOrderId;
    public JFXTextField txtDate;
    public JFXTextField txtCustID;

    public void removeOnAction(ActionEvent actionEvent) {
        try {

            if (CrudUtil.execute("DELETE FROM `Order` WHERE OrderID=?", txtOrderId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        txtOrderId.clear();
        clear();
    }

    public void searchOrder(KeyEvent keyEvent) {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM `Order` WHERE OrderID=?",txtOrderId.getText());
            if (result.next()) {

                txtDate.setText(result.getString(2));
                txtCustID.setText(result.getString(3));


            } else {
                   clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void clear(){

        txtDate.clear();
        txtCustID.clear();
    }
}
