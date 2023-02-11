import Config.FootballConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    public void extractSingleValueWithFind(){
        Response response = RestAssured
                .get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 7784 }.name");
        System.out.println(certainPlayer );
    }

    @Test
    public void extractListOfValueFindAll(){
        Response response = RestAssured
                .get("teams/57");
        List<String> playerNames = response.path("squad.findAll { it.id >= 7784 }.name");
        System.out.println("List of players: "+ playerNames);
    }

    @Test
    public void extractSingleValueWithHighestNumber(){
        Response response = RestAssured
                .get("teams/57");
        String playerName = response.path("squad.max { it.id }.name ");
        System.out.println("Player with hightest id: " + playerName);
    }

    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response = RestAssured
                .get("teams/57");

        int somOfIds = response.path("squad.collect { it.id}.sum()");
        System.out.println("Sum of All IDs" + somOfIds);
    }

}
