package controller;

import bo.BOFactory;
import bo.custom.PurchaseOrderBO;
import bo.custom.impl.PurchaseOrderBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
     * Apply Dependency Injection(Property)
     */
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASE_ORDER);


    public void initialize() {


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
            setCustomerData(newValue);
        });

        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemData(newValue);
        });
        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowCountForDelete = (int) newValue;
        });
    }

    private void setItemData(String itemCode) {

        try {

            ResultSet result = purchaseOrderBO.searchItem(itemCode);


            if (result.next()) {
                txtDiscription.setText(result.getString(2));
                txtQTYOnHand.setText(result.getString(5));
                txtUnitPrice.setText(result.getString(4));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerData(String customerID) {
        try {

            ResultSet result = purchaseOrderBO.searchCustomer(customerID);

            if (result.next()) {
                txtName.setText(result.getString(3));
                txtaddress.setText(result.getString(4));
                txtCity.setText(result.getString(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<String> codes = purchaseOrderBO.getItemIds();
            cmbItemID.setItems(codes);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        try {

            ObservableList<String> ids = purchaseOrderBO.getCustomerIds();
            cmbCustomerID.setItems(ids);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

            String s = purchaseOrderBO.generateNewOrderID();

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

    public void ComfirmOrderOnAction(ActionEvent actionEvent) {


        String s = lblOrderID.getText();
        OrderDTO order = new OrderDTO(s, lblDate.getText(), cmbCustomerID.getSelectionModel().getSelectedItem()
        );

        ArrayList<OrderDetailsDTO> details = new ArrayList<>();
        for (CartTM tm : list) {
            details.add(new OrderDetailsDTO(s, tm.getItemCode(), tm.getQTY(), tm.getDiscount(), tm.getTotal()));
        }

        try {
            purchaseOrderBO.purchaseOrder(order, details);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
