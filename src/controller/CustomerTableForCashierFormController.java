package controller;

import bo.CustomerControllerBOImpl;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDTO;
import view.TM.CustomerTM;

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


}
