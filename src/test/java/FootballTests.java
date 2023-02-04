import Config.FootballConfig;
import io.restassured.RestAssured;

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
                .get("/teams/57")
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
}
