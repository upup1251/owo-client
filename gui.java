import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class gui extends Application{
    public static Scene currentScene;
    public static void gui_main(String[] args) {
        launch(args);
    }  
    public void start(Stage stage) throws Exception {
        Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("owo");
        Scene scene = new Scene(root,500,450);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        currentScene = scene;
        stage.setScene(scene);

        stage.show();
    }
    public void init(){
        try{
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
