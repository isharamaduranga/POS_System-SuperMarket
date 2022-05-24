package dao.custom;

import dao.CrudDAO;
import model.ItemDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemDTO,String> {

    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

}
