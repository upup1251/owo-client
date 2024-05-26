import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.w3c.dom.events.Event;

import com.mysql.cj.protocol.Message;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class message1_control {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button button_addFriend;
    
    @FXML
    private AnchorPane root;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ScrollPane scrollPanemessage;
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
    private ListView<message> listview_displayMessage;

    @FXML
    private ListView<friend> listview_friendDisplay;

    @FXML
    private TextField text_input;

    
    @FXML
    private ImageView avatar_imaga;

    @FXML
    private static Label notification;

    @FXML
    private TextField text_hudong;

    @FXML
    void displayPersonalMessage(MouseEvent event) {


    }
    
    @FXML
    public void initialize(){
        button_exit.setOnMouseEntered(e -> button_exit.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_exit.setOnMouseExited(e -> button_exit.setStyle("-fx-background-color: transparent;"));

        button_suoXiao.setOnMouseEntered(e -> button_suoXiao.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_suoXiao.setOnMouseExited(e -> button_suoXiao.setStyle("-fx-background-color: transparent;"));

        button_send.setOnMouseEntered(e -> button_send.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_send.setOnMouseExited(e -> button_send.setStyle("-fx-background-color: transparent;"));

        button_sousuo.setOnMouseEntered(e -> button_sousuo.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_sousuo.setOnMouseExited(e -> button_sousuo.setStyle("-fx-background-color: transparent;"));

        button_addFriend.setOnMouseEntered(e -> button_addFriend.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
        button_addFriend.setOnMouseExited(e -> button_addFriend.setStyle("-fx-background-color: transparent;"));

        text_input.setOnMouseEntered(e -> text_input.setStyle("-fx-background-color: #484f5c; -fx-text-fill:white;-fx-background-radius:7"));
        text_input.setOnMouseExited(e -> text_input.setStyle("-fx-background-color: #1e1e1e;-fx-background-radius:7"));

        
        System.out.println("scene loading ...");
        File file = new File(Main.mine.getAvatarPath());
        if(file.exists()){
            Image image = new Image(file.toURI().toString());
        }
        else{

        }
        Image image = new Image("./src/useravatar/-1.png");
        avatar_imaga.setImage(image);
        label_name.setText(Main.mine.getname());

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

        text_hudong.setVisible(false);
        text_hudong.setStyle("-fx-background-color:green;-fx-background-radius: 10;-fx-text-fill:white; -fx-border-radius: 10;");
        text_hudong.setOnKeyPressed(event->{
            if(event.getCode()==KeyCode.ESCAPE){
                textdisappear();
            }
        });

        

        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        //listview_friendDisplay.getItems().addAll(friend.friends);
        listview_friendDisplay.setItems(friend.friends);
        //listview_friendDisplay = new ListView<>(friend.friends);
        listview_friendDisplay.setCellFactory(param -> new friendcell());
        listview_friendDisplay.setStyle("-fx-background-color: #1e1e1e; -fx-padding: 0; -fx-background-insets: 0; -fx-border-width: 0;");
        scrollPane.setStyle("-fx-background-color:#1e1e1e");
        
        scrollPanemessage.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPanemessage.setVbarPolicy(ScrollBarPolicy.NEVER);
        listview_displayMessage.setItems(Main.currentmessages);
        listview_displayMessage.setCellFactory(param -> new messageCell());
        scrollPane.setStyle("-fx-background-color:black");


        MenuItem showID = new MenuItem("查看ID");
        showID.setOnAction(event->{
            notified("您的id为： "+Main.mine.getOwo_no());
            System.out.println("show ID.");
        });
        MenuItem changeName = new MenuItem("修改名字");
        changeName.setOnAction(event->{
            textappear("请在此处输入您希望的名字.");
            notified("当前名字为："+Main.mine.getname()+";\n请在下方输入更改后的名字！");
            text_hudong.setOnAction(e->{
                String nameAfter = text_hudong.getText();
                Main.mine.setName(nameAfter);
                Main.server.cout(5, "00000", "username");
                label_name.setText(nameAfter);
                notified("名字更改为"+nameAfter);
                textdisappear();
            });
        });
        MenuItem changeAvatar = new MenuItem("修改头像");
        changeAvatar.setOnAction(e->{
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("选择头像");
            File selectedFile = filechooser.showOpenDialog((Stage)gui.currentScene.getWindow());
            String avatarPath;
            if(selectedFile != null){
                avatarPath = selectedFile.getAbsolutePath();
                System.out.println("choose avatar:"+selectedFile.getAbsolutePath());

                try {
                    Main.server.sendAvatar(avatarPath);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Main.server.cout(5, "00000", "avatar_path");
                Image image1 = new Image(selectedFile.toURI().toString());
                File target = new File("./src/useravatar/"+Main.mine.getOwo_no()+".png");
                File source = new File(avatarPath);
                try {
                    Files.copy(source.toPath(),target.toPath());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                avatar_imaga.setImage(image1);
            }
        });
        MenuItem changecontact = new MenuItem("修改联系方式");
        changecontact.setOnAction(event->{
            textappear("请在此处输入您希望的联系方式.");
            notified("当前联系方式为："+Main.mine.getcontact()+";\n请在下方输入更改后的联系方式！");
            text_hudong.setOnAction(e->{
                String contactafter = text_hudong.getText();
                Main.mine.setContact(contactafter);
                Main.server.cout(5, "00000", "contact");
                notified("联系方式更改为"+contactafter);
                textdisappear();
            });
        });
        MenuItem changePasswd = new MenuItem("修改密码");
        changePasswd.setOnAction((event ->{
            textappear("请在此处输入你希望的密码.");
            notified("当前密码为："+Main.mine.getPassword()+";\n请在下方输入更改后的密码!");
            text_hudong.setOnAction(e->{
                String passwdafter = text_hudong.getText();
                Main.mine.setPasswword(passwdafter);
                Main.server.cout(5, "00000", "password");
                notified("密码更改成功");
                textdisappear();
            });
        }));
        MenuItem changeAltPasswd = new MenuItem("修改备用密码");
        changeAltPasswd.setOnAction((event ->{
            textappear("请在此处输入你希望的备用密码.");
            notified("当前备用密码为："+Main.mine.getPassword()+";\n请在下方输入更改后的备用密码!");
            text_hudong.setOnAction(e->{
                String passwdafter = text_hudong.getText();
                Main.mine.setPasswword(passwdafter);
                Main.server.cout(5, "00000", "alt_password");
                notified("备用密码更改成功");
                textdisappear();
            });
        }));
        ContextMenu avatarMenu = new ContextMenu();
        avatarMenu.setStyle("-fx-background-color :black; -fx-background-radius:10");
        avatarMenu.getItems().addAll(showID,changeName,changeAvatar,changecontact,changePasswd,changeAltPasswd);
        button_avatar.setOnMouseClicked(event -> {
            avatarMenu.show(avatar_imaga, event.getScreenX(), event.getSceneY());
        });
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
    void recoverConnection(MouseEvent event) {

    }

    @FXML
    void send(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!text_input.getText().equals("")){
                Main.server.cout(1, gui.currentTarget,text_input.getText() );
                text_input.clear();
            }
        }
    }
    
    @FXML
    void find(MouseEvent event){
        textappear("请在此处输入对方id.");
        notified("请在下方输入对方id.");
        text_hudong.setOnAction(e->{
                String target = text_hudong.getText();
                boolean isYourFriend = false;
                for(friend i: friend.friends){
                    if(i.getOwo().equals(target)){
                        isYourFriend = true;
                        break;
                    }
                }
                if(isYourFriend){
                    gui.currentTarget = target;
                    System.out.println("current target change to "+target);
                    Main.currentmessages.clear();
                    for(message i : Main.messages){
                        if((i.getSender().equals(Main.mine.getOwo_no()) && i.getAccepter().equals(gui.currentTarget))||i.getSender().equals(gui.currentTarget)){
                            Main.currentmessages.add(i);
                        }
                    } 
                }
                else{
                    notified(target+"不是您的好友！");
                }
                textdisappear();
        });
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
    
    public void textappear(String prompt){
        text_hudong.setPromptText(prompt);
        text_hudong.setVisible(true);
        text_hudong.requestFocus();
    }

    public void textdisappear(){
        text_hudong.setVisible(false);
        text_hudong.clear();
        text_input.requestFocus();
    }


    @FXML
    void sendmessage(MouseEvent event) {
        if(!text_input.getText().equals("")){
            Main.server.cout(1, gui.currentTarget,text_input.getText() );
            text_input.clear();
        }
    }
    
    @FXML
    void addfriend(MouseEvent event){
        textappear("请在此处输入对方id.");
        notified("请在下方输入对方的id.");
        text_hudong.setOnAction(e->{
            try {
                Main.server.addFriend(text_hudong.getText());
                textdisappear();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }


    /**
     * friendcell
     */
    public class friendcell extends ListCell<friend> {
        private VBox content;
        private ImageView image;
        private String owo;
        
        public friendcell(){
            content = new VBox();
            image = new ImageView();
            content.getChildren().addAll(image);
            content.setStyle("-fx-background-color:transparent;");
            image.setFitWidth(31);
            image.setFitHeight(31);

            content.setOnMouseEntered(e -> content.setStyle("-fx-background-color: #484f5c; -fx-background-radius:10"));
            content.setOnMouseExited(e -> content.setStyle("-fx-background-color: transparent;"));            
        }

        @Override
        protected void updateItem(friend item, boolean empty) {
            super.updateItem(item, empty);
            if(empty || item==null){
                setGraphic(null);
            }
            else{
                MenuItem showID = new MenuItem("查看ID");
                showID.setOnAction(e->{
                    notified("好友id为："+item.getOwo());
                });
                MenuItem changeBeizhi = new MenuItem("修改备注");
                changeBeizhi.setOnAction(e->{
                    notified("当前备注为："+item.getBeizhu());
                    textappear("请在此处输入您希望的备注！");
                    text_hudong.setOnAction(event->{
                        String beizhuafter = text_hudong.getText();
                        item.setBeizhu(beizhuafter);
                        String sql = "update friend set beizhu1 = ? where usr2 = ? and usr1 = ?";
                        try {
                            PreparedStatement prestatement = Main.sqls.getCon().prepareStatement(sql);
                            prestatement.setString(1, beizhuafter);
                            prestatement.setString(2, Main.mine.getOwo_no());
                            prestatement.setString(3, item.getOwo());
                            prestatement.executeUpdate();
                            sql = "update friend set beizhu2 = ? where usr1 = ? and usr2 = ?";
                            prestatement = Main.sqls.getCon().prepareStatement(sql);
                            prestatement.setString(1, beizhuafter);
                            prestatement.setString(2, Main.mine.getOwo_no());
                            prestatement.setString(3, item.getOwo());
                            prestatement.executeUpdate();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        textdisappear();
                        notified("好友"+item.getOwo()+" 的备注更改为"+beizhuafter+"！");
                    });
                });
                MenuItem showcontact = new MenuItem("查看联系方式");
                showcontact.setOnAction(e->{
                    notified("好友联系方式为："+item.getContact());
                });
                MenuItem delfriend = new MenuItem("删除好友");
                delfriend.setOnAction(event->{
                    friend.friends.removeIf(element -> element.getOwo().equals(item.getOwo()));

                    String sql = "delete from friend where usr1="+Main.mine.getOwo_no()+" and usr2 = "+item.getOwo();
                    try {
                        PreparedStatement statement = Main.sqls.getCon().prepareStatement(sql);
                        statement.executeUpdate();
                        sql = "delete from friend where usr2="+Main.mine.getOwo_no()+" and usr2 = "+item.getOwo();
                        statement = Main.sqls.getCon().prepareStatement(sql);
                        statement.executeUpdate();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    notified("好友 "+item.getOwo()+" 已删除！");
                });
        
                ContextMenu avatarMenu = new ContextMenu();
                avatarMenu.setStyle("-fx-background-color :black; -fx-background-radius:10");
                avatarMenu.getItems().addAll(showID,changeBeizhi,showcontact,delfriend);


                image.setOnMouseClicked(event -> {
                    if(event.getButton()==MouseButton.PRIMARY){
                        gui.currentTarget = item.getOwo();
                        System.out.println("current target change to "+item.getOwo());
                        notified("正在和"+item.getBeizhu()+"聊天.");
                        Main.currentmessages.clear();
                        for(message i : Main.messages){
                          if((i.getSender().equals(Main.mine.getOwo_no()) && i.getAccepter().equals(gui.currentTarget))||i.getSender().equals(gui.currentTarget)){
                              Main.currentmessages.add(i);
                          }
                        } 
                    }
                    else if(event.getButton()==MouseButton.SECONDARY){
                        avatarMenu.show(avatar_imaga, event.getScreenX(), event.getSceneY());

                    }
                    
                });

                File file = new File("./src/useravatar/"+item.getOwo()+".png");
                if(file.exists()){
                    image.setImage(new Image(file.toURI().toString()));
                }
                else{
                    image.setImage(new Image("./src/useravatar/-1.png"));;
                }
                //image.setImage(new Image(file.toURI().toString()));
                setGraphic(content);
            }
            setBackground(new Background(new BackgroundFill(Color.web("#1e1e1e"), CornerRadii.EMPTY, null))); // 设置背景为透明
            }
            
        }

    public class messageCell extends ListCell<message> {
        private HBox content;
        private ImageView image;
        private Label message;
        
        public messageCell(){
            content = new HBox();
            image = new ImageView();
            message = new Label();
            content.setStyle("-fx-background-color:transparent;");
            image.setFitWidth(30);
            image.setFitHeight(30);
            message.setStyle("-fx-text-fill:white");
            message.setWrapText(true);
            content.setMaxWidth(415);
            
        }

        @Override
        protected void updateItem(message item, boolean empty) {
            super.updateItem(item, empty);
            if(empty || item==null){
                setGraphic(null);
            }
            else{
                File file = new File("./src/useravatar/"+item.getSender()+".png");
                if(!file.exists()){
                    image.setImage(new Image("./src/useravatar/-1.png"));
                }
                else{
                    image.setImage(new Image(file.toURI().toString()));
                    //Image image = new Image(file.toURI().toString());
                }

                content.getChildren().clear();
                if(item.getSender().equals(Main.mine.getOwo_no()) && item.getAccepter().equals(gui.currentTarget)){
                    image.setLayoutX(500);
                    content.setAlignment(Pos.CENTER_RIGHT);
                    message.setText(item.getMessage()+":  ");
                    content.getChildren().addAll(message,image);
                }
                else if(item.getSender().equals(gui.currentTarget)){
                    content.setAlignment(Pos.CENTER_LEFT);
                    message.setText("  :"+item.getMessage());
                    content.getChildren().addAll(image,message);
                }
                setGraphic(content);
            }
            setBackground(new Background(new BackgroundFill(Color.web("#1e1e1e"), CornerRadii.EMPTY, null))); // 设置背景为透明

            }
        }
}

    
        
