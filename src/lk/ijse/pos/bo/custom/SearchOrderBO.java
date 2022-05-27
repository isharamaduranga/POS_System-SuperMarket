package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchOrderBO extends SuperBO {

    ArrayList<CustomDTO> searchOrderByOrderID(String oid) throws SQLException, ClassNotFoundException;
}
