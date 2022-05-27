package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
     ResultSet getOrderDetailsSearchByCustomerID(String id)throws SQLException, ClassNotFoundException;
}
