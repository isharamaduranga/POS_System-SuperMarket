package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SearchCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomerBOImpl implements SearchCustomerBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }
}
