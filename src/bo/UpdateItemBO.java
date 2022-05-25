package bo;

import javafx.collections.ObservableList;
import model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateItemBO {

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllItemCodes() throws SQLException, ClassNotFoundException;

    boolean UpdateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
}
