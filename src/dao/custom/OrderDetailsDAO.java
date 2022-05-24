package dao.custom;

import dao.CrudDAO;
import model.OrderDetailsDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetailsDTO,String> {
     ResultSet getCustomerOrderItem(String id) throws SQLException, ClassNotFoundException;
}

