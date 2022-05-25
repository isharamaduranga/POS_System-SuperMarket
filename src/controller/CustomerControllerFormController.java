package controller;

import bo.CustomerControllerBOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CustomerDTO;
import util.Utilities;
import view.TM.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerControllerFormController {
    public AnchorPane CustomerContext;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colID;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostCode;


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("cusTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));

        loadAllCustomers();
    }


    private void loadAllCustomers() {

        try {
            /** DI/TIGHT */
            CustomerControllerBOImpl customerControllerBO = new CustomerControllerBOImpl();
            ArrayList<CustomerDTO> allCustomer = customerControllerBO.getAllCustomer();

            for (CustomerDTO customer : allCustomer) {
                tblCustomer.getItems().add(new CustomerTM(
                        customer.getCusID(),
                        customer.getCusTitle(),
                        customer.getCusName(),
                        customer.getCusAddress(),
                        customer.getCity(),
                        customer.getProvince(),
                        customer.getPostCode()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext, "AddNewCustomerForm");
    }

    public void editCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext, "UpdateCustomerForm");
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext, "SearchCustomerForm");
    }

    public void removeCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext, "DeleteCustomerForm");
    }
}
