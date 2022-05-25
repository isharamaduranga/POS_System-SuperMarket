package bo.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SearchItemBO {

    ResultSet searchItem(String id) throws SQLException, ClassNotFoundException;
}
