import io.restassured.http.ContentType;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import org.json.JSONObject;


//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

//
public class RestassuredAPII {

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                { "us", "90210", "Beverly Hills" },
                { "us", "12345", "Schenectady" },
                { "ca", "B2R", "Waverley"}
        };
    }


    @Test
    //@UseDataProvider("zipCodesAndPlaces")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));


    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills1() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(200);



    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills2() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);



    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills3() {
                given().
                log().all().
                        when().
                get("http://zippopotam.us/us/90210").
                then().log().body();

    }


    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills4() {
                given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                        assertThat().
                        body("places[0].'place name'", equalTo("Beverly Hills"));

    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills()
    {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }

    @Test
    void testCreateUser() {
        JSONObject request = new JSONObject();
        request.put("name", "John");
        request.put("job", "QA Engineer");

        given()
                .baseUri("https://reqres.in")
                .header("Content-Type", "application/json")
                .body(request.toString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("job", equalTo("QA Engineer"));
    }
}
