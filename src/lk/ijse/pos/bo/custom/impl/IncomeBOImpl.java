package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.IncomeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeBOImpl implements IncomeBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

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
