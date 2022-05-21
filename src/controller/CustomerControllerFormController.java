package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.Utilities;
import view.TM.CustomerTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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



    public void initialize(){

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
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");

            while (rst.next()) {
                tblCustomer.getItems().add(new CustomerTM(
                    rst.getString("cusID"),
                    rst.getString("cusTitle"),
                    rst.getString("cusName"),
                    rst.getString("cusAddress"),
                    rst.getString("city"),
                    rst.getString("Povince"),
                    rst.getString("postCode")

                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext,"AddNewCustomerForm");
    }

    public void editCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext,"UpdateCustomerForm");
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext,"SearchCustomerForm");
    }

    public void removeCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(CustomerContext,"DeleteCustomerForm");
    }
}
