package dao;

import model.ItemDTO;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemDTO,String>{

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
