package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.OrderDetails;
import javafx.collections.ObservableList;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetails>allOrderDetails = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order Details`");
        while (rst.next()) {
            allOrderDetails.add(new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
            ));
        }
        return allOrderDetails;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Order Details` VALUES(?,?,?,?,?)",
                entity.getOrderID(), entity.getItemCode(), entity.getOrderqty(), entity.getDiscount(), entity.getPrice());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("UPDATE `Order Details` SET  Orderqty=?, Discount=?,Price=? WHERE OrderID=?",
                entity.getOrderqty(), entity.getDiscount(), entity.getPrice(), entity.getOrderID());
    }

    @Override
    public ResultSet search(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM `Order Details` WHERE OrderID=?", s);
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
    public ResultSet getCustomerOrderItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * From `Order Details` WHERE OrderID=?",id);

   }
}
