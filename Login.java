import java.util.Scanner;
import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;

public class Login {
    Login(){}
    public static boolean register(String name,String contact,String password,String passwd2,String avatarPath){
        Main.mine.setPasswword(password);
        String sql = "select count(*) counts from usr";
        try{
            PreparedStatement statement = Main.sqls.getCon().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int usrs_num = rs.getInt("counts");
            sql = "insert into usr values(?,?,?,?,?,?)";
            statement = Main.sqls.getCon().prepareStatement(sql);

            statement.setString(1,String.format("%05d", usrs_num));
            Main.mine.setOwo_no(String.format("%05d", usrs_num));
            statement.setString(2,name);
            Main.mine.setName(name);
            statement.setString(3, contact);
            Main.mine.setContact(contact);
            statement.setString(4, password);
            Main.mine.setPasswword(password);
            statement.setString(5, passwd2);
            Main.mine.setPasswd2(passwd2);
            String avatarPathInService;
            avatarPathInService = "./src/usrsavatar/"+Main.mine.getOwo_no()+".png";
            statement.setString(6, avatarPathInService);
            Main.mine.setAvatarPath(avatarPath);
            statement.executeUpdate();


            File target = new File("./src/useravatar/"+Main.mine.getOwo_no()+".png");
            File source = new File(avatarPath);
            try {
                Files.copy(source.toPath(),target.toPath());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            register_control.notified("您的id为"+String.format("%05d", usrs_num));
            
            login(String.format("%05d", usrs_num), password);
            Main.server.cout(1, "00000", Main.mine.getOwo_no());
            //登录后再进行文件传输
            Main.server.sendAvatar(avatarPath);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    public static boolean login(String owo_no,String password){
        Main.mine.setOwo_no(owo_no);
        Main.mine.setPasswword(password);
        String sql = "select * from usr where id = ?";
        try{
            PreparedStatement statement = Main.sqls.getCon().prepareStatement(sql);
            statement.setString(1, Main.mine.getOwo_no());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(Main.mine.getPassword())){
                    System.out.println("login successfully!");
                    Main.mine.setName(rs.getString("username"));
                    Main.mine.setPasswd2("alt_password");
                    Main.mine.setAvatarPath("./src/useravatar/"+owo_no+".png");
                    return true;
                }
                else{
                    System.out.println("the password is incorrected!");
                    System.out.println("please try again.");
                    login_control.notified("密码错误，请重试！");
                    return false;

                }
            }
            else{
                System.out.println("the usr is not existed.");
                login_control.notified("用户不存在！");
                System.out.println("please try again.");
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
