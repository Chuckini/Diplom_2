package user;

import base.UserBaseTest;
import constants.StatusCodes;
import org.junit.Test;

import static data.UserGenerator.credsFrom;
import static data.UserGenerator.randomUser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserLoginTest extends UserBaseTest {

    // Вход под существующим пользователем
    @Test
    public void shouldLoginExistingUser() {
        createdUser = randomUser();
        accessToken = userSteps.register(createdUser).then().extract().path("accessToken");

        userSteps.login(credsFrom(createdUser))
                .then()
                .statusCode(StatusCodes.OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    // Вход с неверным логином и паролем
    @Test
    public void shouldNotLoginWithWrongEmailOrPassword() {
        userSteps.login(new model.user.UserLoginRequest("wrong@yandex.ru", "wrongpass"))
                .then()
                .statusCode(StatusCodes.UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
