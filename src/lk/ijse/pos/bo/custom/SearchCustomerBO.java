package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchCustomerBO extends SuperBO {
    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
