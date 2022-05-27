package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

}
