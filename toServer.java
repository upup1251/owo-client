import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.mysql.cj.jdbc.ha.FailoverConnectionProxy;
import com.mysql.cj.protocol.x.SyncMessageSender;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
public class toServer extends Thread{
    private InetAddress severIP;
    private int severPORT;
    public static Socket scoket;
    private static boolean running=true;
    long heartBeatLastTime;
    long connectionLastTime;

    toServer(){};
    toServer(String ip1,int port1){
        try{
            severIP = InetAddress.getByName(ip1);
            severPORT = port1;
            scoket = new Socket(severIP,severPORT);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        try{
        //发送自己的owo_no
        cout(0,Main.mine.getOwo_no(),Main.mine.getOwo_no());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        running = true;
        heartBeatLastTime = System.currentTimeMillis();
        cout(3,"00000","heartBeat");
        connectionLastTime = System.currentTimeMillis();
        while (running) {
            beat();
            if(running){
                getMessage();
            }
        }
    }
    public static void cout(int type,String accepter,String message){
        message messageToSend = new message(type,Main.mine.getOwo_no(),accepter,message);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(scoket.getOutputStream());
            oos.writeObject(messageToSend);
            if(type ==3 ){
                System.out.println("heartbeart!");
            }
            else if(type==1){
                System.out.println("type : "+type+" message to "+accepter+" already sended.");
                Main.messages.add(messageToSend);
                Main.currentmessages.add(messageToSend);
            }
            else if(type == 5){
                System.out.println("change mysql for "+message);
                if(message.equals("avatar_path")){
                    return;
                }
                String sql = "update usr set "+message+" = ? where id = "+Main.mine.getOwo_no();
                PreparedStatement prestatement = Main.sqls.getCon().prepareStatement(sql);
                System.out.println(message);
                if(message.equals("username")){
                    System.out.println("name:"+Main.mine.getname());
                    prestatement.setString(1,Main.mine.getname());
                }
                else if(message.equals("contact")){
                    prestatement.setString(1, Main.mine.getcontact());
                }
                else if(message.equals("password")){
                    prestatement.setString(1, Main.mine.getPassword());
                }
                else if(message.equals("alt_password")){
                    prestatement.setString(1, Main.mine.getPassword());
                }
                prestatement.executeUpdate();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void sendAvatar(String path) throws IOException{
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        byte[] buffer = new byte[999];
        int byteRead;
        while ((byteRead=fis.read(buffer))!=-1) {
            baos.write(buffer, 0, byteRead);
        }
        byte[] imageBytes = baos.toByteArray();
        String base64image = Base64.getEncoder().encodeToString(imageBytes);
        cout(6,"00000", base64image);
        System.out.println("avatar send successfully.");
    }
    
    public void addFriend(String owo) throws SQLException{
        String sql = "select username,contact from usr where id=?";
        PreparedStatement prestatement = Main.sqls.getCon().prepareStatement(sql);
        prestatement.setString(1, owo);
        ResultSet rs = prestatement.executeQuery();
        if(rs.next()){
            getAvatar(owo);
            friend.friends.add(new friend(owo, rs.getString("username"), rs.getString("username"), rs.getString("contact"), "./src/useravatar/"+owo+"png"));
            sql = "insert into friend values(?,?,?,?)";
            prestatement = Main.sqls.getCon().prepareStatement(sql);
            prestatement.setString(1, Main.mine.getOwo_no());
            prestatement.setString(2, owo);
            prestatement.setString(3, Main.mine.getOwo_no());
            prestatement.setString(4, owo);
            prestatement.executeUpdate();
            message1_control.notified("已添加"+owo+" 为好友！");
            System.out.println("add successfully!current friend num is "+friend.friends.size());
        }
        else{
            System.out.println("target use not exists");
            message1_control.notified(owo+" 不存在");
        }
    }
    public void getAvatar(String owo){
        cout(9, "00000", owo);
    }

    public void getMessage(){
        if(System.currentTimeMillis()-connectionLastTime>5000){
            System.out.println("lose connection");
            try {
                sleep(50000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try{
            if(scoket.getInputStream().available()>0){
                ObjectInputStream ois = new ObjectInputStream(scoket.getInputStream());
                message messageRecvied = (message)ois.readObject();
                messageDO(messageRecvied);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void messageDO(message messageRecvied) throws IOException{
        if(messageRecvied.getType()==4){
            connectionLastTime = System.currentTimeMillis();
        }
        else if(messageRecvied.getType()==1){
            String message = messageRecvied.getSender()+":"+messageRecvied.getMessage();
            System.out.println(message);
            //通过当前场景通过控件id获取控件
            //TextArea messageDisplay = (TextArea)gui.currentScene.lookup("#messageDisplay");
            //messageDisplay.appendText(message+"\n");

            Platform.runLater(()->{
                Main.messages.add(messageRecvied);
                Main.currentmessages.add(messageRecvied);  
            });
                         
        }
        else if(messageRecvied.getType()==5){
            if(messageRecvied.getMessage().equals("avatar_path")){
                getAvatar(messageRecvied.getSender());
                
                
            }
            else{
                //有点懒了属于是
                Main.sqls.getFriendList();
            }
        }
        else if(messageRecvied.getType()==7){
            System.out.println("get friend applycation from "+messageRecvied.getSender());
            //处理
        }
        else if(messageRecvied.getType()==6){
            byte[] imagebytes = Base64.getDecoder().decode(messageRecvied.getMessage());
            File outputFile = new File("./src/useravatar/"+messageRecvied.getSender()+".png");
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            FileOutputStream fops = new FileOutputStream(outputFile);
            fops.write(imagebytes);
            fops.flush();
            fops.close();
            System.out.println("save avatar from "+messageRecvied.getSender());
        }
        else if(messageRecvied.getType()==8){
            System.out.println("friend applycation pass from "+messageRecvied.getSender());
            //处理
        }

    }



    
    private void beat(){
        if(System.currentTimeMillis() - heartBeatLastTime >= 2000){
            cout(3,"00000","heartBeat");
            heartBeatLastTime = System.currentTimeMillis();
        }
    }
    public static void stopRunning(){
        running = false;
    }
    public void loseConnection(){
        Platform.runLater(()->{
            System.out.println("lose connection,offline...");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("与服务器失去连接...");
            alert.setContentText("由于网络问题，您似乎与服务器失去连接...");
            stopRunning();
            alert.showAndWait();
        });
    }

}
