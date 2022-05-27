package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.AddNewItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;

public class AddNewItemBOImpl implements AddNewItemBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemID(),
                dto.getItemDescription(),
                dto.getPackSize(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()));
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }


}
