/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 10:24 AM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.AdminMainBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;
import model.ItemDTO;
import model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminMainBOImpl implements AdminMainBO {
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {




        return orderDetailsDAO.getAll();
    }
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
