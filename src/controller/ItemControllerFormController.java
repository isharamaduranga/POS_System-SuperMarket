package controller;

import bo.BOFactory;
import bo.custom.ItemControllerBO;
import bo.custom.impl.ItemControllerBOImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import util.Utilities;
import view.TM.ItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemControllerFormController {

    public AnchorPane ItemContext;
    public TableView<ItemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;


    /**
     * Apply Dependency Injection(Property)
     */
    private final ItemControllerBO itemControllerBO = (ItemControllerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM_CONTROLLER);

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadAllItems();
    }

    private void loadAllItems() {
        try {
            ArrayList<ItemDTO> allItems = itemControllerBO.getAllItems();

            for (ItemDTO item : allItems) {
                tblItem.getItems().add(new ItemTM(
                        item.getItemID(),
                        item.getItemDescription(),
                        item.getPackSize(),
                        item.getUnitPrice(),
                        item.getQtyOnHand()
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext, "AddNewItemForm");
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext, "UpdateItemForm");
    }

    public void searchItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext, "SearchItemForm");
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(ItemContext, "DeleteItemForm");
    }
}
