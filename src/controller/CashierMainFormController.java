package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Utilities;

import java.io.IOException;
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

    public void initialize() {
        curDateTime();
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

        Utilities.leftTransition("OrderAndOrderDetailsTableForm",btnDetailsMenu,dashBoardContext);

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


}
