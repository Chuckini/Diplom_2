package base;

import io.restassured.response.Response;
import model.user.UserCreate;
import model.user.UserLoginRequest;
import org.junit.After;
import steps.UserSteps;

import static data.UserGenerator.credsFrom;
import static data.UserGenerator.randomUser;

public class UserBaseTest extends BaseApiTest {

    protected final UserSteps userSteps = new UserSteps();

    protected UserCreate createdUser;
    protected String accessToken;

    protected void registerAndRememberTokens() {
        createdUser = randomUser();
        Response r = userSteps.register(createdUser);
        accessToken = r.then().extract().path("accessToken"); // "Bearer ..."
    }

    protected void loginAndRememberTokens() {
        UserLoginRequest creds = credsFrom(createdUser);
        Response r = userSteps.login(creds);
        accessToken = r.then().extract().path("accessToken");
    }

    @After
    public void cleanup() {
        if (accessToken != null && !accessToken.isEmpty()) {
            userSteps.deleteUser(accessToken).then().extract().response();
        }
    }
}

