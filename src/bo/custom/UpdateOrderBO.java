package bo.custom;

import bo.SuperBO;
import model.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateOrderBO extends SuperBO {
    ResultSet searchOrderDetails(String id) throws SQLException, ClassNotFoundException;

    boolean updateOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;
}
