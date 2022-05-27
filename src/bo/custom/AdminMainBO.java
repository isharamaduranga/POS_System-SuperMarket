package bo.custom;

import model.ItemDTO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminMainBO {
    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

}
