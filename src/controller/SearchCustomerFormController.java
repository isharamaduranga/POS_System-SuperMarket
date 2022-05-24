package controller;

import com.jfoenix.controls.JFXTextField;
import dao.CrudDAO;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import model.CustomerDTO;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomerFormController {
    public JFXTextField txtID;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;
    public AnchorPane searchCustomerContext;

    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO crudDAO = new CustomerDAOImpl();


    public void searchCustomerOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        try {

            ResultSet result = crudDAO.search(id);

            if (result.next()) {

                txtTitle.setVisible(true);
                txtName.setVisible(true);
                txtAddress.setVisible(true);
                txtPostCode.setVisible(true);
                txtProvince.setVisible(true);
                txtCity.setVisible(true);

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


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(searchCustomerContext, "CustomerControllerForm");
    }
}
