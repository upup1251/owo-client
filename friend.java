import com.mysql.cj.jdbc.exceptions.SQLError;
import java.util.ArrayList;

import javafx.css.converter.FontConverter;

public class friend {
    public static ArrayList friends;
    private String fname;
    private String fowo;
    private String fbeizhu;
    private String fcontact;
    private String avatar_path;
    friend(){};
    friend(String owo,String name,String beizhu,String contact,String path){
        fname = name;
        fowo = owo;
        fbeizhu = beizhu;
        fcontact = contact;
        avatar_path = path;
    }
    public void setName(String fname1){
        fname = fname1;
    }
    public void setOwo(String fowo1){
        fowo = fowo1;
    }
    public void setBeizhu(String beizhu1){
        fbeizhu = beizhu1;
    }
    public void setContact(String contact1){
        fcontact = contact1;
    }
    public void setPath(String path){
        avatar_path = path;
    }
    public String getPath(){
        return avatar_path;
    }
    public String getName(){
        return fname;
    }
    public String getOwo(){
        return fowo;
    }
    public String getBeizhu(){
        return fbeizhu;
    }
    public String getContact(){
        return fcontact;
    }
}
