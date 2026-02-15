package user;

import base.UserBaseTest;
import constants.StatusCodes;
import model.user.UserCreate;
import org.junit.Test;

import static data.UserGenerator.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserCreateTest extends UserBaseTest {

    // Создать уникального пользователя
    @Test
    public void shouldCreateUniqueUser() {
        createdUser = randomUser();

        userSteps.register(createdUser)
                .then()
                .statusCode(StatusCodes.OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

        // чтобы cleanup удалил:
        accessToken = userSteps.login(credsFrom(createdUser))
                .then().extract().path("accessToken");
    }

    // Создать пользователя, который уже зарегистрирован
    @Test
    public void shouldNotCreateAlreadyRegisteredUser() {
        createdUser = randomUser();
        accessToken = userSteps.register(createdUser).then().extract().path("accessToken");

        userSteps.register(createdUser)
                .then()
                .statusCode(StatusCodes.FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    // Создать пользователя и не заполнить одно из обязательных полей
    @Test
    public void shouldNotCreateUserWithoutRequiredField() {
        UserCreate noName = withoutName();

        userSteps.register(noName)
                .then()
                .statusCode(StatusCodes.FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
