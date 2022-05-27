package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DeleteOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrderBOImpl implements DeleteOrderBO {
    /**
     * Dependency Injection
     */
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ResultSet searchOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

}
