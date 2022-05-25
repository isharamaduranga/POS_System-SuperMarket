package bo;

import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrderBOImpl implements DeleteOrderBO {
    /**
     * Dependency Injection
     */
    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public ResultSet searchOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

}
