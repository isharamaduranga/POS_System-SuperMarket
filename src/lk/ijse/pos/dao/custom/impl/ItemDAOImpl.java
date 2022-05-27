package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();

        while (rst.next()) {
            allItems.add(new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        return allItems;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item (ItemCode,Description,PackSize,UnitPrice,QtyOnHand) VALUES (?,?,?,?,?)",
                entity.getItemCode(), entity.getDescription(), entity.getPackSize(), entity.getUnitPrice(), entity.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE ItemCode=?", code);
    }

    @Override
    public ResultSet search(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Item WHERE ItemCode=?", code);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?",
                entity.getDescription(), entity.getPackSize(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getItemCode());
    }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> codes = FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            codes.add(rst.getString(1));
        }
        return codes;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1");

        if (result.next()) {

            String rnno = result.getString("ItemCode");
            int co = rnno.length();
            String txt = rnno.substring(0, 2);//mul deka  (II)
            String num = rnno.substring(2, co);//aga deaka (1000)

            int n = Integer.parseInt(num);
            n++;
            String snum = Integer.toString(n);
            String newId = txt + snum;
            return newId;
        } else {
            return "II1000";
        }
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET QtyOnHand = QtyOnHand -? WHERE ItemCode=?", qty, itemCode);
    }
}


