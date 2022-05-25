package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteItemBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
}
