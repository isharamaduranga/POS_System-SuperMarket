package controller;

import bo.DeleteOrderBOImpl;
import com.jfoenix.controls.JFXTextField;
import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrderFormController {
    public AnchorPane removeOrderContext;
    public JFXTextField txtOrderId;
    public JFXTextField txtDate;
    public JFXTextField txtCustID;




    public void removeOnAction(ActionEvent actionEvent) {
        try {

            String oid = txtOrderId.getText();

            /** DI / TIGHT*/
            DeleteOrderBOImpl deleteOrderBO = new DeleteOrderBOImpl();
            boolean isDeleted = deleteOrderBO.deleteOrder(oid);

            if (isDeleted) {
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

            String oid = txtOrderId.getText();

            /** DI / TIGHT*/
            DeleteOrderBOImpl deleteOrderBO = new DeleteOrderBOImpl();
            ResultSet result = deleteOrderBO.searchOrder(oid);

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

    public void clear() {
        txtDate.clear();
        txtCustID.clear();
    }
}
