package order;

import base.UserBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.order.OrderCreate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static testdata.UserGenerator.randomUser;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static testdata.UserGenerator.*;


public class OrderCreateTest extends UserBaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Before
    public void setUp() {
        // Пользователь нужен только для теста "с авторизацией" — создаём один раз
        createdUser = randomUser();
        userSteps.register(createdUser)
                .then()
                .statusCode(SC_OK);

        accessToken = userSteps.login(credsFrom(createdUser))
                .then()
                .extract()
                .path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    private List<String> getTwoValidIngredientIds() {
        return orderSteps.getIngredients()
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("data._id[0,1]");
    }

    @Test
    @DisplayName("Создание заказа с авторизацией успешно")
    @Description("Создаём пользователя в @Before, получаем accessToken и создаём заказ с валидными ингредиентами")
    public void shouldCreateOrderWithAuthorization() {
        OrderCreate order = new OrderCreate(getTwoValidIngredientIds());

        orderSteps.createOrderAuth(accessToken, order)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации успешно")
    @Description("Создаём заказ без токена с валидными ингредиентами и ожидаем success=true")
    public void shouldCreateOrderWithoutAuthorization() {
        OrderCreate order = new OrderCreate(getTwoValidIngredientIds());

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать заказ без ингредиентов")
    @Description("Отправляем пустой список ингредиентов и ожидаем 400 и сообщение об ошибке")
    public void shouldNotCreateOrderWithoutIngredients() {
        OrderCreate order = new OrderCreate(Collections.<String>emptyList());

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиента возвращает 500")
    @Description("Отправляем некорректный id ингредиента и ожидаем 500")
    public void shouldFailWithInvalidIngredientHash() {
        OrderCreate order = new OrderCreate(
                Arrays.asList("60d3463f7034a000269f45eZ")
        );

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
