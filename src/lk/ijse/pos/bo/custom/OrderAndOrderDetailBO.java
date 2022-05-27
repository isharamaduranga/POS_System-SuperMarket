package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderAndOrderDetailBO  extends SuperBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
