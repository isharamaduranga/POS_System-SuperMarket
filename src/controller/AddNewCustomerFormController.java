package controller;


import bo.AddNewCustomerBOImpl;
import com.jfoenix.controls.JFXTextField;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.CustomerDTO;

import java.sql.SQLException;

public class AddNewCustomerFormController {

    public JFXTextField txtID;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtPostCode;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public AnchorPane addCustomerContext;




    public void initialize() {
        autoId();


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

                /** DI/TIGHT */
                AddNewCustomerBOImpl addNewCustomerBO = new AddNewCustomerBOImpl();

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
            /** DI/TIGHT */
            AddNewCustomerBOImpl addNewCustomerBO = new AddNewCustomerBOImpl();
            String s = addNewCustomerBO.generateNewCustomerID();
            txtID.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
