package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CrudUtil;
import view.TM.OrderDetailsTM;
import view.TM.OrderTM;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void initialize(){
        try {
            cmbSelectTable.getItems().addAll("Order","Order Details");
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

        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order`");
        while (rst.next()) {
            tblOrder.getItems().add(new OrderTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)));
        }
        tblOrder.refresh();
    }

    private void loadAllOrderDetails() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order Details`");
        while (rst.next()) {
            tblOrderDetails.getItems().add(new OrderDetailsTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getDouble(5)
                    ));
        }
    }

    public void selectTabelOnAction(ActionEvent actionEvent) {
        if(cmbSelectTable.getValue().equals("Order Details")){
            tblOrder.setVisible(false);
            tblOrderDetails.setVisible(true);
        }else{
            tblOrder.setVisible(true);
            tblOrderDetails.setVisible(false);
        }
    }
}
