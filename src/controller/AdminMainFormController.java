package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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


public class AdminMainFormController {

    public AnchorPane dashBoardContext;
    public JFXButton btnCustomerMenu;
    public JFXButton btnItemMenu;
    public JFXButton btnHomeMenu;
    public JFXButton btnLogOut;
    public Label lblDate;
    public Label lblDay;
    public Label lblTime;
    public BarChart barChartItem;
    public Label lblCustomers;
    public Label lblItems;

    public void initialize(){
        curDateTime();

    }

    private void curDateTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Admin_Main_Form.fxml"))));
    }

    public void manageItemsOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext,"ItemControllerForm");
    }

    public void manageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.setUiChildren(dashBoardContext,"CustomerControllerForm");
    }

    public void customerListOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.leftTransition("CustomerControllerForm",btnCustomerMenu,dashBoardContext);
    }

    public void ItemListOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.leftTransition("ItemControllerForm",btnItemMenu,dashBoardContext);
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Utilities.close(dashBoardContext);
        Utilities.setUiNew("Login_Form","Log In Form");
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
