import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class message1_control {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button button_addFriend;

    @FXML
    private ScrollPane scrollPane;

    
    @FXML
    private Button button_top;

    @FXML
    private Button button_avatar;

    @FXML
    private Button button_exit;

    @FXML
    private Button button_online;

    @FXML
    private Button button_send;

    @FXML
    private Button button_sousuo;

    @FXML
    private Button button_suoXiao;

    @FXML
    private Label label_name;

    @FXML
    private ListView<String> listview_displayMessage;

    @FXML
    private ListView<String> listview_friendDisplay;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;
    
    @FXML
    private ImageView avatar_imaga;

    @FXML
    void displayPersonalMessage(MouseEvent event) {


    }
    
    @FXML
    public void initialize(){
        System.out.println("scene loading ...");
        File file = new File(Main.mine.getAvatarPath());
        Image image = new Image(file.toURI().toString());
        avatar_imaga.setImage(image);
        label_name.setText(Main.mine.getname());
        

        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        listview_friendDisplay.setCellFactory(param -> new friendcell());
        listview_friendDisplay.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0; -fx-border-width: 0;");

        listview_friendDisplay.getItems().addAll("0","0","0","0","0","0","0","0");
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
    void recoverConnection(MouseEvent event) {

    }

    @FXML
    void send(KeyEvent event) {

    }

    @FXML
    void sendmessage(MouseEvent event) {

    }
    


    /**
     * friendcell
     */
    public class friendcell extends ListCell<String> {
        private VBox content;
        private ImageView image;
        private String owo;
        
        public friendcell(){
            content = new VBox();
            image = new ImageView();
            content.getChildren().addAll(image);
            content.setStyle("-fx-background-color:transparent;");
            image.setFitWidth(30);
            image.setFitHeight(30);
            
            image.setOnMouseClicked(event -> {
                System.out.println("get clicked.");
            }
            );
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if(empty || item==null){
                setGraphic(null);
            }
            else{
                File file = new File("./src/useravatar/"+item+".png");
                image.setImage(new Image(file.toURI().toString()));
                setGraphic(content);
            }
            //设置单元格背景透明，自定义单元格会覆盖listview的css，导致不透明
            setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));



            }
        }
}

    
        
