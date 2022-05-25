package bo.custom;

import model.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateOrderBO {
    ResultSet searchOrderDetails(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;
}
