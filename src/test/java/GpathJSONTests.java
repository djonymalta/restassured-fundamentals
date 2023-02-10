import Config.FootballConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
public class GpathJSONTests extends FootballConfig {


    @Test
    public void extractMapOfElementsWithFind(){
        Response response = RestAssured
                .get("competitions/2021/teams");

        Map<String, ?> alltTeamDataForSingleTeam = response.path("teams.find { it.name == 'Manchester United FC'}");

        System.out.println("Map of team data = " + alltTeamDataForSingleTeam);
    }
}
