
import org.example.Order;
import org.example.URLS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private int track;


    @Parameterized.Parameters
    public static Object[][] valueColor() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{}}
        };
    }
    public CreateOrderTest(String[] color) {
        this.color = color;
    }
    @Test
    public void createOrderTest() {
        Order order = new Order(color);

        given().log().all()
                .header("Content-type", "application/json")
                .baseUri(URLS.BASE_URL)
                .body(order)
                .when()
                .post(URLS.ORDER)
                .then().log().all()
                .statusCode(201);

    }
    @Test
    public void checkTrackTest() {
        Order order = new Order(color);

        given().log().all()
                .header("Content-type", "application/json")
                .baseUri(URLS.BASE_URL)
                .body(order)
                .when()
                .post(URLS.ORDER)
                .then().log().all()
                .statusCode(201).and().assertThat().body("track", notNullValue());;

    }


}
