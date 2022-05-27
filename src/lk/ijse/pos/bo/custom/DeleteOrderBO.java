package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DeleteOrderBO extends SuperBO {
    ResultSet searchOrder(String id) throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
}
