package controller;

import bo.UpdateOrderBO;
import bo.UpdateOrderBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import model.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateOrderFormController {

    public JFXTextField txtOrderID;
    public JFXTextField txtItemCode;
    public JFXTextField txtOrderQty;
    public JFXTextField txtDiscount;

    /**
     * Apply Dependency Injection(Property)
     */
    private final UpdateOrderBO updateOrderBO = new UpdateOrderBOImpl();

    public void UpdateComfirmOnAction(ActionEvent actionEvent) {
        OrderDetailsDTO dto = new OrderDetailsDTO(txtOrderID.getText(), txtItemCode.getText(), Integer.parseInt(txtOrderQty.getText()),
                Double.parseDouble(txtDiscount.getText()));

        try {

            boolean update = updateOrderBO.updateOrderDetails(dto);

            if (update) {

                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").showAndWait();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtOrderID.clear();
        clear();
    }


    public void searchOrderDetaisOnAction(KeyEvent keyEvent) {
        try {
            String oid = txtOrderID.getText();

            ResultSet result = updateOrderBO.searchOrderDetails(oid);

            if (result.next()) {

                txtItemCode.setText(result.getString(2));
                txtOrderQty.setText(String.valueOf(result.getInt(3)));
                txtDiscount.setText(String.valueOf(result.getDouble(4)));


            } else {
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear() {

        txtItemCode.clear();
        txtDiscount.clear();
        txtOrderQty.clear();
    }
}
