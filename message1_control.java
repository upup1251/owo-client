import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class message1_control {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button button_avatar;

    @FXML
    private Button button_exit;

    @FXML
    private Button button_top;
    
    @FXML
    private Button button_online;

    @FXML
    private ImageView button_send;

    @FXML
    private Button button_suoXiao;

    @FXML
    private Label label_name;

    @FXML
    private TextArea text_messageInput;

    @FXML
    private VBox vbox_messageDisplay;

    @FXML
    void displayPersonalMessage(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {
        Main.server.stopRunning();
        Stage stage = (Stage)gui.currentScene.getWindow();
        stage.close();
    }
    

    @FXML
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage)gui.currentScene.getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }    

    @FXML
    void recoverConnection(MouseEvent event) {

    }

    @FXML
    void sendmessage(MouseEvent event) {

    }

    @FXML
    void suoXiao(MouseEvent event) {
        System.out.println("minisize");
        Stage stage = (Stage)gui.currentScene.getWindow();
        stage.setIconified(true);
    }

    @FXML
    void toButtonSendMessage(KeyEvent event) {

    }

}
