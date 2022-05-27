/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 2:09 PM
 * Year        : 2022
 */

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CashierMainBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierMainBOImpl implements CashierMainBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrderDetailsDAO orderDetailDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ResultSet getCustomerOrderFromOrderID(String oid) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getCustomerOrderItem(oid);
    }

    @Override
    public ResultSet getOrderDetailsFromCustomerID(String cid) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderDetailsSearchByCustomerID(cid);
    }

    @Override
    public ObservableList<String> getAllCustomerIDS() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

}
