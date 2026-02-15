package model.user;

public class UserLoginRequest {
    private String email;
    private String password;

    public UserLoginRequest() {}

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public UserLoginRequest setEmail(String email) { this.email = email; return this; }

    public String getPassword() { return password; }
    public UserLoginRequest setPassword(String password) { this.password = password; return this; }
}

