package bo.custom;

import bo.SuperBO;
import model.CustomerDTO;

import java.sql.SQLException;

public interface AddNewCustomerBO extends SuperBO {

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
