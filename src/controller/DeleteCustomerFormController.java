package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.Utilities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCustomerFormController {
    public JFXComboBox<String> cmdCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtTitle;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostCode;
    public AnchorPane deleteCustomerContext;

    public void initialize(){
        try {
            loadAllCustomerIds();

        } catch (SQLException |  ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmdCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerData(newValue);
        });
    }

    private void setCustomerData(String id) {
        try {

            ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE CusID=?", id);
            if (result.next()) {
                txtTitle.setText(result.getString(2));
                txtName.setText(result.getString(3));
                txtAddress.setText(result.getString(4));
                txtCity.setText(result.getString(5));
                txtProvince.setText(result.getString(6));
                txtPostCode.setText(result.getString(7));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadAllCustomerIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> ids= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        cmdCustomerID.setItems(ids);

    }

    public void clear() {
        cmdCustomerID.setValue("");
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostCode.clear();
    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try{

            if(CrudUtil.execute("DELETE FROM Customer WHERE CusID=?",cmdCustomerID.getValue())){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").showAndWait();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!").show();
            }
        }catch (ClassNotFoundException | SQLException |NullPointerException  e){
            e.printStackTrace();
        }
        clear();
        loadAllCustomerIds();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(deleteCustomerContext,"CustomerControllerForm");
    }
}
