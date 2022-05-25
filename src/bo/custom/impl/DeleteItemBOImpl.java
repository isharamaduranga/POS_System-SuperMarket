package bo.custom.impl;

import bo.custom.DeleteItemBO;
import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteItemBOImpl implements DeleteItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
}
