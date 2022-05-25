package bo;

import javafx.collections.ObservableList;
import model.OrderDTO;
import model.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO {
    void purchaseOrder(OrderDTO order, ArrayList<OrderDetailsDTO> details) throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException;

    ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    String generateNewOrderID() throws SQLException, ClassNotFoundException;
}
