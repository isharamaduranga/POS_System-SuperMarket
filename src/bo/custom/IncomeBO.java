package bo.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IncomeBO {

    ResultSet getOrderDetailsSearchByDate(String month, String year) throws SQLException, ClassNotFoundException;

    ResultSet getOrderDetailsSearchByMonthName(String monthName, String year) throws SQLException, ClassNotFoundException;

    ResultSet getTotalOfPrice() throws SQLException, ClassNotFoundException;
}
