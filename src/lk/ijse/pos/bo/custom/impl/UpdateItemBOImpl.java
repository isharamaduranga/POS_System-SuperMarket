package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UpdateItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import javafx.collections.ObservableList;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItemBOImpl implements UpdateItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

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
        return itemDAO.update(new Item(dto.getItemID(),
                dto.getItemDescription(),
                dto.getPackSize(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()));
    }
}
