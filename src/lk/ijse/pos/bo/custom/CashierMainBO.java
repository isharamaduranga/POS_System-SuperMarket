package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CashierMainBO extends SuperBO {
    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ResultSet getCustomerOrderFromOrderID(String oid) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsFromCustomerID(String cid) throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllCustomerIDS() throws SQLException, ClassNotFoundException;
}
