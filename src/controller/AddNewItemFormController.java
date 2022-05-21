package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.ItemDTO;
import util.CrudUtil;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddNewItemFormController {
    public AnchorPane addItemContext;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;


    public void initialize() {
        autoId();
    }


    public void addItemOnAction(ActionEvent actionEvent) {


        String code = txtCode.getText();
        String description = txtDescription.getText();
        String size = txtPackSize.getText();
        double price= Double.parseDouble(txtUnitPrice.getText());
        int qty= Integer.parseInt(txtQtyOnHand.getText());


        ItemDTO dto = new ItemDTO(
                txtCode.getText(), txtDescription.getText(), txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText())
        );



                    try {

                        if (CrudUtil.execute("INSERT INTO Item (ItemCode,Description,PackSize,UnitPrice,QtyOnHand) VALUES (?,?,?,?,?)",
                                dto.getItemID(), dto.getItemDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand())) {


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
            ResultSet result = CrudUtil.execute("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1");

            if (result.next()) {

                String rnno = result.getString("ItemCode");
                int co = rnno.length();
                String txt = rnno.substring(0, 2);//mul deka  (II)
                String num = rnno.substring(2, co);//aga deaka (1000)

                int n = Integer.parseInt(num);
                n++;
                String snum = Integer.toString(n);
                String ftxt = txt + snum;
                txtCode.setText(ftxt);
            } else {
                txtCode.setText("II1000");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
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
