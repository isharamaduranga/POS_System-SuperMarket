package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateOrderBO extends SuperBO {
    boolean updateOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException;

    ResultSet searchOrderDetails(String id) throws SQLException, ClassNotFoundException;


}
