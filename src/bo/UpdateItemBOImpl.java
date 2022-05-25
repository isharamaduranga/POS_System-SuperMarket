package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import javafx.collections.ObservableList;
import model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItemBOImpl implements UpdateItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public ObservableList<String> getAllItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getIds();
    }

    @Override
    public boolean UpdateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }
}
