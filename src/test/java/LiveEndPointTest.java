import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class LiveEndPointTest {
    private static Response response;

    @BeforeAll
    public static void setUp() {
        response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY);
        System.out.println(response.asString());
    }

    @Test
    public void getAllLiveResponse() {

        response.then().statusCode(200);
    }

    @Test
    public void getAllLiveResultTest() {

        response.then().body("success", notNullValue());
        response.then().body("success", is(true));

        response.then().body("source", containsString("USD"));
        response.then().body("terms", containsString("https://currencylayer.com/terms"));
        response.then().body("privacy", containsString("https://currencylayer.com/privacy"));


        response.then().body("timestamp",notNullValue());
        response.then().body("timestamp", equalTo(1610009886));


        response.then().body("quotes.USDALL", notNullValue());
        response.then().body("quotes.USDALL", equalTo(100.40955f));

    }


}



