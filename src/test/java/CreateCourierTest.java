import io.restassured.RestAssured;
import org.example.Courier;
import org.example.URLS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class CreateCourierTest {

    Courier courier = new Courier("Lex","1234", "Aleksandr");

    @Before
    public void setUp() {
        RestAssured.baseURI = URLS.BASE_URL;
    }


    @Test
    public void createCourierTest() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.CREATE_COURIER)
                .then().log().all()
                .statusCode(201);
    }

    @Test
    public void createTwinCourierTest() {
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.CREATE_COURIER)
                .then().log().all().statusCode(201);
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.CREATE_COURIER)
                .then().log().all()
                .statusCode(409);

    }

    @After
    public void deleteCourier() {
        Integer id = given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all().extract().body().<Integer>path("id");
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(URLS.DELETE_COURIER + id)
                .then().statusCode(200);
    }
}



