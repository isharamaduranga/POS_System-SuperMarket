package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import model.ItemDTO;

import java.sql.SQLException;

public class AddNewItemBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
       return itemDAO.save(dto);
    }

    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
      return itemDAO.generateNewId();
    }



}
