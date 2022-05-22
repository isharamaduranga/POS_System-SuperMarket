package controller;

import com.jfoenix.controls.JFXTextField;
import dao.ItemDAOImpl;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemFormController {
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtCode;
    public AnchorPane searchItemContext;

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            String code =txtCode.getText();

            ItemDAOImpl itemDAO = new ItemDAOImpl();
            ResultSet result = itemDAO.searchItem(code);

            if (result.next()) {
                txtDescription.setText(result.getString(2));
                txtPackSize.setText(result.getString(3));
                txtUnitPrice.setText(String.valueOf(result.getDouble(4)));
                txtQtyOnHand.setText(String.valueOf(result.getInt(5)));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(searchItemContext,"ItemControllerForm");
    }
}
