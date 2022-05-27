package bo.custom.impl;

import bo.custom.SearchItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemBOImpl implements SearchItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ResultSet searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
}
