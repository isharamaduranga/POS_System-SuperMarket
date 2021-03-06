package lk.ijse.pos.controller;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderAndOrderDetailBO;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.view.TM.OrderDetailsTM;
import lk.ijse.pos.view.TM.OrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderAndOrderDetailsTableFormController {
    public TableView<OrderTM> tblOrder;
    public TableColumn colOrderTemp;
    public TableColumn colOrderDate;
    public TableColumn colCustId;
    public ComboBox cmbSelectTable;
    public TableView<OrderDetailsTM> tblOrderDetails;
    public TableColumn colOrderID2;
    public TableColumn colItemCode2;
    public TableColumn colQTY;
    public TableColumn colDisCount;
    public TableColumn colPrice;

    /**
     * Apply Dependency Injection(Property)
     */
    private final OrderAndOrderDetailBO orderAndOrderDetailBO = (OrderAndOrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_AND_ORDER_DETAIL);

    public void initialize() {
        try {
            cmbSelectTable.getItems().addAll("Order", "Order Details");
            cmbSelectTable.setValue("Order");

            colOrderTemp.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
            colCustId.setCellValueFactory(new PropertyValueFactory<>("cusID"));

            colOrderID2.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colItemCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("orderQTY"));
            colDisCount.setCellValueFactory(new PropertyValueFactory<>("discoUnt"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("total"));

            loadAllOrder();
            loadAllOrderDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllOrder() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDTO> allOrder = orderAndOrderDetailBO.getAllOrder();

        for (OrderDTO order : allOrder) {
            tblOrder.getItems().add(new OrderTM(
                    order.getOrderID(),
                    order.getOrderDate(),
                    order.getCusID()
            ));
        }
        tblOrder.refresh();
    }

    private void loadAllOrderDetails() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetailsDTO> allOrderDetails = orderAndOrderDetailBO.getAllOrderDetails();

        for (OrderDetailsDTO Details : allOrderDetails) {
            tblOrderDetails.getItems().add(new OrderDetailsTM(
                    Details.getOrderID(),
                    Details.getItemCode(),
                    Details.getOrderQTY(),
                    Details.getDiscount(),
                    Details.getTotal())
            );
        }
        tblOrderDetails.refresh();
    }

    public void selectTabelOnAction(ActionEvent actionEvent) {
        if (cmbSelectTable.getValue().equals("Order Details")) {
            tblOrder.setVisible(false);
            tblOrderDetails.setVisible(true);
        } else {
            tblOrder.setVisible(true);
            tblOrderDetails.setVisible(false);
        }
    }
}
