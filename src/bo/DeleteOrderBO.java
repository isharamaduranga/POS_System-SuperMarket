package bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteOrderBO {
    ResultSet searchOrder(String id) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
}
