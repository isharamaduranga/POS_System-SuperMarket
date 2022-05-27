package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminMainBO extends SuperBO {
    ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

}
