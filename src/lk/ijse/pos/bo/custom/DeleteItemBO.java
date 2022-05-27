package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteItemBO extends SuperBO {

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
