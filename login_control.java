import java.io.IOException;
import java.lang.classfile.FieldModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class login_control {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button button_suoXiao;

    @FXML
    private Button button_exit;
    @FXML
    private ImageView avatar;

    @FXML
    private MenuItem button_forget;

    @FXML
    private Button button_top;

    @FXML
    private Button button_login;

    @FXML
    private MenuItem button_register;

    @FXML
    private TextField text_no;

    @FXML
    private PasswordField text_passwd;

    @FXML
    void button_register(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage stage = (Stage)gui.currentScene.getWindow();
            Scene scene = new Scene(root);
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
    void getPassword(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("findpasswd.fxml"));
            Stage stage = (Stage)gui.currentScene.getWindow();
            Scene scene = new Scene(root);
            gui.currentScene = scene;
            stage.setScene(scene);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void login(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            loginInto(null);
        }

    }

    @FXML
    void loginInto(ActionEvent event) {
        if(Login.login(text_no.getText(), text_passwd.getText())){
            //连接服务器
            //Main.server = new toServer(Main.ip,Main.port);
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

    }

    @FXML
    void toPasswdInput(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
                text_passwd.requestFocus();
        }

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

}