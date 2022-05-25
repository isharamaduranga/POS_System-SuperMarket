package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomerBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
}
