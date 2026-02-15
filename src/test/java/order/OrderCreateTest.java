package order;

import base.UserBaseTest;
import constants.StatusCodes;
import model.order.OrderCreate;
import org.junit.Test;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static data.UserGenerator.credsFrom;
import static data.UserGenerator.randomUser;
import static org.hamcrest.Matchers.equalTo;

public class OrderCreateTest extends UserBaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    private List<String> getTwoValidIngredientIds() {
        return orderSteps.getIngredients()
                .then()
                .statusCode(StatusCodes.OK)
                .extract()
                .path("data._id[0,1]");
    }

    // Создание заказа: с авторизацией
    @Test
    public void shouldCreateOrderWithAuthorization() {
        createdUser = randomUser();
        userSteps.register(createdUser).then().statusCode(StatusCodes.OK);
        accessToken = userSteps.login(credsFrom(createdUser)).then().extract().path("accessToken");

        OrderCreate order = new OrderCreate(getTwoValidIngredientIds());

        orderSteps.createOrderAuth(accessToken, order)
                .then()
                .statusCode(StatusCodes.OK)
                .body("success", equalTo(true));
    }

    // Создание заказа: без авторизации
    @Test
    public void shouldCreateOrderWithoutAuthorization() {
        OrderCreate order = new OrderCreate(getTwoValidIngredientIds());

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(StatusCodes.OK)
                .body("success", equalTo(true));
    }

    // Создание заказа: с ингредиентами
    @Test
    public void shouldCreateOrderWithIngredients() {
        OrderCreate order = new OrderCreate(getTwoValidIngredientIds());

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(StatusCodes.OK)
                .body("success", equalTo(true));
    }

    // Создание заказа: без ингредиентов
    @Test
    public void shouldNotCreateOrderWithoutIngredients() {
        OrderCreate order = new OrderCreate(Collections.<String>emptyList());

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(StatusCodes.BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    // Создание заказа: с неверным хешем ингредиентов
    @Test
    public void shouldFailWithInvalidIngredientHash() {
        OrderCreate order = new OrderCreate(
                Arrays.asList("60d3463f7034a000269f45eZ")
        );

        orderSteps.createOrderNoAuth(order)
                .then()
                .statusCode(StatusCodes.INTERNAL_SERVER_ERROR);
    }
}
