package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> searchOrderByCusID(String id) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByDate(String Month, String Year) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByMonthName(String Month, String Year) throws SQLException, ClassNotFoundException;

    ResultSet getTotalIncome() throws SQLException, ClassNotFoundException;
}
