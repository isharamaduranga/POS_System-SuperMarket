package bo;

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

public class PurchaseOrderBOImpl {
    /**
     * Dependency Injection
     */
    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    private final QueryDAO queryDAO = new QueryDAOImpl();

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

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    public ObservableList<String> getItemIds() throws SQLException, ClassNotFoundException {
       return itemDAO.getIds();
    }

    public ObservableList<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }
    public boolean updateQty(String itemCode ,int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(itemCode, qty);
    }
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
       return orderDAO.generateNewId();
    }
}
