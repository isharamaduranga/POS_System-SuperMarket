package lk.ijse.pos.controller;


import com.jfoenix.controls.JFXButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AddNewCustomerBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {

    public JFXTextField txtID;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtPostCode;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public AnchorPane addCustomerContext;
    public JFXButton btnAdd;

    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();


    /**
     * Apply Dependency Injection (Property)
     */
    private final AddNewCustomerBO addNewCustomerBO = (AddNewCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_NEW_CUSTOMER);


    public void initialize() {
        autoId();


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

    public void addNewCustomerOnAction(ActionEvent actionEvent) {

        String id = txtID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostCode.getText();

        CustomerDTO dto = new CustomerDTO(txtID.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(),
                txtCity.getText(), txtProvince.getText(), txtPostCode.getText());

        if (!title.equals("") && !name.equals("") && !address.equals("") && !city.equals("") && !province.equals("") && !postalCode.equals("")) {
            try {


                if (addNewCustomerBO.saveCustomer(dto)) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved...").showAndWait();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Customer Already exists !!!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            }
            clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "something went wrong Please Check Fields... !!!").show();
        }
    }

    public void clear() {
        txtID.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostCode.clear();
        autoId();
    }

    public void autoId() {
        try {

            String s = addNewCustomerBO.generateNewCustomerID();
            txtID.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
