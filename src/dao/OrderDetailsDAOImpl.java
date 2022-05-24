package dao;

import javafx.collections.ObservableList;
import model.OrderDetailsDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO{
    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetailsDTO>allOrderDetails = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order Details`");
        while (rst.next()) {
            allOrderDetails.add(new OrderDetailsDTO(
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
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Order Details` VALUES(?,?,?,?,?)",
                dto.getOrderID(), dto.getItemCode(), dto.getOrderQTY(), dto.getDiscount(), dto.getTotal());
    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("UPDATE `Order Details` SET  Orderqty=?, Discount=?,Price=? WHERE OrderID=?",
                dto.getOrderQTY(), dto.getDiscount(), dto.getTotal(), dto.getOrderID());
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
}
