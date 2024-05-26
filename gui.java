import java.time.Duration;

import javax.swing.border.LineBorder;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class gui extends Application{
    public static Scene currentScene;
    public static String currentTarget="00000";
    public static void gui_main(String[] args) {
        launch(args);
    }  
    public void start(Stage stage) throws Exception {

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);


        Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("owo");
        Scene scene = new Scene(root,500,450);
        currentScene = scene;
        stage.setScene(scene);

        stage.show();
    }
    public void init(){
        try{
            Main.sqls = new connectToSql();
            Main.server = new toServer(Main.ip,Main.port);
            System.out.println("connect to the server successfully.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessageTouser(String context){
        Platform.runLater(()->{
            
        });
    }
}
