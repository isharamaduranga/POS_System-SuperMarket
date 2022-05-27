package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IncomeBO extends SuperBO {

    ResultSet getOrderDetailsSearchByDate(String month, String year) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByMonthName(String monthName, String year) throws SQLException, ClassNotFoundException;

    ResultSet getTotalOfPrice() throws SQLException, ClassNotFoundException;
}
