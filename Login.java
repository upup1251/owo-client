import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Login {
    Login(){}
    public static boolean register(String password){
        Main.mine.setPasswword(password);
        connectToSql sqls = new connectToSql();
        String sql = "select count(*) counts from usr";
        try{
            PreparedStatement statement = sqls.getCon().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int usrs_num = rs.getInt("counts");
            sql = "insert into usr values(?,?)";
            statement = sqls.getCon().prepareStatement(sql);
            statement.setString(1,String.format("%05d", usrs_num));
            Main.mine.setOwo_no(String.format("%05d", usrs_num));
            statement.setString(2, Main.mine.getPassword());
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public static boolean login(String owo_no,String password){
        Main.mine.setOwo_no(owo_no);
        Main.mine.setPasswword(password);
        connectToSql sqls = new connectToSql();
        String sql = "select password from usr where owo_no = ?";
        try{
            PreparedStatement statement = sqls.getCon().prepareStatement(sql);
            statement.setString(1, Main.mine.getOwo_no());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(Main.mine.getPassword())){
                    System.out.println("login successfully!");
                    return true;
                }
                else{
                    System.out.println("the password is incorrected!");
                    System.out.println("please try again.");
                    return false;

                }
            }
            else{
                System.out.println("the usr is not existed.");
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
