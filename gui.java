import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class gui extends Application{
    public static void gui_main(String[] args) {
        launch();
    }  
    public void start(Stage stage) throws Exception {
        Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("owo");
        stage.setScene(new Scene(root,500,450));
        stage.show();
    }
}
