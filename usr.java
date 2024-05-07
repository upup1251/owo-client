public class usr {
    private String owo_no;
    private String sex;
    private String password;
    usr(){
        owo_no = null;
        password = null;
        sex = null;
    }
    usr(String owo_no1,String password1){
        owo_no = owo_no1;
        password = password1;
        sex = null;
    }
    public void setPasswword(String password1){
        password = password1;
    }
    public void setOwo_no(String owo_no1){
        owo_no = owo_no1;
    }
    public String getOwo_no(){
        return owo_no;
    }
    public String getPassowrd(){
        return password;
    }
    
    
}
