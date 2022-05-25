package bo;

import model.OrderDTO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderAndOrderDetailBO {

    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;
}
