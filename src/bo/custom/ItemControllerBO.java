package bo.custom;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemControllerBO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
