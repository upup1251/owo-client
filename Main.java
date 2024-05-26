import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.ObjIntConsumer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.*;
import java.sql.Statement;

public class Main{
    //当前进程用户
    public static usr mine = new usr();
    //服务器信息
    public static toServer server;
    public static String ip = "127.0.0.1";
    public static int port = 40801;
    //数据库
    public static connectToSql sqls;
    
    public static ObservableList<message> messages = FXCollections.observableArrayList();
    public static ObservableList<message> currentmessages = FXCollections.observableArrayList();

    public static void main(String[] args) {
        gui.gui_main(args);
    }
    
    public static void exit() {
        try {
            savaMessagesToLoacal();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Main.server.stopRunning();
        Stage stage = (Stage)gui.currentScene.getWindow();
        stage.close();

        
    }
    public static void loadLoacalMessages() throws ClassNotFoundException, IOException{
        File file = new File("./src/messagedate/"+mine.getOwo_no());
        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try{
                    Main.messages.add((message) ois.readObject());
                }
                catch(IOException e){
                    break;
                }
            }
            System.out.print("get ");
            System.out.print(Main.messages.size());
            System.out.println(" messages from local");
        }
        
    }
    public static void savaMessagesToLoacal() throws IOException{
        File file = new File("./src/messagedate/"+mine.getOwo_no());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(message i : messages){
            oos.writeObject(i);
        }
        System.out.print(messages.size());
        System.out.println(" messages data already write to "+file.toURI().toString());
    }

}