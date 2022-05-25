package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemControllerBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
      return   itemDAO.getAll();
    }
}
