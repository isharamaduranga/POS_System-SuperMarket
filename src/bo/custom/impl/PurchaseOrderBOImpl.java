package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import dao.custom.impl.*;
import db.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.OrderDTO;
import model.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    /**
     * Dependency Injection
     */
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    @Override
    public void purchaseOrder(OrderDTO order, ArrayList<OrderDetailsDTO> details) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        boolean isOrderSaved = orderDAO.save(order);

        if (isOrderSaved) {

            boolean isDetailsSaved = false;
            for (OrderDetailsDTO detail : details) {


                isDetailsSaved = orderDetailsDAO.save(detail);

                //**  quantity update *//*
                updateQty(detail.getItemCode(), detail.getOrderQTY());
            }

            if (isDetailsSaved) {
                connection.commit();
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").showAndWait();
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Error...!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Error...!").show();
        }
        connection.setAutoCommit(true);
    }

    @Override
    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getIds();
    }

    @Override
    public ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(itemCode, qty);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }
}
