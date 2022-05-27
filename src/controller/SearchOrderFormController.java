package controller;

import bo.BOFactory;
import bo.custom.SearchOrderBO;
import bo.custom.impl.SearchOrderBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.input.KeyEvent;
import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderFormController {
    public JFXTextField txtOrderID;
    public JFXTextField txtOrderDate;
    public JFXTextField txtItemCode;
    public JFXTextField txtDiscount;
    public JFXTextField txtCusID;
    public JFXTextField txtOrderQty;
    public JFXTextField txtPrice;

    /**
     * Apply Dependency Injection(Property)
     */
    private final SearchOrderBO searchOrderBO = (SearchOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SEARCH_ORDER);


    public void searchOrderDetaisOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String oid = txtOrderID.getText();

        ArrayList<CustomDTO> record = searchOrderBO.searchOrderByOrderID(oid);

        for (CustomDTO dto : record) {
            txtOrderDate.setText(dto.getOrderDate());
            txtItemCode.setText(dto.getItemCode());
            txtDiscount.setText(String.valueOf(dto.getDiscount()));
            txtCusID.setText(dto.getCusID());
            txtOrderQty.setText(String.valueOf(dto.getOrderQTY()));
            txtPrice.setText(String.valueOf(dto.getTotal()));
        }
        if (record.isEmpty()) {
            clear();
        }
    }

    public void clear() {

        txtOrderDate.clear();
        txtItemCode.clear();
        txtDiscount.clear();
        txtCusID.clear();
        txtOrderQty.clear();
        txtPrice.clear();
    }
}
