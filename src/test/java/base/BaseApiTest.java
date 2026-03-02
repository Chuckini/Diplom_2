package base;

import client.Spec;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.requestSpecification = Spec.requestSpec();
    }
}
