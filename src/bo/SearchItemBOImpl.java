package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemBOImpl {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    public ResultSet searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
}
