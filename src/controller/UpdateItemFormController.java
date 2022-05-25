package controller;

import bo.UpdateItemBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateItemFormController {
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYOnHand;
    public JFXComboBox<String> cmbItemCode;
    public AnchorPane updateItemContext;


    public void initialize() {

        try {
            loadAllItemCode();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemData(newValue);
        });
    }

    private void setItemData(String itemCode) {
        try {

            String code = cmbItemCode.getValue();

            /** di / tight*/
            UpdateItemBOImpl updateItemBO = new UpdateItemBOImpl();
            ResultSet result = updateItemBO.searchItem(code);


            if (result.next()) {
                txtDescription.setText(result.getString(2));
                txtPackSize.setText(result.getString(3));
                txtUnitPrice.setText(String.valueOf(result.getDouble(4)));
                txtQTYOnHand.setText(String.valueOf(result.getInt(5)));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItemCode() throws SQLException, ClassNotFoundException {

        /** di / tight*/
        UpdateItemBOImpl updateItemBO = new UpdateItemBOImpl();
        ObservableList<String> itemsCode = updateItemBO.getAllItemCodes();

        cmbItemCode.setItems(itemsCode);

    }

    public void ConfirmUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO dto = new ItemDTO((String) cmbItemCode.getValue(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQTYOnHand.getText()));

        try {

            /** di / tight*/
            UpdateItemBOImpl updateItemBO = new UpdateItemBOImpl();

            if (updateItemBO.UpdateItem(dto)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").showAndWait();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();

    }

    public void clear() throws SQLException, ClassNotFoundException {
        cmbItemCode.setItems(null);
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQTYOnHand.clear();
        loadAllItemCode();

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(updateItemContext, "ItemControllerForm");
    }
}
