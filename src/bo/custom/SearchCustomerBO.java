package bo.custom;

import bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchCustomerBO extends SuperBO {
    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
