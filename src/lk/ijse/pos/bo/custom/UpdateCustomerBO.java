package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateCustomerBO extends SuperBO {

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
