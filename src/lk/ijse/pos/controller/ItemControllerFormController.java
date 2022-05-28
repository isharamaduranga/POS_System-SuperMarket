package lk.ijse.pos.controller;

import animatefx.animation.Bounce;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemControllerBO;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.util.Utilities;
import lk.ijse.pos.view.TM.ItemTM;

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
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnDelete;

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadAllItems();

        new ZoomIn(tblItem).play();
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

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
        if(((JFXButton) mouseEvent.getSource()).getText().equals("ADD ITEM")){
            new Bounce(btnAdd).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("UPDATE ITEM")){
            new Bounce(btnUpdate).play();

        }else if(((JFXButton) mouseEvent.getSource()).getText().equals("SEARCH ITEM")){
            new Bounce(btnSearch).play();

        }else{
            new Bounce(btnDelete).play();
        }
    }
}
