package bo;

import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteOrderBOImpl {
    /**
     * Dependency Injection
     */
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public ResultSet searchOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

}
