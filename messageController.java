import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class messageController {

    @FXML
    private Button buttonSend;

    @FXML
    private TextArea messageDisplay;

    @FXML
    private TextField messageInput;

    @FXML
    private TextField targetUsr;

    @FXML
    void sendMessage(ActionEvent event){
        message messageToSend = new message(1, Main.mine.getOwo_no(), targetUsr.getText(), messageInput.getText());
        Main.server.cout(1, targetUsr.getText(), messageInput.getText());
        messageDisplay.appendText(Main.mine.getOwo_no()+':'+messageInput.getText()+"\n");
        messageInput.clear();
    }

    @FXML
    public  void getMessage(String message){
        messageDisplay.appendText(message);
    }
}
