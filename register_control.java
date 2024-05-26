import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.event.MouseInputListener;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class register_control {
    private double xOffset = 0;
    private double yOffset = 0;
    private String avatarPath;

    @FXML
    private Button button_suoXiao;

        @FXML
    private AnchorPane root;


    @FXML
    private Button button_exit;
    @FXML
    private Button avatar;

    @FXML
    private Button button_login;

    @FXML
    private TextField text_contact;

    @FXML
    private TextField text_name;

    @FXML
    private TextField text_passwd2;

    @FXML
    private PasswordField text_passwd;

    @FXML
    private static Label notification;

    @FXML
    private Button button_home;
    
    @FXML
    private ImageView avatar_imag;

    @FXML
    private void initialize(){
        text_contact.setOnMouseEntered(e -> text_contact.setStyle("-fx-background-color: #484f5c; -fx-background-radius:7"));
        text_contact.setOnMouseExited(e -> text_contact.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        text_name.setOnMouseEntered(e -> text_name.setStyle("-fx-background-color: #484f5c; -fx-background-radius:7"));
        text_name.setOnMouseExited(e -> text_name.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        text_passwd2.setOnMouseEntered(e -> text_passwd2.setStyle("-fx-background-color: #484f5c; -fx-background-radius:7"));
        text_passwd2.setOnMouseExited(e -> text_passwd2.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        text_passwd.setOnMouseEntered(e -> text_passwd.setStyle("-fx-background-color: #484f5c; -fx-background-radius:7"));
        text_passwd.setOnMouseExited(e -> text_passwd.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        button_exit.setOnMouseEntered(e -> button_exit.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_exit.setOnMouseExited(e -> button_exit.setStyle("-fx-background-color: transparent;"));

        avatar.setOnMouseEntered(e -> avatar.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        avatar.setOnMouseExited(e -> avatar.setStyle("-fx-background-color: transparent;"));

        button_suoXiao.setOnMouseEntered(e -> button_suoXiao.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_suoXiao.setOnMouseExited(e -> button_suoXiao.setStyle("-fx-background-color: transparent;"));

        button_login.setOnMouseEntered(e -> button_login.setStyle("-fx-background-color: #484f5c; -fx-background-radius:5"));
        button_login.setOnMouseExited(e -> button_login.setStyle("-fx-background-color: gray; -fx-background-radius: 5;"));

        button_home.setOnMouseEntered(e -> button_home.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_home.setOnMouseExited(e -> button_home.setStyle("-fx-background-color: transparent;"));
    


        notification = new Label();
        notification.setWrapText(true);
        notification.setLayoutX(100);
        notification.setLayoutY(-50);
        notification.setPrefWidth(300);
        notification.setMaxWidth(300);
        notification.setAlignment(Pos.CENTER);
        notification.setStyle("-fx-background-color: #41436b; " +
        "-fx-text-fill: white; " +
        "-fx-padding: 10px; " +
        "-fx-font-size: 16px; " +
        "-fx-border-width: 0px; " +
        "-fx-background-radius: 7px; " +
        "-fx-border-radius: 7px;");
        root.getChildren().add(notification);
    
    }

    @FXML
    void chooseAvatar(MouseEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("选择头像");
        File selectedFile = filechooser.showOpenDialog((Stage)gui.currentScene.getWindow());
        if(selectedFile != null){
            avatarPath = selectedFile.getAbsolutePath();
            System.out.println("choose avatar:"+selectedFile.getAbsolutePath());

            Image image = new Image(selectedFile.toURI().toString());
            avatar_imag.setImage(image);

        }
        else{
            avatarPath = "null";
        }
    }

    @FXML
    void register(ActionEvent event) {
        if(text_passwd.getText().equals("")){
            notified("请输入密码!");
        }
        Login.register(text_name.getText(), text_contact.getText(), text_passwd.getText(), text_passwd2.getText(), avatarPath);
        Main.server.start();
            //切换场景
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("message.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)button_login.getScene().getWindow();
                gui.currentScene = scene;
                stage.setScene(scene);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
    @FXML
    void suoXiao(MouseEvent event) {
        System.out.println("minisize");
        Stage stage = (Stage)gui.currentScene.getWindow();
        stage.setIconified(true);
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
    void toTextContact(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER){
            text_contact.requestFocus();
        }
    }
    
    @FXML
    void toregister(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER){
            register(null);
        }
    }
    

    
    @FXML 
    void toTextpasswd2(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER){
            text_passwd2.requestFocus();
        }
    }


    @FXML
    void toPasswdInput(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            text_passwd.requestFocus();
        }

    }

    @FXML
    void goHome(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage)gui.currentScene.getWindow();
            Scene scene = new Scene(root);
            gui.currentScene = scene;
            stage.setScene(scene);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

        public static void notified(String mess) {
        notification.setText(mess);
        
        // Slide down animation
        TranslateTransition slideDown = new TranslateTransition(Duration.millis(300), notification);
        slideDown.setFromY(0);
        slideDown.setToY(50);
        
        // Slide up animation
        TranslateTransition slideUp = new TranslateTransition(Duration.millis(300), notification);
        slideUp.setFromY(50);
        slideUp.setToY(0);

        
        // Timeline to delay the slide up animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            slideUp.play();
        }));
        
        // Play the slide down animation and then the timeline
        slideDown.setOnFinished(event -> {
            timeline.play();
            Timeline timelineClear = new Timeline(new KeyFrame(Duration.seconds(4), e->{
                notification.setText(" ");
            }));
            timelineClear.play();
        });
        slideDown.play();
    }
}

    

