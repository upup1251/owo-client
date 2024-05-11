import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
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

    public static void main(String[] args) {
        gui.gui_main(args);
    }

}