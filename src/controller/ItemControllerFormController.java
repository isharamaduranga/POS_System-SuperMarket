package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.Utilities;
import view.TM.ItemTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemControllerFormController {

    public AnchorPane ItemContext;
    public TableView<ItemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadAllItems();
    }

    private void loadAllItems() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Item");

            while (rst.next()) {
                tblItem.getItems().add(new ItemTM(
                        rst.getString("ItemCode"),
                        rst.getString("Description"),
                        rst.getString("PackSize"),
                        rst.getDouble("UnitPrice"),
                        rst.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext,"AddNewItemForm");
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext,"UpdateItemForm");
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext,"SearchItemForm");
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext,"DeleteItemForm");
    }
}
