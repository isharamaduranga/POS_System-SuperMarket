package bo.custom;

import bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchItemBO extends SuperBO {

    ResultSet searchItem(String id) throws SQLException, ClassNotFoundException;
}
