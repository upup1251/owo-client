import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Login {
    Login(){}
    public void choose(){
        System.out.println("======welcome to owo!======");
        System.out.println("do you have a account:?y/n");
        Scanner scanner = new Scanner(System.in);
        char choose = scanner.next().charAt(0);
        if(choose == 'y'){
            while (!login()) {};
        }
        else if(choose == 'n'){
            register();
            while (!login()) {};
        }
        else{
        }
    }
    public boolean register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("======welcome to owo,here is the register======");
        System.out.print("please enter your password:");
        Main.mine.setPasswword(scanner.next());
        System.out.println("=========================");
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
            statement.setString(2, Main.mine.getPassowrd());
            statement.executeUpdate();
            System.out.println("thanks for coming,you are the "+usrs_num+" usrs,you owo_no is:"+String.format("%05d", usrs_num));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("======welcome to owo,here is the login======");
        System.out.print("please enter your owo_no:");
        Main.mine.setOwo_no(scanner.next());
        System.out.print("please enter your password:");
        Main.mine.setPasswword(scanner.next());
        System.out.println("=========================");
        connectToSql sqls = new connectToSql();
        

        String sql = "select password from usr where owo_no = ?";
        try{
            PreparedStatement statement = sqls.getCon().prepareStatement(sql);
            statement.setString(1, Main.mine.getOwo_no());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                if(rs.getString("password").equals(Main.mine.getPassowrd())){
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
