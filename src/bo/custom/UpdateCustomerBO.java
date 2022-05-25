package bo.custom;

import model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateCustomerBO {

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
