import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class loginController{

    @FXML
    private Button loginButton;

    @FXML
    private Button register_button;

    @FXML
    private TextField text_owo_no;

    @FXML
    private PasswordField text_password;

    @FXML
    void loginIn(ActionEvent event) throws IOException {
        if(Login.login(text_owo_no.getText(), text_password.getText())){
            //连接服务器
            Main.server = new toServer(Main.ip,Main.port);
            Main.server.start();
            //切换场景
            Parent root = FXMLLoader.load(getClass().getResource("message.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)loginButton.getScene().getWindow();
            stage.setScene(scene);
        }
    }

    @FXML
    void toRegister(ActionEvent event) {

    }

}
