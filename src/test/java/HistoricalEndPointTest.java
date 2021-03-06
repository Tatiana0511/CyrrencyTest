
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HistoricalEndPointTest {

    private static Response response;
    private static final String DATE = "&Date=2020-01-01";
    private static final String INVALID_DATE = "&Date=2022-13-13";


    @Test
    public void getDate() {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + DATE);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("timestamp", equalTo(1577923199));

    }

    @Test
    public void withoutDateTest() {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(301));

    }

    @Test
    public void invalidDateTest() {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + INVALID_DATE);
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(302));

    }

    @Test
    public void wrongRequestDateTest() {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.ACCESS_KEY + "test");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(101));

    }

    @Test
    public void endPointsNegativeTest() {
        response = given().get(Consts.URL + "/change" + Consts.ACCESS_KEY );
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(105));

    }
}
