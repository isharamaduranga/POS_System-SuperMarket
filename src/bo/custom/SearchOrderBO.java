package bo.custom;

import bo.SuperBO;
import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchOrderBO extends SuperBO {

    ArrayList<CustomDTO> searchOrderByOrderID(String oid) throws SQLException, ClassNotFoundException;
}
