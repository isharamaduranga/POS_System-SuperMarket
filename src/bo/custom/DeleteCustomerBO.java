package bo.custom;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteCustomerBO {
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;
}
