import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class VideoGameTests extends VideoGameConfig {
    String gameBodyJson = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2023-01-25\",\n" +
            "  \"reviewScore\": 89\n" +
            "}";

    @Test
    public void getAllGames() {

        RestAssured.given().when().get(VideoGameEndPoints.ALL_VIDEO_GAMES).then();

    }

    @Test
    public void createNewGameBYJSON() {

        RestAssured.given()
                .body(gameBodyJson)
                .when()
                .post(VideoGameEndPoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByXML() {

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

    @Test
    public void updateGame() {

        RestAssured.given().body(gameBodyJson).when().put("videogame/3").then();

    }

    @Test
    public void deleteGame() {
        RestAssured.given().accept("text/plain").when().delete("videogame/8").then();
    }

    @Test
    public void getSingleGame(){
        RestAssured.given().pathParam("videoGameId", 5)
                .when().get(VideoGameEndPoints.SINGLE_VIDEO_GAME).then();
    }
}