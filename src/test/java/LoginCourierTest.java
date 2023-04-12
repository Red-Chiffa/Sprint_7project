import io.restassured.RestAssured;
import org.example.Courier;
import org.example.URLS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {

    Courier courier = new Courier("Lex","1234", "Aleksandr");



    @Before
    public void setUp() {
        RestAssured.baseURI = URLS.BASE_URL;
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.CREATE_COURIER);
    }

    @Test
    public void loginCourier (){
        given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(200).and().assertThat().body("id", notNullValue());
    }


    @Test
    public void noLoginLoginCourier (){
        given()
                .header("Content-type", "application/json")
                .body(courier.getPassword())
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(400);
    }
    @Test
    public void noPasswordLoginCourier (){
        given()
                .header("Content-type", "application/json")
                .body(courier.getLogin())
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(400);
    }

    @Test
    public void wrongLoginLoginCourier (){
        given()
                .header("Content-type", "application/json")
                .body("  {\"login\": \"WrongLogin\", \"password\": \"1234\"}")
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(404);
    }

    @Test
    public void wrongPasswordLoginCourier (){
        given()
                .header("Content-type", "application/json")
                .body("{\"login\": \"Lex\", \"password\": \"WrongPassword\"}")
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(404);
    }

    @Test
    public void wrongDataLoginCourier (){
        given()
                .header("Content-type", "application/json")
                .body("{\"login\": \"WrongLogin\", \"password\": \"WrongPassword\"}")
                .when()
                .post(URLS.LOGIN_COURIER)
                .then().log().all()
                .statusCode(404);
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
