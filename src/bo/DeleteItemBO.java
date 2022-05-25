package bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteItemBO {

    ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
}
