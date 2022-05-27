package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchItemBO extends SuperBO {

    ResultSet searchItem(String id) throws SQLException, ClassNotFoundException;
}
