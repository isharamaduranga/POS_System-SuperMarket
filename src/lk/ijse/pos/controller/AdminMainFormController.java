package lk.ijse.pos.controller;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.AdminMainBO;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.util.Utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class AdminMainFormController {
    public AnchorPane dashBoardContext;
    public JFXButton btnCustomerMenu;
    public JFXButton btnItemMenu;
    public JFXButton btnHomeMenu;
    public JFXButton btnLogOut;
    public Label lblDate;
    public Label lblDay;
    public Label lblTime;
    public LineChart lineChartAccess;
    public PieChart mostOrderItemPieChart;

    /**
     * Dependency Injection
     */

    private final AdminMainBO adminMainBO = (AdminMainBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN_MAIN);


    public void initialize() {

        try {
            initLineChart();
            curDateTime();
            initPieChart();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        lineChartAccess.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

    }

    private void initPieChart() {
        ObservableList<PieChart.Data> productData = FXCollections.observableArrayList();

        try {

            ArrayList<OrderDetailsDTO> allOrderDetails = adminMainBO.getAllOrderDetails();

            for (OrderDetailsDTO allOrderDetail : allOrderDetails) {
                String code = allOrderDetail.getItemCode();
                int qty = allOrderDetail.getOrderQTY();

                productData.add(new PieChart.Data(code, qty));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        productData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName(), " ", data.pieValueProperty())
                )
        );
        mostOrderItemPieChart.setData(productData);
        mostOrderItemPieChart.setTitle("M O S T   S A L E   I T E M S   P R O G R E S S");

    }

    private void initLineChart() throws SQLException, ClassNotFoundException {
        XYChart.Series seriesRe = new XYChart.Series();
        seriesRe.setName("I T E M   W I S E   P R O G R E S S");


        ArrayList<ItemDTO> allItems = adminMainBO.getAllItem();

        for (ItemDTO allItem : allItems) {
            String code = allItem.getItemID();
            int qty = allItem.getQtyOnHand();
            seriesRe.getData().add(new XYChart.Data(code, qty));
        }
        lineChartAccess.getData().add(seriesRe);
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

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Admin_Main_Form.fxml"))));
    }

    public void manageItemsOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "ItemControllerForm");
    }

    public void manageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "CustomerControllerForm");
    }

    public void displayIncomeOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext, "IncomeForm");
    }

    public void customerListOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.leftTransition("CustomerControllerForm", btnCustomerMenu, dashBoardContext);
    }

    public void ItemListOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.leftTransition("ItemControllerForm", btnItemMenu, dashBoardContext);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.close(dashBoardContext);
        Utilities.setUiNew("Login_Form", "Log In Form");
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }


}
