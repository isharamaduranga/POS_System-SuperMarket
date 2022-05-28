package lk.ijse.pos.controller;

import animatefx.animation.Bounce;
import animatefx.animation.ZoomIn;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerControllerBO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.view.TM.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerTableForCashierFormController {

    public TableView tblCustomer;
    public TableColumn colID;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostCode;

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


}
