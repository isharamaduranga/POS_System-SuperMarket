package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;

public interface AddNewCustomerBO extends SuperBO {

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
