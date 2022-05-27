package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddNewItemBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.util.Utilities;
import lk.ijse.pos.util.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewItemFormController {
    public AnchorPane addItemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnAdd;


    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    /**
     * Apply Dependency Injection(Property)
     */
    private final AddNewItemBO addNewItemBO = (AddNewItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_NEW_ITEM);

    public void initialize() {
        autoId();

        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern DescriptionPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern PackSizePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern UnitPricePattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern QtyOnHand = Pattern.compile("^[0-9]{1,20}$");



        //add pattern and text to the map
        map.put(txtDescription,DescriptionPattern);
        map.put(txtPackSize,PackSizePattern);
        map.put(txtUnitPrice,UnitPricePattern);
        map.put(txtQtyOnHand,QtyOnHand);

    }
    /** Define the function of cross to among the textFields(use Enter press)  */
    public void textFields_Key_Released(KeyEvent keyEvent) {

        ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAdd);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
    }

    public void addItemOnAction(ActionEvent actionEvent) {


        ItemDTO dto = new ItemDTO(
                txtCode.getText(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText())
        );
        try {

            if (addNewItemBO.saveItem(dto)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved...").showAndWait();

            } else {
                new Alert(Alert.AlertType.WARNING, "Item Already exists !!!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Item " + e.getMessage()).show();
        }


        clear();
    }

    public void autoId() {
        try {
            String s = addNewItemBO.generateNewItemCode();
            txtCode.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        txtCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        autoId();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(addItemContext, "ItemControllerForm");
    }
}
