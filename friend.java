import com.mysql.cj.jdbc.exceptions.SQLError;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.FontConverter;

public class friend {
    public static ObservableList<friend> friends = FXCollections.observableArrayList();

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
        File file = new File(path);
        if(!file.exists()){
            avatar_path = "./src/useravatar/-1.png";
        }
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
