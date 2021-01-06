import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;


public class GetCurrencyTest {
    private static Response response;



    @Test
    public void getCADCurrencyTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.CAD_CURRENCY);
        System.out.println(response.asString());
        response.then().body("quotes", notNullValue());
        response.then().body("quotes", anything("USDCAD=1.26685"));


    }

    @Test
    public void getEURCurrencyTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.EUR_CURRENCY);
        System.out.println(response.asString());
        response.then().body("quotes", notNullValue());



    }

    @Test
    public void getNISCurrencyTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.NIS_CURRENCY);
        System.out.println(response.asString());
        response.then().body("quotes", notNullValue());

    }

    @Test
    public void getRUBCurrencyTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.RUB_CURRENCY);
        System.out.println(response.asString());
        response.then().body("quotes", notNullValue());

    }

    @Test
    public void getCurrenciesTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.CURRENCIES);
        System.out.println(response.asString());
        response.then().body("quotes", notNullValue());

    }



    @Test
    public void getCurrencyPerformanceTest(){
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.CURRENCIES);
        response.then().time(lessThan(1000l));

    }

    @ParameterizedTest
    @ValueSource(strings = {"EUR","USD","GBP","CAD","SAR"})
    public void getCurrencyResultTest(String currencyValue){
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + "&currencies="+ currencyValue);
        System.out.println(response.asString());
        response.then().statusCode(200);



    }
}