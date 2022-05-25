package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomerBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = new CustomerDAOImpl();


    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
      return   customerDAO.delete(id);
    }

    public ObservableList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
       return customerDAO.getIds();
    }

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.search(id);
    }



}
