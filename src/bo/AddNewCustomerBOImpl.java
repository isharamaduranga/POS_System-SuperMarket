package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;

public class AddNewCustomerBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = new CustomerDAOImpl();


    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
       return customerDAO.generateNewId();
    }

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       return customerDAO.save(dto);
    }


}
