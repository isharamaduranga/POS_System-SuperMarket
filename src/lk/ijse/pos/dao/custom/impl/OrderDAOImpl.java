package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.entity.Order;
import javafx.collections.ObservableList;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order`");
        ArrayList<Order> allOrder = new ArrayList<>();
        while (rst.next()) {

            allOrder.add(new Order(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return allOrder;

    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("INSERT INTO `Order` values(?,?,?)", entity.getOrderID(),
              entity.getOrderDate(), entity.getCusID());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet search(String oid) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("SELECT * FROM `Order` WHERE OrderID=?",oid);
    }

    @Override
    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("DELETE FROM `Order` WHERE OrderID=?",oid);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1");

        if (result.next()) {
            String rnno = result.getString("OrderID");
            int co = rnno.length();
            String txt = rnno.substring(0, 2);//mul deka  (CI)
            String num = rnno.substring(2, co);//aga deaka (1000)

            int n = Integer.parseInt(num);
            n++;
            String snum = Integer.toString(n);
            String NewId = txt + snum;
            return NewId;
        } else {
            return "OI1000";
        }
    }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet getOrderDetailsSearchByCustomerID(String id)throws SQLException, ClassNotFoundException{
        return CrudUtil.execute("SELECT * FROM `Order` WHERE CusID=?", id);
    }

}
