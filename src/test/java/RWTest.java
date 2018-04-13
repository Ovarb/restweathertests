/*
 *  run in project folder  "mvn clean test site"
 *  after run "allure serve allure-results"
 *
 */



import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RWTest {

    String restAPIUrl = "https://api.openweathermap.org/data/2.5";
    String basePath = "/weather";
    String apiKey = "ef7ccb9a197dad63d3c8171253375286";


    @Test
    public void testCurrentWeatherByCityName() {
        String cityNameRequested = "Penza";

        Response resp = given().
                        queryParam("APPID", apiKey).
                        queryParam("q", cityNameRequested).
                        queryParam("units", "metric").
                        when().
                        get("" + restAPIUrl + basePath);

        JSONObject jsonResponse = new JSONObject(resp.asString());
        String cityNameResponse = jsonResponse.getString("name");

        assertEquals (cityNameRequested, cityNameResponse);
    }

    @Test
    public void testCurrentWeatherByCityId() {
        int cityIdRequested = 511565;

        Response resp = given().
                queryParam("APPID", apiKey).
                queryParam("id", cityIdRequested).
                queryParam("units", "metric").
                when().
                get("" + restAPIUrl + basePath);

        JSONObject jsonResponse = new JSONObject(resp.asString());

        int cityIdResponse = jsonResponse.getInt("id");

        assertEquals (cityIdRequested, cityIdResponse);
    }
}
