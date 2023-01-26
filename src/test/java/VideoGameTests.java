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
}
