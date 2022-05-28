package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.UpdateCustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.Utilities;
import lk.ijse.pos.util.ValidationUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateCustomerFormController {
    /**
     * Apply Dependency Injection(Property)
     */
    private final UpdateCustomerBO updateCustomerBO = (UpdateCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_CUSTOMER);
    public JFXTextField txtCustomerID;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;
    public AnchorPane updateCustomerContext;
    public JFXButton btnUpdate;
    /**
     * Define Linked-HashMap for the validation
     */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize(){
        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern TitlePattern = Pattern.compile("^[A-z .]{3,}$");
        Pattern NamePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern CityPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern ProvincePattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern postalCodePattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");

        //add pattern and text to the map
        map.put(txtTitle,TitlePattern);
        map.put(txtName,NamePattern);
        map.put(txtAddress,AddressPattern);
        map.put(txtCity,CityPattern);
        map.put(txtProvince,ProvincePattern);
        map.put(txtPostCode,postalCodePattern);
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

    public void SelectCustomerKeyReleased(KeyEvent keyEvent) {
        try {
            String cusId = txtCustomerID.getText();

            ResultSet rst = updateCustomerBO.searchCustomer(cusId);

            if (rst.next()) {
                txtTitle.setText(rst.getString(2));
                txtName.setText(rst.getString(3));
                txtAddress.setText(rst.getString(4));
                txtCity.setText(rst.getString(5));
                txtProvince.setText(rst.getString(6));
                txtPostCode.setText(rst.getString(7));

            } else {
                clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ConfirmUpdateOnAction(ActionEvent actionEvent) {
        CustomerDTO dto = new CustomerDTO(txtCustomerID.getText(), txtTitle.getText(), txtName.getText(),
                txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostCode.getText());

        try {

            if (updateCustomerBO.updateCustomer(dto)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").showAndWait();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtCustomerID.clear();
        clear();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(updateCustomerContext, "CustomerControllerForm");
    }

    public void clear() {
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostCode.clear();
    }


}
