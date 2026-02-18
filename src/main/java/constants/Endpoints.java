package constants;

public class Endpoints {

    // USER
    //регистрация
    public static final String USER_REGISTER = "/api/auth/register";
    //авторизация
    public static final String USER_LOGIN = "/api/auth/login";
    //выход из системы
    public static final String USER_LOGOUT = "/api/auth/logout";
    //удаление пользователя
    public static final String USER_DELETE = "/api/auth/user";

    // ORDER
    //создание заказа
    public static final String CREATE_ORDER = "/api/orders";
    public static final String INGREDIENTS = "/api/ingredients";


}
