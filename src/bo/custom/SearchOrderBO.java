package bo.custom;

import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchOrderBO {

    ArrayList<CustomDTO> searchOrderByOrderID(String oid) throws SQLException, ClassNotFoundException;
}
