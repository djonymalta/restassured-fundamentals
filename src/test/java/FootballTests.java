import Config.FootballConfig;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

public class FootballTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea(){
        RestAssured
                .given()
                .queryParam("areas", 2076)
                .when()
                .get("/areas");
    }

    @Test
    public void getDetailsOfMultipleAreas(){
        String areaIds = "2076,2077,2080";

        RestAssured
                .given()
                .urlEncodingEnabled(false)
                .queryParam("areas", areaIds)
                .when()
                .get("/areas");

    }

    @Test
    public void getDateFounded(){
        RestAssured
                .get("teams/57")
                .then()
                .body("founded", CoreMatchers.equalTo(1886));
    }

    @Test
    public void getFirstTeamName(){
        RestAssured
                .given()
                .when()
                .get("competitions/2021/teams")
                .then()
                .body("teams.name[0]", CoreMatchers.equalTo("Arsenal FC"));

    }

    @Test
    public void getAllTeamData(){
        String responseBody = RestAssured.get("teams/57").asString();
        System.out.println(responseBody);

    }

    @Test
    public void getAllTeamData_DoCheckFirst(){
        Response response = RestAssured
                .given()
                .when()
                .get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        String jsonResponseAsString = response.asString();

        System.out.println(jsonResponseAsString);
    }

    @Test
    public void extractHeaders(){
        Response response = RestAssured
                .get("teams/57")
                .then()
                .extract().response();

        String contentTypHeader = response.getContentType();
        System.out.println(contentTypHeader);

        String apiVersionHeader = response.getHeader("X-API-Version");
        System.out.println(apiVersionHeader);

    }
}
