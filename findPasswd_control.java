import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class findPasswd_control {
    private double xOffset = 0;
    private double yOffset = 0;

        @FXML
    private AnchorPane root;

    @FXML
    private Button button_suoXiao;

    @FXML
    private Button button_exit;
    @FXML
    private ImageView avatar;

    @FXML
    private Button button_findPassword;

    @FXML
    private TextField text_no;

    @FXML
    private Button button_home;

    @FXML
    private static Label notification;

    @FXML
    private PasswordField text_passwd;

    @FXML
    private void initialize(){
        text_no.setOnMouseEntered(e -> text_no.setStyle("-fx-background-color: #484f5c;-fx-text-fill:white; -fx-background-radius:7"));
        text_no.setOnMouseExited(e -> text_no.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        text_passwd.setOnMouseEntered(e -> text_passwd.setStyle("-fx-background-color: #484f5c;-fx-text-fill:white; -fx-background-radius:7"));
        text_passwd.setOnMouseExited(e -> text_passwd.setStyle("-fx-background-color: black; -fx-border-color: white;-fx-text-fill:white;  -fx-border-width: 0 0 0.5 0;"));

        button_exit.setOnMouseEntered(e -> button_exit.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_exit.setOnMouseExited(e -> button_exit.setStyle("-fx-background-color: transparent;"));

        button_suoXiao.setOnMouseEntered(e -> button_suoXiao.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_suoXiao.setOnMouseExited(e -> button_suoXiao.setStyle("-fx-background-color: transparent;"));

        button_findPassword.setOnMouseEntered(e -> button_findPassword.setStyle("-fx-background-color: #484f5c; -fx-background-radius:5"));
        button_findPassword.setOnMouseExited(e -> button_findPassword.setStyle("-fx-background-color: gray; -fx-background-radius: 5;"));

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
    void findpassword(MouseEvent event){
        String owo_no = text_no.getText();
        String passwd2 = text_passwd.getText();
        String sqls = "select alt_password,password from usr where id=?";
        PreparedStatement statement;
        try {
            statement = Main.sqls.getCon().prepareStatement(sqls);
            statement.setString(1, owo_no);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                if(rs.getString("alt_password").equals(passwd2)){
                    System.out.println("your password is \""+rs.getString("password")+"\"");
                    notified("您的密码为："+rs.getString("password !"));
                }
                else{
                    System.out.println("your alt_password is uncorrect");
                    notified("备用密码错误，请重试！");
                }
            }
            else{
                System.out.println("the owo_no is not exists,please try again");
                notified("该用户不存在！");
            }
        } catch (SQLException e) {
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
        Main.exit();
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
    void finpassowrd(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            findpassword(null);
        }

    }

    @FXML
    void toPasswdInput(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            text_passwd.requestFocus();
        }

    }

    @FXML
    void goHome(MouseEvent event) {
        try {
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
