import org.example.Order;
import org.example.URLS;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
//    Список заказов
//    Проверь, что в тело ответа возвращается список заказов. GET /api/v1/orders

    private String[] color = new String[]{"BLACK"};

    @Test
    public void checkTrackTest() {
        Order order = new Order(color);

        given().log().all()
                .header("Content-type", "application/json")
                .baseUri(URLS.BASE_URL)
                .body(order)
                .when()
                .get(URLS.ORDER)
                .then().log().all()
                .assertThat().body("orders", notNullValue());

    }
}
