package bo.custom;

import model.ItemDTO;

import java.sql.SQLException;

public interface AddNewItemBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewItemCode() throws SQLException, ClassNotFoundException;

}
