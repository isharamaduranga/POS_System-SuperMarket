package controller;

import com.jfoenix.controls.JFXTextField;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
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

    public void searchOrderDetaisOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String oid=txtOrderID.getText();
        QueryDAO queryDAO = new QueryDAOImpl();
        ArrayList<CustomDTO> record = queryDAO.searchOrderByCusID(oid);
        for (CustomDTO dto : record) {
            txtOrderDate.setText(dto.getOrderDate());
            txtItemCode.setText(dto.getItemCode());
            txtDiscount.setText(String.valueOf(dto.getDiscount()));
            txtCusID.setText(dto.getCusID());
            txtOrderQty.setText(String.valueOf(dto.getOrderQTY()));
            txtPrice.setText(String.valueOf(dto.getTotal()));
        }
        if (record.isEmpty()){
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
