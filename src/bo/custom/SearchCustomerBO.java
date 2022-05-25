package bo.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchCustomerBO {
    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
