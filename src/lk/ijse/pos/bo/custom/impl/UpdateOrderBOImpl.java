package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UpdateOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateOrderBOImpl implements UpdateOrderBO {
    /**
     * Dependency Injection
     */
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ResultSet searchOrderDetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.search(id);
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.update(new OrderDetails(dto.getOrderID(),
                dto.getItemCode(),
                dto.getOrderQTY(),
                dto.getDiscount(),
                dto.getTotal()
        ));
    }
}
