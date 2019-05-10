package Model;

public class UserDTO {
    private String user_email;
    private String user_pw;
    private  boolean autopay;
    private String paynumber;
    private String user_addr;

    public UserDTO(String user_email, String user_pw, boolean autopay, String paynumber, String user_addr) {

        this.user_email = user_email;
        this.user_pw = user_pw;
        this.autopay = autopay;
        this.paynumber = paynumber;
        this.user_addr = user_addr;
    }

    public UserDTO(String user_email, String user_pw) {
        this.user_email = user_email;
        this.user_pw = user_pw;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public boolean isAutopay() {
        return autopay;
    }

    public void setAutopay(boolean autopay) {
        this.autopay = autopay;
    }

    public String getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(String paynumber) {
        this.paynumber = paynumber;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }
}
