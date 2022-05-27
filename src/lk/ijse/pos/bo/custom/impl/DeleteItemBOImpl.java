package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DeleteItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteItemBOImpl implements DeleteItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
}
