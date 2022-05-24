package dao.custom;

import dao.CrudDAO;
import model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO,String> {
     ResultSet getOrderDetailsSearchByCustomerID(String id)throws SQLException, ClassNotFoundException;
}
