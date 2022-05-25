package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemBOImpl implements SearchItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ResultSet searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
}
