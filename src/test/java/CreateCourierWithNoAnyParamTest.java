import io.restassured.RestAssured;
import org.example.Courier;
import org.example.URLS;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class CreateCourierWithNoAnyParamTest {

    Courier courier = new Courier("Lex","1234", "Aleksandr");

    @Before
    public void setUp() {
        RestAssured.baseURI = URLS.BASE_URL;
    }

    // создание курьера

    @Test
    public void noLoginCreateCourierTest (){
        given()
                .header("Content-type", "application/json")
                .body(courier.getPassword())
                .when()
                .post(URLS.CREATE_COURIER)
                .then().log().all().statusCode(400);

    }
    @Test
    public void noPasswordCreateCourierTest (){
        given()
                .header("Content-type", "application/json")
                .body(courier.getLogin())
                .when()
                .post(URLS.CREATE_COURIER)
                .then().log().all().statusCode(400);

    }



}
