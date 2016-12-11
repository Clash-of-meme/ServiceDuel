package io.swagger.commons;

/**
 * Model class for UserBO
 */
public class UserBO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String login;
    private String password;
    private String token;
    private String email;

    public UserBO() {
    }

    public UserBO(String plogin, String ppassword, String ptoken, String pemail) {
        this.login = plogin;
        this.password = ppassword;
        this.token = ptoken;
        this.email = pemail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer userId) {
        id = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}