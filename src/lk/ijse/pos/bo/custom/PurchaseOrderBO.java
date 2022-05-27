package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import javafx.collections.ObservableList;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {
    void purchaseOrder(OrderDTO order, ArrayList<OrderDetailsDTO> details) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException;

    ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    String generateNewOrderID() throws SQLException, ClassNotFoundException;
}
