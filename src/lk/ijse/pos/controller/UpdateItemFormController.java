package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UpdateItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.util.Utilities;
import lk.ijse.pos.util.ValidationUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;


public class UpdateItemFormController {
    /**
     * Apply Dependency Injection(Property)
     */
    private final UpdateItemBO updateItemBO = (UpdateItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_ITEM);
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYOnHand;
    public JFXComboBox<String> cmbItemCode;
    public AnchorPane updateItemContext;
    public JFXButton btnUpdate;
    /**
     * Define Linked-HashMap for the validation
     */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {

        try {
            loadAllItemCode();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemData(newValue);
        });

        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern DescriptionPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern PackSizePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern UnitPricePattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern QtyOnHand = Pattern.compile("^[0-9]{1,20}$");


        //add pattern and text to the map
        map.put(txtDescription, DescriptionPattern);
        map.put(txtPackSize, PackSizePattern);
        map.put(txtUnitPrice, UnitPricePattern);
        map.put(txtQTYOnHand, QtyOnHand);
    }

    /** Define the function of cross to among the textFields(use Enter press)  */
    public void textFields_Key_Released(KeyEvent keyEvent) {

        ValidationUtil.validate(map,btnUpdate);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnUpdate);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
    }

    private void setItemData(String itemCode) {
        try {

            String code = cmbItemCode.getValue();

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

        ObservableList<String> itemsCode = updateItemBO.getAllItemCodes();
        cmbItemCode.setItems(itemsCode);

    }

    public void ConfirmUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO dto = new ItemDTO(cmbItemCode.getValue(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQTYOnHand.getText()));

        try {

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
