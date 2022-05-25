package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCustomerBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }
}
