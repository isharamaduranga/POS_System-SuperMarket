package bo.custom.impl;

import bo.custom.CustomerControllerBO;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerControllerBOImpl implements CustomerControllerBO {

    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

}