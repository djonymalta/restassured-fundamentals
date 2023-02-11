import Config.VideoGameConfig;
import Config.VideoGameEndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class GpathXMLTests extends VideoGameConfig {

    @Test
    public void getFirstGameInList(){

        Response response =
                get(VideoGameEndPoints.ALL_VIDEO_GAMES);

        String name = response.path("List.item.name[0]");

        System.out.println(name);
    }
}
