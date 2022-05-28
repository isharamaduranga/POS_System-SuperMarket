package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
     ResultSet getCustomerOrderItem(String id) throws SQLException, ClassNotFoundException;


}

