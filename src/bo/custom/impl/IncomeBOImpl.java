package bo.custom.impl;

import bo.custom.IncomeBO;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeBOImpl implements IncomeBO {
    private final QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public ResultSet getOrderDetailsSearchByDate(String month, String year) throws SQLException, ClassNotFoundException {
        return queryDAO.getOrderDetailsSearchByDate(month, year);
    }

    @Override
    public ResultSet getOrderDetailsSearchByMonthName(String monthName,String year) throws SQLException, ClassNotFoundException {
        return queryDAO.getOrderDetailsSearchByMonthName(monthName,year);
    }

    @Override
    public ResultSet getTotalOfPrice() throws SQLException, ClassNotFoundException {
        return queryDAO.getTotalIncome();
    }

}
