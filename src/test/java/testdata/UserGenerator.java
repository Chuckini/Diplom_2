package data;

import model.user.UserCreate;
import model.user.UserLoginRequest;

import java.util.UUID;

public class UserGenerator {

    public static UserCreate randomUser() {
        String uniq = UUID.randomUUID().toString().substring(0, 8);
        String email = "test_" + uniq + "@yandex.ru";
        String password = "Pass_" + uniq + "1!";
        String name = "User_" + uniq;
        return new UserCreate(email, password, name);
    }

    public static UserLoginRequest credsFrom(UserCreate user) {
        return new UserLoginRequest(user.getEmail(), user.getPassword());
    }

    public static UserCreate withoutEmail() { return randomUser().setEmail(null); }
    public static UserCreate withoutPassword() { return randomUser().setPassword(null); }
    public static UserCreate withoutName() { return randomUser().setName(null); }
}
