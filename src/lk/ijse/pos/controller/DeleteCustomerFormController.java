package lk.ijse.pos.controller;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.DeleteCustomerBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeleteCustomerFormController {
    public JFXComboBox<String> cmdCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtTitle;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;
    public AnchorPane deleteCustomerContext;

    /**
     * Apply Dependency Injection(Property)
     */
    private final DeleteCustomerBO deleteCustomerBO = (DeleteCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DELETE_CUSTOMER);

    public void initialize() {
        try {
            loadAllCustomerIds();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmdCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerData(newValue);
        });
    }

    private void setCustomerData(String id) {
        try {

            ResultSet result = deleteCustomerBO.searchCustomer(id);

            if (result.next()) {
                txtTitle.setText(result.getString(2));
                txtName.setText(result.getString(3));
                txtAddress.setText(result.getString(4));
                txtCity.setText(result.getString(5));
                txtProvince.setText(result.getString(6));
                txtPostCode.setText(result.getString(7));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadAllCustomerIds() throws SQLException, ClassNotFoundException {

        ObservableList<String> customerIds = deleteCustomerBO.getAllCustomerIds();
        cmdCustomerID.setItems(customerIds);
    }

    public void clear() {
        cmdCustomerID.setValue("");
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostCode.clear();
    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {

            if (deleteCustomerBO.deleteCustomer(cmdCustomerID.getValue())) {

                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        clear();
        loadAllCustomerIds();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(deleteCustomerContext, "CustomerControllerForm");
    }
}
