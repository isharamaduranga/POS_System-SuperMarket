package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXButton adminBtn;
    public JFXButton cashierBtn;

    int attemptsLogAdmin = 0;
    int attemptsLogCashier = 0;

    public void LogInAdminOnAction(ActionEvent actionEvent) throws IOException {

        if (adminBtn.getText().equals("LOG IN ADMIN")) {

            attemptsLogAdmin++;
            if (attemptsLogAdmin < 5) {  // attempts calculate

                if (txtUserName.getText().equals("a") & pwdPassword.getText().equals("1")) {

                    String title = "Hello...!! Admin, Sign in Successfully";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(title);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                    Stage stage = (Stage) loginFormContext.getScene().getWindow();
                    stage.close();
                    URL resource = getClass().getResource("../view/Admin_Main_Form.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    stage1.centerOnScreen();
                    stage1.show();



                } else {
                    // error warning information

                    String tilte = "Sign In Admin";
                    String message = "Something Went Wrong  Check fields";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(3000));

                }

            } else {
                //  number of wrong input visible false option
                txtUserName.setVisible(false);
                pwdPassword.setVisible(false);
            }

        } else {
        }

        if (adminBtn.getText().equals("LOG IN CASHIER")) {

            attemptsLogCashier++;
            if (attemptsLogCashier < 5) {       // attempts calculate

                if (txtUserName.getText().equals("c") & pwdPassword.getText().equals("1")) {

                    String title = "Hello...!! Cashier Sign in Successfully";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(title);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                    // build new Scene

                    Stage stage = (Stage) loginFormContext.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Cashier_Main_Form.fxml"))));


                } else {
                    String tilte = "Sign In Cashier";
                    String message = "Something Went Wrong  Check fields";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(3000));
                }
            } else {  //  number of wrong input visible false option
                txtUserName.setVisible(false);
                pwdPassword.setVisible(false);
            }

        } else {
        }

    }

    public void LogInCashierOnAction(ActionEvent actionEvent) throws IOException {
        // cashier button catch that name and set admin get text
        String temp = adminBtn.getText();
        adminBtn.setText(cashierBtn.getText());
        cashierBtn.setText(temp);

    }

}
