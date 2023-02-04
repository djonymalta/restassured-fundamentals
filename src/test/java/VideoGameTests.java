import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class VideoGameTests extends VideoGameConfig {

    @Test
    public void getAllGames() {

        RestAssured.given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();

    }

    @Test
    public void createNewGameBYJSON(){
        String gameBodyJson = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2023-01-25\",\n" +
                "  \"reviewScore\": 89\n" +
                "}";
        RestAssured.given()
                            .body(gameBodyJson)
                    .when()
                            .post(VideoGameEndPoints.ALL_VIDEO_GAMES)
                    .then();
    }

    @Test
    public void createNewGameByXML(){

        String gameBodyXml = "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";

        RestAssured.given().body(gameBodyXml)
                .contentType("application/xml")
                .accept("application/xml")
                .when().post(VideoGameEndPoints.ALL_VIDEO_GAMES).then();
    }
}
