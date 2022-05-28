package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.input.KeyCode;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UpdateOrderBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.util.ValidationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateOrderFormController {

    public JFXTextField txtOrderID;
    public JFXTextField txtItemCode;
    public JFXTextField txtOrderQty;
    public JFXTextField txtDiscount;
    public JFXButton btnUpdate;
    /**
     * Apply Dependency Injection(Property)
     */
    private final UpdateOrderBO updateOrderBO = (UpdateOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_ORDER);


    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();



    public void initialize(){

        btnUpdate.setDisable(true);

        Pattern OrderQty = Pattern.compile("^[0-9]{1,20}$");
        Pattern Discount = Pattern.compile("^[1-9][0-9]*(.[0-9]{1,2})?$");


        //add pattern and text to the map
        map.put(txtOrderQty,OrderQty);
        map.put(txtDiscount,Discount);
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



    public void UpdateComfirmOnAction(ActionEvent actionEvent) {
        OrderDetailsDTO dto = new OrderDetailsDTO(txtOrderID.getText(), txtItemCode.getText(), Integer.parseInt(txtOrderQty.getText()),
                Double.parseDouble(txtDiscount.getText()));

        if((!txtOrderID.getText().equals("")|| !txtOrderQty.getText().equals("") || !txtDiscount.getText().equals(""))){

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
        }else{
            new Alert(Alert.AlertType.WARNING, "Check your fields Again...").show();
        }
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
        btnUpdate.setDisable(false);
    }

    public void clear() {

        txtItemCode.clear();
        txtDiscount.clear();
        txtOrderQty.clear();
    }
}
