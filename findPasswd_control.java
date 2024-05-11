import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class findPasswd_control {
    private double xOffset = 0;
    private double yOffset = 0;

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
    private PasswordField text_passwd;

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
                }
                else{
                    System.out.println("your alt_password is uncorrect");
                }
            }
            else{
                System.out.println("the owo_no is not exists,please try again");
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
}
