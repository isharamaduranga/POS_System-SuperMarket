package controller;

import bo.BOFactory;
import bo.custom.CashierMainBO;
import bo.custom.impl.CashierMainBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Utilities;
import view.TM.CustomerOrderDetails;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CashierMainFormController {
    public AnchorPane CashierDashboardContext;
    public AnchorPane dashBoardContext;
    public Label lblDate;
    public Label lblDay;
    public Label lblTime;
    public JFXButton btnDetailsMenu;
    public TableView<CustomerOrderDetails> tblItemDetails;
    public JFXComboBox<String> cmbCustomerID;
    public TableColumn colItems;
    public TableColumn colQTY;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField TxtDate;
    public JFXTextField txtOrderID;
    public Label lblTotalprice;

        private final CashierMainBO cashierMainBO = (CashierMainBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CASHIER_MAIN);

    public void initialize() {
        colItems.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            loadCustomerIds();

        } catch (SQLException |ClassNotFoundException  e) {
            e.printStackTrace();
        }


        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        curDateTime();curDateTime();
    }

    private void setCustomerData(String cid) throws SQLException, ClassNotFoundException {

        ResultSet search = cashierMainBO.searchCustomer(cid);

        if (search.next()) {
            txtCusName.setText(search.getString(3));
            txtCusAddress.setText(search.getString(4));
        }
        setItems();
        loadorder();
    }
    private void setItems() throws SQLException, ClassNotFoundException{
        double price=0;
        ResultSet item = cashierMainBO.getCustomerOrderFromOrderID(txtOrderID.getText());
        ObservableList<CustomerOrderDetails> obList= FXCollections.observableArrayList();

        while (item.next()) {
            price+=item.getDouble(5);
            obList.add(new CustomerOrderDetails(
                    item.getString(2),
                    item.getInt(3)
            ));
        }
        tblItemDetails.setItems(obList);
        lblTotalprice.setText(String.valueOf(price));

    }
        public void loadorder() throws SQLException, ClassNotFoundException {
            ResultSet rst = cashierMainBO.getOrderDetailsFromCustomerID(cmbCustomerID.getValue());

        if(rst.next()){
            txtOrderID.setText(rst.getString(1));
            TxtDate.setText(rst.getString(2));

            setItems();
        }else if(!rst.next()){

            tblItemDetails.getItems().clear();
            txtOrderID.clear();
            lblTotalprice.setText("0.00");
            TxtDate.clear();
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> ids = cashierMainBO.getAllCustomerIDS();
        cmbCustomerID.setItems(ids);
    }

    private void curDateTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void ViewOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {

        Utilities.leftTransition("OrderAndOrderDetailsTableForm", btnDetailsMenu, dashBoardContext);

    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.close(dashBoardContext);
        Utilities.setUiNew("Login_Form", "Log In Form");
    }

    public void shutDownOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Cashier_Main_Form.fxml"))));
    }

    public void manageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "CustomerTableForCashierForm");
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "AddNewCustomerForm");
    }

    public void updateOrderOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "UpdateOrderForm");
    }

    public void deleteOrderOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "DeleteOrderForm");
    }

    public void PlaceOrder(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "PlaceOrderForm");
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "PlaceOrderForm");
    }

    public void orderTabels(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "OrderAndOrderDetailsTableForm");
    }

    public void searchOrderOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "SearchOrderForm");
    }
}
