import java.io.File;
import java.io.IOException;

import javax.swing.event.MouseInputListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class register_control {
    private double xOffset = 0;
    private double yOffset = 0;
    private String avatarPath;

    @FXML
    private Button button_suoXiao;

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
    private ImageView avatar_imag;

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
}

    

