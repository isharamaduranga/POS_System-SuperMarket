package controller;

import bo.AddNewItemBO;
import bo.AddNewItemBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import util.Utilities;

import java.io.IOException;
import java.sql.SQLException;

public class AddNewItemFormController {
    public AnchorPane addItemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;

    /**
     * Apply Dependency Injection(Property)
     */
    private final AddNewItemBO addNewItemBO = new AddNewItemBOImpl();

    public void initialize() {
        autoId();
    }


    public void addItemOnAction(ActionEvent actionEvent) {


        String code = txtCode.getText();
        String description = txtDescription.getText();
        String size = txtPackSize.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQtyOnHand.getText());


        ItemDTO dto = new ItemDTO(
                txtCode.getText(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText())
        );
        try {

            if (addNewItemBO.saveItem(dto)) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved...").showAndWait();

            } else {
                new Alert(Alert.AlertType.WARNING, "Item Already exists !!!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Item " + e.getMessage()).show();
        }


        clear();
    }

    public void autoId() {
        try {
            String s = addNewItemBO.generateNewItemCode();
            txtCode.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        txtCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        autoId();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(addItemContext, "ItemControllerForm");
    }
}
