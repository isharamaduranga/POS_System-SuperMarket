package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemControllerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemControllerBOImpl implements ItemControllerBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> AllItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            AllItems.add(new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            ));
        }
        return AllItems;
    }
}
