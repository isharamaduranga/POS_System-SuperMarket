package bo;

import dao.custom.OrderDetailsDAO;
import dao.custom.impl.OrderDetailsDAOImpl;
import model.OrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateOrderBOImpl implements UpdateOrderBO {
    /**
     * Dependency Injection
     */
    private final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();

    @Override
    public ResultSet searchOrderDetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.search(id);
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(dto);
    }
}
