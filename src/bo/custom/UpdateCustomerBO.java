package bo.custom;

import bo.SuperBO;
import model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateCustomerBO extends SuperBO {

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
