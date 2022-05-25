package bo.custom.impl;

import bo.custom.OrderAndOrderDetailBO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;
import model.OrderDTO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderAndOrderDetailBOImpl implements OrderAndOrderDetailBO {
    /**
     * Dependency Injection
     */
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.getAll();
    }
}
