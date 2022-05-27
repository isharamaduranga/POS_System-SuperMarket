package bo.custom;

import bo.SuperBO;
import model.ItemDTO;

import java.sql.SQLException;

public interface AddNewItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewItemCode() throws SQLException, ClassNotFoundException;

}
