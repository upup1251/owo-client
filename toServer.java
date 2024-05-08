import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
public class toServer extends Thread{
    private InetAddress severIP;
    private int severPORT;
    private Socket scoket;
    toServer(){};
    toServer(String ip1,int port1){
        try{
            severIP = InetAddress.getByName(ip1);
            severPORT = port1;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        try{
        scoket = new Socket(severIP, severPORT);
        System.out.println("connect to the server successfully.");
        //发送自己的owo_no
        cout(0,Main.mine.getOwo_no(),Main.mine.getOwo_no());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        while (true) {
            getMessage();
        }
    }
    public void cout(int type,String accepter,String message){
        message messageToSend = new message(type,Main.mine.getOwo_no(),accepter,message);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(scoket.getOutputStream());
            oos.writeObject(messageToSend);
            System.out.println("message to "+accepter+" already sended.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void getMessage(){
        try{
            ObjectInputStream ois = new ObjectInputStream(scoket.getInputStream());
            message messageRecvied = (message)ois.readObject();
            String message = messageRecvied.getSender()+":"+messageRecvied.getMessage();
            System.out.println(message);
            //通过当前场景通过控件id获取控件
            TextArea messageDisplay = (TextArea)gui.currentScene.lookup("#messageDisplay");
            messageDisplay.appendText(message+"\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
