/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 10:24 AM
 * Year        : 2022
 */

package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.AdminMainBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminMainBOImpl implements AdminMainBO {
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetailsDTO>allOrderDetails=new ArrayList<>();
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        for (OrderDetails details : all) {
            allOrderDetails.add(new OrderDetailsDTO(details.getOrderID(),
                    details.getItemCode(),
                    details.getOrderqty(),
                    details.getDiscount(),
                    details.getPrice()));
        }
        return allOrderDetails;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO>allItems=new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
                    ));
        }
        return allItems;
    }
}
