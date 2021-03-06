package lk.ijse.pos.controller;

import animatefx.animation.Bounce;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerControllerBO;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.Utilities;
import lk.ijse.pos.view.TM.CustomerTM;

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
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;


    /**
     * Apply Dependency Injection(Property)
     */
    private final CustomerControllerBO customerControllerBO = (CustomerControllerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_CONTROLLER);

    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("cusTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));

        loadAllCustomers();
        new ZoomIn(tblCustomer).play();

    }


    private void loadAllCustomers() {

        try {


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

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
        if(((JFXButton) mouseEvent.getSource()).getText().equals("ADD CUSTOMER")){
            new Bounce(btnAdd).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("UPDATE CUSTOMER")){
            new Bounce(btnUpdate).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("SEARCH CUSTOMER")){
            new Bounce(btnSearch).play();

        }else{
            new Bounce(btnDelete).play();
        }
    }
}
