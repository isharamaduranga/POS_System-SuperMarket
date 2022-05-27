package lk.ijse.pos.util;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Utilities {
    /**
     *
     * @param ap
     * @param location
     * @param title
     * @throws IOException
     */
    public void setUi(AnchorPane ap, String location, String title) throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Set Form on to a specific AP
     * @param ap --> replacing anchorpane
     * @param location --> Form which want to add
     * @throws IOException
     */
    public static void setUiChildren(AnchorPane ap,String location) throws IOException {
        ap.getChildren().clear();
        Parent parent = FXMLLoader.load(Utilities.class.getResource("../view/"+location+".fxml"));
        ap.getChildren().add(parent);
    }

    /**
     * Make a new window and set a fxml file
     * @param location --> the form which want to set
     * @param title --> the title name of new window
     * @throws IOException
     */
    public static void setUiNew(String location, String title) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(Utilities.class.getResource("../view/"+location+".fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.show();
    }

    /** Define the Function of Left Transition and Load UI */
    public static void leftTransition(String location, JFXButton name, AnchorPane anchorPane) throws IOException {
        anchorPane.getChildren().clear();
        Parent root = FXMLLoader.load(Utilities.class.getResource("../view/"+location+".fxml"));
        Scene scene = name.getScene();


        root.translateXProperty().set(scene.getWidth());

        anchorPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.50), kv);
        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(event -> {

        });
        timeline.play();
    }


    /**
     * close the stage
     * @param ap --> The anchorpane which we want to close
     */
    public static void  close(AnchorPane ap){
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public static void clearFields(TextField...param){
        for (TextField txt : param) {
            txt.setText("");
        }
    }

    public static void clearCmb(ComboBox...param){
        for (ComboBox cmb : param) {
            cmb.getSelectionModel().select(-1);
        }
    }

    public static void clearLabels(Label...param){
        for (Label lbl : param) {
            lbl.setText("");
        }
    }

}
