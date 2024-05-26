import java.sql.*;
import java.io.File;
import java.io.IOException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

public class connectToSql {
    private Connection con;
    private Statement statement;
    private String deiver;
    private String usr;
    private String passwrd;
    private String url;
    private PreparedStatement prestatement;
    public connectToSql(){
        deiver = "com.mysql.cj.jdbc.Driver";
        usr = "upupup";
        passwrd = "tianqi985";
        url = "jdbc:mysql://47.120.73.143:3306/owo";
        try{
            Class.forName(deiver);
            //System.out.println("driver loaded successfully");
        }
        catch(ClassNotFoundException e){
            System.out.println("driver loaded unsuccessfully");
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(url, usr, passwrd);
            if(!con.isClosed()){
                //System.out.println("mysql getConnection successfully");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(url, usr, passwrd);
            statement= con.createStatement();
            //System.out.println("mysql createStatement successfully");
        }
        catch(Exception e){
            System.out.println("mysql createStatement unsuccessfully");
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sqls) throws SQLException{
        return statement.executeQuery(sqls);
    }
    public void executeUpdate(String sqls) throws SQLException{
        statement.executeUpdate(sqls);
    }
    public Connection getCon(){
        return con;
    }
    public void getMessage() throws IOException{
        String sql = "select * from message where accpter=?";
        try {
            prestatement = con.prepareStatement(sql);
            prestatement.setString(1, Main.mine.getOwo_no());
            ResultSet rs = prestatement.executeQuery();
            while (rs.next()) {
                message messageToTake = new message(Integer.parseInt(rs.getString("type")), rs.getString("sender"), rs.getString("accpter"), rs.getString("message"));
                Main.server.messageDO(messageToTake);
                sql = "delete from message where accpter=?";
                prestatement = con.prepareStatement(sql);
                prestatement.setString(1, Main.mine.getOwo_no());
                prestatement.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void getFriendList(){
        String sql = "select usr1,beizhu1 from friend where usr2=?";
        try {
            prestatement = con.prepareStatement(sql);
            prestatement.setString(1, Main.mine.owo_no);
            ResultSet rs = prestatement.executeQuery();
            while (rs.next()) {
                String usr1_owo = rs.getString("usr1");
                String usr1beizhu = rs.getString("beizhu1");
                sql = "select username,contact from usr where id=?";
                prestatement = con.prepareStatement(sql);
                prestatement.setString(1, usr1_owo);
                ResultSet rs1 = prestatement.executeQuery();
                rs1.next();
                String avatarPath = "./src/useravatar/"+usr1_owo+".png";
                File file = new File(avatarPath);
                if(file.exists()){
                    friend.friends.add(new friend(usr1_owo, rs1.getString("username"), usr1beizhu, rs1.getString("contact"), "./src/useravatar/"+usr1_owo+".png"));
                }
                else{
                    Main.server.getAvatar(usr1_owo);
                    friend.friends.add(new friend(usr1_owo, rs1.getString("username"), usr1beizhu, rs1.getString("contact"), "./src/useravatar/"+usr1_owo+".png"));
                }
            }
            sql = "select usr2,beizhu2 from friend where usr1=?";
            prestatement = con.prepareStatement(sql);
            prestatement.setString(1, Main.mine.owo_no);
            rs = prestatement.executeQuery();
            while (rs.next()) {
                String usr2_owo = rs.getString("usr2");
                String usr2beizhu = rs.getString("beizhu2");
                sql = "select username,contact from usr where id=?";
                prestatement = con.prepareStatement(sql);
                prestatement.setString(1, usr2_owo);
                ResultSet rs1 = prestatement.executeQuery();
                rs1.next();
                String avatarPath = "./src/useravatar/"+usr2_owo+".png";
                File file = new File(avatarPath);
                if(file.exists()){
                    friend.friends.add(new friend(usr2_owo, rs1.getString("username"), usr2beizhu, rs1.getString("contact"), "./src/useravatar/"+usr2_owo+".png"));
                }
                else{
                    Main.server.getAvatar(usr2_owo);
                    friend.friends.add(new friend(usr2_owo, rs1.getString("username"), usr2beizhu, rs1.getString("contact"), "./src/useravatar/"+usr2_owo+".png"));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
