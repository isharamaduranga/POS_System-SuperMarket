import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/Login_Form.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
