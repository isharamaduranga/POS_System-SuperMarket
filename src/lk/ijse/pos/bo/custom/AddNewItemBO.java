package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;

public interface AddNewItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewItemCode() throws SQLException, ClassNotFoundException;

}
