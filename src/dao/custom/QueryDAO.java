package dao.custom;

import dao.SuperDAO;
import model.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomDTO> searchOrderByCusID(String id) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByDate(String Month, String Year) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByMonthName(String Month, String Year) throws SQLException, ClassNotFoundException;

    ResultSet getTotalIncome() throws SQLException, ClassNotFoundException;
}
