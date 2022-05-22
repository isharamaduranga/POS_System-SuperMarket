package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> allItems = new ArrayList<>();

        while (rst.next()) {
            allItems.add(new ItemDTO(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        return allItems;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item (ItemCode,Description,PackSize,UnitPrice,QtyOnHand) VALUES (?,?,?,?,?)",
                dto.getItemID(), dto.getItemDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand());
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE ItemCode=?", code);
    }

    @Override
    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Item WHERE ItemCode=?", code);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?",
                dto.getItemDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getItemID());
    }

    @Override
    public ObservableList<String> getItemsCode() throws SQLException, ClassNotFoundException {
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


}
