package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemControllerBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
