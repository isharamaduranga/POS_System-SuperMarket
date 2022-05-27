package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderAndOrderDetailBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderAndOrderDetailBOImpl implements OrderAndOrderDetailBO {
    /**
     * Dependency Injection
     */
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDTO> allOrders= new ArrayList<>();
        ArrayList<Order> all = orderDAO.getAll();
        for (Order order : all) {
            allOrders.add(new OrderDTO(order.getOrderID(), order.getOrderDate(),order.getCusID()));
        }
        return allOrders;
    }

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> allOrderDetails= new ArrayList<>();
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        for (OrderDetails details : all) {
            allOrderDetails.add(new OrderDetailsDTO(details.getOrderID(),
                    details.getItemCode(),
                    details.getOrderqty(),
                    details.getDiscount(),
                    details.getPrice()

                    ));
        }
        return allOrderDetails;
    }
}
