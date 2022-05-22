package controller;

import com.jfoenix.controls.JFXTextField;
import dao.ItemDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteItemFormController {
    public JFXTextField txtCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public AnchorPane deleteItemContext;

    public void SelectItemKeyReleased(KeyEvent keyEvent) {
        try {
            String code=txtCode.getText();

            ItemDAOImpl itemDAO = new ItemDAOImpl();
            ResultSet result = itemDAO.searchItem(code);

            if (result.next()) {

                txtDescription.setText(result.getString(2));
                txtPackSize.setText(result.getString(3));
                txtUnitPrice.setText(String.valueOf(result.getDouble(4)));
                txtQtyOnHand.setText(String.valueOf(result.getInt(5)));


            } else {
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) {

        try {
            String code=txtCode.getText();

            ItemDAOImpl itemDAO = new ItemDAOImpl();

            if (itemDAO.deleteItem(code)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        txtCode.clear();
        clear();
    }

    public void clear() {

        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(deleteItemContext, "ItemControllerForm");
    }


}
