package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DeleteCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomerBOImpl implements DeleteCustomerBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ObservableList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

    @Override
    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }


}
