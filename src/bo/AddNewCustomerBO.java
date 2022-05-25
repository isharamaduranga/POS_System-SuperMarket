package bo;

import model.CustomerDTO;

import java.sql.SQLException;

public interface AddNewCustomerBO {

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
