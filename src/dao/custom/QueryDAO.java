package dao.custom;

import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO {
   ArrayList<CustomDTO> searchOrderByCusID(String id)throws SQLException,ClassNotFoundException;
}
