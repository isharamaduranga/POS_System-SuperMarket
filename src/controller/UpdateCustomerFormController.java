package controller;

import com.jfoenix.controls.JFXTextField;
import dao.CrudDAO;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.CustomerDTO;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCustomerFormController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;
    public AnchorPane updateCustomerContext;

    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO crudDAO = new CustomerDAOImpl();

    public void SelectCustomerKeyReleased(KeyEvent keyEvent) {
        try {
            String cusId = txtCustomerID.getText();

            ResultSet rst = crudDAO.search(cusId);
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
            if (crudDAO.update(dto)) {

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
