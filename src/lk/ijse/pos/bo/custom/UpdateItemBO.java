package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import javafx.collections.ObservableList;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UpdateItemBO extends SuperBO {

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllItemCodes() throws SQLException, ClassNotFoundException;

    boolean UpdateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
}
