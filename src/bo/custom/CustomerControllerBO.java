package bo.custom;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerControllerBO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
}
