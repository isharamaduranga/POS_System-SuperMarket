package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SearchItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;

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
