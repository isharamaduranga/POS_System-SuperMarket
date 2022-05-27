package bo.custom.impl;

import bo.custom.DeleteOrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;

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
