package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.*;
import dao.custom.impl.*;
import db.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.OrderDTO;
import model.OrderDetailsDTO;
import view.TM.CartTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderFormController {

    public Label lblOrderID;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtaddress;
    public JFXTextField txtCity;

    public JFXComboBox<String> cmbItemID;
    public JFXTextField txtDiscription;
    public JFXTextField txtQTYOnHand;
    public JFXTextField txtUnitPrice;

    public TextField txtQTY;
    public TextField txtDiscount;

    public TableView<CartTM> tblCart;
    public TableColumn colItemID;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;

    public Label lblTotal;

    int cartSelectedRowCountForDelete = -1;

    /**
     * Dependency Injection
     */
    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    private final QueryDAO queryDAO = new QueryDAOImpl();

    public void initialize() {

        QueryDAO queryDAO = new QueryDAOImpl();


        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        curDateTime();
        autoId();

        try {
            loadCustomerIds();
            loadItemIds();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowCountForDelete = (int) newValue;
        });
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {


        ResultSet result = itemDAO.search(itemCode);

        if (result.next()) {
            txtDiscription.setText(result.getString(2));
            txtQTYOnHand.setText(result.getString(5));
            txtUnitPrice.setText(result.getString(4));
        }
    }

    private void setCustomerData(String customerID) throws SQLException, ClassNotFoundException {

        ResultSet result = customerDAO.search(customerID);

        if (result.next()) {
            txtName.setText(result.getString(3));
            txtaddress.setText(result.getString(4));
            txtCity.setText(result.getString(5));

        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {


        ObservableList<String> codes = itemDAO.getIds();
        cmbItemID.setItems(codes);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {


        ObservableList<String> ids = customerDAO.getIds();
        cmbCustomerID.setItems(ids);
    }

    private void curDateTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void autoId() {
        try {
            String s = orderDAO.generateNewId();
            lblOrderID.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowCountForDelete == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            list.remove(cartSelectedRowCountForDelete);

            calculateCost();
            tblCart.refresh();
        }
    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < list.size(); i++) {
            if (tm.getItemCode().equals(list.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }


    ObservableList<CartTM> list = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (!txtQTY.getText().equals("") && !txtDiscount.getText().equals("")) {

            String discription = txtDiscription.getText();
            int qtyOnHand = Integer.parseInt(txtQTYOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qtyForCustomer = Integer.parseInt(txtQTY.getText());
            double discount = Double.parseDouble(txtDiscount.getText());

            double calTot = (unitPrice * qtyForCustomer);
            Double total = calTot - ((calTot / 100) * discount);


            if (qtyOnHand < qtyForCustomer) {
                new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
                return;
            }

            CartTM tm = new CartTM(cmbItemID.getValue(), discription, qtyForCustomer, unitPrice, discount, total);

            int numberOfRow = isExists(tm);

            if (numberOfRow == -1) {
                list.add(tm);
            } else {
                CartTM temp = list.get(numberOfRow);
                CartTM newTm = new CartTM(
                        temp.getItemCode(),
                        temp.getDescription(),
                        temp.getQTY() + qtyForCustomer,
                        unitPrice,
                        discount,
                        total + temp.getTotal()
                );
                list.remove(numberOfRow);
                list.add(newTm);
            }
            tblCart.setItems(list);
            quntityChange();
            calculateCost();


        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong. Check Fields... ").show();
        }
    }

    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(itemCode, qty);
    }

    private void quntityChange() {
        int value = Integer.parseInt(txtQTYOnHand.getText());
        if (!txtQTY.getText().equals("") & (value > 0)) {
            int q = Integer.parseInt(txtQTY.getText());
            int q2 = Integer.parseInt(txtQTYOnHand.getText());
            int result = q2 - q;

            if (result <= 0) {
                new Alert(Alert.AlertType.WARNING, "Out Of Stock...!").show();
            } else {
                txtQTYOnHand.setText(String.valueOf(result));
            }
        }
    }


    private void calculateCost() {
        double total = 0;
        for (CartTM tm : list) {
            total += tm.getTotal();
        }
        lblTotal.setText(total + " /=");
    }

    public void ComfirmOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String s = lblOrderID.getText();
        OrderDTO order = new OrderDTO(
                s,
                lblDate.getText(),
                cmbCustomerID.getSelectionModel().getSelectedItem()
        );
        ArrayList<OrderDetailsDTO> details = new ArrayList<>();
        for (CartTM tm : list
        ) {
            details.add(
                    new OrderDetailsDTO(
                            s,
                            tm.getItemCode(),
                            tm.getQTY(),
                            tm.getDiscount(),
                            tm.getTotal()
                    )
            );
        }
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAO.save(order);

            if (isOrderSaved) {

                boolean isDetailsSaved = false;
                for (OrderDetailsDTO detail : details) {


                    isDetailsSaved = orderDetailsDAO.save(detail);

                    /**  quantity update */
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
        } catch (SQLException | ClassNotFoundException e) {
        } finally {
            connection.setAutoCommit(true);
        }
        autoId();
        lblTotal.setText("0.00 /=");
        cmbCustomerID.getSelectionModel().clearSelection();
        cmbItemID.getSelectionModel().clearSelection();
        tblCart.getItems().clear();
        txtQTY.clear();
        txtName.clear();
        txtaddress.clear();
        txtCity.clear();
        txtDiscription.clear();
        txtQTYOnHand.clear();
        txtUnitPrice.clear();
        txtDiscount.clear();
    }
}
