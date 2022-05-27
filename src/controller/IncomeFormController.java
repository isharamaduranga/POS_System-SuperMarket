package controller;

import bo.custom.IncomeBO;
import bo.custom.impl.IncomeBOImpl;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomDTO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncomeFormController {
    public TableView<CustomDTO> tblIncomes;
    public TableColumn colOid;
    public TableColumn colDate;
    public TableColumn colAmount;
    public Label lblTotal;
    public JFXComboBox monthLoadCombo;
    public Label lblMonthIncome;
    public Label lblTotalIncome;

    /**
     * Dependency Injection
     */
    private final IncomeBO incomeBO = new IncomeBOImpl();

    public void initialize(){
        colOid.setCellValueFactory(new PropertyValueFactory("orderID"));
        colDate.setCellValueFactory(new PropertyValueFactory("orderDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory("total"));

        loadAllIncome();
        comboLoad();
        calulateTotalIncome();

    }

    private void calulateTotalIncome() {
        try {

            ResultSet result = incomeBO.getTotalOfPrice();

            result.next();

            String s = result.getString(1);

            lblTotalIncome.setText(s);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    String Month= new SimpleDateFormat("MM").format(new Date());
    String Year=new SimpleDateFormat("yyyy").format(new Date());

    private void comboLoad() {
        ObservableList<String> obList= FXCollections.observableArrayList();
        obList.add("January");
        obList.add("February");
        obList.add("March");
        obList.add("April");
        obList.add("May");
        obList.add("June");
        obList.add("July");
        obList.add("August");
        obList.add("September");
        obList.add("October");
        obList.add("November");
        obList.add("December");
        monthLoadCombo.setItems(obList);
    }


    private void loadAllIncome()  {
        try {

            ResultSet result = incomeBO.getOrderDetailsSearchByDate(Month, Year);
            totalCharge(result);

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    }


    private void totalCharge(ResultSet result) throws SQLException {
        ObservableList<CustomDTO> income = FXCollections.observableArrayList();

        while (result.next()) {
            income.add(
                    new CustomDTO(
                            result.getString(1),
                            result.getString(2),
                            result.getDouble(3)
                    ));
        }
        tblIncomes.setItems(income);

        calMonthTotal(result);
    }


    private void calMonthTotal(ResultSet result) throws SQLException {
        double total=0;
        for (CustomDTO d: tblIncomes.getItems()
             ) {
            total+=d.getTotal();
        }
        lblMonthIncome.setText(String.valueOf(total));
    }

    public void ComboOnActionSelectMonth(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String value = String.valueOf(monthLoadCombo.getValue());

        ResultSet result = incomeBO.getOrderDetailsSearchByMonthName(value,Year);
        totalCharge(result);
    }
}
