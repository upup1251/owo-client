public class usr {
    String owo_no;
    String name;
    String contact;
    String password;
    String passwd2;
    String avatarPath;
    usr(){
        owo_no = null;
        password = null;
        name = null;
        contact = null;
        passwd2 = null;
        avatarPath = null;
    }
    public String getOwo_no(){
        return owo_no;
    }
    public String getPassword(){
        return password;
    }
    public String getname(){
        return name;
    }
    public String getcontact(){
        return contact;
    }
    public String getpasswd2(){
        return passwd2;
    }
    public String getAvatarPath(){
        return avatarPath;
    }
    public void setOwo_no(String owo_no1){
        owo_no = owo_no1;
    }
    public void setPasswword(String password1){
        password = password1;
    }
    public void setName(String name1){
        name = name1;
    }
    public void setContact(String contact1){
        contact = contact1;
    }
    public void setPasswd2(String passwd22){
        passwd2 = passwd22;
    }
    public void setAvatarPath(String path2){
        avatarPath = path2;
    }
}