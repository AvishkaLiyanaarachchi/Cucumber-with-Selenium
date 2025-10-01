import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredAPI {

    private static RequestSpecification requestSpec;

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public void createRequestSpectification() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withRequestSpec() {

        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withResponseSpec() {

        given().
                spec(requestSpec).
                when().
                get("http://zippopotam.us/us/90210").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_extractPlaceNameFromResponseBody_assertEqualToBeverlyHills() {

        String placeName =
                given().
                        spec(requestSpec).
                        when().
                        get("http://zippopotam.us/us/90210").
                        then().
                        extract().
                        path("places[0].'Beverly Hills'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        RestAssuredAPI3 location =
                given().
                        when().
                        get("http://api.zippopotam.us/us/90210").
                        as(RestAssuredAPI3.class);

        Assert.assertEquals(
                "Beverly Hills",
                location.getPostCode()
        );
    }

    @Test
    public void sendLvZipCode1050_checkStatusCode_expect200() {

        RestAssuredAPI3 location = new RestAssuredAPI3();
        location.setCountry("Netherlands");

        given().
                contentType(ContentType.JSON).
                body(location).
                log().body().
                when().
                post("http://localhost:9876/lv/1050").
                then().
                assertThat().
                statusCode(200);
    }
}
